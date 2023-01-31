package cn.xmy.base.readwritelock;

import java.util.Random;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/31 13:58:54
 * @Description:
 */
public class WriterThread extends Thread {

    private static final Random random = new Random();
    private final Data data;
    private String filler;
    private int index = 0;

    public WriterThread(String threadName, String filler, Data data) {
        super(threadName);
        this.filler = filler;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index ++;
        if (index >= filler.length())
            index = 0;
        return c;
    }
}
