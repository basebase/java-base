package ch02;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/25 14:45:29
 * @Description:
 */
public class Main1 {
    public static void main(String[] args) {
        List<String> result = Dish.menu.stream()
                .filter(dish -> dish.getCalories() < 300)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(dish -> dish.getName())
//                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
