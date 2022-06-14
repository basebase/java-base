package cn.xmy.base.ch05;

import java.io.IOException;

/**
 * @Author xiaomoyu
 * @Date: 2022/6/14 16:57:57
 * @Description:    保存数据内容线程
 */
public class SaverThread extends Thread {

    private final Data data;

    public SaverThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                data.save();
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
