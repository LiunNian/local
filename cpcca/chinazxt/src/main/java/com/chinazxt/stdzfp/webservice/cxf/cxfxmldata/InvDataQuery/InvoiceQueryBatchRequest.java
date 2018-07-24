package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.InvDataQuery;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.LinkedList;
import java.util.List;

@XStreamAlias("InvoiceQueryBatchRequest")
public class InvoiceQueryBatchRequest{
    @XStreamImplicit(itemFieldName="InvoiceQueryRequest")
    private List<InvoiceQueryRequest> invoiceQueryRequestList;

    public List<InvoiceQueryRequest> getInvoiceQueryRequestList() {
        return invoiceQueryRequestList;
    }

    public void setInvoiceQueryRequestList(List<InvoiceQueryRequest> invoiceQueryRequestList) {
        this.invoiceQueryRequestList = invoiceQueryRequestList;
    }
    public void add(InvoiceQueryRequest invoiceQueryRequest) {
        if(this.invoiceQueryRequestList == null){
            this.invoiceQueryRequestList = new LinkedList<InvoiceQueryRequest>();
        }
        this.invoiceQueryRequestList.add(invoiceQueryRequest);
    }
}
