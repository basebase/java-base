package cn.xmy.base.future;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/2 14:29:20
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main BEGIN");
        Host host = new Host();

        Data data1 = host.request(10, 'A');
        Data data2 = host.request(20, 'B');
        Data data3 = host.request(30, 'C');

        System.out.println("Main Slepp 2s BEGIN");
        Thread.sleep(2000);
        System.out.println("Main Slepp 2s END");


        System.out.println("data1 = " + data1.getContent());
        System.out.println("data2 = " + data2.getContent());
        System.out.println("data3 = " + data3.getContent());

        System.out.println("Main END");
    }
}
