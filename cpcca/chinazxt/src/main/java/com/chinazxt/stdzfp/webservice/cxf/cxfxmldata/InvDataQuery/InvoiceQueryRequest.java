package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.InvDataQuery;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("InvoiceQueryRequest")
public class InvoiceQueryRequest {
    private String DDLSH = "";

    public String getDDLSH() {
        return DDLSH;
    }

    public void setDDLSH(String DDLSH) {
        this.DDLSH = DDLSH;
    }
}
