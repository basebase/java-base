package cn.xmy.base.ch01;

import java.util.Iterator;
import java.util.List;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/3 15:44:45
 * @Description:   数据消费者
 */
public class Consumption implements Runnable {
    private Object lock;
    private List<String> datas;
    final int CAPCITY = 10;

    public Consumption(Object lock, List<String> datas) {
        this.lock = lock;
        this.datas = datas;
    }

    @Override
    public void run() {
        while (true) {
            // 获取到锁后进行消费数据
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 获取到锁, 当前队列大小: " + datas.size());
                // 如果消费速度较快, 没有数据消费则让消费端等待
                if (datas.isEmpty()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 等待中 当前队列大小: " + datas.size());
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + " 被唤醒 当前队列大小: " + datas.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 消费时间
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Iterator<String> iterator = datas.iterator();
                while (iterator.hasNext()) {
                    String v = iterator.next();
                    System.out.println(Thread.currentThread().getName() + " 消费: " + v);
                    iterator.remove();
                }

                // 当数据被消费完后, 唤醒生产者生产数据
                lock.notifyAll();
            }
        }
    }
}
