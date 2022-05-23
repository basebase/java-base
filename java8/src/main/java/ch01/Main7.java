package ch01;

import java.util.function.Function;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/23 16:12:50
 * @Description:
 */
public class Main7 {
    public static void main(String[] args) {
        Function<Double, Integer> f = t -> t.intValue() + 1;
        Function<Integer, Double> g = t -> t + 1.0;



        Function<Double, Double> doubleDoubleFunction = f.andThen(g);
//
//        Function<Integer, Double> integerDoubleFunction = f.andThen(g);
    }
}
