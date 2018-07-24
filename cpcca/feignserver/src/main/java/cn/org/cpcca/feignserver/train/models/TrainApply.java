package cn.org.cpcca.feignserver.train.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "培训信息")
public class TrainApply {
    @ApiModelProperty(value = "id", name = "id", hidden = true)
    private Integer id;

    @ApiModelProperty(value = "uid", name = "用户id", required = true, example = "用户id")
    private Integer uid;

    @ApiModelProperty(value = "mid", name = "培训id", required = true, example = "培训id")
    private Integer mid;

    @ApiModelProperty(value = "name", name = "姓名", required = true, example = "姓名")
    private String name;

    @ApiModelProperty(value = "nation", name = "民族", required = true, example = "汉")
    private String nation;

    @ApiModelProperty(value = "sex", name = "性别", required = true, example = "男")
    private String sex;

    @ApiModelProperty(value = "province", name = "省份", required = true, example = "四川省")
    private String province;

    @ApiModelProperty(value = "city", name = "市级", required = true, example = "成都市")
    private String city;

    @ApiModelProperty(value = "birth", name = "出生年月", required = true, example = "出生年月:2018-06-01")
    private String birth;

    @ApiModelProperty(value = "idcard", name = "身份证号", required = true, example = "身份证号")
    private String idcard;

    @ApiModelProperty(value = "duty", name = "duty", required = true, example = "职务")
    private String duty;

    @ApiModelProperty(value = "unitname", name = "单位名称", required = true, example = "单位名称")
    private String unitname;

    @ApiModelProperty(value = "unitaddress", name = "单位地址", required = true, example = "单位地址")
    private String unitaddress;

    @ApiModelProperty(value = "price", name = "价格", hidden = true)
    private Double price;

    @ApiModelProperty(value = "ismember", name = "是否为会员", required = true, example = "1")
    private Integer ismember;

    @ApiModelProperty(value = "phone", name = "电话", required = true, example = "电话")
    private String phone;

    @ApiModelProperty(value = "email", name = "email", example = "email")
    private String email;

    @ApiModelProperty(value = "officephone", name = "办公电话", example = "办公电话")
    private String officephone;

    @ApiModelProperty(value = "remark", name = "备注", example = "备注")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname == null ? null : unitname.trim();
    }

    public String getUnitaddress() {
        return unitaddress;
    }

    public void setUnitaddress(String unitaddress) {
        this.unitaddress = unitaddress == null ? null : unitaddress.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getIsmember() {
        return ismember;
    }

    public void setIsmember(Integer ismember) {
        this.ismember = ismember;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOfficephone() {
        return officephone;
    }

    public void setOfficephone(String officephone) {
        this.officephone = officephone == null ? null : officephone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}