package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.UserInfoModel;

public interface UserInfoServiceInterface {
    int updateUserInfo(UserInfoModel userInfoModel);
    UserInfoModel getUserInfo(int uid);

}
