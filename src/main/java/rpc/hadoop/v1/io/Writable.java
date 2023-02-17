package rpc.hadoop.v1.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/16 14:03:19
 * @Description:
 */
public interface Writable {
    void write(DataOutput out) throws IOException;
    void readFields(DataInput in) throws IOException;
}
