package cn.org.cpcca.paperserver.models;

import org.apache.commons.lang.StringUtils;

import java.sql.Date;
import java.util.List;

/**
 * 论文
 */
public class PaperModel {
    //id序号
    private int id;
    //账号关联
    private int uid;
    //方向关联
    private int did;
    //论文关联
    private int fid;
    //证明关联
    private String prove;
    //作者
    private String auids = "";
    //请输入论文题目
    private String title = "";
    //请输入论文副标题
    private String subtitle = "";
    //摘要
    private String summary = "";
    //关键词
    private String keyword = "";
    //论文内容
    private String content = "";
    //参考文献
    private String reference = "";
    //创建时间
    private Date ctime ;
    //状态
    private int state = 1;
    //编辑状态
    private  int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getProve() {
        return prove;
    }

    public void setProve(String prove) {
        this.prove = prove;
    }

    public String getAuids() {
        return auids;
    }

    public void setAuids(String auids) {
        this.auids = auids;
    }
    public void setAuids(List<Integer> auids) {
        this.auids = StringUtils.join(auids,",");
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
