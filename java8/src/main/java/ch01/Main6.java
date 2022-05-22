package ch01;

import java.util.function.Function;

/***
 * @Auth Joker
 * @Date 2022/05/22 22:44:18
 * @Description
 */
public class Main6 {
    public static void main(String[] args) {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        // andThen执行顺序是从做到右
        // 先计算f()函数, 然后将f()函数结果乘2
        // 可以理解为g(f(x))
        Function<Integer, Integer> h = f.andThen(g);


        // compose先执行g()函数, 然后在把结果进行加1
        // 可以理解f(g(x))
        Function<Integer, Integer> compose = f.compose(g);


        Integer val1 = h.apply(1);
        Integer val2 = compose.apply(1);
        System.out.println(val1);
        System.out.println(val2);
    }
}
