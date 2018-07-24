package cn.org.cpcca.feignserver.paper.shiro.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ShiroserverRunner implements CommandLineRunner {
    @Resource
    private ShiroService shiroService;
    @Override
    public void run(String... strings) throws Exception {
        shiroService.loadFilterChainDefinitions();
        shiroService.updatePermission();
    }

}
