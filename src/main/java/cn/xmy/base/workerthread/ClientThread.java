package cn.xmy.base.workerthread;

import java.util.Random;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 15:34:06
 * @Description:
 */
public class ClientThread extends Thread {

    private final Channel channel;
    private static final Random RANDOM = new Random();

    public ClientThread(String threadName, Channel channel) {
        super(threadName);
        this.channel = channel;
    }

    @Override
    public void run() {
        int index = 0;
        try {
            while (true) {
                Request request = new Request(getName(), index ++);
                // 发布工作
                channel.putRequest(request);
                Thread.sleep(RANDOM.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
