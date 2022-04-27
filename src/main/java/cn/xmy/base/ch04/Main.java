package cn.xmy.base.ch04;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/26 18:05:36
 * @Description:    处理服务请求
 */
public class Main {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "client", 3141592L).start();
        new ServerThread(requestQueue, "server", 6535897L).start();
    }
}
