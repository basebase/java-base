package cn.xmy.base.readwritelock;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/31 13:58:35
 * @Description:
 */
public class Data {

    private final ReadWriteLock lock;
    private final char[] buffer;

    public Data(int buffSize) {
        this.lock = new ReadWriteLock();
        this.buffer = new char[buffSize];
        for (int i = 0; i < buffSize; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        lock.readLock();
        System.out.println(Thread.currentThread().getName() + " lock info: " + lock);
        try {
            return doRead();
        } finally {
            lock.readUnlock();
        }
    }

    private char[] doRead() throws InterruptedException {
        char[] newBuff = new char[buffer.length];
        for (int i = 0; i < newBuff.length; i++) {
            newBuff[i] = buffer[i];
        }

        slowly();
        return newBuff;
    }

    public void write(char c) throws InterruptedException {
        lock.writeLock();
        System.out.println(Thread.currentThread().getName() + " lock info: " + lock);
        try {
            doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) throws InterruptedException {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly();
        }
    }


    public void slowly() throws InterruptedException {
        Thread.sleep(50);
    }
}
