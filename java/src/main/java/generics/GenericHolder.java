package generics;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/23 11:04:18
 * @Description:
 */
public class GenericHolder<T> {
    private T t;

    public GenericHolder() {}

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        GenericHolder<Automobile> genericHolder = new GenericHolder<>();
        // 类型校验, 报错
//        genericHolder.setT("Hello generic");
        genericHolder.setT(new Automobile());

    }
}
