package core.j8.proxy.statics;

import core.j8.proxy.UserInfo;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/14 16:05:57
 * @Description:
 */
public class UserInfoStaticImpl implements UserInfo {

    @Override
    public void saveInfo(String info) {
        // 实际的实现类
        System.out.println(this + " 保存成功");
    }

    @Override
    public int reduce(int value1, int value2) {
        return value1 + value2;
    }

}
