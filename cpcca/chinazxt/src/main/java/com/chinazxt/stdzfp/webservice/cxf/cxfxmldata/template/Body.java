package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.template;

import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave.InvoiceInfoBatchRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("body")
public class Body {
    @XStreamAlias("dataDesc")
    private DataDesc dataDesc;
    @XStreamAlias("signDesc")
    private SignDesc signDesc;
    @XStreamAlias("content")
    private String content;
    public DataDesc getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(DataDesc dataDesc) {
        this.dataDesc = dataDesc;
    }

    public SignDesc getSignDesc() {
        return signDesc;
    }

    public void setSignDesc(SignDesc signDesc) {
        this.signDesc = signDesc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
