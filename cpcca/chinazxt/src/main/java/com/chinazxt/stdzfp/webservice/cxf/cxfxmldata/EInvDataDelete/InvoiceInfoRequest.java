package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataDelete;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("InvoiceInfoRequest")
public class InvoiceInfoRequest {
    private String  DDLSH = ""; //订单流水号
    private String  NSRSBH = ""; // 纳税人识别号
    private String  DDJYH = ""; // 订单交易号

    public String getDDLSH() {
        return DDLSH;
    }

    public void setDDLSH(String DDLSH) {
        this.DDLSH = DDLSH;
    }

    public String getNSRSBH() {
        return NSRSBH;
    }

    public void setNSRSBH(String NSRSBH) {
        this.NSRSBH = NSRSBH;
    }

    public String getDDJYH() {
        return DDJYH;
    }

    public void setDDJYH(String DDJYH) {
        this.DDJYH = DDJYH;
    }
}
