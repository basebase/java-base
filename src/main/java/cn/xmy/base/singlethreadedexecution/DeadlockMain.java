package cn.xmy.base.singlethreadedexecution;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/9 16:02:27
 * @Description:
 */
public class DeadlockMain {
    public static void main(String[] args) {
        Tool spoon = new Tool("勺子");
        Tool fork = new Tool("叉子");



        // 0. ReentrantLock解决死锁
//        new EaterThread("张三", spoon, fork).start();
//        new EaterThread("李四", fork, spoon).start();

        // 1. 通过顺序来破坏死锁
        // 两个人都优先获取勺子然后再尝试获取叉子, 反之亦然, 这样获取锁的顺序就有了, 从而解决死锁问题
//        new EaterThread("张三", spoon, fork).start();
//        new EaterThread("李四", spoon, fork).start();


        // 2. 多个资源合并为一个资源, 解除死锁
        Pair pair = new Pair(spoon, fork);
        new EaterThread("王五", pair).start();
        new EaterThread("赵六", pair).start();


    }
}


class EaterThread extends Thread {
    private String name;
    private Tool leftHand;
    private Tool rightHand;

    private Pair pair;


    public EaterThread(String name, Tool leftHand, Tool rightHand) {
        this.name = name;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public EaterThread(String name, Pair pair) {
        this.name = name;
        this.pair = pair;
    }

    // 2. 提供一个对象, 封装勺子和叉子, 破坏多个共享资源, 解除死锁问题
    public void eatPair() {
        synchronized (pair) {
            System.out.println(name + " 拿起: " + pair);
            System.out.println(name + " 正在吃饭...");
            System.out.println(name + " 放下: " + pair);
        }
    }



    // 1. ReentrantLock解决死锁问题
    public void eatRelease() throws InterruptedException {

        boolean leftLockStatus = leftHand.tryLock(1000, TimeUnit.MILLISECONDS);
        System.out.println(name + " leftLockStatus: " + leftLockStatus);


        if (leftLockStatus) {

            try {
                System.out.println(name + " 左手拿起: " + leftHand);



                boolean rightLockStatus = rightHand.tryLock(1000, TimeUnit.MILLISECONDS);
                System.out.println(name + " rightLockStatus: " + rightLockStatus);

                if (rightLockStatus) {
                    try {
                        System.out.println(name + " 右手拿起: " + rightHand);
                        System.out.println(name + " 正在吃饭...");
                        System.out.println(name + " 右手放下: " + rightHand);
                    } finally {
                        rightHand.unlock();
                    }
                }
            } finally {
                leftHand.unlock();
            }
        }

    }


    private void eat() throws InterruptedException {
        synchronized (leftHand) {
            System.out.println(name + " 左手拿起: " + leftHand);
            synchronized (rightHand) {
                System.out.println(name + " 右手拿起: " + rightHand);
                System.out.println(name + " 正在吃饭...");
                System.out.println(name + " 右手放下: " + rightHand);
            }

            System.out.println(name + " 左右放下: " + leftHand);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
//                eat();
//                eatRelease();
                eatPair();
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Tool extends ReentrantLock {

    private String name;

    public Tool(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ " + name + " ]";
    }
}

class Pair {
    private Tool leftHand;
    private Tool rightHand;

    public Pair(Tool leftHand, Tool rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    @Override
    public String toString() {
        return leftHand + " " + rightHand;
    }
}