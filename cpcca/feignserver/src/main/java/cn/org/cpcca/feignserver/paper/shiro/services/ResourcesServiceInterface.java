package cn.org.cpcca.feignserver.paper.shiro.services;


import cn.org.cpcca.feignserver.paper.shiro.models.ResourcesModel;

import java.util.List;

public interface ResourcesServiceInterface {
    List<ResourcesModel> getAll();
    List<String> getPrems(int uid);
}
