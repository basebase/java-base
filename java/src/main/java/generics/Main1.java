package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/23 14:23:28
 * @Description:
 */
public class Main1 {
    public static void main(String[] args) {
        List<? extends RedColor> colors = null;
        colors = new ArrayList<PinkColor>();

        // 不能添加数据
//        colors.add(new PinkColor());

        colors = Arrays.asList(
                new LightRedColor(),
                new PinkColor(),
                new ReddishColor(),
                new RedColor()
                // new Color()      // 不能超越了父类类型
        );

        RedColor c1 = colors.get(0);
        RedColor c2 = colors.get(1);
        RedColor c3 = colors.get(2);
        RedColor c4 = colors.get(3);


        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);



        List<? super PinkColor> pinks =
                new ArrayList<RedColor>();

        pinks.add(new PinkColor());
        pinks.add(new LightRedColor());

        // 只能添加其子类
//        pinks.add(new RedColor());


    }
}
