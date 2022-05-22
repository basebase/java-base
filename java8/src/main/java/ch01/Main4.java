package ch01;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/20 16:38:19
 * @Description:
 */
public class Main4 {

    public static void main(String[] args) {

        // 使用方法引用用于替代lambda表达式
        Function<String, Integer> f = Integer::parseInt;
        BiPredicate<List<String>, String> contains = List::contains;


        // 构造函数引用


        // 1. 没有参数的构造引用
        Supplier<Apple> supplier = Apple::new;
        Apple app1 = supplier.get();
        System.out.println(app1);

        // 2. 有一个参数的构造引用
        Function<Integer, Apple> function = Apple::new;
        Apple app2 = function.apply(10);// 传递参数
        System.out.println(app2);

        // 3. 有2个参数的构造函数
        BiFunction<Integer, String, Apple> biFunction = Apple::new;
        Apple app3 = biFunction.apply(10, "red");
        System.out.println(app3);

        // 4. 自定义接口, 满足3个参数
        TriFunction<Integer, String, String, Apple> triFunction = Apple::new;
        Apple app4 = triFunction.apply(100, "green", "beijing");
        System.out.println(app4);
    }
}
