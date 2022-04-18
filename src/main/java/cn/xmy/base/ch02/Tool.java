package cn.xmy.base.ch02;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/12 14:15:23
 * @Description:
 */
public class Tool {

    private final String name;

    public Tool(String name) {
        this.name = name;
    }

    public String toString() {
        return " [ " + name + " ] ";
    }
}
