package cn.org.cpcca.paperserver.models;

public class ReviewPaperInfoModel {

    private int pexid;
    private int revpid;
    private int revid;
    private int id;
    private String title;
    private String subtitle;
    private String summary;
    private String keyword;
    private String content;
    private String reference;
    private String direction;
    private String theme;
    private String item;
    private String filename = "";
    private String uri = "";
    private String authors = "";

    public int getPexid() {
        return pexid;
    }

    public void setPexid(int pexid) {
        this.pexid = pexid;
    }

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
