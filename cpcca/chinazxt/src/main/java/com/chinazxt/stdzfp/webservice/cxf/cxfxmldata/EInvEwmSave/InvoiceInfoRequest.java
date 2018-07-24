package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvEwmSave;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class InvoiceInfoRequest {
    private String  DDJYH = ""; // 订单交易号
	private String  DDBH = ""; // 订单编号
	private String  DDRQ = ""; // 订单日期（yyyy-MM-dd HH:mm:ss）
	private String  DDLX = ""; // 订单类型
	private String  NSRSBH = ""; // 纳税人识别号
	private String  GMFSJHM = ""; // 购买方手机号码
	private String  GMFYXDZ = ""; // 购买方邮箱	
	private String  JSHJ = ""; // 价税合计
	private String  BZ = ""; // 备注
	private String  YDDLSH = ""; // 原订单流水号（退货订单时必填）
	@XStreamAlias("InvoiceItemList")
	private InvoiceItemList invoiceItemList;

	public String getDDJYH() {
		return DDJYH;
	}

	public void setDDJYH(String DDJYH) {
		this.DDJYH = DDJYH;
	}

	public String getDDBH() {
		return DDBH;
	}

	public void setDDBH(String DDBH) {
		this.DDBH = DDBH;
	}

	public String getDDRQ() {
		return DDRQ;
	}

	public void setDDRQ(String DDRQ) {
		this.DDRQ = DDRQ;
	}

	public String getDDLX() {
		return DDLX;
	}

	public void setDDLX(String DDLX) {
		this.DDLX = DDLX;
	}

	public String getNSRSBH() {
		return NSRSBH;
	}

	public void setNSRSBH(String NSRSBH) {
		this.NSRSBH = NSRSBH;
	}

	public String getGMFSJHM() {
		return GMFSJHM;
	}

	public void setGMFSJHM(String GMFSJHM) {
		this.GMFSJHM = GMFSJHM;
	}

	public String getGMFYXDZ() {
		return GMFYXDZ;
	}

	public void setGMFYXDZ(String GMFYXDZ) {
		this.GMFYXDZ = GMFYXDZ;
	}

	public String getJSHJ() {
		return JSHJ;
	}

	public void setJSHJ(String JSHJ) {
		this.JSHJ = JSHJ;
	}

	public String getBZ() {
		return BZ;
	}

	public void setBZ(String BZ) {
		this.BZ = BZ;
	}

	public String getYDDLSH() {
		return YDDLSH;
	}

	public void setYDDLSH(String YDDLSH) {
		this.YDDLSH = YDDLSH;
	}

	public InvoiceItemList getInvoiceItemList() {
		return invoiceItemList;
	}

	public void setInvoiceItemList(InvoiceItemList invoiceItemList) {
		this.invoiceItemList = invoiceItemList;
	}
}
