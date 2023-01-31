package cn.xmy.base.readwritelock;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/31 13:58:28
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Data data = new Data(10);
        new ReaderThread("read-01", data).start();
        new ReaderThread("read-02", data).start();
        new ReaderThread("read-03", data).start();
        new ReaderThread("read-04", data).start();
        new ReaderThread("read-05", data).start();
        new ReaderThread("read-06", data).start();
        new WriterThread("write-01", "ABCDEFG", data).start();
        new WriterThread("write-02", "hijklmn", data).start();
    }
}
