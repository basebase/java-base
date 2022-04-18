package cn.xmy.base.ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/18 11:37:19
 * @Description:    当前线程打印输出内容
 */
public class PrintPersonThread extends Thread{
    private Person person;

    public PrintPersonThread(Person person) {
        this.person = person;
    }

    int i = 1000;

    @Override
    public void run() {
        while (i > 0) {
            -- i;
            System.out.println(Thread.currentThread().getName() + " prints " + person);
        }
    }
}
