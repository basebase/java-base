package cn.xmy.base.readwritelock;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/31 13:59:14
 * @Description:
 */
public class ReadWriteLock {

    // 执行readLock的线程数, 但还未执行readUnlock
    private int readingReaders;

    // 执行到writeLock, 但是要等待的线程数
    private int waitingWriters;

    // 实际写入的线程数, 真正获取到writeLock的线程
    private int writingWriters;

    private boolean preferWriter = true;



    public synchronized void readLock() throws InterruptedException {
        // 如果没有preferWriter和waitingWriters则完全就不考虑写锁
        // 假设当一个线程读取时, 由于读锁可以共享其它N个线程也来读, 这样写锁就无法获取到机会, 导致一直在读取数据
        // preferWriter和waitingWriters其实可以看成一种策略, 当线程一直在读时, 就需要有一种机制来进行破坏, 让用户获取到写锁
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0))
            wait();

        readingReaders ++;
    }

    public synchronized void readUnlock() {
        readingReaders --;
        preferWriter = true;
        // 唤醒所有等待的线程
        notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {

        waitingWriters ++;

        try {
            while (readingReaders > 0 || writingWriters > 0)
                wait();
        } finally {
            waitingWriters --;
        }

        writingWriters ++;

    }

    public synchronized void writeUnlock() {
        writingWriters --;
        preferWriter = false;
        notifyAll();
    }

//    @Override
//    public String toString() {
//        return "ReadWriteLock{" +
//                "readingReaders=" + readingReaders +
//                ", waitingWriters=" + waitingWriters +
//                ", writingWriters=" + writingWriters +
//                ", preferWriter=" + preferWriter +
//                '}';
//    }
}
