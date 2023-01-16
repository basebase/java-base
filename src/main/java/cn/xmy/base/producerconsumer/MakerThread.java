package cn.xmy.base.producerconsumer;

import java.util.Random;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 14:59:27
 * @Description:
 */
public class MakerThread extends Thread {
    private final Table table;
    private final Random R;

    private static Integer ID = 0;

    public MakerThread(String threadName, Table table, Integer random) {
        super(threadName);
        this.table = table;
        this.R = new Random(random);
    }

    private synchronized Integer nextId() {
        return ID ++;
    }

    @Override
    public void run() {
        try {

            while (true) {
                // 模拟制作蛋糕时间
                Thread.sleep(R.nextInt(1000));
                String cake = "[ Cake No." + nextId() + " by " + getName() + " ]";
                // 放入蛋糕
                table.put(cake);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
