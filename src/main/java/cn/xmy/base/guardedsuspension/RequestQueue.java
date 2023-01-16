package cn.xmy.base.guardedsuspension;

import java.util.Queue;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/11 15:11:45
 * @Description:
 */
public class RequestQueue<E> {

    private Queue<E> queue;

    public RequestQueue(Queue<E> queue) {
        this.queue = queue;
    }

    public int putRequest(E data) {

        // 如果直接在这里写会触发Server端永远陷入等待的问题, 假设当client-thread线程执行完if判断, 并将server-thread线程唤醒
        // 此时server-thread线程正好调用getRequest方法并且队列中没有数据进入等待状态,
        // 此时client-thread线程添加元素后再次判断发现队列中是否有元素, 发现有元素就不将server-thread线程唤醒, 所以server-thread线程一直阻塞
        // 解决方法: 1. 全局进行synchronized包裹, 这样其它线程没有锁就无法执行, 数据是一致的
        // 解决方法: 2. 只要调用该方法就进行唤醒, 无论队列是否为空
//        if (getSize() <= 0) {
//            // 唤醒线程
//            synchronized (queue) {
//                queue.notifyAll();
//            }
//        }


//        synchronized (queue) {
//            queue.notifyAll();
//        }

//        System.out.println(Thread.currentThread().getName() + " 执行完检查, 但是Server端Queue大小为: " + getSize() + " 导致不会在被唤醒");
        queue.add(data);
        synchronized (this) {
            this.notifyAll();
//            queue.notifyAll();
        }


        return queue.size();



    }

    public E getRequest() throws InterruptedException {

        synchronized (this) {
//        synchronized (queue) {
            // 如果队列中没有数据, 线程进入等待
            if (getSize() <= 0) {       // 守护条件
                System.out.println(Thread.currentThread().getName() + " 进入等待状态");
//                queue.wait();
                this.wait();
//                this.wait(1000);
                System.out.println(Thread.currentThread().getName() + " 被唤醒");
            }

            return queue.poll();
        }
    }

    public int getSize() {
        synchronized (queue) {
            return queue.size();
        }
    }


}
