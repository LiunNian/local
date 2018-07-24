package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.LinkedList;
import java.util.List;

public class InvoiceItemList {
    @XStreamImplicit(itemFieldName="InvoiceItemInfo")
    private List<InvoiceItemInfo> invoiceItemInfoList;

    public List<InvoiceItemInfo> getInvoiceItemInfoList() {
        return invoiceItemInfoList;
    }

    public void setInvoiceItemInfoList(List<InvoiceItemInfo> invoiceItemInfoList) {
        this.invoiceItemInfoList = invoiceItemInfoList;
    }
    public void add(InvoiceItemInfo invoiceItemInfo){
        if(invoiceItemInfoList == null){
            this.invoiceItemInfoList = new LinkedList<InvoiceItemInfo>();
        }
        this.invoiceItemInfoList.add(invoiceItemInfo);
    }
}
