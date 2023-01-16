package cn.xmy.base.producerconsumer;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 14:59:43
 * @Description:
 */
public class Table {

    private String[] buff;
    private Integer tail;
    private Integer head;
    private Integer count;

    public Table(Integer count) {
        this.buff = new String[count];
        this.count = count;
        this.head = 0;
        this.tail = 0;
    }

    public synchronized void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        while (count >= buff.length)
            wait();

        buff[tail] = cake;

        // 取模运算, 形成一个环
        tail = (tail + 1) % buff.length;
        count ++;
        // 蛋糕已经制作完成, 唤醒吃蛋糕线程
        notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (count <= 0)
            wait();

        String cake = buff[head];
        head = (head + 1) % buff.length;
        count --;
        // 唤醒生产蛋糕线程, 不满足3个蛋糕数量
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " takes " + cake);
        return cake;
    }

}
