package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.DirectionMapper;
import cn.org.cpcca.paperserver.mappers.ThemeMapper;
import cn.org.cpcca.paperserver.mappers.UserInfoMappr;
import cn.org.cpcca.paperserver.mappers.UserMapper;
import cn.org.cpcca.paperserver.models.DirectionModel;
import cn.org.cpcca.paperserver.models.ThemeModel;
import cn.org.cpcca.paperserver.models.UserInfoModel;
import cn.org.cpcca.paperserver.models.UserModel;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SynthesizeService implements SynthesizeServiceInterface {
    @Resource
    private ThemeMapper themeMapper;
    @Resource
    private DirectionMapper directionMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoMappr userInfoMappr;
    public List<Map<String,Object>> themes(int id) {
        Map<String,Object> dataMap;
        List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
        List<ThemeModel> themes= themeMapper.listTheme(id);
        for (ThemeModel themeModel:themes) {
            dataMap = new HashMap<String,Object>();
            dataMap.put("theme",themeModel);
            List<DirectionModel> directions = directionMapper.listDirection(themeModel.getId());
            dataMap.put("direction",directions);
            dataList.add(dataMap);
        }
        return dataList;
    }

    @Override
    public int updateUserInfo(UserInfoModel userInfoModel) {
        return userInfoMappr.updateUserInfo(userInfoModel);
    }
    @Override
    public Map<String,Boolean> updateUserInfo (UserModel userModel, UserInfoModel userInfoModel) {
        Map<String,Boolean> resultData = new HashMap<String,Boolean>();
        resultData.put("pwd",false);
        resultData.put("info",false);
        if(userInfoMappr.updateUserInfo(userInfoModel)>0){
            resultData.put("info",true);
        }
        if(userMapper.updatePassword(new Md5Hash(userModel.getPassword()).toString(),userModel.getUsername())>0) {
            resultData.put("pwd",true);
        }
        return resultData;
    }

    @Override
    public Map<String, Object> addTheme(ThemeModel themeModel,List<DirectionModel> directionModels) {
        Map<String,Object> returnData = new HashMap<String,Object>();
        themeMapper.addTheme(themeModel);
        returnData.put("thid",themeModel.getId());
        List<Integer> dids = new ArrayList<Integer>();
        for (DirectionModel directionModel:directionModels) {
            directionModel.setThid(themeModel.getId());
            directionMapper.addDirection(directionModel);
            dids.add(directionModel.getId());
        }
        returnData.put("dids",dids);
        return returnData;
    }

    @Override
    public Map<String, Object> updateTheme(ThemeModel themeModel, List<DirectionModel> directionModels,List<Integer> dids) {
        Map<String,Object> returnData = new HashMap<String,Object>();
        themeMapper.updateTheme(themeModel);
        returnData.put("thid",themeModel.getId());
        if(dids.size()>0) {
            directionMapper.deleteDirection(dids);
        }
        List<Integer> didList = new ArrayList<>();
        for (DirectionModel directionModel:directionModels) {
            directionModel.setThid(themeModel.getId());
            directionMapper.addDirection(directionModel);
            didList.add(directionModel.getId());
        }
        returnData.put("dids",didList);
        return returnData;
    }

    @Override
    public List<Integer> getDirectionByItemId(int itid) {
        return directionMapper.getDirectionByItemId(itid);
    }

    @Override
    public List<Integer> getDirectionByThemeId(int thid) {
        return directionMapper.getDirectionByThemeId(thid);
    }

}
