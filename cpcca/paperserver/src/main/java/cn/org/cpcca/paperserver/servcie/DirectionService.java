package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.DirectionMapper;
import cn.org.cpcca.paperserver.models.DirectionModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.RespectBinding;
import java.util.List;
import java.util.Map;

@Service
public class DirectionService implements DirectionServiceInterface{
    @Resource
    private DirectionMapper directionMapper;

    @Override
    public int addDirection(DirectionModel directionModel) {
        directionMapper.addDirection(directionModel);
        return directionModel.getId();
    }

    @Override
    public List<DirectionModel> listDircetion(int thid) {
        return directionMapper.listDirection(thid);
    }

    @Override
    public DirectionModel selectDirection(int id) {
        return directionMapper.selectDirection(id);
    }

    @Override
    public int updateDircetion(DirectionModel directionModel) {
        return directionMapper.updateDirection(directionModel);
    }

    @Override
    public int updateState(Map<String, Object> directionMap) {
        return directionMapper.updateState(directionMap);
    }

    @Override
    public int deleteDircetion(List<Integer> dids) {
        return directionMapper.deleteDirection(dids);
    }


}
