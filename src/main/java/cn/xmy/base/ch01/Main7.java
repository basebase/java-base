package cn.xmy.base.ch01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/3 15:45:05
 * @Description:    启动生产消费程序
 */
public class Main7 {
    public static void main(String[] args) {
        Object lock = new Object();
        List<String> datas = new ArrayList();

        Product p = new Product(lock, datas);
        Consumption c = new Consumption(lock, datas);

        Thread productThread = new Thread(p, "p");
        Thread consumpointThread = new Thread(c, "c");

        productThread.start();
        consumpointThread.start();
    }
}
