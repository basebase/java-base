package cn.xmy.base.singlethreadedexecution;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/7 15:54:58
 * @Description:
 */
public enum PresonEnum {

    ZHANGSAN("张三", "北京"),
    LISI("李四", "上海"),
    WANGWU("王五", "杭州");

    private String userName;
    private String city;

    PresonEnum(String userName, String city) {
        this.userName = userName;
        this.city = city;
    }

    public boolean presonInfoIsEq() {
        for (PresonEnum value : values()) {
            if (value.userName.equals(this.userName) &&
                value.city.equals(this.city)) {
                return true;
            }
        }

        return false;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserName() {
        return userName;
    }

    public String getCity() {
        return city;
    }
}
