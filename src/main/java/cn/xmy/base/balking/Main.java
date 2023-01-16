package cn.xmy.base.balking;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 11:10:43
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Data data = new Data("test", "empty");
        new SaverThread("save", data).start();
        new ChangerThread("chang", data).start();
    }
}
