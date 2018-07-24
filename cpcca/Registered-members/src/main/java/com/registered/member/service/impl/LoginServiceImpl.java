package com.registered.member.service.impl;

import com.paper.common.model.Result;
import com.registered.member.dao.RegisteredUserInformationMapper;
import com.registered.member.model.UserInformation;
import com.registered.member.model.Users;
import com.registered.member.redis.service.impl.SensitiveServiceImpl;
import com.registered.member.service.LoginService;
import com.registered.member.util.SmsClientSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

    @Value("${duanxin.send.name}")
    private String sendName;

    @Value("${duanxin.send.password}")
    private String sendPassword;

    @Autowired
    private SensitiveServiceImpl service;

    @Resource
    RegisteredUserInformationMapper registeredUserInformationMapper;

    @Override
    public Object login(String username, String password) {
       String id= registeredUserInformationMapper.login(username,password);
       if(id!=null){
           return new Result("登录成功", id , 1, true);
       }
       else{
           return new Result("登录认证失败,重新登陆", id , 0, false);

       }
    }

    @Override
    public Object registered(String unitname, String password, String linkman, String phonenum,String verify, String email) {

        /*
        登录表
        * */
        Users user=new Users();
        user.setUsername(unitname);
        user.setPassword_salt(password);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        Integer res= registeredUserInformationMapper.registeredUser(user);
        /*
        信息表
         */
        UserInformation Uinfo=new UserInformation();
        Uinfo.setUnitname(unitname);
        Uinfo.setUid(res);
        Uinfo.setLinkman(linkman);
        Uinfo.setPhonenum(phonenum);
        Uinfo.setEmail(email);
        Integer rest=registeredUserInformationMapper.insertUserinfo(Uinfo);
        Map map=new HashMap();
        map.put("loginName",unitname);
        map.put("password",password);
        int count = registeredUserInformationMapper.selectCountByPhone(phonenum);
        if(count > 0 ){
            return new Result("手机号码已存在", 0, false);
        }
        else if( !verify.equals(service.get(phonenum))){
            return new Result("验证码错误", -1, false);
        }
        else{
            return new Result("注册成功", map , 1, true);
        }

    }
    /**
     * 发送短信并缓存验证码
     * @param phone
     * @return
     */
    @Override
    public Object getVerifyCode(String phone) {
        System.out.println(service.get(phone));
        if (phone != null && phone != "") {
            if (service.get(phone) == null || "".equals(service.get(phone))) {
                int temp = (int) (Math.random() * 999999);
                String verify = String.valueOf(temp);
                int length = verify.length();

                if (length < 6) {
                    for (int i = 0; i < 6 - length; i++) {
                        verify = "0" + verify;
                    }
                }
                sendDuanXin(phone, verify);

                Long milis = Long.valueOf(2 * 185);
                service.put(phone, verify, milis);

                return new Result("发送成功", verify, 1, true);
            } else {
                return new Result("发送验证码失败", 0, false);
            }
        }
        else{
            return new Result("发送失败手机号不能为空",  0, false);
        }
    }

    @Override
    public Object update(String username, String password,Integer id) {
        Users user=registeredUserInformationMapper.selectUser(id);
        user.setPassword_salt(password);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setUsername(username);
        registeredUserInformationMapper.update(user);
        return new Result("账户密码更新成功", 0, false);
    }


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
