package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("InvoiceInfoBatchRequest")
public class InvoiceInfoBatchRequest{
    @XStreamAlias("InvoiceInfoRequest")
    private InvoiceInfoRequest invoiceInfoRequest;

    public InvoiceInfoRequest getInvoiceInfoRequest() {
        return invoiceInfoRequest;
    }

    public void setInvoiceInfoRequest(InvoiceInfoRequest invoiceInfoRequest) {
        this.invoiceInfoRequest = invoiceInfoRequest;
    }
}
