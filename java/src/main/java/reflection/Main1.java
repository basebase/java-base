package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/30 14:41:56
 * @Description:
 */
public class Main1 {
    public static void main(String[] args) throws Exception {

        Apple apple = new Apple(111);
        System.out.println(apple);

        apple.eat("GreenApple");
        System.out.println(apple.getFoodName());

        Class c = Class.forName("reflection.Apple");

        Method eatM = c.getMethod("eat", Class.forName("java.lang.String"));

        Method sleepM = c.getDeclaredMethod("sleep");
        //
        sleepM.setAccessible(true);

        System.out.println("============================getMethods============================");

        for (Method method : c.getMethods()) {
            System.out.println(method.getName());
        }

        System.out.println("============================getMethods============================");



        System.out.println("============================getDeclaredMethods============================");

        for (Method method : c.getDeclaredMethods()) {
            System.out.println(method.getName());
        }

        System.out.println("============================getDeclaredMethods============================");


        Object o = c.newInstance();

        Constructor cons = c.getConstructor(int.class);
        Object oc = cons.newInstance(222);

        eatM.invoke(oc, "FuckApple");
        sleepM.invoke(oc);
        System.out.println(oc);

    }
}
