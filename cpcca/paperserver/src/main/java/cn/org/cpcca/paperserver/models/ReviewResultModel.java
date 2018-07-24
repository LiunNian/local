package cn.org.cpcca.paperserver.models;

public class ReviewResultModel {
    private Integer  pexid;
    private String  item;
    private String  theme;
    private String  direction;
    private String  reitem;
    private String  title;
    private String  score;
    private String  comment;
    private String  username;
    private String company;
    private String  authors;

    public Integer getPexid() {
        return pexid;
    }

    public void setPexid(Integer pexid) {
        this.pexid = pexid;
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

    public String getReitem() {
        return reitem;
    }

    public void setReitem(String reitem) {
        this.reitem = reitem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
