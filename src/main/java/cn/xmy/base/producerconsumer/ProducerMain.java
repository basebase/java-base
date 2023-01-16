package cn.xmy.base.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 16:34:14
 * @Description:
 *
 * @see ProducerThread
 * @see ConsumerThread
 */
public class ProducerMain {
    public static void main(String[] args) {
        Exchanger<List> exchanger = new Exchanger<>();
        Integer buffSize = 10;
        List buffer1 = new ArrayList(buffSize);
        List buffer2 = new ArrayList(buffSize);
        new ProducerThread(exchanger, buffer1, buffSize,1000).start();
        new ConsumerThread(exchanger, buffer2, buffSize,800).start();
    }
}
