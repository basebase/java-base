package core.j8.concurrent.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/7 13:52:17
 * @Description:
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main程序开始");
        new Thread(new Run()).start();
        System.out.println("Main程序结束");
    }
}

class Run implements Runnable {
    @Override
    public void run() {
        System.out.println("Run程序开始执行");
        CountDownLatch countDownLatch = new CountDownLatch(5);
        new Thread(new Job(countDownLatch), "T-1").start();
        new Thread(new Job(countDownLatch), "T-2").start();
        new Thread(new Job(countDownLatch), "T-3").start();
        new Thread(new Job(countDownLatch), "T-4").start();
        new Thread(new Job(countDownLatch), "T-5").start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Run程序执行结束");
    }
}

class Job implements Runnable {
    private static final Random RANDOM = new Random();
    private final CountDownLatch countDownLatch;

    Job(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": 提交任务中...");
        try {
            Thread.sleep(RANDOM.nextInt(1000));
            System.out.println(Thread.currentThread().getName() + ": 任务计算中...");
            Thread.sleep(RANDOM.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ": 任务执行完成");
        countDownLatch.countDown();
    }
}
