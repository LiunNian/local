package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.template;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("signDesc")
public class SignDesc {
    @XStreamAlias("signCode")
    private String signCode = "0"; //签名标识 0：不签名  1：签名
    @XStreamAlias("signData")
    private String signData = ""; //签名数据 1 对原文进行SHA1摘要 2 对摘要进行加密，BASE64编码
    @XStreamAlias("signKey")
    private String signKey = ""; //签名密钥 签名密钥（需要BASE64编码）

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    public String getSignData() {
        return signData;
    }

    public void setSignData(String signData) {
        this.signData = signData;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }
}
