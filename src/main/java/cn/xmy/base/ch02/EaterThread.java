package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/12 14:17:49
 * @Description:
 */
public class EaterThread extends Thread {
    private String name;
    private final Tool leftand;
    private final Tool righthand;

    public EaterThread(String name, Tool leftand, Tool righthand) {
        super(name);
        this.name = name;
        this.leftand = leftand;
        this.righthand = righthand;
    }

    public void run() {
        while (true) {
            try {
                eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void eat() throws InterruptedException {

//        if (name.equals("zs")) {
//            Thread.sleep(30);
//        } else {
//            Thread.sleep(20);
//        }

        synchronized (leftand) {
            System.out.println(name + " 左手获取到" + leftand);

            String currThread = Thread.currentThread().getName();



            synchronized (righthand) {
                System.out.println(this.name + " 右手获取到" + righthand);
                Thread.sleep(20);
                System.out.println(this.name + " 右手释放" + righthand);
            }

            System.out.println(this.name + " 左手释放" + leftand);
        }
    }
}
