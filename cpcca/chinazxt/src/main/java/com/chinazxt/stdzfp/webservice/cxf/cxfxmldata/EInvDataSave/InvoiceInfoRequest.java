package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("InvoiceInfoRequest")
public class InvoiceInfoRequest {
    private String DDJYH = ""; //订单交易号
    private String DDBH = ""; //订单编号
    private String DDRQ = ""; //订单日期（yyyy-MM-dd HH:mm:ss）
    private String DDLX = ""; //订单类型 1：正常订单 2：退货订单
    private String NSRSBH = ""; //纳税人识别号
    private String GMFNSRSBH = ""; //购买方纳税人识别号
    private String GMFMC = ""; //购买方名称
    private String GMFDZ = ""; //购买方地址
    private String GMFDH = ""; //购买方电话
    private String GMFKHYH = ""; //购买方开户行
    private String GMFYHZH = ""; //购买方银行账号
    private String GMFSJHM = ""; //购买方手机号码（电子发票必填，纸质发票可以不填）
    private String GMFYXDZ = ""; //购买方邮箱
    private String JSHJ = ""; //价税合计
    private Integer  ISMAIL; //是否邮寄
    private String  SJRMC="";
    private String  SJRDZSHENG="";
    private String  SJRDZSHI="";
    private String  SJRDZQX="";
    private String  SJRXXDZ="";
    private String  SJRDH="";
    private String  SJRYZBM="";
    private String BZ = ""; //备注
    private String YDDLSH = ""; //原订单流水号（退货订单时必填）
    private String YQKPSJ = "";//预期开票时间（yyyy-MM-dd）

    /*private String  SKFS="";//收款方式*/

   /* public String getSKFS() {
        return SKFS;
    }

    public void setSKFS(String SKFS) {
        this.SKFS = SKFS;
    }*/

    public String getSJRMC() {
        return SJRMC;
    }

    public void setSJRMC(String SJRMC) {
        this.SJRMC = SJRMC;
    }

    public String getSJRDZSHENG() {
        return SJRDZSHENG;
    }

    public void setSJRDZSHENG(String SJRDZSHENG) {
        this.SJRDZSHENG = SJRDZSHENG;
    }

    public String getSJRDZSHI() {
        return SJRDZSHI;
    }

    public void setSJRDZSHI(String SJRDZSHI) {
        this.SJRDZSHI = SJRDZSHI;
    }

    public String getSJRDZQX() {
        return SJRDZQX;
    }

    public void setSJRDZQX(String SJRDZQX) {
        this.SJRDZQX = SJRDZQX;
    }

    public String getSJRXXDZ() {
        return SJRXXDZ;
    }

    public void setSJRXXDZ(String SJRXXDZ) {
        this.SJRXXDZ = SJRXXDZ;
    }

    public String getSJRDH() {
        return SJRDH;
    }

    public void setSJRDH(String SJRDH) {
        this.SJRDH = SJRDH;
    }

    public String getSJRYZBM() {
        return SJRYZBM;
    }

    public void setSJRYZBM(String SJRYZBM) {
        this.SJRYZBM = SJRYZBM;
    }



    public Integer getISMAIL() {
        return ISMAIL;
    }

    public void setISMAIL(Integer ISMAIL) {
        this.ISMAIL = ISMAIL;
    }
    @XStreamAlias("InvoiceItemList")
    private InvoiceItemList invoiceItemList;

    public String getDDJYH() {
        return DDJYH;
    }

    public void setDDJYH(String DDJYH) {
        this.DDJYH = DDJYH;
    }

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public String getDDRQ() {
        return DDRQ;
    }

    public void setDDRQ(String DDRQ) {
        this.DDRQ = DDRQ;
    }

    public String getDDLX() {
        return DDLX;
    }

    public void setDDLX(String DDLX) {
        this.DDLX = DDLX;
    }

    public String getNSRSBH() {
        return NSRSBH;
    }

    public void setNSRSBH(String NSRSBH) {
        this.NSRSBH = NSRSBH;
    }

    public String getGMFNSRSBH() {
        return GMFNSRSBH;
    }

    public void setGMFNSRSBH(String GMFNSRSBH) {
        this.GMFNSRSBH = GMFNSRSBH;
    }

    public String getGMFMC() {
        return GMFMC;
    }

    public void setGMFMC(String GMFMC) {
        this.GMFMC = GMFMC;
    }

    public String getGMFDZ() {
        return GMFDZ;
    }

    public void setGMFDZ(String GMFDZ) {
        this.GMFDZ = GMFDZ;
    }

    public String getGMFDH() {
        return GMFDH;
    }

    public void setGMFDH(String GMFDH) {
        this.GMFDH = GMFDH;
    }

    public String getGMFKHYH() {
        return GMFKHYH;
    }

    public void setGMFKHYH(String GMFKHYH) {
        this.GMFKHYH = GMFKHYH;
    }

    public String getGMFYHZH() {
        return GMFYHZH;
    }

    public void setGMFYHZH(String GMFYHZH) {
        this.GMFYHZH = GMFYHZH;
    }

    public String getGMFSJHM() {
        return GMFSJHM;
    }

    public void setGMFSJHM(String GMFSJHM) {
        this.GMFSJHM = GMFSJHM;
    }

    public String getGMFYXDZ() {
        return GMFYXDZ;
    }

    public void setGMFYXDZ(String GMFYXDZ) {
        this.GMFYXDZ = GMFYXDZ;
    }

    public String getJSHJ() {
        return JSHJ;
    }

    public void setJSHJ(String JSHJ) {
        this.JSHJ = JSHJ;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    public String getYDDLSH() {
        return YDDLSH;
    }

    public void setYDDLSH(String YDDLSH) {
        this.YDDLSH = YDDLSH;
    }

    public String getYQKPSJ() {
        return YQKPSJ;
    }

    public void setYQKPSJ(String YQKPSJ) {
        this.YQKPSJ = YQKPSJ;
    }

    public InvoiceItemList getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(InvoiceItemList invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }
}
