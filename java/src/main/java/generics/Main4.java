package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/25 16:46:28
 * @Description:
 *
 *          https://dzone.com/articles/covariance-and-contravariance
 *          https://howtodoinjava.com/java/generics/java-generics-what-is-pecs-producer-extends-consumer-super/
 *
 */
public class Main4 {

    public static void main(String[] args) {
        Number[] numbers = new Number[3];
        numbers[0] = new Integer(10);
        numbers[1] = new Double(1.1);
        numbers[2] = new Byte("0");

        Integer[] ints = {1, 2, 3, 4};
        Number[] myNums = ints;

//        myNums[0] = 31.4;

        List<Integer> myIntList = new ArrayList<>();
        myIntList.add(1);
        myIntList.add(2);
        // 编译错误
//        List<Number> myNumList = myIntList;
//        myNumList.add(1.1);

        Integer[] its = {1, 2, 3, 4, 5};
        Double[] dts = {1.1, 2.2, 3.3, 4.4, 5.5};
        Long[] lts = {1L, 2L, 3L, 4L, 5L};
//        System.out.println(sum(its));
//        System.out.println(sum(dts));
//        System.out.println(sum(lts));

        List<Integer> itList = Arrays.asList(ints);
        List<Double> dtList = Arrays.asList(dts);
        List<Long> ltList = Arrays.asList(lts);

        // <? extends Number> 也是一样的, 不清楚有多少种类型, 所以不然写入数据

        // 编译出错
//        sum(itList);
//        sum(dtList);
//        sum(ltList);


        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Double> list2 = Arrays.asList(1.5, 2.5, 3.5);
        List<String> list3 = Arrays.asList("a");
        List<? extends Number> myNList = list;
        for (Number number : myNList) {
            System.out.println(number);
        }
    }

    public static long sum(Number[] numbers) {
        long total = 0;
        for (Number number : numbers) {
            total += number.longValue();
        }

        return total;
    }

    public static long sum(List<Number> numberList) {
        long total = 0;
        for (Number number : numberList) {
            total += number.longValue();
        }

        return total;
    }
}
