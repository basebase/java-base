package rpc.hadoop.v1;

import rpc.hadoop.v1.io.ObjectWritable;
import rpc.hadoop.v1.io.UTF8;
import rpc.hadoop.v1.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/16 11:40:18
 * @Description:
 */
public class RPC {

    public static Server getServer(final Object instance, final int port) {
        return getServer(instance, port, 1, false);
    }

    /** Construct a server for a protocol implementation instance listening on a
     * port. */
    public static Server getServer(final Object instance, final int port,
                                   final int numHandlers,
                                   final boolean verbose) {
        return new Server(instance, port, numHandlers, verbose);
    }

    public static class Server extends rpc.hadoop.v1.Server {
        private Object instance;
        private Class implementation;
        private boolean verbose;

        public Server(Object instance, int port) {
            this(instance, port, 1, false);
        }


        public Server(Object instance, int port,
                      int numHandlers, boolean verbose) {
            super(port, Invocation.class, numHandlers);

            System.out.println("初始化Server");

            this.instance = instance;
            this.implementation = instance.getClass();
            this.verbose = verbose;
        }

        public Writable call(Writable param) throws IOException {
            System.out.println("Server call方法被调用");
            try {
                Invocation call = (Invocation)param;
                if (verbose) {
                    System.out.println(("Call: " + call));
                }

                Method method =
                        implementation.getMethod(call.getMethodName(),
                                call.getParameterClasses());

                Object value = method.invoke(instance, call.getParameters());
                if (verbose) {
                    System.out.println(("Return: " + value));
                }

                return new ObjectWritable(method.getReturnType(), value);

            } catch (InvocationTargetException e) {
                Throwable target = e.getTargetException();
                if (target instanceof IOException) {
                    throw (IOException)target;
                } else {
                    IOException ioe = new IOException(target.toString());
                    ioe.setStackTrace(target.getStackTrace());
                    throw ioe;
                }
            } catch (Throwable e) {
                IOException ioe = new IOException(e.toString());
                ioe.setStackTrace(e.getStackTrace());
                throw ioe;
            }
        }
    }





    public static Object getProxy(Class protocol, InetSocketAddress addr) {
        System.out.println("客户端代理被调用");
        return Proxy.newProxyInstance(protocol.getClassLoader(),
                new Class[] { protocol },
                new Invoker(addr));
    }

    private static Client CLIENT;
    private static class Invoker implements InvocationHandler {
        private InetSocketAddress address;

        public Invoker(InetSocketAddress address) {
            System.out.println("Invoker初始化");
            this.address = address;
            CLIENT = new Client(ObjectWritable.class);
        }

        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            System.out.println("代理客户端调用远程方法: " + method.getName());
            ObjectWritable value = (ObjectWritable)
                    CLIENT.call(new Invocation(method, args), address);
            return value.get();
        }
    }




    private static class Invocation implements Writable {
        private String methodName;
        private Class[] parameterClasses;
        private Object[] parameters;

        public Invocation() {}

        public Invocation(Method method, Object[] parameters) {
            System.out.println("Invocation初始化");
            this.methodName = method.getName();
            this.parameterClasses = method.getParameterTypes();
            this.parameters = parameters;
        }

        /** The name of the method invoked. */
        public String getMethodName() { return methodName; }

        /** The parameter classes. */
        public Class[] getParameterClasses() { return parameterClasses; }

        /** The parameter instances. */
        public Object[] getParameters() { return parameters; }

        public void readFields(DataInput in) throws IOException {
            System.out.println("Invocation反序列化数据");
            methodName = UTF8.readString(in);
            parameters = new Object[in.readInt()];
            parameterClasses = new Class[parameters.length];
            ObjectWritable objectWritable = new ObjectWritable();
            for (int i = 0; i < parameters.length; i++) {
                parameters[i] = ObjectWritable.readObject(in, objectWritable);
                parameterClasses[i] = objectWritable.getDeclaredClass();
            }
        }

        public void write(DataOutput out) throws IOException {
            System.out.println("Invocation序列化数据");
            UTF8.writeString(out, methodName);
            out.writeInt(parameterClasses.length);
            for (int i = 0; i < parameterClasses.length; i++) {
                ObjectWritable.writeObject(out, parameters[i], parameterClasses[i]);
            }
        }

        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(methodName);
            buffer.append("(");
            for (int i = 0; i < parameters.length; i++) {
                if (i != 0)
                    buffer.append(", ");
                buffer.append(parameters[i]);
            }
            buffer.append(")");
            return buffer.toString();
        }
    }
}

