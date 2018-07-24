package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataDelete;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("InvoiceInfoBatchRequest")
public class InvoiceInfoBatchRequest {
    @XStreamAlias("InvoiceInfoRequest")
    private InvoiceInfoRequest invoiceInfoRequest;
}
