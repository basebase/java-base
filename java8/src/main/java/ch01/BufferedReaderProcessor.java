package ch01;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/20 14:28:57
 * @Description:
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
