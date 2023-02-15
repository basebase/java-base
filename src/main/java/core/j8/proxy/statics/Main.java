package core.j8.proxy.statics;

import core.j8.proxy.UserInfo;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/14 16:08:37
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        // 目标对象
        UserInfo target = new UserInfoStaticImpl();
        // 代理对象
        UserInfoStaticProxy proxy = new UserInfoStaticProxy(target);
        proxy.saveInfo("用户信息");
    }
}
