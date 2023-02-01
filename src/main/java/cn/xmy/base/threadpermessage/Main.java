package cn.xmy.base.threadpermessage;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 10:20:31
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Host host = new Host();
        System.out.println("main BEGIN");
        host.request(10, 'A');
        host.request(20, 'B');
        host.request(30, 'C');
        System.out.println("main END");
    }
}
