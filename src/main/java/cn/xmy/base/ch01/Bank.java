package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/19 17:26:08
 * @Description:   银行类, 用户存取款
 */
public class Bank {
    private int amount;
    private String name;

    public int getAmount() {
        return amount;
    }



    public String getName() {
        return name;
    }


    public Bank(int amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    /***
     * 存款, 添加synchronized关键字, 形成线程之间的互斥
     * @param m
     */
    public synchronized void deposit(int m) {

        // 其实可以不再这里加入sleep等待, 也会导致线程的不安全, 但是现代计算机的优化需要运行很多次才会出现一次异常
        // 模拟存款等待时间
        // 假设用户A这在存款, 用户B也来存款, 用户C...很多用户都同一时间
        // 如果没有线程之间的互斥, 那么存款的金额或许不是用户期待的存款金额
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        amount += m;
    }

    /**
     * 取款, 添加synchronized关键字, 线程进行互斥
     * @param m
     * @return
     */
    public synchronized boolean withdraw(int m) {
        if (amount >= m) {

            // 其实可以不再这里加入sleep等待, 也会导致线程的不安全, 但是现代计算机的优化需要运行很多次才会出现一次异常
            // 多个用户在同一时间去取款, 取款花费一定的时间
            // 此时其它用户也进入该判断, 但其实当前用户的账户已经没有可以取的金额了
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            amount -= m;
            return true;
        } else {
            return false;
        }
    }
}
