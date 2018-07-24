package cn.org.cpcca.feignserver.paper.shiro.controllers;

import cn.org.cpcca.feignserver.paper.shiro.services.ShiroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "",hidden = true)
@RestController
public class SystemUpdateController {
    @Resource
    private ShiroService shiroService;

    @ApiOperation(value = "",hidden = true)
    @RequestMapping(value = "/role/refresh")
    public String  roleRefresh(){
        shiroService.loadFilterChainDefinitions();
        shiroService.updatePermission();
        return "OK";
    }
}
