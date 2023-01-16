package cn.xmy.base.immutable;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/10 09:31:16
 * @Description:
 */
public class PresonMain {
    public static void main(String[] args) {
        /**
         * 由于Preson类没有提供setting修改的方法, 所以我们多个线程调用无需使用synchronized来保护某个状态
         * 而且字段都是使用preivate进行修饰, 外部类无法获取而且类声明也使用final修饰这样也不会出现子类的问题
         */

        Preson preson = new Preson("张三", "杭州");
        new PrintPresonThread("t1", preson).start();
        new PrintPresonThread("t2", preson).start();
        new PrintPresonThread("t3", preson).start();
    }
}
