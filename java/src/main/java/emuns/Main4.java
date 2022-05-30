package emuns;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/27 14:28:26
 * @Description:    switch使用枚举
 */
public class Main4 {

    Signal color = Signal.GREEN;

    public void change() {

        // switch语句可以不需要枚举类.常量
        // 可以直接使用常量进行判断
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    @Override
    public String toString() {
        return "当前颜色: " + color;
    }

    public static void main(String[] args) {
        Main4 m = new Main4();
        System.out.println(m);
        m.change();
        System.out.println(m);
    }

}

enum Signal {
    GREEN, YELLOW, RED,
}
