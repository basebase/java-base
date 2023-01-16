package cn.xmy.base.balking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author xiaomoyu
 * @Date: 2023/1/15 11:10:06
 * @Description:
 */
public class Data {

    private final String fileName;
    private String content;
    private boolean changed;

    public Data(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent) {
        // synchronized保护的实际上就是这两个字段值
        if (!this.equals(newContent)) {
            this.changed = true;
            this.content = newContent;
        }
    }

    public synchronized void save() throws IOException {
        // 这里需要synchronized保护, 看到最新的changed值
        if (changed) {
            doSave();
            changed = false;
        }
    }

    private void doSave() throws IOException {
        // 保存内容到文件
        System.out.println(Thread.currentThread().getName() + " calls doSave, content = " + content);
        FileWriter fw = new FileWriter(new File(fileName));
        fw.write(this.content);
        fw.close();
    }

}
