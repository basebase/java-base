package cn.xmy.base.immutable;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/10 09:27:55
 * @Description:
 */

// 没有可修改方法, 线程安全
public final class Preson {
    private String name;
    private String address;

    public Preson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Preson{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
