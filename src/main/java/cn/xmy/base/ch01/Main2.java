package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/17 16:02:59
 * @Description:   通过Thread启动线程
 */
public class Main2 {
    public static void main(String[] args) {
        PrintThread t1 = new PrintThread();
        PrintThread t2 = new PrintThread("H1");
        // 只有调用start方法才算是启动一个线程
        t2.start();
        /**
         * 我们只是创建了一个对象实例, 并非创建一个线程, 就算线程结束对象实例也不一定立马结束
         * 还需要等待GC回收
         */
        t1.setMessage("H@");
//        t1.start();
    }
}
