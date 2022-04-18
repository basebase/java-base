package cn.xmy.base.ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/18 17:51:27
 * @Description:    比较synchronized修饰的方式和非synchronized修饰方法耗时比较
 *                  循环执行10亿次, 非synchronized是synchronized方法的3.5倍, 而且里面还没有涉及到过于复杂的计算逻辑
 *
 *                  该例子主要用于说明synchronized方法和非synchronized方法之间的性能比较
 */
public class Main2 {

    private static final long CALL_COUNT = 1000000000L;

    private static void trial(String msg, long count, Object obj) {
        System.out.println(msg + ": BEGIN");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < CALL_COUNT; i++) {
            obj.toString();
        }
        System.out.println(msg + ": END");
        System.out.println("Elapsed time = " + (System.currentTimeMillis() - startTime) + " msec.");
    }

    public static void main(String[] args) {
        trial("NotSync", CALL_COUNT, new NotSync());
        trial("Sync", CALL_COUNT, new Sync());
    }
}

class NotSync {
    private final String name = "NotSync";

    @Override
    public String toString() {
        return "NotSync{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Sync {
    private final String name = "sync";

    @Override
    public synchronized String toString() {
        return "Sync{" +
                "name='" + name + '\'' +
                '}';
    }
}
