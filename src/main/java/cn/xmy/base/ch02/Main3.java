package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/23 17:52:03
 * @Description:    餐具死锁问题
 */
public class Main3 {
    public static void main(String[] args) {
        Tool spoon = new Tool("Spoon");
        Tool fork = new Tool("Fork");
        new EaterThread("zhangsan", spoon, fork).start();
        new EaterThread("lisi", fork, spoon).start();
    }
}
