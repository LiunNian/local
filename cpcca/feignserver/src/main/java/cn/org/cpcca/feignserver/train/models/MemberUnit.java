package cn.org.cpcca.feignserver.train.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "会员单位信息")
public class MemberUnit {
    @ApiModelProperty(value = "id", name = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "name", name = "单位名称", required = true, example = "单位名称")
    private String name;

    @ApiModelProperty(value = "address", name = "单位地址", required = true, example = "单位地址")
    private String address;

    @ApiModelProperty(value = "level", name = "级别", example = "级别")
    private Integer level;

    @ApiModelProperty(value = "stopDate", name = "到期时间", example = "到期时间")
    private Date stopDate;

    @ApiModelProperty(value = "status", name = "状态", example = "状态")
    private Integer status;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}