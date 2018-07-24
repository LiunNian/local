package cn.org.cpcca.paperserver.models;

//主题方向
public class DirectionModel {
    //id序号
    private int id;
    //主题关联
    private int thid;
    //名称
    private String title;
    //描述
    private String description="";
    //创建时间
    private String ctime;
    //状态
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThid() {
        return thid;
    }

    public void setThid(int thid) {
        this.thid = thid;
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

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
