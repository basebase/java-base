package emuns;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/27 14:11:24
 * @Description:
 */

// 静态导入某一个常量
import static emuns.SpicinessEnum.NOT;
// 导入所有常量
import static emuns.SpicinessEnum.*;
public class Main2 {

    private SpicinessEnum spicinessEnum;

    public Main2(SpicinessEnum spicinessEnum) {
        this.spicinessEnum = spicinessEnum;
    }

    @Override
    public String toString() {
        return "Main2{" +
                "spicinessEnum=" + spicinessEnum +
                '}';
    }

    public static void main(String[] args) {

        // 静态导入后, 直接使用枚举常量
        System.out.println(new Main2(NOT));
        System.out.println(new Main2(HOT));

        // 非静态导入需要枚举类.常量
        System.out.println(new Main2(SpicinessEnum.FLAMING));


    }
}
