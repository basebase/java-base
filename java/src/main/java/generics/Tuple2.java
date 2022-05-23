package generics;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/23 11:07:49
 * @Description:
 */
public class Tuple2<A, B> {
    // 通过final修饰, 无法被修改
    public final A a;
    public final B b;

    public Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Tuple2{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
