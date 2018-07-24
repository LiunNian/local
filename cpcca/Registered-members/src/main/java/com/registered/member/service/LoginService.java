package com.registered.member.service;

public interface LoginService {

    Object login(String username,String password);
    Object registered(String unitname,String password,String linkman,String phonenum,String verify,String email);
    /*
   获取验证码
    */
    Object getVerifyCode(String phone);

    Object update(String username,String password,Integer id );
}
