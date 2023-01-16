package cn.xmy.base.producerconsumer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 16:24:33
 * @Description:
 */
public class ProducerThread extends Thread {
    private final Exchanger<List> exchanger;
    private List buffer;
    private char index = 0;
    private final Random R;
    private final Integer BUFF_SIZE;

    public ProducerThread(Exchanger<List> exchanger, List buffer, Integer BUFF_SIZE, long seed) {
        super("producer");
        this.exchanger = exchanger;
        this.buffer = buffer;
        this.R = new Random(seed);
        this.BUFF_SIZE = BUFF_SIZE;
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i < BUFF_SIZE; i++) {
                buffer.add(nextChar());
                System.out.println(getName() + ": " + buffer.get(i) + " -> ");
            }

            System.out.println(getName() + ": BEFORE exchange " + buffer);
            // 交换数据
            // 阻塞等待另一个线程调用exchange方法
            buffer = exchanger.exchange(buffer);
            System.out.println(getName() + ": AFTER exchange " + buffer);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() throws InterruptedException {
        char c = (char) ('A' + index % 26);
        index ++;
        Thread.sleep(R.nextInt(1000));
        return c;
    }
}
