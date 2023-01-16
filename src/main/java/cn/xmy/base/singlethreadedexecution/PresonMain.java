package cn.xmy.base.singlethreadedexecution;

import java.util.concurrent.CountDownLatch;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/7 15:49:05
 * @Description:
 */
public class PresonMain {
    public static void main(String[] args) throws InterruptedException {
        Gate gate = new Gate();
        new PresonThread(gate, "beisan", "beijing").start();
        new PresonThread(gate, "shangqi", "shanghai").start();
        new PresonThread(gate, "hangwang", "hangzhou").start();
    }
}
