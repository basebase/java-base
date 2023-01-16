package cn.xmy.base.balking;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 14:07:59
 * @Description:
 */
public class TestThreadMain {
    public static void main(String[] args) {
        TestThread thread = new TestThread();
        // 线程不允许多次调用start方法, 启动一次即可
        // 至于只打印了一次BEGIN/END, 由于线程启动一次, 多次调用start也无效并且抛出异常
        while (true) {
            thread.start();
        }
    }
}


class TestThread extends Thread {
    @Override
    public void run() {
        System.out.println("BEGIN:");
        for (int i = 0; i < 50; i++) {
            System.out.println(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("END");
    }
}
