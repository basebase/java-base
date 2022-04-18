package cn.xmy.base.ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/18 18:23:57
 * @Description:
 */
public class Main3 {
    public static void main(String[] args) {
        UserInfo u = new UserInfo("zhangsan", "beijing");
        new PrintUserInfoThread(u).start();
        new PrintUserInfoThread(u).start();
    }
}
