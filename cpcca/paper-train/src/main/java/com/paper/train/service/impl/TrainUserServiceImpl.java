package com.paper.train.service.impl;

import com.paper.approve.redis.service.impl.SensitiveServiceImpl;
import com.paper.common.model.Result;
import com.paper.common.util.StringUtils;
import com.paper.train.dao.TrainUserMapper;
import com.paper.train.model.AddTrainUser;
import com.paper.train.model.TrainUser;
import com.paper.train.service.TrainUserService;
import com.paper.train.util.SmsClientSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service("trainUserService")
@Transactional
public class TrainUserServiceImpl implements TrainUserService {

    @Value("${duanxin.send.name}")
    private String sendName;

    @Value("${duanxin.send.password}")
    private String sendPassword;

    @Autowired
    private TrainUserMapper trainUserMapper;

    @Autowired
    private SensitiveServiceImpl service;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public Object trainRegist(TrainUser user) {
        int count = trainUserMapper.selectCountByPhone(user.getPhonenum());

        if(count > 0 ){
            return new Result("已存在", 0, false);
        }else if(user.getPhonenum() == null){
            return new Result("电话号码不能为空", -1, false);
        }else if(user.getPassword() == null){
            return new Result("密码不能为空", -2, false);
        }else if( !user.getVerify().equals(service.get(user.getPhonenum()))){
            return new Result("验证码错误", -3, false);
        }

        if(!StringUtils.isEmpty(user.getName()) && user.getVerify().equals(service.get(user.getPhonenum())) ){
            AddTrainUser addTrainUser = new AddTrainUser(user);
            addTrainUser.setPwd(user.getPassword());
            addTrainUser.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            service.remove(user.getPhonenum());
            if (trainUserMapper.insert(addTrainUser) > 0) {
                return new Result("注册成功", 1, true);
            }
        }
        return new Result("注册失败", 0, false);
    }

    @Override
    public Object trainLogin(String phone) {
        return trainUserMapper.selectByName(phone);
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


    @Override
    public Object checkPhone(String phone) {
        int count = trainUserMapper.selectCountByPhone(phone);
        if(count > 0) {
            return new Result("号码已存在", 0, false);
        }else {
            return new Result("号码不存在", 1, true);
        }
    }

    @Override
    public Object resetPassword(TrainUser user) {
        String phonenum = user.getPhonenum();
        String vCode = user.getVerify();
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        if(StringUtils.isEmpty( phonenum )) {
            return new Result("电话号码不能为空", -1, false);
        }else if( vCode.equals(service.get(phonenum))){
            String pwd = user.getPassword();
            service.remove(user.getPhonenum());
            trainUserMapper.updatePassword(phonenum, password, pwd);
            return new Result("重置密码成功", 1, false);
        }
        else if( ( StringUtils.isEmpty( vCode ) || !vCode.equals(service.get(phonenum)) )){
            return new Result("验证码错误", -2, false);
        }
        return new Result("重置密码失败", 0, true);
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
