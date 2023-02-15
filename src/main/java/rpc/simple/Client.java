package rpc.simple;

import java.io.*;
import java.net.Socket;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/15 13:47:52
 * @Description:
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
        client.request();
    }

    public void request() throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 8888);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        System.out.println("请输入要调用的远程方法及参数: ");
        String request = new BufferedReader(new InputStreamReader(System.in)).readLine();
        // write需要加上\n, 否则readLine会一直阻塞
        out.write(request + "\n");
        out.flush();

        System.out.println("获取返回结果");
        String s = in.readLine();
        System.out.println("请求返回值为: " + s);
    }
}
