package com.paper.train.model;

public class AddTrainUser {
    private Integer id;

    private String name;

    private String phonenum;

    private String password;

    private String pwd;

    private String unitname;

    private String jobtitle;

    private Integer status;

    private String verify;

    public AddTrainUser(TrainUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phonenum = user.getPhonenum();
        this.password = user.getPassword();
        this.unitname = user.getUnitname();
        this.jobtitle = user.getJobtitle();
        this.status = user.getStatus();
        this.verify = user.getVerify();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname == null ? null : unitname.trim();
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle == null ? null : jobtitle.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}