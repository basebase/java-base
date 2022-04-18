package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/12 14:16:26
 * @Description:
 */
public class Main3 {
    public static void main(String[] args) {
        Tool spoon = new Tool("Spoon");
        Tool fork = new Tool("Fork");
        new EaterThread("zs", spoon, fork).start();
        new EaterThread("ls", fork, spoon).start();
    }
}
