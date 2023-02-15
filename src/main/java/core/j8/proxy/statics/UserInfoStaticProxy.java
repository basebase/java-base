package core.j8.proxy.statics;

import core.j8.proxy.UserInfo;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/14 16:06:58
 * @Description:
 */
public class UserInfoStaticProxy implements UserInfo {

    private UserInfo target;

    public UserInfoStaticProxy(UserInfo target) {
        this.target = target;
    }

    @Override
    public void saveInfo(String info) {
        System.out.println(this + " 代理对象调用实际方法前的操作");
        target.saveInfo(info);
        System.out.println(this + " 代理对象调用实际方法后的操作");
    }

    @Override
    public int reduce(int value1, int value2) {
        return 0;
    }
}
