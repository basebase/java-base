package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/17 17:23:34
 * @Description:    线程暂停, 执行一次输出等待1s
 */
public class Main4 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("main");
            try {
                // 线程暂停等待1s
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
