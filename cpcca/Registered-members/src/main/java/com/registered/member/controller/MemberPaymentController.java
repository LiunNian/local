package com.registered.member.controller;

import com.registered.member.dao.MemberPaymentMapper;
import com.registered.member.model.Invoice;
import com.registered.member.service.MemberPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member/payment")
public class MemberPaymentController {

    @Autowired
    MemberPaymentService memberPaymentService;

    @Autowired
    MemberPaymentMapper memberPaymentMapperm;

    @PostMapping("/payInfo")
    @ResponseBody
    public Object registered(@RequestParam("id") String id){
        return memberPaymentService.PaymentInfo(id);
    }

    /*
    获取缴费标准
     */
    @PostMapping("/paystandard")
    @ResponseBody
    public Object paystandard(){
        return memberPaymentService.paystandard();
    }

    /*
    电子发票
   */
    @PostMapping("/eInvoice")
    @ResponseBody
    public Object eInvoice(@RequestParam("invoiceName") String invoiceName,@RequestParam("code") String code,
                              @RequestParam("invoiceHead") String invoiceHead,@RequestParam("email") String email,
                              @RequestParam("standard") String standard,@RequestParam("id") String id,
                              @RequestParam("time") String time){
        Invoice obj=new Invoice();
        obj.setInvoiceName(invoiceName);
        obj.setCode(code);
        obj.setInvoiceHead(invoiceHead);
        obj.setEmail(email);
       obj.setPayWay("电子发票");
        return memberPaymentService.eInvoice(obj,standard,id,time);
    }

}
