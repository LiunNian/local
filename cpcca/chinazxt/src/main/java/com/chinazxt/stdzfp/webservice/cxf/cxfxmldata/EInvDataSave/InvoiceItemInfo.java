package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("InvoiceItemInfo")
public class InvoiceItemInfo {
    private String FPHXZ= ""; // 发票行性质（0：正常行 1：折扣行 2：被折扣行）
    private String XMXH= ""; // 项目序号（从1开始）
    private String XMDM = ""; // 项目代码（纳税人商品代码）
    private String XMMC = ""; // 项目名称
    private String XMSL = ""; // 项目数量（小数点后6位）
    private String XMDJ = ""; // 项目单价（含税价）（小数点后6位）
    private String XMJE= ""; // 项目金额（含税价）（小数点后2位）

    public String getFPHXZ() {
        return FPHXZ;
    }

    public void setFPHXZ(String FPHXZ) {
        this.FPHXZ = FPHXZ;
    }

    public String getXMXH() {
        return XMXH;
    }

    public void setXMXH(String XMXH) {
        this.XMXH = XMXH;
    }

    public String getXMDM() {
        return XMDM;
    }

    public void setXMDM(String XMDM) {
        this.XMDM = XMDM;
    }

    public String getXMMC() {
        return XMMC;
    }

    public void setXMMC(String XMMC) {
        this.XMMC = XMMC;
    }

    public String getXMSL() {
        return XMSL;
    }

    public void setXMSL(String XMSL) {
        this.XMSL = XMSL;
    }

    public String getXMDJ() {
        return XMDJ;
    }

    public void setXMDJ(String XMDJ) {
        this.XMDJ = XMDJ;
    }

    public String getXMJE() {
        return XMJE;
    }

    public void setXMJE(String XMJE) {
        this.XMJE = XMJE;
    }
}
