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

    public void post(int m) {
        amount += m;
    }

    public /* synchronized */ boolean trak(int m) {
        if (amount >= m) {
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
