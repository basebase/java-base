package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/17 16:38:30
 * @Description:    实现Runnable接口启动一个线程
 */
public class Printer implements Runnable {

    private String msg;

    public Printer(String msg) {
        this.msg = msg;
    }

    /**
     *  只需要实现run方法即可
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(msg);
        }
    }
}
