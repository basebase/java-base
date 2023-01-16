package cn.xmy.base.guardedsuspension;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/11 15:25:35
 * @Description:
 */
public class ServerThread extends Thread {
    private RequestQueue<String> requestQueue;

    public ServerThread(String threadName, RequestQueue<String> requestQueue) {
        super(threadName);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName() + ": " + requestQueue.getRequest() + " queue size: " + requestQueue.getSize());
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
