package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/19 17:29:09
 * @Description:   模拟多个用户同时操作一个用户账户
 */
public class Main6 {
    public static void main(String[] args) {
        Bank bank = new Bank(100, "zs");

        Thread[] threads = new Thread[20];
        for (int i = 0; i < 19; i++) {
            BankThread thread = new BankThread(bank, (i <= 0 ? (i + 1) : i) * 2);
            threads[i] = thread;
        }

        for (int i = 0; i < 19; i++) {
            threads[i].start();
        }
    }
}
