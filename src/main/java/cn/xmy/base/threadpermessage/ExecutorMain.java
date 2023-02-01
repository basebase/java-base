package cn.xmy.base.threadpermessage;

import java.util.concurrent.Executor;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 14:20:02
 * @Description:
 */
public class ExecutorMain {
    private final Executor ex = new Executor() {
        @Override
        public void execute(Runnable r) {
            new Thread(r).start();
        }
    };

    public void executor() {
        ex.execute(() -> System.out.println("Executor Example."));
    }

    public static void main(String[] args) {
        new ExecutorMain().executor();
    }
}
