package cn.xmy.base.singlethreadedexecution;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/9 15:08:55
 * @Description:
 */
public class PresonStringFuncMain {

    public static void main(String[] args) {
        Gate gate = new Gate();


        /***
         *
         *
         *          Gate类的toString方法如果不添加synchronized关键字, 外部的类很可能看到的就是不正确的数据
         *          当其它的类并发去调用toString方法, 此时用户A正在赋值userName字段, 而city字段还是上一次赋值的值
         *          那么读取出来时则会出错
         *
         *
         *                      Output:
         *                              Success: Gate{count=1, userName='zhangsan', city='zcx'}
         *                              → Gate toString: Gate{count=2, userName='wangwu', city='zcx'}
         *                              Success: Gate{count=2, userName='wangwu', city='wwx'}
         *                              → Gate toString: Gate{count=2, userName='wangwu', city='wwx'}
         *                              Success: Gate{count=3, userName='lisi', city='lx'}
         *                              → Gate toString: Gate{count=3, userName='lisi', city='lx'}
         */

        new Thread(() -> {
            try {
                gate.accessSync("zhangsan", "zcx");
                System.out.println("Gate toString: " + gate.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                gate.accessSync("lisi", "lx");
                System.out.println("Gate toString: " + gate.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                gate.accessSync("wangwu", "wwx");
                System.out.println("Gate toString: " + gate.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
