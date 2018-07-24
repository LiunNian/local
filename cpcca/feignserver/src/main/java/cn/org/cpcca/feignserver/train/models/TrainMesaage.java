package cn.org.cpcca.feignserver.train.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "培训课程信息")
public class TrainMesaage {
    @ApiModelProperty(value = "id", name = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "classes", name = "班次", required = true, example = "班次")
    private String classes;

    @ApiModelProperty(value = "intro", name = "课程介绍", required = true, example = "课程介绍")
    private String intro;

    @ApiModelProperty(value = "level", name = "预报名/正式报名", example = "预报名/正式报名")
    private Integer level;

    @ApiModelProperty(value = "startDate", name = "开始时间", required = true, example = "开始时间")
    private Date startDate;

    @ApiModelProperty(value = "stopDate", name = "结束时间", required = true, example = "结束时间")
    private Date stopDate;

    @ApiModelProperty(value = "imgpath", name = "上传照片", required = true, example = "上传照片")
    private String imgpath;

    @ApiModelProperty(value = "price", name = "课程价格", hidden = true)
    private Double price;

    @ApiModelProperty(value = "memberprice", name = "会员价格", hidden = true)
    private Double memberprice;

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
}