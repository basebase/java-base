package cn.xmy.base.ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/18 11:38:48
 * @Description:    启动多个线程同时去读取Person数据
 */
public class Main {
    public static void main(String[] args) {
        Person p = new Person("zhangsan", "beijing");
        new PrintPersonThread(p).start();
        new PrintPersonThread(p).start();
        new PrintPersonThread(p).start();
    }
}
