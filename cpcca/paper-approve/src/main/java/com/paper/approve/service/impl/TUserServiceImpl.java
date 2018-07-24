package com.paper.approve.service.impl;

import com.paper.approve.dao.TUserMapper;
import com.paper.approve.model.TUser;
import com.paper.approve.service.TUserService;
import com.paper.common.model.Result;
import com.paper.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("tUserService")
@Transactional
public class TUserServiceImpl implements TUserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public Object trainRegist(TUser user ) {
        int count = tUserMapper.selectCountByPhone(user.getPhonenum());
        if(count > 0){
            return new Result("注册失败", 0, false);
        }

        if(StringUtils.isEmpty(user.getName())){
            if (tUserMapper.insert(user) > 0) {
                return new Result("注册成功", 1, true);
            }
        }
        return new Result("注册失败", 0, false);
    }

    @Override
    public Object trainLogin(String phonenum, String verifyCode) {

        return new Result("注册失败", 0, false);
    }

    @Override
    public Object getVerifyCode(String phone) {
        int temp = (int)(Math.random() * 999999);
        String verify = String.valueOf(temp);
        int length = verify.length();

        if(length < 6){
            for (int i = 0; i < 6 - length; i++) {
                verify = "0" + verify;
            }
        }

        return new Result("发送成功",verify, 1, true);
    }


    @Override
    public Object checkPhone(String phone) {
        int count = tUserMapper.selectCountByPhone(phone);
        if(count > 0) {
            return new Result("号码已存在", 0, false);
        }else {
            return new Result("号码不存在", 1, true);
        }
    }


}
