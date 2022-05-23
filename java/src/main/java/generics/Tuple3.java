package generics;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/23 11:07:49
 * @Description:
 */
public class Tuple3<A, B, C> extends Tuple2<A, B> {
    // 通过final修饰, 无法被修改
    public final C c;

    public Tuple3(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public String toString() {
        return "Tuple3{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
