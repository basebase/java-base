package cn.xmy.base.ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/18 18:24:40
 * @Description:
 */
public class PrintUserInfoThread extends Thread{
    private UserInfo userInfo;

    public PrintUserInfoThread(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    int i = 1000;

    @Override
    public void run() {
        while (i > 0) {
            -- i;

            // 这里可以修改StringBuff对象实例状态, 虽然没有通过setter修改, 但获取到当前对象实例进行修改
            userInfo.getInfo().append("wuqing");
            System.out.println(Thread.currentThread().getName() + " prints " + userInfo);
        }
    }
}
