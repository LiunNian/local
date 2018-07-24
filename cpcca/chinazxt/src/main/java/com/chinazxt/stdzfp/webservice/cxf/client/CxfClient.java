package com.chinazxt.stdzfp.webservice.cxf.client;

import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.template.Body;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.template.DataDesc;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.template.SignDesc;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.template.Head;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.template.Root;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave.InvoiceInfoBatchRequest;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave.InvoiceInfoRequest;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave.InvoiceItemInfo;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave.InvoiceItemList;
import com.chinazxt.stdzfp.webservice.cxf.util.XmlUtil;
import com.tax.webservice.IVatiWebService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CxfClient {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //数据模板
    public String xmlData(String fun,String content){
        String returnData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        Root root = new Root();
        Head head = new Head();
        Body body = new Body();
        DataDesc dataDesc = new DataDesc();
        SignDesc signDesc = new SignDesc();
        String requestTime = sdf.format( new Date() );
        head.setRequestTime(requestTime);
        head.setBusiness(fun);
        body.setDataDesc(dataDesc);
        body.setSignDesc(signDesc);
        body.setContent(content);
        root.setHead(head);
        root.setBody(body);
        returnData += XmlUtil.toXml(root);
        //System.out.println(returnData);
        return returnData;
    }
    //开发content样例
    private String xmlDataContent(){
        String returnData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        InvoiceInfoBatchRequest invoiceInfoBatchRequest = new InvoiceInfoBatchRequest();
        InvoiceInfoRequest invoiceInfoRequest = new InvoiceInfoRequest();
        InvoiceItemList invoiceItemList = new InvoiceItemList();
        InvoiceItemInfo invoiceItemInfo = new InvoiceItemInfo();
        invoiceItemList.add(invoiceItemInfo);
        invoiceInfoRequest.setInvoiceItemList(invoiceItemList);
        invoiceInfoBatchRequest.setInvoiceInfoRequest(invoiceInfoRequest);
        returnData += XmlUtil.toXml(invoiceInfoBatchRequest);
        return returnData;
    }
    /**
     * 方式1.代理类工厂的方式,需要拿到对方的接口
     */
    public String ticketApi(String paramsData) {
        try {
            // 接口地址
            String address = "http://stdzfp.chinazxt.com/vati/webservice/VatiWebService?wsdl";
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(address);
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(IVatiWebService.class);
            // 创建一个代理接口实现
            IVatiWebService cs = (IVatiWebService) jaxWsProxyFactoryBean.create();
            // 调用代理接口的方法调用并返回结果
            String result = cs.service(paramsData);
            System.out.println("返回结果:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 动态调用方式 暂时未测试
     */
    public String ticketApi2(String paramsData) {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://stdzfp.chinazxt.com/vati/webservice/VatiWebService?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
        // PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            /*System.out.println(paramsData);*/
            objects = client.invoke("service", paramsData);
            System.out.println("返回数据:" + objects[0]);
            return objects[0].toString();
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        return "";
    }
   /* public static void main(String[] args){
        //new CxfClient().cl1();
        //System.out.println(new CxfClient().xmlData("EInvDataSave"));
        //System.out.println(new CxfClient().xmlDataContent());
        String returnData = new CxfClient().ticketApi(new CxfClient().xmlData("EInvDataSave",new CxfClient().xmlDataContent()));
        System.out.println(returnData);
    } */
}
