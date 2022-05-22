package ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/***
 * @Auth Joker
 * @Date 2022/05/22 10:27:18
 * @Description
 */
public class Main5 {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple(10, "red", "jilin"),
                new Apple(15, "green", "shanghai"),
                new Apple(5, "green", "shenzhen"),
                new Apple(20, "green", "beijing"),
                new Apple(10, "red", "hangzhou"),
                new Apple(100, "red", "nanjing")
        );

        System.out.println(apples);


        // 1. lambda表达式排序
        apples.sort((app1, app2) -> app1.getWeight().compareTo(app2.getWeight()));
        System.out.println(apples);

        // 2. 方法引用排序
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(apples);

        // 3. 复合lambda表达式
        // 3.1 倒序
        // 通过使用reversed方法可以完成倒序, 按照重量
        apples.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(apples);

        // 3.2 比较器
        // 如果两个苹果一样重, 哪个苹果排在前面呢? 可能还需要提供一个比较器, 比如按照生产地比较
        // 使用thenComparing方法
        apples.sort(Comparator.comparing(Apple::getWeight)
                              .reversed()
                              // 如果苹果一样重, 按照生产地比较
                              .thenComparing(Apple::getCity));
        System.out.println(apples);


        System.out.println("===========================");

        // 3.3 谓词复合
        Predicate<Apple> p1 = apple -> apple.getColor().equals("red");
        // 链接两个谓词, 产生新的Predicate对象
        // 请注意，and和or方法是按照在表达式链中的位置，从左向右确定优先级的。
        // 因此，a.or(b).and(c)可以看作(a || b) && c
        Predicate<Apple> p2 = p1.and(apple -> apple.getWeight() > 20).or(apple -> apple.equals("green") && apple.getWeight() <= 5);

        f(apples, p2);

    }


    public static void f(List<Apple> apples, Predicate<Apple> p) {
        apples.forEach(app -> {
            if (p.test(app))
                System.out.println(app);
        });
    }
}
