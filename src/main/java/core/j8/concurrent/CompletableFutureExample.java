package core.j8.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/3 15:56:12
 * @Description:
 */
public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("任务执行结束...");
        }).thenAccept(res -> {
            System.out.println("任务执行成功...");

        });

        Thread.sleep(2000);
    }
}
