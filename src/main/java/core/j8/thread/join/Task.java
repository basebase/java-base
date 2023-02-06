package core.j8.thread.join;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/6 16:16:06
 * @Description:
 */
public interface Task<T, R> extends Runnable {
    public R executor();
    public void setThread(Thread thread);
}
