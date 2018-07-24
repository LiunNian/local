package com.registered.member.controller;

import com.paper.common.controller.BaseController;
import com.registered.member.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
会员登录，注册，老会员转系统
 */
@Controller
@RequestMapping("/user/")
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class LoginController extends BaseController{

    @Autowired
    private LoginService loginService;

    /**
    用户登录
     */
    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password){
        return loginService.login(username,password);
    }

    /**
     * 获取验证码
     * @param phonenum
     * @return
     */
    @PostMapping("/verification")
    @ResponseBody
    public Object getVerifyCode(@RequestParam("phonenum") String phonenum){
        return loginService.getVerifyCode(phonenum);
    }
    /**
     用户注册
     */
    @PostMapping("/registered")
    @ResponseBody
    public Object registered(
            @RequestParam("unitname") String unitname, @RequestParam("password") String password,
            @RequestParam("linkman") String linkman, @RequestParam("phonenum") String phonenum,
            @RequestParam("verify") String verify,@RequestParam("email") String email
    ){
        return loginService.registered(unitname,password,linkman,phonenum,verify,email);
    }

    /**
     用户修改
     */
    @PostMapping("/update")
    @ResponseBody
    public Object update(
            @RequestParam("unitname") String unitname, @RequestParam("password") String password,
            @RequestParam("id") Integer id
    ){
        return loginService.update(unitname,password,id);
    }


}