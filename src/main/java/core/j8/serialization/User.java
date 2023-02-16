package core.j8.serialization;

import java.io.Serializable;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/15 17:36:55
 * @Description:      序列化的对象
 */
public class User implements Serializable {

    private String name;
    private Integer age;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}
