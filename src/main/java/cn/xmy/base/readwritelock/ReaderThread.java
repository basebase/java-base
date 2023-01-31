package cn.xmy.base.readwritelock;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/31 13:59:04
 * @Description:
 */
public class ReaderThread extends Thread {

    private final Data data;

    public ReaderThread(String threadName, Data data) {
        super(threadName);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuff = data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuff));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
