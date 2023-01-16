package cn.xmy.base.producerconsumer;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 14:59:15
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);
        new MakerThread("maker-thread-1", table, 31415).start();
        new MakerThread("maker-thread-2", table, 92653).start();
        new MakerThread("maker-thread-3", table, 58979).start();

        new EaterThread("eater-thread-1", table, 32384).start();
        new EaterThread("eater-thread-2", table, 62643).start();
        new EaterThread("eater-thread-3", table, 38327).start();
    }
}
