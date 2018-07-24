package cn.org.cpcca.paperserver.controllers;

import cn.org.cpcca.paperserver.shiro.services.ShiroService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SystemUpdateController {
    @Resource
    private ShiroService shiroService;
    @RequestMapping(value = "/role/refresh")
    public String  roleRefresh(){
        shiroService.loadFilterChainDefinitions();
        shiroService.updatePermission();
        return "OK";
    }
}
