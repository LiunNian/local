package com.registered.member.controller;

import com.registered.member.model.MemberInformation;
import com.registered.member.service.MemberInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member/info")
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class MemberInformationController {
    @Autowired
    MemberInformationService memberInformationService;
    /*
    会员信息
     */
    /*
    通过id获取会员信息,查看是不是会员，会员表是否有信息，有的话，查看是不是在流程当中
     */
    @PostMapping("/certification")
    @ResponseBody
    public Object registered(@RequestParam("id") String id){
        return memberInformationService.Cheakmember(id);
    }


    @PostMapping("/initiate")
    @ResponseBody
    public Object initiate(

                           @RequestParam("attachmentCode") String attachmentCode,
                           @RequestParam("memberId") String memberId ){
        return memberInformationService.initiate(attachmentCode,memberId);
    }

    @PostMapping("/inprocess")
    @ResponseBody
    public Object inprocess(
            @RequestParam("contactName") String contactName,
            @RequestParam("contactPhone") String contactPhone,
            @RequestParam("id") String id,
            @RequestParam("Verify") String Verify){
        return memberInformationService.inprocess(contactName,contactPhone,id,Verify);
    }


}
