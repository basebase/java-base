package cn.xmy.base.workerthread;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/1 15:34:19
 * @Description:
 */
public class Channel {

    private final static int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    private final WorkerThread[] threadPool;

    private int tail;       // 下次putRequest位置
    private int head;      // 下次takeRequest位置
    private int count;    // Request数量



    public Channel(int threads) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.tail = 0;
        this.head = 0;
        this.count = 0;

        this.threadPool = new WorkerThread[threads];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    public void startWorkers() {
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
    }

    public synchronized void putRequest(Request request) {
        while (count >= requestQueue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count ++;
        notifyAll();
    }

    public synchronized Request takeReuqest() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Request request = requestQueue[head];
        requestQueue[head] = null;
        head = (head + 1) % requestQueue.length;
        count --;
        notifyAll();
        return request;
    }
}
