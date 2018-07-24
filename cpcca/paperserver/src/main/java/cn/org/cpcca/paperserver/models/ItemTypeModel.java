package cn.org.cpcca.paperserver.models;

import java.sql.Date;

public class ItemTypeModel {
    //id序号
    private int id;
    //类型名称
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
