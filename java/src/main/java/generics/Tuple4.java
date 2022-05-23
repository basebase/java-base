package generics;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/23 11:07:49
 * @Description:
 */
public class Tuple4<A, B, C, D> extends Tuple3<A, B, C> {
    // 通过final修饰, 无法被修改
    public final D d;

    public Tuple4(A a, B b, C c, D d) {
        super(a, b, c);
        this.d = d;
    }

    @Override
    public String toString() {
        return "Tuple4{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
