package ch01;

/***
 * @Auth Joker
 * @Date 2022/05/22 09:57:06
 * @Description
 */
public class Apple {

    private Integer weight;
    private String color;
    private String city;

    public Apple() {}

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple(Integer weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Apple(Integer weight, String color, String city) {
        this.weight = weight;
        this.color = color;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
