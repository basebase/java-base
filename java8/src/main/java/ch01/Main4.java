package ch01;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/20 16:38:19
 * @Description:
 */
public class Main4 {

    public static void main(String[] args) {

        // 使用方法引用用于替代lambda表达式
        Function<String, Integer> f = Integer::parseInt;
        BiPredicate<List<String>, String> contains = List::contains;
    }
}
