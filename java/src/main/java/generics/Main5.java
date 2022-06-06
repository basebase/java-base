package generics;

import com.sun.istack.internal.NotNull;

/**
 * @Author xiaomoyu
 * @Date: 2022/6/6 16:29:05
 * @Description:
 */
public class Main5 {
    public static void main(String[] args) {
        Pair<String, Integer> p1 = new Pair<>("zhangsan", 100);
        Pair<String, Float> p2 = new Pair<>("lisi", 200f);

        Pair<String, String> p3 = new Pair<>("lisi", "200f");
        Pair<Double, Integer> p4 = new Pair<>(1.0, 222);



//        p1.test(p2);
        System.out.println(p1.test(p4));
        System.out.println(p1.test2(p4));
    }
}

class Pair<K, V> {

    K k;
    V v;
    public Pair() {}

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    // 这里的泛型方法, 首先判断传递过来的Pair对象是否满足了extends关系
    // 如果没有则出现异常

    // 这里的V采用的调用方的泛型<V>即p1引用
    // 则T由于是新的泛型, 则接受传入的Pair对象的类型, 即p4的泛型<K>
    public <T> Pair<T, V> test(@NotNull Pair<? extends T, ? extends V> k2) {
        return new Pair<>(k2.k, k2.v);
    }

    // 使用super的时候还需要(T)转换下?
    public <T> Pair<T, V> test2(@NotNull Pair<? super T, ? extends V> k2) {
        return new Pair<>((T) k2.k, k2.v);
    }


    @Override
    public String toString() {
        return "Pair{" +
                "k=" + k +
                ", v=" + v +
                '}';
    }
}
