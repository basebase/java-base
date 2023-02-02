package cn.xmy.base.workerthread;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 15:34:29
 * @Description:
 */
public class WorkerThread extends Thread {
    private final Channel channel;
    public WorkerThread(String threadName, Channel channel) {
        super(threadName);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            Request request = channel.takeReuqest();
            request.execute();
        }
    }
}
