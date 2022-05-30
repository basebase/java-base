package emuns;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/27 14:55:18
 * @Description:
 */
public class Main6 {

    public static void main(String[] args) {
        Food food = Food.Appetizer.SALAD;
        food = Food.MainCourse.LASAGNE;
        food = Food.Dessert.FRUIT;
        food = Food.Coffee.LATTE;
    }
}


interface Food {

    // enum无法继承一个类, 此时可以在一个接口内部创建枚举并实现接口
    // 所有枚举都实现食物, 但又可以是不同的食物

    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS;
    }

    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO;
    }

    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }

    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA;
    }

}