package core.j8.proxy.dynamic;

import core.j8.proxy.UserInfo;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/14 16:14:52
 * @Description:
 */
public class UserInfoDynamicImpl implements UserInfo {

    @Override
    public void saveInfo(String info) {
        System.out.println(this + " 保存数据成功");
    }

    @Override
    public int reduce(int value1, int value2) {
        return value1 + value2;
    }
}
