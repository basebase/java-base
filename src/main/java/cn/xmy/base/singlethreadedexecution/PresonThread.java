package cn.xmy.base.singlethreadedexecution;

import java.util.concurrent.CountDownLatch;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/7 15:49:30
 * @Description:       用户类
 */
public class PresonThread extends Thread {

    private Gate gate;
    private PresonEnum presonEnum;

    private String userName;
    private String city;

    public PresonThread(Gate gate,  PresonEnum presonEnum) {
        this.gate = gate;
        this.presonEnum = presonEnum;
    }

    public PresonThread(Gate gate, String userName, String city) {
        this.gate = gate;
        this.userName = userName;
        this.city = city;
    }

    @Override
    public void run() {
        try {

            // 非线程安全方法
            gate.access(userName, city);
            // 线程安全方法
            gate.accessSync(userName, city);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
