package cn.xmy.base.balking;

import java.io.IOException;
import java.util.Random;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 11:10:36
 * @Description:
 */
public class ChangerThread extends Thread {
    private final Data data;

    private final Random random = new Random();

    public ChangerThread(String threadName, Data data) {
        super(threadName);
        this.data = data;
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i < 20; i++) {
                data.change("No." + i);
                Thread.sleep(random.nextInt(1000));
                data.save();
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
