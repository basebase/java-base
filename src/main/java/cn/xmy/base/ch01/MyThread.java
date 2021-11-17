package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/17 15:47:18
 * @Description:   启动一个新线程来处理
 */
public class MyThread extends Thread {

    /***
     * 重写该方法, 线程具体要做的工作
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("run.");
        }
    }
}
