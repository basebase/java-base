package cn.xmy.base.singlethreadedexecution;

import java.util.Arrays;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/7 15:49:39
 * @Description:
 */
public class Gate {

    private int count ;
    private PresonEnum presonEnum;

    private String userName;
    private String city;

    private boolean flag = true;


    private boolean check() {
        if (userName.charAt(0) != city.charAt(0)) {
            System.out.println("Error: " + this);
            flag = false;
        } else {
            System.out.println("Success: " + this);
        }

        return flag;
    }


    /**
     *      非线程安全方法
     *          Output:
     *                  Error: Gate{count=3, userName='hangwang', city='beijing'}
     *                  Error: Gate{count=3, userName='hangwang', city='shanghai'}
     *                  Success: Gate{count=3, userName='hangwang', city='hangzhou'}
     *         1. 用户的姓名全部变为同一个人, 这样只有一条数据"或许"是正确的
     *         2. count的计数值直接变为3, 并没有预期的顺序输出
     */
    public boolean access(String userName, String city) throws InterruptedException {
        ++ count;
        this.userName = userName;

        if (Thread.currentThread().getName().equals("Thread-0"))
            Thread.sleep(100);
        else if (Thread.currentThread().getName().equals("Thread-1"))
            Thread.sleep(105);
        else
            Thread.sleep(110);

        this.city = city;
        return check();
    }

    /***
     *             线程安全方法
     *                      Output:
     *                              Success: Gate{count=1, userName='beisan', city='beijing'}
     *                              Success: Gate{count=2, userName='hangwang', city='hangzhou'}
     *                              Success: Gate{count=3, userName='shangqi', city='shanghai'}
     *
     *                      用户的姓名和地址信息都可以对上, 并且count值也是按照顺序推进
     *
     *
     *
     *             PS: 这里有人把toString方法也加上synchronized关键字, 但是我觉得没有必要
     *                 因为这是直接在整个方法上加, 而并非是部分, 如果是synchronized部分则toString则需要加上
     *
     */
    public synchronized boolean accessSync(String userName, String city) throws InterruptedException {
        this.count ++;
        this.userName = userName;
        if (Thread.currentThread().getName().equals("Thread-0"))
            Thread.sleep(100);
        else if (Thread.currentThread().getName().equals("Thread-1"))
            Thread.sleep(105);
        else
            Thread.sleep(110);
        this.city = city;
        return check();
    }


    /***
     *              toString方法如果不加synchronized关键字是否真的没有问题
     *              @see PresonStringFuncMain       参考该测试类, 是否需要添加synchronized关键字
     * */
    @Override
    public String toString() {
        return "Gate{" +
                "count=" + count +
                ", userName='" + userName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


}
