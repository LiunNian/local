package com.registered.member.controller;

import com.registered.member.model.OldMenberValidation;
import com.registered.member.service.OldMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.paper.common.controller.BaseController;

/*
会员登录，注册，老会员转系统
 */
@Controller
@RequestMapping("/oldmember/")
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class OldMemberController extends BaseController{

    @Autowired
    private OldMemberService oldMemberService;

    /**
     * 老会员转系统
     * @return
     */
    @PostMapping("/turn")
    @ResponseBody
    public Object OldMember(@RequestParam("unitName") String unitName,
                            @RequestParam("legalName") String legalName,
                            @RequestParam("contactName") String contactName,
                            @RequestParam("loginName") String loginName,
                            @RequestParam("loginPassword") String loginPassword,
                            @RequestParam("contactPhone") String contactPhone){
        OldMenberValidation obj=new OldMenberValidation();
        obj.setLegalName(legalName);
        obj.setUnitName(unitName);
        obj.setContactName(contactName);
        obj.setLoginName(loginName);
        obj.setLoginPassword(loginPassword);
        obj.setContactPhone(contactPhone);
        return oldMemberService.checkOldMember(obj);
    }
}