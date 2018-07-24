package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvPdfDown;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("InvoicePdfBatchRequest")
public class InvoicePdfBatchRequest {
    @XStreamAlias("InvoicePdfRequest")
    private InvoicePdfRequest invoicePdfRequest;

    public InvoicePdfRequest getInvoicePdfRequest() {
        return invoicePdfRequest;
    }

    public void setInvoicePdfRequest(InvoicePdfRequest invoicePdfRequest) {
        this.invoicePdfRequest = invoicePdfRequest;
    }
}
