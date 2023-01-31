package cn.xmy.base.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/31 15:22:32
 * @Description:
 */
public class ReentrantReadWriteLockMain {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


//        new Thread(() -> {
//            readLock.lock();
//            try {
//                System.out.println(Thread.currentThread().getName() + " read ing...");
//                Thread.sleep(800);
//                System.out.println(Thread.currentThread().getName() + " read end...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                readLock.unlock();
//            }
//        }, "read-01").start();
//
//
//
//
//        new Thread(() -> {
//            readLock.lock();
//            try {
//                System.out.println(Thread.currentThread().getName() + " read ing...");
//                Thread.sleep(200);
//                System.out.println(Thread.currentThread().getName() + " read end...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                readLock.unlock();
//            }
//        }, "read-02").start();
//
//        new Thread(() -> {
//            writeLock.lock();
//            try {
//                System.out.println(Thread.currentThread().getName() + " write data...");
//                Thread.sleep(1200);
//                System.out.println(Thread.currentThread().getName() + " write end...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                writeLock.unlock();
//            }
//        }, "write-01").start();
//
//
//        new Thread(() -> {
//            writeLock.lock();
//            try {
//                System.out.println(Thread.currentThread().getName() + " write data...");
//                Thread.sleep(400);
//                System.out.println(Thread.currentThread().getName() + " write end...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                writeLock.unlock();
//            }
//        }, "write-02").start();




        ReadWriteLock lock = new ReadWriteLock();
        new Thread(() -> {
            try {
                lock.readLock();
                System.out.println(Thread.currentThread().getName() + " read ing...");
                Thread.sleep(800);
                System.out.println(Thread.currentThread().getName() + " read end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readUnlock();
            }
        }, "read-01").start();


        new Thread(() -> {
            try {
                lock.readLock();
                System.out.println(Thread.currentThread().getName() + " read ing...");
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + " read end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readUnlock();
            }
        }, "read-02").start();

        new Thread(() -> {
            try {
                lock.writeLock();
                System.out.println(Thread.currentThread().getName() + " write data...");
                Thread.sleep(1200);
                System.out.println(Thread.currentThread().getName() + " write end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeUnlock();
            }
        }, "write-01").start();


        new Thread(() -> {

            try {
                lock.writeLock();
                System.out.println(Thread.currentThread().getName() + " write data...");
                Thread.sleep(400);
                System.out.println(Thread.currentThread().getName() + " write end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeUnlock();
            }
        }, "write-02").start();

    }
}
