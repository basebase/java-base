package cn.xmy.base.threadpermessage;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 14:16:18
 * @Description:
 */
public class ExecutorsMain {

    public static void main(String[] args) {
        // 使用Executors创建ThreadFactory
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadFactory.newThread(() -> System.out.println("T1")).start();
        threadFactory.newThread(() -> System.out.println("T2")).start();
        threadFactory.newThread(() -> System.out.println("T3")).start();
    }
}
