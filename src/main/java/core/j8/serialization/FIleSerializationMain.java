package core.j8.serialization;

import java.io.*;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/15 17:37:51
 * @Description:
 */
public class FIleSerializationMain {
    public static void main(String[] args) throws Exception {
        ser();
        deser();
    }

    public static void ser() throws IOException {
        //
        User user = new User();
        user.setAge(20);
        user.setCity("Beijing");
        user.setName("张三");

        // 序列化对象并写入到对应文件
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.ser"));
        out.writeObject(user);
        out.close();
    }

    public static void deser() throws IOException, ClassNotFoundException {
        //

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.ser"));
        User u = (User) in.readObject();
        System.out.println(u);
        in.close();
    }
}
