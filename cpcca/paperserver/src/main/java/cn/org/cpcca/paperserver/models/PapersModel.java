package cn.org.cpcca.paperserver.models;

import java.util.List;
import java.util.Map;

public class PapersModel extends  PaperModel{
    //请输入作者姓名
    private List<String> author;
    //参考文献
    private List<String> reference;
    //文件
    private Map<String,String> files;

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public void setReference(List<String> reference) {
        this.reference = reference;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public void setFiles(Map<String, String> files) {
        this.files = files;
    }
}
