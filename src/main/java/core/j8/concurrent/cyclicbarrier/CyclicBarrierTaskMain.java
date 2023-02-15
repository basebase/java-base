package core.j8.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/6 17:40:43
 * @Description:
 */
public class CyclicBarrierTaskMain {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("所有任务执行完成...");
        });
        new Thread(new Task(cyclicBarrier)).start();
        new Thread(new Task(cyclicBarrier)).start();
        new Thread(new Task(cyclicBarrier)).start();
        new Thread(new Task(cyclicBarrier)).start();
        new Thread(new Task(cyclicBarrier)).start();



        // 屏障


    }
}
