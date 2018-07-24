package cn.org.cpcca.paperserver.models;

public class ReviewPaperListModel {
    private int revpid;
    private int revid;
    private int id;
    private String title;
    private String subtitle;
    private String item;
    private String theme;
    private String direction;
    private String mark;
    private Integer score;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
