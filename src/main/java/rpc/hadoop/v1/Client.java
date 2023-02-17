package rpc.hadoop.v1;

import rpc.hadoop.v1.io.UTF8;
import rpc.hadoop.v1.io.Writable;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.rmi.RemoteException;
import java.util.Hashtable;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/16 11:40:35
 * @Description:
 */
public class Client {

    private Hashtable connections = new Hashtable();

    private Class valueClass;                       // class of call values
    private int timeout ;// timeout for calls
    private int counter;                            // counter for call ids
    private boolean running = true;                 // true while client runs

    /** A call waiting for a value. */
    private class Call {
        int id;                                       // call id
        Writable param;                               // parameter
        Writable value;                               // value, null if error
        String error;                                 // error, null if value
        long lastActivity;                            // time of last i/o
        boolean done;                                 // true when call is done

        protected Call(Writable param) {
            this.param = param;
            synchronized (Client.this) {
                this.id = counter++;
            }
            touch();
        }

        /** Called by the connection thread when the call is complete and the
         * value or error string are available.  Notifies by default.  */
        public synchronized void callComplete() {
            notify();                                 // notify caller
        }

        /** Update lastActivity with the current time. */
        public synchronized void touch() {
            lastActivity = System.currentTimeMillis();
        }

        /** Update lastActivity with the current time. */
        public synchronized void setResult(Writable value, String error) {
            this.value = value;
            this.error = error;
            this.done = true;
        }

    }

    /** Thread that reads responses and notifies callers.  Each connection owns a
     * socket connected to a remote address.  Calls are multiplexed through this
     * socket: responses may be delivered out of order. */
    private class Connection extends Thread {
        private InetSocketAddress address;            // address of server
        private Socket socket;                        // connected socket
        private DataInputStream in;
        private DataOutputStream out;
        private Hashtable calls = new Hashtable();    // currently active calls
        private Call readingCall;
        private Call writingCall;

        public Connection(InetSocketAddress address) throws IOException {
            System.out.println("初始化客户端Connection");
            this.address = address;
            this.socket = new Socket(address.getAddress(), address.getPort());
            socket.setSoTimeout(timeout);
            this.in = new DataInputStream
                    (new BufferedInputStream
                            (new FilterInputStream(socket.getInputStream()) {
                                public int read(byte[] buf, int off, int len) throws IOException {
                                    int value = super.read(buf, off, len);
                                    if (readingCall != null) {
                                        readingCall.touch();
                                    }
                                    return value;
                                }
                            }));
            this.out = new DataOutputStream
                    (new BufferedOutputStream
                            (new FilterOutputStream(socket.getOutputStream()) {
                                public void write(byte[] buf, int o, int len) throws IOException {
                                    out.write(buf, o, len);
                                    if (writingCall != null) {
                                        writingCall.touch();
                                    }
                                }
                            }));
            this.setDaemon(true);
            this.setName("Client connection to "
                    + address.getAddress().getHostAddress()
                    + ":" + address.getPort());
        }

        public void run() {
            System.out.println(getName() + ": starting");
            try {
                while (running) {
                    int id;
                    try {
                        id = in.readInt();                    // try to read an id
                    } catch (SocketTimeoutException e) {
                        continue;
                    }


                    Call call = (Call)calls.remove(new Integer(id));
                    boolean isError = in.readBoolean();     // read if error
                    if (isError) {
                        UTF8 utf8 = new UTF8();
                        utf8.readFields(in);                  // read error string
                        call.setResult(null, utf8.toString());
                    } else {
                        Writable value = makeValue();
                        try {
                            readingCall = call;
                            value.readFields(in);                 // read value
                        } finally {
                            readingCall = null;
                        }
                        call.setResult(value, null);
                    }
                    call.callComplete();                   // deliver result to caller
                }
            } catch (EOFException eof) {
                // This is what happens when the remote side goes down
            } catch (Exception e) {
                System.out.println(getName() + " caught: " + e);
            } finally {
                close();
            }
        }

        /** Initiates a call by sending the parameter to the remote server.
         * Note: this is not called from the Connection thread, but by other
         * threads.
         */
        public void sendParam(Call call) throws IOException {
            System.out.println("sendParam被调用");
            boolean error = true;
            try {
                calls.put(new Integer(call.id), call);
                synchronized (out) {
                    try {
                        writingCall = call;
                        out.writeInt(call.id);
                        call.param.write(out);
                        out.flush();
                    } finally {
                        writingCall = null;
                    }
                }
                error = false;
            } finally {
                if (error)
                    close();                                // close on error
            }
        }

        /** Close the connection and remove it from the pool. */
        public void close() {
            System.out.println(getName() + ": closing");
            synchronized (connections) {
                connections.remove(address);              // remove connection
            }
            try {
                socket.close();                           // close socket
            } catch (IOException e) {}
        }

    }

    /** Call implementation used for parallel calls. */
    private class ParallelCall extends Call {
        private ParallelResults results;
        private int index;

        public ParallelCall(Writable param, ParallelResults results, int index) {
            super(param);
            this.results = results;
            this.index = index;
        }

        /** Deliver result to result collector. */
        public void callComplete() {
            results.callComplete(this);
        }
    }

    /** Result collector for parallel calls. */
    private static class ParallelResults {
        private Writable[] values;
        private int size;
        private int count;

        public ParallelResults(int size) {
            this.values = new Writable[size];
            this.size = size;
        }

        /** Collect a result. */
        public synchronized void callComplete(ParallelCall call) {
            values[call.index] = call.value;            // store the value
            count++;                                    // count it
            if (count == size)                          // if all values are in
                notify();                                 // then notify waiting caller
        }
    }

    /** Construct an IPC client whose values are of the given {@link Writable}
     * class. */
    public Client(Class valueClass) {
        this.valueClass = valueClass;
        this.timeout =10000;
    }



    /** Make a call, passing <code>param</code>, to the IPC server running at
     * <code>address</code>, returning the value.  Throws exceptions if there are
     * network problems or if the remote code threw an exception. */
    public Writable call(Writable param, InetSocketAddress address)
            throws IOException {
        System.out.println("客户端call(1)方法调用");
        Connection connection = getConnection(address);
        Call call = new Call(param);
        synchronized (call) {
            connection.sendParam(call);                 // send the parameter
            long wait = timeout;
            do {
                try {
                    call.wait(wait);                       // wait for the result
                } catch (InterruptedException e) {}
                wait = timeout - (System.currentTimeMillis() - call.lastActivity);
            } while (!call.done && wait > 0);

            if (call.error != null) {
                throw new RemoteException(call.error);
            } else if (!call.done) {
                throw new IOException("timed out waiting for response");
            } else {
                return call.value;
            }
        }
    }


    private Connection getConnection(InetSocketAddress address)
            throws IOException {
        System.out.println("客户端getConnection方法被调用");
        Connection connection;
        synchronized (connections) {
            connection = (Connection)connections.get(address);
            if (connection == null) {
                connection = new Connection(address);
                connections.put(address, connection);
                connection.start();
            }
        }
        return connection;
    }

    private Writable makeValue() {
        Writable value;                             // construct value
        try {
            value = (Writable)valueClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e.toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.toString());
        }
        return value;
    }
}
