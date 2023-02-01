package cn.xmy.base.threadpermessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 14:47:10
 * @Description:
 */
public class MiniServer {
    private final int port;

    public MiniServer(int port) {
        this.port = port;
    }

    public void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("监听服务: " + serverSocket);
        try {
            while (true) {
                System.out.println("等待接收...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("连接成功: " + clientSocket);
                new Thread(() -> {
                    try {
                        Service.service(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } finally {
            serverSocket.close();
        }
    }
}
