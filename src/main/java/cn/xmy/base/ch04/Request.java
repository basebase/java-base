package cn.xmy.base.ch04;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/26 17:55:14
 * @Description:    请求类, 一个请求主体内容
 */
public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
