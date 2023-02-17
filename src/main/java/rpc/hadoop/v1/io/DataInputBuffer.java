package rpc.hadoop.v1.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/16 14:07:02
 * @Description:
 */
public class DataInputBuffer extends DataInputStream {

    private static class Buffer extends ByteArrayInputStream {
        public Buffer() {
            super(new byte[] {});
        }

        public void reset(byte[] input, int start, int length) {
            this.buf = input;
            this.count = start+length;
            this.mark = start;
            this.pos = start;
        }

        public int getPosition() { return pos; }
        public int getLength() { return count; }
    }

    private Buffer buffer;

    /** Constructs a new empty buffer. */
    public DataInputBuffer() {
        this(new Buffer());
    }

    private DataInputBuffer(Buffer buffer) {
        super(buffer);
        this.buffer = buffer;
    }

    /** Resets the data that the buffer reads. */
    public void reset(byte[] input, int length) {
        buffer.reset(input, 0, length);
    }

    /** Resets the data that the buffer reads. */
    public void reset(byte[] input, int start, int length) {
        buffer.reset(input, start, length);
    }

    /** Returns the current position in the input. */
    public int getPosition() { return buffer.getPosition(); }

    /** Returns the length of the input. */
    public int getLength() { return buffer.getLength(); }

}
