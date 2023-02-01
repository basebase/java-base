package cn.xmy.base.threadpermessage;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 14:22:51
 * @Description:
 */
public class ExecutorServiceMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            Executor executor = executorService;
            executor.execute(() -> System.out.println("running..."));
        } finally {
            executorService.shutdown();
        }
    }
}
