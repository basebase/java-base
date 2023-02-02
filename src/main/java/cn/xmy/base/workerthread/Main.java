package cn.xmy.base.workerthread;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 15:33:48
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        // 工人线程数
        Channel channel = new Channel(5);
        channel.startWorkers();

        new ClientThread("张三", channel).start();
        new ClientThread("李四", channel).start();
        new ClientThread("王五", channel).start();
    }
}
