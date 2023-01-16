package cn.xmy.base.guardedsuspension;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 10:15:16
 * @Description:
 */
public class TalkThread extends Thread {
    private final RequestQueue<String> input;
    private final RequestQueue<String> output;

    public TalkThread(RequestQueue<String> input, RequestQueue<String> output, String name) {
        super(name);
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":BEGIN");
        for (int i = 0; i < 20; i++) {
            // 接收对方请求
            try {

                // 由于队列中没有元素, 会让线程进入阻塞状态。无法等到状态的更新, 所以线程无法被唤醒
                // 1. 调用顺序的替换, 先调用put然后再调用get
                // 2. wait超时等待
                // 3. mian中优先put数据

                String request = input.getRequest();
                System.out.println(Thread.currentThread().getName() + " gets " + request);

                // 加上!返回
                String response = request + "!";
                System.out.println(Thread.currentThread().getName() + " puts " + response);
                output.putRequest(response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + ":END");
    }
}
