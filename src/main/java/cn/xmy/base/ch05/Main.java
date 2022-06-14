package cn.xmy.base.ch05;

/**
 * @Author xiaomoyu
 * @Date: 2022/6/14 17:06:36
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Data data = new Data("data.txt", "empty");
        new ChangerThread("changer-thread", data).start();
        new SaverThread("saver-thread", data).start();
    }
}
