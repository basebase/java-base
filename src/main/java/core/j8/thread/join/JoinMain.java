package core.j8.thread.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/6 16:24:22
 * @Description:
 */
public class JoinMain {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main程序开始");

        List<Integer> taskDataList1 = Stream.of(1, 2, 3, 4)
                .collect(Collectors.toList());
        List<Integer> taskDataList2 = Stream.of(5, 6, 7, 8)
                .collect(Collectors.toList());
        List<Integer> taskDataList3 = Stream.of(9, 10, 11, 12)
                .collect(Collectors.toList());

        Task task1 = new JoinTask(taskDataList1);
        Task task2 = new JoinTask(taskDataList2);
        Task task3 = new JoinTask(taskDataList3);

        Thread taskThread1 = new Thread(task1, "T-1");
        Thread taskThread2 = new Thread(task2, "T-2");
        Thread taskThread3 = new Thread(task3, "T-3");

        // 设置等待
        task2.setThread(taskThread1);
        task3.setThread(taskThread2);

        taskThread1.start();
        taskThread2.start();
        taskThread3.start();

//        taskThread1.join();
//        taskThread2.join();
        taskThread3.join();

        System.out.println("Main程序结束");
    }
}
