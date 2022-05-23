package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/23 18:00:31
 * @Description:
 */

public class Main2 {

    public static void main(String[] args) {
        List<? super Integer> list = null;
        List<? extends Integer> list1 = null;

        // 这里不会出错
        // 这种初始化的情况为什么不会存在向下转型的问题?
        // 应该编译的时候类型就可以推断出来, 而add属于动态的, 故而这里可以检查
        list = Arrays.asList(100, new Object());
        // 这里会出错
        list1 = Arrays.asList(100 /*, new Object()*/);



        list.add(100);

        // 这里会出错, 这里直接add会存在向下转型的问题
//        list.add(new Object());
    }
}
