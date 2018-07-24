package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvPdfDown;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("InvoicePdfRequest")
public class InvoicePdfRequest {
    private String  FPDM = ""; // 发票代码
    private String  FPHM = ""; // 发票号码
    private String  FPJYM = ""; // 发票校验码
    private String  PDFBZ = ""; // PDF标志

    public String getFPDM() {
        return FPDM;
    }

    public void setFPDM(String FPDM) {
        this.FPDM = FPDM;
    }

    public String getFPHM() {
        return FPHM;
    }

    public void setFPHM(String FPHM) {
        this.FPHM = FPHM;
    }

    public String getFPJYM() {
        return FPJYM;
    }

    public void setFPJYM(String FPJYM) {
        this.FPJYM = FPJYM;
    }

    public String getPDFBZ() {
        return PDFBZ;
    }

    public void setPDFBZ(String PDFBZ) {
        this.PDFBZ = PDFBZ;
    }
}
