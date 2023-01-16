package cn.xmy.base.producerconsumer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 16:30:28
 * @Description:
 */
public class ConsumerThread extends Thread {
    private final Exchanger<List> exchanger;
    private List buffer ;
    private final Random R;
    private final Integer BUFF_SIZE;

    public ConsumerThread(Exchanger<List> exchanger, List buffer, Integer BUFF_SIZE, long seed) {
        super("consumer");
        this.exchanger = exchanger;
        R = new Random(seed);
        this.buffer = buffer;
        this.BUFF_SIZE = BUFF_SIZE;
    }

    @Override
    public void run() {
        try {

            // 交换数据
            System.out.println(getName() + ": BEFORE exchange " + buffer);
            // 阻塞等待另一个线程调用exchange方法
            buffer = exchanger.exchange(buffer);
            System.out.println(getName() + ": AFTER exchange " + buffer);

            // 从缓冲区获取数据
            for (int i = 0; i < BUFF_SIZE; i++) {
                System.out.println(getName() + ": -> " + buffer.get(i));
                Thread.sleep(R.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
