package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/19 17:27:48
 * @Description:    多个线程同时操作银行业务类
 */
public class BankThread extends Thread {
    private Bank bank;
    private int m;

    public BankThread(Bank bank, int m) {
        this.bank = bank;
        this.m = m;
    }

    @Override
    public void run() {
        bank.deposit(m);
        System.out.println(Thread.currentThread().getName() + " 存款(" + m + "): " +  " 可用余额: " + bank.getAmount() );
        boolean trak = bank.withdraw(m);
        int amount = bank.getAmount();
        System.out.println(Thread.currentThread().getName() + " 取款(" + m + "): " + (trak ? "成功" : "失败") + " 可用余额: " + amount );
    }
}
