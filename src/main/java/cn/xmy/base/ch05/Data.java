package cn.xmy.base.ch05;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @Author xiaomoyu
 * @Date: 2022/6/14 16:50:57
 * @Description:    数据类, 用于保存到文件
 */
public class Data {
    // 保存文件名称
    private final String filename;
    // 数据内容
    private String content;
    // 修改后的内容, 如未保存则为true
    private boolean changed;

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }


    // 修改数据内容
    public synchronized void change(String newContent) {
        this.content = newContent;
        changed = true;
    }

    // 如若数据内容修改过, 则保存到文件中
    public synchronized void save() throws IOException {
        if (!changed)
            return ;

        doSave();
        this.changed = false;
    }

    // 将数据内容保存到文件中
    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " 调用doSave, content = " + this.content);
        Writer writer = new FileWriter(filename);
        writer.write(content);
        writer.flush();
        writer.close();
    }


}
