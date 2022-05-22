package ch01;

/***
 * @Auth Joker
 * @Date 2022/05/22 10:06:12
 * @Description
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
