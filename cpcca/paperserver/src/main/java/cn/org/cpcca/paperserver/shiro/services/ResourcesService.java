package cn.org.cpcca.paperserver.shiro.services;

import cn.org.cpcca.paperserver.shiro.models.ResourcesModel;
import cn.org.cpcca.paperserver.shiro.mappers.ResourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourcesService implements ResourcesServiceInterface{
    @Resource
    private ResourcesMapper resourcesMapper;
    @Override
    public List<ResourcesModel> getAll(){
        return resourcesMapper.getAll();
    }
}
