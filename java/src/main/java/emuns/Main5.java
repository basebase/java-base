package emuns;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/27 14:39:31
 * @Description:
 */
public class Main5 {

//    public static <T> void printEnumInfo(Supplier<T> s) {
//        System.out.println(s.get());
//    }

    public static void printEnumInfo(RandomEnum re) {
        System.out.println(re.get());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
//            printEnumInfo(RandomEnum.F1);
            printEnumInfo(RandomEnum.F1);
        }
    }
}


// 所有的enum都继承自Enum类, 所以自定义Enum类无法继承其它类, 但是可以实现多个接口
//enum RandomEnum extends Main4 {
//
//}

enum RandomEnum implements Supplier<RandomEnum> {
    F1, F2, F3, F4, F5, F6, F7, F8;

    private Random random = new Random(40);

    @Override
    public RandomEnum get() {
        return values()[random.nextInt(values().length)];
    }
}
