package generics;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/23 11:13:34
 * @Description:
 */
public class TupleTest {
    public static void main(String[] args) {
        Tuple2<String, Integer> tuple2 = new Tuple2<>("zhangsan", 111);
        Tuple3<Amphibian, Integer, Integer> tuple3 = new Tuple3<>(new Amphibian(), 1, 1);
        Tuple4<Vehicle, String, Automobile, Integer> tuple4 = new Tuple4<>(new Vehicle(), "zhangsan", new Automobile(), 111);

        // 编译错误，因为 final 不能重新赋值
//        tuple2.a = "hi";

        System.out.println(tuple2);
        System.out.println(tuple3);
        System.out.println(tuple4);
    }
}
