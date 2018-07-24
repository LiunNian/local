package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.DirectionModel;
import cn.org.cpcca.paperserver.models.ThemeModel;

import java.util.List;
import java.util.Map;

public interface DirectionServiceInterface {
    int addDirection(DirectionModel directionModel);
    List<DirectionModel> listDircetion(int thid);
    DirectionModel selectDirection(int id);
    int updateDircetion(DirectionModel directionModel);
    int updateState(Map<String,Object> directionMap);
    int deleteDircetion(List<Integer> dids);
}
