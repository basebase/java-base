package cn.xmy.base.ch04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/26 17:56:22
 * @Description:    缓存请求队列
 */
public class RequestQueue {
    private final Queue<Request> queue = new LinkedList<>();


    public synchronized Request getRequest() {
//        while (queue.peek() == null) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        if (queue.peek() == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return queue.remove();
    }

    public synchronized void putRequest(Request request) {
        queue.offer(request);
        notifyAll();
    }
}
