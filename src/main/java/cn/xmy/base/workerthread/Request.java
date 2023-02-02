package cn.xmy.base.workerthread;

import java.util.Random;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 15:34:14
 * @Description:
 */
public class Request {

    private final String name;      // 工作发布者名称
    private final int number;       // 工作发布者编号
    private static final Random RANDOM = new Random();

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executes " + this);
        try {
            Thread.sleep(RANDOM.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[ Request from " + name + " No. " + number + " ]";
    }
}
