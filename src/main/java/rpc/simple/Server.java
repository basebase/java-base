package rpc.simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/15 13:47:47
 * @Description:
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(8888);
        BufferedReader in = null;
        BufferedWriter out = null;
        Socket socket = null;

        try {
            socket = server.accept();
            System.out.println("客户端: " + socket + " 连接成功...");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String request = in.readLine();



            System.out.println("client request: " + request);



            String[] params = request.split(",");
            if (params.length != 3) {
                System.out.println("请正确的调用远程方法");
                return ;
            }

            if (params[0].equals("reduce")) {
                System.out.println("reduce方法被调用");
                int reduce = reduce(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
                out.write("reduce远程方法调用成功, 返回结果为: " + reduce + "\n");
                out.flush();
                System.out.println("返回给客户端数据");
            } else if (params[0].equals("saveInfo")) {
                out.write("数据保存成功: " + saveInfo() + "\n");
                out.flush();
            } else if (params[0].equals("sendHeart")) {
                out.write("心跳发送成功: " + sendHeart() + "\n");
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
            socket.close();
        }
    }

    public int reduce(int value1, int value2) {
        return value1 + value2;
    }

    public boolean saveInfo() {
        System.out.println("保存成功");
        return true;
    }

    public boolean sendHeart() {
        return true;
    }
}
