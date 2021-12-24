package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/23 17:52:42
 * @Description:    左手和右手分别拿起餐具, 开始用餐类
 */
public class EaterThread extends Thread {
    private String name;
    private final Tool leftHand;
    private final Tool rightHand;

    public EaterThread(String name, Tool leftHand, Tool rightHand) {
        this.name = name;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    @Override
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
        synchronized (leftHand) {
//            Thread.sleep(10);
            System.out.println(name + " 获取到 " + leftHand + " (left).");

            if (name.equals("zhangsan")) {
                leftHand.wait(100);
            } else {
                leftHand.wait(200);
            }


            System.out.println(name + "被唤醒...");

            synchronized (rightHand) {
                System.out.println(name + " 获取到 " + rightHand + " (right). ");
                System.out.println(name + " 开始用餐...");
                System.out.println(name + " 放下右手 " + rightHand + " (right). ");
                rightHand.notifyAll();
            }

            System.out.println(name + " 放下左手 " + leftHand + " (left). ");

            // 在这里添加后, 执行到一定还是会发生执行不下去的问题, 两天线程都陷入无限等待中
//            if (name.equals("lisi")) {
//                leftHand.wait();
//            }
        }
    }
}
