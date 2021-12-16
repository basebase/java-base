package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/16 13:55:53
 * @Description:    门类, 记录通过用户的姓名和地址
 */
public class Gate {
    private int count = 0;
    private String name = "NoBody";
    private String address = "Nowhere";

    /**
     * 通过门的类, 添加synchronized确保只有一个线程执行
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        count ++;
        this.name = name;
        this.address = address;
        check();    // 由于是pass方法调用的check可以保证线程的串行性, 并且还是private修饰, 故check方法不需要增加synchronized
    }

    /***
     * 检查类, 用来判断名字和地址首字母是否一致
     */
    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("***** BROKEN ***** " + toString());
        }
    }

    /***
     * 对于toString方法, 外部多个线程依旧可以调用, 这就会导致A线程在执行pass但是B线程输出了toString方法
     * 从而导致名称和地址不是对应的。
     * @return
     */
    @Override
    public synchronized String toString() {
        return "No." + count + ": " + name + ", " + address;
    }
}
