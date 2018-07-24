package cn.org.cpcca.paperserver.shiro.services;

import cn.org.cpcca.paperserver.shiro.models.ResourcesModel;

import java.util.List;

public interface ResourcesServiceInterface {
    List<ResourcesModel> getAll();
}
