package cn.xmy.base.threadpermessage;

import java.io.IOException;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 14:46:57
 * @Description:
 */
public class HttpServiceMain {
    public static void main(String[] args) throws IOException {
        new MiniServer(8888).execute();
    }
}
