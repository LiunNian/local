package com.paper.train.model;

import java.util.Date;

public class TrainMesaage {
    private Integer id;

    private String classes;

    private String intro;

    private Integer level;

    private Date startDate;

    private Date stopDate;

    private String address;

    private String imgpath;

    private Double price;

    private Double memberprice;

    private String obj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath == null ? null : imgpath.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMemberprice() {
        return memberprice;
    }

    public void setMemberprice(Double memberprice) {
        this.memberprice = memberprice;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj == null ? null : obj.trim();
    }
}