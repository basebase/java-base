package cn.xmy.base.ch05;

import java.io.IOException;
import java.util.Random;

/**
 * @Author xiaomoyu
 * @Date: 2022/6/14 17:02:20
 * @Description:    修改并保存数据内容线程
 */
public class ChangerThread extends Thread {

    private final Data data;
    private final Random random = new Random();

    public ChangerThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                data.change("No." + i++);
                Thread.sleep(random.nextInt(1000));
                data.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
