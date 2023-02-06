package core.j8.thread.join;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/6 16:16:37
 * @Description:
 */
public class JoinTask implements Task<Integer, Integer> {

    private static final Random RANDOM = new Random();
    private final List<Integer> datas;
    private int num = 0;
    private Thread thread;

    public JoinTask(List<Integer> datas) {
        this.datas = datas;
    }

    @Override
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {

            if (!Thread.currentThread().getName().equals("T-1")) {
                System.out.println(Thread.currentThread().getName() + " 等待线程: " + thread.getName() + " 执行完才继续...");
                thread.join();
            }

            System.out.println(Thread.currentThread().getName() + "   " + new Date() + " 开始任务...");
            Thread.sleep(RANDOM.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor();
        System.out.println(Thread.currentThread().getName() + "   " + new Date() + " 任务完成...");
    }

    @Override
    public Integer executor() {
        datas.forEach(data -> {
            num += data;
        });
        System.out.println(Thread.currentThread().getName() + " 计算结果: " + num);
        return num;
    }
}
