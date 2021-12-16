package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/16 13:55:14
 * @Description:    创建通过的门, 并让三个人不断通过门
 */
public class Main {
    public static void main(String[] args) {
        Gate gate = new Gate();
        UserThread u1 = new UserThread(gate, "Alice", "Alaska");
        UserThread u2 = new UserThread(gate, "Bobby", "Brazil");
        UserThread u3 = new UserThread(gate, "Chris", "China");

        u1.start();
        u2.start();
        u3.start();
    }
}
