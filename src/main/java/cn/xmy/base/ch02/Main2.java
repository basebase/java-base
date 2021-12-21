package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/17 16:20:24
 * @Description:    多线程死锁
 */
public class Main2 {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        GrabResourceThread1 grabResourceThread1 = new GrabResourceThread1("GRT-1", lock1, lock2);
        GrabResourceThread2 grabResourceThread2 = new GrabResourceThread2("GRT-2", lock1, lock2);

        grabResourceThread1.start();
        grabResourceThread2.start();
    }
}
