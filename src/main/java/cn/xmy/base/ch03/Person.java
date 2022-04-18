package cn.xmy.base.ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/18 11:35:52
 * @Description:    Person类是线程安全的
 *                          1. 类声明为final表示无法创建Person子类, 防止子类修改数据
 *                          2. Person字段都设置为private, 自有该类的内部才可以访问
 *                          3. 并未字段提供setting方法修改字段信息
 *                          4. name和address都被声明为final类型, 意味着一旦被赋值一次, 就不会在被赋值
 *
 *                  getName/getAddress/toString方法可以不用声明synchronized
 */
public final class Person {
    private final String name;
    private final String address;

    public Person(String name, String address) {
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
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
