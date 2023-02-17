package rpc.hadoop.v1;

import rpc.hadoop.v1.io.UTF8;
import rpc.hadoop.v1.io.Writable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/16 11:40:26
 * @Description:
 */
public abstract class Server {

    private static final ThreadLocal SERVER = new ThreadLocal();
    public static Server get() {
        return (Server)SERVER.get();
    }

    private int port;                               // port we listen on
    private int handlerCount;                       // number of handler threads
    private int maxQueuedCalls;                     // max number of queued calls
    private Class paramClass;                       // class of call parameters

    private int timeout;

    private boolean running = true;                 // true while server runs
    private LinkedList callQueue = new LinkedList(); // queued calls
    private Object callDequeued = new Object();     // used by wait/notify

    /** A call queued for handling. */
    private static class Call {
        private int id;                               // the client's call id
        private Writable param;                       // the parameter passed
        private Connection connection;                // connection to client

        public Call(int id, Writable param, Connection connection) {
            this.id = id;
            this.param = param;
            this.connection = connection;
        }
    }

    /** Listens on the socket, starting new connection threads. */
    private class Listener extends Thread {
        private ServerSocket socket;

        public Listener() throws IOException {
            System.out.println("初始化服务器Listener");
            this.socket = new ServerSocket(port);
            socket.setSoTimeout(timeout);
            this.setDaemon(true);
            this.setName("Server listener on port " + port);
        }

        public void run() {
            System.out.println(getName() + ": starting");
            while (running) {
                try {
                    new Connection(socket.accept()).start(); // start a new connection
                } catch (SocketTimeoutException e) {      // ignore timeouts
                } catch (Exception e) {                   // log all other exceptions
                    System.out.println(getName() + " caught: " + e);
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(getName() + ": e=" + e);
            }
            System.out.println(getName() + ": exiting");
        }
    }

    /** Reads calls from a connection and queues them for handling. */
    private class Connection extends Thread {
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;

        public Connection(Socket socket) throws IOException {
            System.out.println("初始化服务器Connection");
            this.socket = socket;
            socket.setSoTimeout(timeout);
            this.in = new DataInputStream
                    (new BufferedInputStream(socket.getInputStream()));
            this.out = new DataOutputStream
                    (new BufferedOutputStream(socket.getOutputStream()));
            this.setDaemon(true);
            this.setName("Server connection on port " + port + " from "
                    + socket.getInetAddress().getHostAddress());
        }

        public void run() {
            System.out.println(getName() + ": starting");
            SERVER.set(Server.this);
            try {
                while (running) {
                    int id;
                    try {
                        id = in.readInt();                    // try to read an id
                    } catch (SocketTimeoutException e) {
                        continue;
                    }

                    Writable param = makeParam();           // read param
                    param.readFields(in);

                    Call call = new Call(id, param, this);

                    synchronized (callQueue) {
                        callQueue.addLast(call);              // queue the call
                        callQueue.notify();                   // wake up a waiting handler
                    }

                    while (running && callQueue.size() >= maxQueuedCalls) {
                        synchronized (callDequeued) {         // queue is full
                            callDequeued.wait(timeout);         // wait for a dequeue
                        }
                    }
                }
            } catch (EOFException eof) {
                // This is what happens on linux when the other side shuts down
            } catch (SocketException eof) {
                // This is what happens on Win32 when the other side shuts down
            } catch (Exception e) {
                System.out.println(getName() + " caught: " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {}
                System.out.println(getName() + ": exiting");
            }
        }

    }

    /** Handles queued calls . */
    private class Handler extends Thread {
        public Handler(int instanceNumber) {
            System.out.println("初始化服务器Connection");
            this.setDaemon(true);
            this.setName("Server handler "+ instanceNumber + " on " + port);
        }

        public void run() {
            System.out.println(getName() + ": starting");
            SERVER.set(Server.this);
            while (running) {
                try {
                    Call call;
                    synchronized (callQueue) {
                        while (running && callQueue.size()==0) { // wait for a call
                            callQueue.wait(timeout);
                        }
                        if (!running) break;
                        call = (Call)callQueue.removeFirst(); // pop the queue
                    }

                    synchronized (callDequeued) {           // tell others we've dequeued
                        callDequeued.notify();
                    }


                    String error = null;
                    Writable value = null;
                    try {
                        value = call(call.param);             // make the call
                    } catch (Exception e) {
                        System.out.println(getName() + " call error: " + e);
                    }

                    DataOutputStream out = call.connection.out;
                    synchronized (out) {
                        out.writeInt(call.id);                // write call id
                        out.writeBoolean(error!=null);        // write error flag
                        if (error != null)
                            value = new UTF8(error);
                        value.write(out);                     // write value
                        out.flush();
                    }

                } catch (Exception e) {
                    System.out.println(getName() + " caught: " + e);
                }
            }
            System.out.println(getName() + ": exiting");
        }

    }

    /** Constructs a server listening on the named port.  Parameters passed must
     * be of the named class.  The <code>handlerCount</handlerCount> determines
     * the number of handler threads that will be used to process calls.
     */
    protected Server(int port, Class paramClass, int handlerCount) {
        this.port = port;
        this.paramClass = paramClass;
        this.handlerCount = handlerCount;
        this.maxQueuedCalls = handlerCount;
    }

    public synchronized void start() throws IOException {
        System.out.println("服务器start方法调用");
        Listener listener = new Listener();
        listener.start();

        for (int i = 0; i < handlerCount; i++) {
            Handler handler = new Handler(i);
            handler.start();
        }
    }


    /** Called for each call. */
    public abstract Writable call(Writable param) throws IOException;


    private Writable makeParam() {
        Writable param;                               // construct param
        try {
            param = (Writable)paramClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e.toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.toString());
        }
        return param;
    }
}
