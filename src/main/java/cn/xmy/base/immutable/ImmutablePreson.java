package cn.xmy.base.immutable;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/10 14:37:09
 * @Description:
 */
public final class ImmutablePreson {
    private final String name;
    private final String address;

    public ImmutablePreson(MutablePreson preson) {
       synchronized (preson) {
           // 这里会出现线程安全问题, get相关方法没有synchronized保护, 此时其它线程在修改, 另外线程读取就出出错, 所以这里的构造方法需要使用synchronized保护
           this.name = preson.getName();
           this.address = preson.getAddress();
       }
    }

    public ImmutablePreson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public MutablePreson getMutablePreson() {
        return new MutablePreson(this);
    }

    @Override
    public String toString() {
        return "ImmutablePreson{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }




}
