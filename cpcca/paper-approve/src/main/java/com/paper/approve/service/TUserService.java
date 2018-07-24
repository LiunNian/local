package com.paper.approve.service;

import com.paper.approve.model.TUser;

public interface TUserService {
    Object trainRegist(TUser user );

    Object trainLogin(String phonenum, String verifyCode);

    Object getVerifyCode(String phonenum);

    Object checkPhone(String phone);
}
