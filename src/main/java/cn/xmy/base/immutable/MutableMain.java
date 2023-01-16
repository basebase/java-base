package cn.xmy.base.immutable;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/10 14:48:54
 * @Description:
 *
 * @see ImmutablePreson
 * @see MutablePreson
 */
public class MutableMain {
    public static void main(String[] args) {
        final MutablePreson mutablePreson = new MutablePreson("null", "null");


        new Thread(() -> {
            while (true) {
                ImmutablePreson immutablePreson = new ImmutablePreson(mutablePreson);
                if (!immutablePreson.getName().equals(immutablePreson.getAddress())) {
                    System.out.println(Thread.currentThread().getName() + " 非线程安全: " + immutablePreson);
                }
            }
        }, "t1").start();



        new Thread(() -> {
            while (true) {
                ImmutablePreson immutablePreson = new ImmutablePreson(mutablePreson);
                if (!immutablePreson.getName().equals(immutablePreson.getAddress())) {
                    System.out.println(Thread.currentThread().getName() + " 非线程安全: " + immutablePreson);
                }
            }
        }, "t2").start();

        new Thread(() -> {
            while (true) {
                ImmutablePreson immutablePreson = new ImmutablePreson(mutablePreson);
                if (!immutablePreson.getName().equals(immutablePreson.getAddress())) {
                    System.out.println(Thread.currentThread().getName() + " 非线程安全: " + immutablePreson);
                }
            }
        }, "t3").start();

        for (int i = 1; true; i++) {
            mutablePreson.setPreson("" + i, "" + i);
        }

    }
}
