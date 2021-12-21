package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/17 16:21:11
 * @Description:    死锁线程
 */
public class GrabResourceThread1 extends Thread {
    private Object lock1;
    private Object lock2;

    public GrabResourceThread1(String threadName, Object lock1, Object lock2) {
        super(threadName);
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " 获取到lock1");
            try {
                // 模拟处理程序所需时间
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 执行结束");

            int count = 0;
            while (!Thread.holdsLock(lock2)) {
                try {

                    // 如果等待多次, 则进入休眠状态, 并且释放当前拥有的锁
                    if (count >= 3) {
                        System.out.println(Thread.currentThread().getName() + " 进入休眠, 不会释放lock1锁");
                        break;
                    }

                    ++ count;
                    Thread.sleep(4000);
                    System.out.println(Thread.currentThread().getName() + " 尝试获取lock2...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

//            try {
                // 唤醒
//                lock2.notifyAll();
//                lock1.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            synchronized (lock2) {

                System.out.println(Thread.currentThread().getName() + " 获取到lock2");
                try {
                    // 模拟处理程序所需时间
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " 执行结束");

                // 必须在这里唤醒lock2上的所有等待线程
                lock2.notifyAll();
            }

            // 出现异常, 当前线程没有获取到对应的锁就去调用
//            lock2.notifyAll();

            System.out.println(Thread.currentThread().getName() + " 释放lock2");

        }

        System.out.println(Thread.currentThread().getName() + " 释放lock1");
    }
}
