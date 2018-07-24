package com.registered.member.service.impl;

import com.paper.common.model.Result;
import com.registered.member.dao.MemberRegisteredMapper;
import com.registered.member.model.MemberInformation;
import com.registered.member.redis.service.impl.SensitiveServiceImpl;
import com.registered.member.service.MemberRegisteredService;
import com.registered.member.util.SmsClientSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("memberRegisteredService")
@Transactional
public class MemberRegisteredImpl implements MemberRegisteredService {

    @Value("${duanxin.send.name}")
    private String sendName;

    @Value("${duanxin.send.password}")
    private String sendPassword;
    @Resource
    MemberRegisteredMapper memberRegisteredMapper;

    /*
    会员注册
     */
    @Autowired
    private SensitiveServiceImpl service;
    @Override
    public Object memberRegistered(MemberInformation obj) {
        obj.setStatus("completed");
        Date date=new Date();
        obj.setCtime(date);
        int count = memberRegisteredMapper.selectCountByPhone(obj.getContactPhone());
        if(count > 0 ){
            return new Result("手机号码已存在", 0, false);
        }
        else if(!obj.getVerify().equals(service.get(obj.getContactPhone()))){
            return new Result("验证码错误", -1, false);
        }
        else if(memberRegisteredMapper.memberRegistered(obj)<0){
            return new Result("会员注册失败", -2, false);
        }
        else{
            return new Result("会员注册成功", obj,1, true);
        }
    }
    @Override
    public Object oldmemberRegistered(MemberInformation obj) {

         if(memberRegisteredMapper.memberRegistered(obj)<0){
            return new Result("会员注册失败", -1, false);
        }
        else{
            return new Result("会员注册成功", obj,1, true);
        }
    }
    /**
     * 发送短信并缓存验证码
     * @param phone
     * @return
     */
    @Override
    public Object getVerifyCode(String phone) {
        System.out.println(service.get(phone) );
        if(service.get(phone) == null || "".equals(service.get(phone))){
            int temp = (int)(Math.random() * 999999);
            String verify = String.valueOf(temp);
            int length = verify.length();

            if(length < 6){
                for (int i = 0; i < 6 - length; i++) {
                    verify = "0" + verify;
                }
            }
            sendDuanXin(phone, verify);
            Long milis = Long.valueOf(2 * 185);
            service.put(phone, verify, milis);

            return new Result("发送成功", 1, true);
        }
        return new Result("已发送请注意查收", 2, false);
    }
    /*
    查询会员类型
     */
    @Override
    public Integer memberType(String name) {
        int res=memberRegisteredMapper.memberType(name);
        return res;
    }

    /*
    会员更新
     */
    @Override
    public Object update(MemberInformation obj) {
        memberRegisteredMapper.updateMember(obj);
        return new Result("更新数据成功", obj.getId(),1, true);
    }

    @Value("${web.upload-path}")
    String basePath;
    @Override
    public Object fileUpload(MultipartFile file, String flag) {
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        String filePath = basePath;
        String fileName = UUID.randomUUID().toString().replaceAll("-","") + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            byte[] bytes = file.getBytes();
            file.transferTo(dest);
            Map map = new HashMap();
            /*String path = filePath.substring(filePath.lastIndexOf("uploads"), filePath.length())+fileName;*/

            map.put("fileName",fileName);
            return new Result("文件上传成功", map, 1, true);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return new Result("文件上传出错", 0, false);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result("文件上传出错", 0, false);
        }
    }


    /*
    老会员通过单位名称获取会员类型
     */
    @Override
    public String getMemberType(String name) {
        String res=memberRegisteredMapper.getMemberType(name);
        return res;
    }


    /*
          * @param url
          * ：必填--发送连接地址URL——http://sms.kingtto.com:9999/sms.aspx
          * @param userid
          * ：必填--用户ID，为数字
          * @param account
          * ：必填--用户帐号
          * @param password
          * ：必填--用户密码
          * @param mobile
          * ：必填--发送的手机号码，多个可以用逗号隔比如>130xxxxxxxx,131xxxxxxxx
          * @param content
          * ：必填--实际发送内容，
          * @param action
          * ：选填--访问的事件，默认为send
          * @param sendType
          * ：选填--发送方式，默认为POST
          * @param codingType
          * ：选填--发送内容编码方式，默认为UTF-8
          * @param backEncodType
          * ：选填--返回内容编码方式，默认为UTF-8
          * @return 返回发送之后收到的信息
          */
    private void sendDuanXin(String phone, String verifyCode){
        try {
            String url = "http://sms.kingtto.com:9999/sms.aspx";
            String userid = "5184";
            String account =  sendName;
            String password = sendPassword;
            String mobile = phone;
            String content = "【中国文化馆协会会员服务系统】您好，验证码是:"+verifyCode+",请勿泄露！";
            String action = "send";
            String sendType = "POST";
            String codingType = "UTF-8";
            String backEncodType = "UTF-8";

            SmsClientSend.sendSms(url, userid, account,
                    password, mobile, content, action,
                    sendType, codingType, backEncodType);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
