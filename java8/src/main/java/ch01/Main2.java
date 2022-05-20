package ch01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/20 14:22:56
 * @Description:
 */
public class Main2 {

    /***
     *
     *      在不使用lambda表达式我只能返回一条数据, 如果要返回多条或者高频词句统计就有点力不从心
     */
    public static String processFile() throws FileNotFoundException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("/Users/xiaomoyu/a.csv"))) {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /***
     *
     *
     * 将行为参数化,  对于不同行为可以传递不同的函数接口来实现
     *
     *
     */
    public static String processFile(BufferedReaderProcessor p) throws FileNotFoundException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("/Users/xiaomoyu/a.csv"))) {
            // 用户行为参数化, 传递的接口实际上是一个行为, 可以运行想要的动作
            return p.process(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(processFile());


        BufferedReaderProcessor processor = ((BufferedReader br) -> {
            return br.readLine() + "\n" + br.readLine();
        });
        System.out.println(processFile(processor));
    }
}
