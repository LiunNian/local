package cn.org.cpcca.feignserver.paper.shiro.services;


import cn.org.cpcca.feignserver.paper.shiro.mappers.ResourcesMapper;
import cn.org.cpcca.feignserver.paper.shiro.models.ResourcesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesService implements ResourcesServiceInterface{
    @Autowired
    private ResourcesMapper resourcesMapper;
    @Override
    public List<ResourcesModel> getAll(){
        return resourcesMapper.getAll();
    }
    @Override
    public List<String> getPrems(int uid){
        if(resourcesMapper.getPermsId(uid).contains(0)){
            return resourcesMapper.getPremAll();
        }else{
            return resourcesMapper.getPrems(uid);
        }

    }

}
