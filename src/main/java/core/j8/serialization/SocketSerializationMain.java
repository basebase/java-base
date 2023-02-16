package core.j8.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/16 09:41:29
 * @Description:
 */
public class SocketSerializationMain {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        Client client = new Client();

        new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                client.start();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();

    }
}

class Request implements Serializable {
    private Integer requestId;
    private String methodName;
    private Object[] params;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}

class Server {

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket client = server.accept();
            System.out.println("客户端连接成功: " + client);
            new Thread(() -> {
                ObjectInputStream in = null;
                ObjectOutputStream out = null;
                try {

                    in = new ObjectInputStream(client.getInputStream());
                    out = new ObjectOutputStream(client.getOutputStream());

                    Request request = (Request) in.readObject();

                    if (request.getMethodName().equals("sum")) {
                        int res = sum((Integer) request.getParams()[0], (Integer) request.getParams()[1]);
                        out.writeObject(res);
                        out.flush();
                    } else if (request.getMethodName().equals("test")) {
                        test();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public int sum(int v1, int v2) {
        return v1 + v2;
    }

    public void test() {
        System.out.println("服务器收到消息");
    }
}

class Client {

    public void start() throws IOException, ClassNotFoundException {

        Socket client = new Socket("localhost", 8888);
        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(client.getInputStream());
        Request request = new Request();
        request.setRequestId(1);
        request.setMethodName("sum");
        request.setParams(new Object[]{1, 2});
        out.writeObject(request);
        out.flush();
        Integer sum = (Integer) in.readObject();
        System.out.println("服务器返回结果: " + sum);

    }
}