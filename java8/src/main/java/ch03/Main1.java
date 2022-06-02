package ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/6/2 14:40:11
 * @Description:
 */
import ch02.Dish;

import java.util.Arrays;
import java.util.List;

import static ch02.Dish.menu;
import static java.util.stream.Collectors.toList;

public class Main1 {
    public static void main(String[] args) {

        // 使用filter过滤出素食
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        System.out.println(vegetarianMenu);

        // 使用distinct进行去重
        Arrays.asList(1, 2, 3, 4, 5)
                .stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        // 使用limit截取前面3条数据
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        System.out.println(dishes);

        // 使用skip跳过前面N个元素, 如果不足N个则返回一个空的流
        List<Dish> dishes2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(3)
                .collect(toList());
        System.out.println(dishes2);
    }
}
