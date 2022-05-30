package emuns;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/27 14:19:52
 * @Description:    枚举添加方法
 */
public class Main3 {
    public static void main(String[] args) {
        for (StatusEnum status : StatusEnum.values()) {
            System.out.println(status + ": " + status.getStatusName());
        }
    }
}

enum StatusEnum {

    // 先定义变量或者方法都会报错
//    private String statusName;

    // 先初始化实例
    START("启动程序"),
    SLEEP("程序休眠"),
    FAILD("程序失败"),
    END("程序结束");


    private String statusName;

    // 访问符是package或者private
    StatusEnum(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
