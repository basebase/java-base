package cn.xmy.base.ch04;

import java.util.Random;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/26 18:02:56
 * @Description:    接受处理请求
 */
public class ServerThread extends Thread{
    private final Random random ;
    private RequestQueue requestQueue;


    public ServerThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName() + " handles " + request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
