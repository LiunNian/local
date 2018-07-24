package com.registered.member.service;

import com.registered.member.model.MemberInformation;
import org.springframework.web.multipart.MultipartFile;

public interface MemberRegisteredService {
    /*
    新用户注册会员
     */
    Object memberRegistered(MemberInformation obj);
    /*
    老用户会员转系统
     */
    Object oldmemberRegistered(MemberInformation obj);
    /*
    获取验证码
     */
    Object getVerifyCode(String phone);

    String getMemberType(String name);
    /*
    会员类型
  */
    Integer memberType(String obj);
    /*
    会员更新
     */
    Object update(MemberInformation obj);

    /*
    w文件上传
     */
    Object fileUpload(MultipartFile file, String flag);
}
