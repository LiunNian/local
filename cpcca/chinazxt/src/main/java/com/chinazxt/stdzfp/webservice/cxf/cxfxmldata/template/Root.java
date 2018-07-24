package com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.template;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("root")
public class Root {
    @XStreamAlias("head")
    private Head head;
    @XStreamAlias("body")
    private Body body;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

}