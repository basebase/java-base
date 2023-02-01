package cn.xmy.base.threadpermessage;

import java.util.concurrent.ThreadFactory;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 14:07:21
 * @Description:
 */
public class ThreadFactoryMain {
    private final ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    };

    public void execute() {
        threadFactory.newThread(() -> System.out.println("Test 1")).start();
        threadFactory.newThread(() -> {
            System.out.println("开始读取文本数据");
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("读取文本数据结束");
        }).start();
    }

    public static void main(String[] args) {
        // ThreadFactory创建并管理线程
        new ThreadFactoryMain().execute();
    }
}
