package cn.xmy.base.producerconsumer;

import java.util.Random;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 14:59:38
 * @Description:
 */
public class EaterThread extends Thread {
    private final Table table;
    private final Random R;

    public EaterThread(String threadName, Table table, Integer random) {
        super(threadName);
        this.table = table;
        this.R = new Random(random);
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 取到蛋糕
                String cake = table.take();
                // 模拟吃蛋糕时间
                Thread.sleep(R.nextInt(1000));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
