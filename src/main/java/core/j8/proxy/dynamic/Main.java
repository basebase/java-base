package core.j8.proxy.dynamic;

import core.j8.proxy.UserInfo;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/14 16:15:27
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        UserInfo target = new UserInfoDynamicImpl();
        UserInfoDynamicProxy dynamicProxy = new UserInfoDynamicProxy(target);
        UserInfo proxy = (UserInfo) dynamicProxy.getProxyInstance();
        System.out.println("Main: " + proxy);
        System.out.println("Main: " + proxy.reduce(1, 1));
    }
}
