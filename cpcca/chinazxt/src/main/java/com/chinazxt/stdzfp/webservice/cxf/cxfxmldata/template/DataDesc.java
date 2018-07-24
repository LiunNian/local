package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.template;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("dataDesc")
public class DataDesc {
    @XStreamAlias("zipCode")
    private String zipCode = "0"; //压缩标识 0：不压缩  1：压缩
    @XStreamAlias("encryptCode")
    private String encryptCode = "0"; //加密标识 0：不加密  1：加密
    @XStreamAlias("codeType")
    private String codeType = "UTF-8"; //编码标识 暂固定为：UTF-8
    @XStreamAlias("digesData")
    private String digesData = "CHINAZXTSIGNDATA"; //content内容摘要数据 XML使用SHA1算法做摘要数据（BASE64）

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEncryptCode() {
        return encryptCode;
    }

    public void setEncryptCode(String encryptCode) {
        this.encryptCode = encryptCode;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getDigesData() {
        return digesData;
    }

    public void setDigesData(String digesData) {
        this.digesData = digesData;
    }
}
