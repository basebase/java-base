package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/17 16:40:11
 * @Description:    Runnable启动线程例子
 */
public class Main3 {
    public static void main(String[] args) {
        Printer p = new Printer("msg");
        /**
         *  Printer实例只有run方法, 没有start方法, 如何启动一个线程呢?
         */
//        p.run();
//        p.start()     // 不存在

        // 使用Thread包装后启动
        Thread t1 = new Thread(p);
        t1.start();
    }
}
