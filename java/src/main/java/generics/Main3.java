package generics;

import com.sun.istack.internal.NotNull;

import java.util.Objects;
import java.util.function.Function;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/23 18:57:39
 * @Description:
 */

interface Func<T, R> {

    R apply(T t) ;

    // 这里的<V>使用的是传入Func的T的值
    default <V> Function<V, R> compose(@NotNull Func<? super V, ? extends T> before) {
        System.out.println(before);
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }
}

public class Main3 {
    public static void main(String[] args) {
        Func<Double, Integer> f = t -> t.intValue() + 1;
        Func<Integer, Double> g = t -> t * 2.0;

        Function<Double, Integer> f1 = t -> t.intValue() + 1;
        Function<Float, Double> f2 = t -> t * 2.0;

        f1.compose(f2);

        System.out.println("f: " + f);
        System.out.println("f: " + g);

        f.compose(g);

    }
}
