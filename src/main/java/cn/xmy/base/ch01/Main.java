package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/17 15:43:22
 * @Description:  第一个多线程例子
 */
public class Main {
    public static void main(String[] args) {
        Thread t = new MyThread();
        // 主线程启动一个新线程
        t.start();

        /***
         *     主线程输出和我们新启动的线程输出内容是交织在一起输出的,
         *     两个线程并发运行, 所以会混在一起
         */
        for (int i = 0; i < 100; i++) {
            System.out.println("main");
        }
    }
}
