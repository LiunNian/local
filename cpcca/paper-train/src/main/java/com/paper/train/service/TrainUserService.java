package com.paper.train.service;


import com.paper.train.model.TrainUser;

public interface TrainUserService {
    Object trainRegist(TrainUser user);

    Object trainLogin(String phone);

    Object getVerifyCode(String phonenum);

    Object checkPhone(String phone);

    Object resetPassword(TrainUser user);
}
