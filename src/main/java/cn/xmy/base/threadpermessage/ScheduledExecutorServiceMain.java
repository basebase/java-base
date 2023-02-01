package cn.xmy.base.threadpermessage;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 14:28:46
 * @Description:
 */
public class ScheduledExecutorServiceMain {
    public static void main(String[] args) {
        // 创建5个线程数
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        try {
            System.out.println("当前时间: " + new Date());
            scheduledExecutorService.schedule(() -> System.out.println("3秒后执行: " + new Date()), 3, TimeUnit.SECONDS);
        } finally {
            scheduledExecutorService.shutdown();
        }
    }
}
