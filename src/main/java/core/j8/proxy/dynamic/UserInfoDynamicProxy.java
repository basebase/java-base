package core.j8.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/14 16:15:52
 * @Description:
 */
public class UserInfoDynamicProxy {

    // 目标对象
    private Object target;

    public UserInfoDynamicProxy(Object target) {
        this.target = target;
    }

    // 创建代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println(this + " 动态代理前置操作");
                    Object invoke = method.invoke(target, args);
                    System.out.println(this + " 动态代理后置操作");
                    return invoke;
                });
    }
}
