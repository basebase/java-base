package cn.xmy.base.immutable;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/10 09:31:27
 * @Description:
 */
public class PrintPresonThread extends Thread {
    private Preson preson;

    public PrintPresonThread(String threadName, Preson preson) {
        super(threadName);
        this.preson = preson;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " Preson Info: " + preson.toString());
        }
    }
}
