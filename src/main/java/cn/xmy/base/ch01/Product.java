package cn.xmy.base.ch01;

import java.util.List;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/3 15:44:06
 * @Description:   数据生产者
 */
public class Product implements Runnable {
    private Object lock;
    private List<String> datas;
    final int CAPCITY = 10;

    public Product(Object lock, List<String> datas) {
        this.lock = lock;
        this.datas = datas;
    }

    @Override
    public void run() {
        while (true) {
            // 获取到锁后生产数据
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 获取到锁, 当前队列大小: " + datas.size());
                // 如果生产过快, 导致队列无法支撑, 则将生产线程暂停
                if (datas.size() >= CAPCITY) {
                    try {
                        // 在lock实例上进行等待
                        System.out.println(Thread.currentThread().getName() + " 等待中 当前队列大小: " + datas.size());
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " 被唤醒 当前队列大小: " + datas.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    // 生产所需速度
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < CAPCITY; i++) {
                    datas.add("数据-" + (i));
                }

                // 唤醒消费线程, 进行数据消费
                lock.notifyAll();
            }
        }
    }
}
