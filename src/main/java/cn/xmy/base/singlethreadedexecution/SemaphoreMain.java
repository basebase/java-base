package cn.xmy.base.singlethreadedexecution;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/9 15:19:58
 * @Description:
 */
public class SemaphoreMain {

    public static void main(String[] args) {
        BoundResource resource = new BoundResource(5);
        Stream.of("work-1", "work-2", "work-3",
                "work-4", "work-5", "work-6",
                "work-7", "work-8", "work-9", "work-10").forEach(data -> {
                    new BoundResourceThread(data, resource).start();
        });
    }
}


class BoundResource {

    private final Semaphore semaphore;
    private final int semaphoreCount;
    private static final Random R = new Random(5000);

    public BoundResource(int semaphoreCount) {
        this.semaphore = new Semaphore(semaphoreCount);
        this.semaphoreCount = semaphoreCount;
    }

    public void use() throws InterruptedException {
        // 申请资源
        semaphore.acquire();
        try {
            doUse();
        } finally {
            // 释放资源
            semaphore.release();
        }
    }

    private void doUse() throws InterruptedException {
        // 假设执行的时候
        int sleepTime = R.nextInt(500);
        System.out.println(Thread.currentThread().getName() + " doUse running (" + sleepTime + ")ms ...");
        Thread.sleep(sleepTime);
        System.out.println(Thread.currentThread().getName() + " doUse done resourceCount(" + semaphore.availablePermits() + ")...");
    }
}


class BoundResourceThread extends Thread {
    private BoundResource boundResource;

    public BoundResourceThread(String name, BoundResource boundResource) {
        super(name);
        this.boundResource = boundResource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                boundResource.use();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
