package cn.org.cpcca.paperserver.models;

import java.sql.Timestamp;
import java.util.List;

public class ReviewPaperModel {
    private int revpid;
    private int revid;
    private int id;
    private String title;
    private String item;
    private String theme;
    private String direction;
    private String progress="";
    private String result="";
    private String experts="";
    private Timestamp ctime;

    public int getRevpid() {
        return revpid;
    }

    public void setRevpid(int revpid) {
        this.revpid = revpid;
    }

    public int getRevid() {
        return revid;
    }

    public void setRevid(int revid) {
        this.revid = revid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExperts() {
        return experts;
    }

    public void setExperts(String experts) {
        this.experts = experts;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }
}
