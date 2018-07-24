package cn.org.cpcca.paperserver.servcie;


import cn.org.cpcca.paperserver.models.DirectionModel;
import cn.org.cpcca.paperserver.models.ThemeModel;
import cn.org.cpcca.paperserver.models.UserInfoModel;
import cn.org.cpcca.paperserver.models.UserModel;

import java.util.List;
import java.util.Map;

public interface SynthesizeServiceInterface {
    List<Map<String,Object>> themes(int id);
    int updateUserInfo(UserInfoModel userInfoModel);
    Map<String,Boolean> updateUserInfo(UserModel userModel, UserInfoModel userInfoModel);
    //主题和方向添加
    Map<String,Object> addTheme(ThemeModel themeModel, List<DirectionModel> directionModels);
    Map<String,Object> updateTheme(ThemeModel themeModel, List<DirectionModel> directionModels,List<Integer> dids);
    List<Integer> getDirectionByItemId(int itid);
    List<Integer> getDirectionByThemeId(int thid);
 }
