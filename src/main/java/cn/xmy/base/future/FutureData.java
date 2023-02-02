package cn.xmy.base.future;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/2 14:29:52
 * @Description:
 */
public class FutureData implements Data {
    private RealData realData;
    private boolean ready = false;


    public synchronized void setRealData(RealData realData) {
        //
        if (ready)
            return ;

        this.realData = realData;
        this.ready = true;
        notifyAll();
    }

    @Override
    public synchronized String getContent() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getContent();
    }
}
