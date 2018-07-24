package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.UserInfoMappr;
import cn.org.cpcca.paperserver.models.UserInfoModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoService implements UserInfoServiceInterface{
    @Resource
    private UserInfoMappr userInfoMappr;
    @Override
    public int updateUserInfo(UserInfoModel userInfoModel) {
        return userInfoMappr.updateUserInfo(userInfoModel);
    }

    @Override
    public UserInfoModel getUserInfo(int uid) {
        return userInfoMappr.getUserInfo(uid);
    }
}
