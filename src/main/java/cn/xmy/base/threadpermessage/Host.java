package cn.xmy.base.threadpermessage;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 10:20:38
 * @Description:
 */
public class Host {

    private final Helper helper = new Helper();


    // 来一个请求, 创建一个线程处理
    public void request(int count, char c) {
        System.out.println("    request(" + count + ", " + c + ") BEGIN" );

        new Thread(() -> {
            helper.handle(count, c);
        }).start();

        System.out.println("    request(" + count + ", " + c + ") END" );
    }
}
