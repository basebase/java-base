package rpc.hadoop.v1.io;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/16 14:09:51
 * @Description:
 */
public interface WritableFactory {
    Writable newInstance();
}
