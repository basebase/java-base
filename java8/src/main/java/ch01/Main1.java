package ch01;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/20 14:11:33
 * @Description: 第一个Lambda表达式例子
 */
public class Main1 {

    public static void process(Runnable r) {
        r.run();
    }

    public static void main(String[] args) {

        // Runnable接口Lambda使用
        Runnable r1 = () -> System.out.println("lambda runnable");

        // Runnable非Lambda使用
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("not lambda runnable");
            }
        };

        process(r1);
        process(r2);
        // 直接通过参数形式传递
        process(() -> System.out.println("params runnable"));
    }
}
