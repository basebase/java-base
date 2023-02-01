package cn.xmy.base.threadpermessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 14:47:23
 * @Description:
 */
public class Service {

    private Service() {}

    public static void service(Socket clientSocket) throws IOException {
        System.out.println(Thread.currentThread().getName() + ": Service.service(" + clientSocket + ") BEGIN");
        try {
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            outputStream.writeBytes("HTTP/1.0 200 OK\r\n");
            outputStream.writeBytes("Content-type: text/html\r\n");
            outputStream.writeBytes("\r\n");
            outputStream.writeBytes("<html><head><title>CountDown</title></head>");
            outputStream.writeBytes("<h1>CountDown Start!</h1>");
            for (int i = 10; i >= 0; i--) {
                System.out.println(Thread.currentThread().getName() + ": CountDown i = " + i);
                outputStream.writeBytes("<h1>" + i + "</h1>");
                outputStream.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            outputStream.writeBytes("</html>");
        } finally {
            clientSocket.close();
        }
    }
}
