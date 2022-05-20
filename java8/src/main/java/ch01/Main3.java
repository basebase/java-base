package ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/20 14:56:41
 * @Description:
 */
public class Main3 {

    /***
     *
     * 当需要返回一个boolean值的函数接口, 可以使用Predicate
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (p.test(t))
                results.add(t);
        }

        return results;
    }


    /***
     *
     * 该接口没有返回对象, 常见于数据遍历使用
     *
     */
    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }


    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> results = new ArrayList<>();
        for (T t : list) {
            results.add(f.apply(t));
        }

        return results;
    }


    public static void main(String[] args) {
        System.out.println(filter(Arrays.asList("A", "B", "C", "D", "E", "EF"), (t) -> t.contains("E")));
        System.out.println(filter(Arrays.asList("A", "B", "C", "D", "E", "EF"), (t) -> t.length() > 1));

        forEach(Arrays.asList("A", "B", "C", "D", "E", "EF"), (t) -> System.out.println(t));

        System.out.println(map(Arrays.asList("A", "TVB", "B", "C", "D", "E", "EF"), (t) -> t.length()));

        // 创建一个对象
        Supplier<String> supplier = () -> "Test Supplier";
        System.out.println(supplier.get());




    }
}
