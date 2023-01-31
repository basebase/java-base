package cn.xmy.base.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/31 17:08:34
 * @Description:
 */
public class DatabaseMain {
    public static void main(String[] args) {
        Database<String, Integer> database = new Database();
        new Thread(() -> {
            try {
                int index = 1;
                while (true) {
                    database.assign("test", index ++);
                    Thread.sleep(30);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "puts").start();

        new Thread(() -> {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + ": " + database.get("test"));
                    Thread.sleep(25);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "gets").start();
    }
}

class Database<K, V> {
    private final Map<K, V> map = new HashMap();

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 全部清除
    public /* synchronized */ void clear() {
        readWriteLock.writeLock().lock();
        try {
            verySlowly();
            map.clear();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    // 设置value
    public /* synchronized */ void assign(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            verySlowly();
            map.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    // 获取value
    public /* synchronized */ V get(K key) {

        readWriteLock.readLock().lock();
        try {
            slowly();
            return map.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    // 耗时操作
    private void slowly() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 非常耗时操作
    private void verySlowly() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
