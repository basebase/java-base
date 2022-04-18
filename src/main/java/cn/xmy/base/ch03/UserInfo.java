package cn.xmy.base.ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/18 18:17:42
 * @Description:    该类为什么不是一个Immutable类
 *                      1. 字段类型为private final类型
 *                      2. 也没有setter方法
 *                  请找出问题点
 *
 */
public final class UserInfo {
    private final StringBuffer info;

    public UserInfo(String name, String address) {
        this.info = new StringBuffer("<info name = '" + name + "' address = '" + address + "'>");
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "info=" + info +
                '}';
    }

    /***
     *
     * getInfo方法返回了当前对象的实例, 那么其它类就有机会进行修改, 所以这才是导致UserInfo即使
     * 类声明为final字段也设置为private final并且没有提供setter方法, 但依旧不是一个Immutable类
     *
     * @return
     */
    public StringBuffer getInfo() {
        return info;
    }
}
