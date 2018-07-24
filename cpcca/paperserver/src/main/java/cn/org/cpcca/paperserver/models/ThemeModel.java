package cn.org.cpcca.paperserver.models;

import java.sql.Date;

/**
 * 主题
 */
public class ThemeModel {
    //id序号
    private int id;
    //项目关联序号
    private int itid;
    //主题名称
    private String title;
    //描述
    private String description;
    //创建时间
    private Date ctime;
    //状态
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItid() {
        return itid;
    }

    public void setItid(int itid) {
        this.itid = itid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
