package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.template;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("head")
public class Head {
    @XStreamAlias("appSys")
    private String appSys = "VATI"; //应用系统名称（VATI）
    @XStreamAlias("requestSys")
    private String requestSys = "YC"; //请求系统名称 依项目情况而定
    @XStreamAlias("sessionId")
    private String sessionId = "1234567890123456"; //交易的全局编号
    @XStreamAlias("business")
    private String business = ""; //业务标识
    @XStreamAlias("requestTime")
    private String requestTime = ""; //请求时间
    @XStreamAlias("version")
    private String version = "1.0"; //消息版本

    public String getAppSys() {
        return appSys;
    }

    public void setAppSys(String appSys) {
        this.appSys = appSys;
    }

    public String getRequestSys() {
        return requestSys;
    }

    public void setRequestSys(String requestSys) {
        this.requestSys = requestSys;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
