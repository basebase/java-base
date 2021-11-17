package cn.xmy.base.ch01;

/**
 * @Author xiaomoyu
 * @Date: 2021/11/17 16:01:05
 * @Description:   通过Thread类实现线程输出指定字符
 */
public class PrintThread extends Thread {
    private String message;

    public PrintThread() {
    }

    public PrintThread(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(message);
        }
    }
}
