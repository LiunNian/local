package cn.org.cpcaa.feignserver.services;

import cn.org.cpcaa.feignserver.hystrics.DefaultServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="service.paper",fallback = DefaultServiceHystric.class)
public interface PaperService {
    @RequestMapping(value="/login",method= RequestMethod.POST)
    String login(@RequestParam("username") String username, @RequestParam("password") String password);
    @RequestMapping(value="/logout",method= RequestMethod.POST)
    String logout();
}
