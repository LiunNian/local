package com.chinazxt.stdzfp.controller;

import com.chinazxt.stdzfp.webservice.cxf.client.CxfClient;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave.InvoiceInfoBatchRequest;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave.InvoiceInfoRequest;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave.InvoiceItemInfo;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvDataSave.InvoiceItemList;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvPdfDown.InvoicePdfBatchRequest;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.EInvPdfDown.InvoicePdfRequest;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.InvDataQuery.InvoiceQueryBatchRequest;
import com.chinazxt.stdzfp.webservice.cxf.cxfxmldata.InvDataQuery.InvoiceQueryRequest;
import com.chinazxt.stdzfp.webservice.cxf.util.XmlUtil;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class ChinaZXTController {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 /*   @RequestBody InvoiceInfoRequest invoiceInfoRequest,@RequestBody InvoiceItemInfo invoiceItemInfo*/
    @RequestMapping("eInvDataSave")
    public String eInvDataSave(@RequestParam("DDJYH") String DDJYH,
                               @RequestParam("DDBH") String DDBH, @RequestParam("DDLX") String DDLX,
                               @RequestParam("NSRSBH") String NSRSBH, @RequestParam("GMFNSRSBH") String GMFNSRSBH,
                               @RequestParam("GMFMC") String GMFMC, @RequestParam("GMFDZ") String GMFDZ,
                               @RequestParam("GMFDH") String GMFDH, @RequestParam("GMFKHYH") String GMFKHYH,
                               @RequestParam("GMFYHZH") String GMFYHZH, @RequestParam("GMFSJHM") String GMFSJHM,
                               @RequestParam("GMFYXDZ") String GMFYXDZ, @RequestParam("JSHJ") String JSHJ,
                               @RequestParam("ISMAIL") Integer ISMAIL, @RequestParam("SJRMC") String SJRMC,
                               @RequestParam("SJRDZSHENG") String SJRDZSHENG, @RequestParam("SJRDZSHI") String SJRDZSHI,
                               @RequestParam("SJRDZQX") String SJRDZQX, @RequestParam("SJRXXDZ") String SJRXXDZ,
                               @RequestParam("SJRDH") String SJRDH, @RequestParam("SJRYZBM") String SJRYZBM,
                               @RequestParam("BZ") String BZ, @RequestParam("YDDLSH") String YDDLSH,
                               @RequestParam("FPHXZ") String FPHXZ, @RequestParam("XMXH") String XMXH,
                               @RequestParam("XMDM") String XMDM, @RequestParam("XMMC") String XMMC,
                               @RequestParam("XMSL") String XMSL, @RequestParam("XMDJ") String XMDJ,
                               @RequestParam("XMJE") String XMJE
           ){

        CxfClient cxfClient = new CxfClient();
        //根据说明文档组装数据，节点数据已经封装为实体类
        InvoiceInfoBatchRequest invoiceInfoBatchRequest = new InvoiceInfoBatchRequest();
        InvoiceInfoRequest invoiceInfoRequest = new InvoiceInfoRequest();
        invoiceInfoRequest.setDDRQ(sdf.format( new Date() )); //订单日期
        invoiceInfoRequest.setDDLX(DDLX); //订单类型

        invoiceInfoRequest.setDDJYH(DDJYH);//订单交易号
        invoiceInfoRequest.setDDBH(DDBH);//订单编号
        invoiceInfoRequest.setDDRQ(sdf.format( new Date() )); //订单日期
        invoiceInfoRequest.setDDLX(DDLX); //订单类型
        invoiceInfoRequest.setNSRSBH(NSRSBH);//纳税人识别号
        invoiceInfoRequest.setGMFNSRSBH(GMFNSRSBH);
        invoiceInfoRequest.setGMFMC(GMFMC);//购买方名称
        invoiceInfoRequest.setGMFDZ(GMFDZ); //购买方地址dong
        invoiceInfoRequest.setGMFDH(GMFDH);
        invoiceInfoRequest.setGMFKHYH(GMFKHYH); //购买方开户行
        invoiceInfoRequest.setGMFYHZH(GMFYHZH); //购买方银行账号
        invoiceInfoRequest.setGMFSJHM(GMFSJHM);//购买方手机号码
        invoiceInfoRequest.setGMFYXDZ(GMFYXDZ);//购买方邮箱
        invoiceInfoRequest.setJSHJ(JSHJ);//价税合计
        invoiceInfoRequest.setISMAIL(ISMAIL);//是否邮寄
        if(ISMAIL==0){

        }
        invoiceInfoRequest.setBZ(BZ);//备注*/
        InvoiceItemList invoiceItemList = new InvoiceItemList();
       InvoiceItemInfo invoiceItemInfo = new InvoiceItemInfo();
        invoiceItemInfo.setFPHXZ(FPHXZ);//发票行性质
        invoiceItemInfo.setXMXH(XMXH);// 项目序号
        invoiceItemInfo.setXMMC(XMMC);// 项目名称
        invoiceItemInfo.setXMSL(XMSL);//项目数量
        invoiceItemInfo.setXMDJ(XMDJ);//项目单价
        invoiceItemInfo.setXMJE(XMJE);//项目金额
        invoiceItemList.add(invoiceItemInfo);
        invoiceInfoRequest.setInvoiceItemList(invoiceItemList);
        invoiceInfoBatchRequest.setInvoiceInfoRequest(invoiceInfoRequest);
        /*String requestData =
                "<InvoiceInfoBatchRequest>" +
                "<InvoiceInfoRequest>" +
                "<DDJYH>test-20180615-1</DDJYH>" +
                "<DDBH>test-20180615-1</DDBH>" +
                "<DDRQ>2018-06-15 09:30:15</DDRQ>" +
                "<DDLX>1</DDLX>" +
                "<NSRSBH>91500000747150426A</NSRSBH>" +
                "<GMFNSRSBH>023456789012345</GMFNSRSBH>" +
                "<GMFMC>BHG</GMFMC>" +
                "<GMFDZ>北京市海淀区中关村软件园1期15号</GMFDZ>" +
                "<GMFDH>010-22328012</GMFDH>" +
                "<GMFKHYH>交通银行</GMFKHYH>" +
                "<GMFYHZH>62226012</GMFYHZH>" +
                "<GMFSJHM>13681129431</GMFSJHM>" +
                "<GMFYXDZ>cjlizp@163.com</GMFYXDZ>" +
                "<JSHJ>100</JSHJ>" +
                "<ISMAIL>0</ISMAIL>" +
                "<SJRMC>张三</SJRMC>" +
                "<SJRDZSHENG>北京市</SJRDZSHENG>" +
                "<SJRDZSHI>北京市</SJRDZSHI>" +
                "<SJRDZQX>海淀区</SJRDZQX>" +
                "<SJRXXDZ>北京市海淀区中关村软件园</SJRXXDZ>" +
                "<SJRDH>010-12328012</SJRDH>" +
                "<SJRYZBM>100090</SJRYZBM>" +
                "<BZ>接口测试</BZ>" +
                "<YDDLSH></YDDLSH>" +
                "<InvoiceItemList>" +
                "<InvoiceItemInfo>" +
                "<FPHXZ>0</FPHXZ>" +
                "<XMXH>1</XMXH>" +
                "<XMDM></XMDM>" +
                "<XMMC>测试名称a001</XMMC>" +
                "<XMSL>1</XMSL>" +
                "<XMDJ>100</XMDJ>" +
                "<XMJE>100</XMJE>" +
                "</InvoiceItemInfo>" +
                "</InvoiceItemList>" +
                "</InvoiceInfoRequest>" +
                "</InvoiceInfoBatchRequest>";
*/
        //File file = new File("1.txt");
        //FileIO.write(requestData);
//        byte[] bs = requestData.getBytes();
//        String tempStr = "";
//        try {
//            tempStr = new String(bs,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        System.out.println(tempStr);
        String resultData  = cxfClient.xmlData("EInvDataSave",XmlUtil.toXml(invoiceInfoBatchRequest));
        //System.out.println(requestData);
        //String resultData  = cxfClient.xmlData("EInvDataSave",requestData);
        //System.out.println(resultData);
        System.out.println("请求");
        System.out.println(resultData);
        String resultXml = cxfClient.ticketApi2(resultData);
        return resultXml;
    }
    @RequestMapping("invDataQuery")
    public String invDataQuery(@RequestParam(name = "ddlsh") String ddlsh){
        CxfClient cxfClient = new CxfClient();
        InvoiceQueryBatchRequest invoiceQueryBatchRequest = new InvoiceQueryBatchRequest();
        InvoiceQueryRequest invoiceQueryRequest = new InvoiceQueryRequest();
        invoiceQueryRequest.setDDLSH(ddlsh);
        invoiceQueryBatchRequest.add(invoiceQueryRequest);
        String resultData  = cxfClient.xmlData("InvDataQuery",XmlUtil.toXml(invoiceQueryBatchRequest));
        String resultXml = cxfClient.ticketApi2(resultData);
        return resultXml;
    }

    @RequestMapping("eInvPdfDown")
    public String eInvPdfDown(
            @RequestParam(name = "fpdm") String fpdm,
            @RequestParam(name = "fphm") String fphm,
            @RequestParam(name = "fpjym") String fpjym,
            @RequestParam(name = "pdfbz",defaultValue = "1") String pdfbz
    ){
        CxfClient cxfClient = new CxfClient();
        InvoicePdfBatchRequest invoicePdfBatchRequest = new InvoicePdfBatchRequest();
        InvoicePdfRequest invoicePdfRequest = new InvoicePdfRequest();
        invoicePdfRequest.setFPDM(fpdm);
        invoicePdfRequest.setFPHM(fphm);
        invoicePdfRequest.setFPJYM(fpjym);
        invoicePdfRequest.setPDFBZ(pdfbz);
        invoicePdfBatchRequest.setInvoicePdfRequest(invoicePdfRequest);
        String resultData  = cxfClient.xmlData("EInvPdfDown",XmlUtil.toXml(invoicePdfBatchRequest));
        String resultXml = cxfClient.ticketApi2(resultData);
        return resultXml;
    }
}
