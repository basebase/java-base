package emuns;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/27 13:56:33
 * @Description:
 */
public class Main1 {
    public static void main(String[] args) {
        for (MyEnum myEnum : MyEnum.values()) {
            System.out.println(myEnum + " 序列: " + myEnum.ordinal());
            System.out.println(myEnum.name());
        }
    }
}

enum MyEnum {
    GROUND, HAVING, COUNT
}
