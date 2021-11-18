package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/18 11:06:20
 * @Description:    多个线程同一时段操作一段代码, 无互斥下产生预期外效果
 */
public class Main5 {

    private class MyTask implements Runnable {

        // 余额
        private int balance = 100;
        // 取款金额
        private int amount;

        public MyTask(int amount) {
            this.amount = amount;
        }

        public int getBalance() {
            return balance;
        }

        @Override
        public void run() {
            if (balance >= amount) {

                // 线程A在取钱过程等待一小会时间, 此时线程B也来执行这段代码
                // 由于线程之间没有互斥关系, 所以最后的结果不符合我们的预期
                if (Thread.currentThread().getName().equals("Thread-0")) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                balance -= amount;
            }

            System.out.println(Thread.currentThread().getName() + "余额: " + balance);
        }
    }

    public void run() {
        MyTask task = new MyTask(100);
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new Main5().run();
    }
}
