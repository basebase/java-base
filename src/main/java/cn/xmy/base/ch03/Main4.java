package cn.xmy.base.ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/19 16:51:31
 * @Description:    由于Line类中使用Point类构造方法, 而Point类字段是public可以修改从而可以破坏Line类的Immutable
 *
 *
 *                 如果想让Line称为Immutable可以有以下方法
 *                      1. 设置Point类字段为final
 *                      2. 设置Line的Point构造函数
 */
public class Main4 {
    public static void main(String[] args) throws InterruptedException {
        Point x = new Point(1, 1);
        Point y = new Point(2, 2);

        Line line = new Line(x, y);

        new PrintLineThread(line).start();
        new PrintLineThread(line).start();
        new PrintLineThread(line).start();

        Thread.sleep(30);

        // 先让主线程进入休眠状态, 后面我们在修改Point对象的值也就破坏了Line对象的Immutable模式
        x.x = 101;
        x.y = 102;

    }
}

class PrintLineThread extends Thread {
    private Line line;

    private Point x;
    private Point y;

    public PrintLineThread(Line line) {
        this.line = line;
    }

    public PrintLineThread(Line line, Point x, Point y) {
        this.line = line;
        this.x = x;
        this.y = y;
    }

    int i = 1000;

    @Override
    public void run() {
        while (i > 0) {
            --i;
//            x.x = 111;
//            x.y = 222;
            System.out.println(Thread.currentThread().getName() + " prints " + line);
        }
    }
}
