package cn.org.cpcca.paperserver;

import cn.org.cpcca.paperserver.shiro.services.ShiroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PaperserverRunner implements CommandLineRunner {
    @Resource
    private ShiroService shiroService;
    @Override
    public void run(String... strings) throws Exception {
        shiroService.loadFilterChainDefinitions();
        shiroService.updatePermission();
    }

}
