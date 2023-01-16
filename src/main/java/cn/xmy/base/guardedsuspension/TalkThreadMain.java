package cn.xmy.base.guardedsuspension;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 10:19:21
 * @Description:
 */
public class TalkThreadMain {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        RequestQueue<String> requestQueue1 = new RequestQueue<>(queue);
        RequestQueue<String> requestQueue2 = new RequestQueue<>(queue);

        // put数据
        requestQueue1.putRequest("Hello1");
        requestQueue2.putRequest("Hello2");

        new TalkThread(requestQueue1, requestQueue2, "Alice").start();
        new TalkThread(requestQueue2, requestQueue1, "Bobby").start();
    }
}
