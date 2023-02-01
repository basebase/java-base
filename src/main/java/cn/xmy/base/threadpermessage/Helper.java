package cn.xmy.base.threadpermessage;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 10:20:58
 * @Description:
 */
public class Helper {
    public void handle(int count, char c) {
        System.out.println("    handle(" + count + ", " + c + ") BEGIN" );
        for (int i = 0; i < count; i++) {
            slowly();
            System.out.print(c);
        }
        System.out.println("    handle(" + count + ", " + c + ") END" );
    }

    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
