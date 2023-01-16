package cn.xmy.base.guardedsuspension;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/11 15:17:55
 * @Description:
 */
public class ClientThread extends Thread {
    private RequestQueue<String> requestQueue;

    public ClientThread(String threadName, RequestQueue requestQueue) {
        super(threadName);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 1; true; i++) {
//            System.out.println(Thread.currentThread().getName() + "put queue size: " + requestQueue.putRequest("" + i));
            requestQueue.putRequest("" + i);
            try {
                Thread.sleep(81);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
