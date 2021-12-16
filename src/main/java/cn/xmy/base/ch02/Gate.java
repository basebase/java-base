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
     * 通过门的类
     * @param name
     * @param address
     */
    public void pass(String name, String address) {
        count ++;
        this.name = name;
        this.address = address;
        check();
    }

    /***
     * 检查类, 用来判断名字和地址首字母是否一致
     */
    private void check() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("***** BROKEN ***** " + toString());
        }
    }

    @Override
    public String toString() {
        return "No." + count + ": " + name + ", " + address;
    }
}
