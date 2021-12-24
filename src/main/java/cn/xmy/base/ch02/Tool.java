package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2021/12/23 17:52:17
 * @Description:    餐具类
 */
public class Tool {
    private final String name;

    public Tool(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return " [ " + name + " ] ";
    }
}
