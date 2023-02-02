package cn.xmy.base.future;

/**
 * @Author xiaomoyu
 * @Date: 2023/2/2 14:30:09
 * @Description:
 */
public class RealData implements Data {

    private String content;

    public RealData(int count, char c) {
        char[] buff = new char[count];
        for (int i = 0; i < count; i++) {
            buff[i] = c;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.content = String.valueOf(buff);
    }

    @Override
    public String getContent() {
        return content;
    }
}
