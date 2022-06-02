package ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/6/2 14:48:18
 * @Description:
 */

import ch02.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static ch02.Dish.menu;
import static java.util.stream.Collectors.toList;

public class Main2 {
    public static void main(String[] args) {

        // 使用map方法转换成一个新的类型
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);

        // 假设我们想知道每个字符串长度
        // 使用map获取一个字符串长度并返回
        List<Integer> wordLengths = Arrays.asList("Java", "Flink", "Python", "Hadoop")
                .stream()
                .map(String::length)
                .collect(toList());



        // 把字符串解析成每个字符, 然后进行去重, 但是使用map方法是无法实现的
//        List<String[]> collect = Arrays.asList("Hello", "World")
//                .stream()
                  // 这里返回的是Stream<String[]>类型, 但是我们想要的是Stream<String>类型来表示一个字符流
//                .map(word -> word.split(""))
//                .distinct()
//                .collect(toList());
//        System.out.println(collect);

//        List<Stream<String>> collect = Arrays.asList("Hello", "World")
//                .stream()
//                .map(word -> word.split(""))
//                // 这里把数组进行stream转换拟定为Stream<String>, 但是外层还是一个Stream, 所以为Stream<Stream<String>>
//                .map(Arrays::stream)
//                .distinct()
//                .collect(toList());
//        System.out.println(collect);

        List<String> uniqueCharacters = Arrays.asList("Hello", "World")
                .stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(uniqueCharacters);


        List<Integer> collect = Arrays.asList(1, 2, 3, 4, 5)
                .stream()
                .map(n -> n * n)
                .collect(toList());
        System.out.println(collect);


        List<Integer> n1 = Arrays.asList(1, 2, 3);
        List<Integer> n2 = Arrays.asList(4, 5);

        List<int[]> collect1 = n1.stream().flatMap(i -> n2.stream().map(j -> new int[]{i, j})).collect(toList());
        System.out.println(collect1);


        List<Integer[]> collect2 = n1.stream().flatMap(new Function<Integer, Stream<Integer[]>>() {
            @Override
            public Stream<Integer[]> apply(Integer i) {
//                System.out.println("i = " + i);
                Stream<Integer[]> stream = n2.stream().map(j -> new Integer[]{i, j});
                return stream;
            }
        }).collect(toList());

        System.out.println(collect2);


    }
}
