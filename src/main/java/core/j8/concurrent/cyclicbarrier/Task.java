package core.j8.concurrent.cyclicbarrier;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/6 17:37:08
 * @Description:
 */
public class Task implements Runnable {

    private static final Random RANDOM = new Random();
    private CyclicBarrier barrier;

    public Task(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 初始化任务中");
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName() + "     " + new Date() + " 初始完成, 开始执行任务");

        try {
            Thread.sleep(RANDOM.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "     " + new Date() + " 任务执行完成");

    }
}
