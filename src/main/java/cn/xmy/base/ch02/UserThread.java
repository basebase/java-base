package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/16 13:56:26
 * @Description:    表示通过门的类
 */
public class UserThread extends Thread {
    private String name ;
    private String address ;
    private Gate gate;

    public UserThread(Gate gate, String name, String address) {
        this.gate = gate;
        this.name = name;
        this.address = address;
    }

    @Override
    public void run() {
        while (true) {
            gate.pass(name, address);
        }
    }
}