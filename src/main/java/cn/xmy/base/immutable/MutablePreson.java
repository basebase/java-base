package cn.xmy.base.immutable;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/10 14:36:17
 * @Description:
 */
public final class MutablePreson {
    private String name;
    private String address;

    public MutablePreson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public MutablePreson(ImmutablePreson preson) {
        this.name = preson.getName();
        this.address = preson.getAddress();
    }

    public synchronized void setPreson(String newName, String newAddress) {
        this.name = newName;

        // 这里如果是程序执行时间, 其它线程调用getAddress方法, 那么就会引发安全问题
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        this.address = newAddress;
    }

    public synchronized ImmutablePreson getImmutablePreson() {
        return new ImmutablePreson(this);
    }



    // 1. get方法可能存在线程安全问题, 虽然set有synchronized保护, 但是其它线程在读取时可能还是旧的值

    String getName() {
        return name;
    }

    String getAddress() {
        return address;
    }

    @Override
    public synchronized String toString() {
        return "MutablePreson{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
