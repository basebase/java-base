package cn.xmy.base.balking;

import java.io.IOException;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 11:10:22
 * @Description:
 */
public class SaverThread extends Thread {
    private final Data data;

    public SaverThread(String threadName, Data data) {
        super(threadName);
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            try {
                data.save();
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
