package cn.xmy.base.future;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/2 14:29:26
 * @Description:
 */
public class Host {

    public Data request(int count, char c) {
        System.out.println("      request("+ count + ", " + c + ") BEGIN");
        FutureData futureData = new FutureData();
        new Thread(() -> {
            try {
                // 前置处理
                Thread.sleep(300);
                RealData realData = new RealData(count, c);
                futureData.setRealData(realData);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("      request("+ count + ", " + c + ") END");
        return futureData;
    }
}
