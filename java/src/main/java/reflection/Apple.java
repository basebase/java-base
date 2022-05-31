package reflection;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/30 14:42:02
 * @Description:
 */
public class Apple {

    private String name;
    private int weight;

    public Apple() {}

    public Apple(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple(String name) {
        this.name = name;
    }

    public void eat(String food) {
        System.out.println("Hi I like " + food);
    }

    private void sleep() {
        System.out.println("slepp not work");
    }

    public String getFoodName() {
        return "RedApple";
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
