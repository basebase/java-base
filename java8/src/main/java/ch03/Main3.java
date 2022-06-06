package ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/6/6 16:06:42
 * @Description:
 */

import ch02.Dish;

import static ch02.Dish.menu;
import static java.util.stream.Collectors.toList;

public class Main3 {
    public static void main(String[] args) {


        // 使用anyMatch进行匹配, 只要有一个匹配就可以
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        // allMatch所有食物热量都必须大于200才可以匹配
        if (menu.stream().allMatch(dish -> dish.getCalories() > 200)) {
            System.out.println("yes!");
        }

        // noneMatch匹配所有条件都不匹配
        // 如果dish.getCalories() >= 800, 只要有一个食物热量匹配上就不会输出
        if (menu.stream().noneMatch(dish -> dish.getCalories() >= 900)) {
            System.out.println("noneMatch");
        }

    }
}
