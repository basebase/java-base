package cn.xmy.base.guardedsuspension;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/11 15:25:44
 * @Description:
 */
public class RequestMain {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        RequestQueue<String> requestQueue = new RequestQueue<>(queue);

        new ClientThread("client-thread", requestQueue).start();
        new ServerThread("server-thread", requestQueue).start();
    }
}
