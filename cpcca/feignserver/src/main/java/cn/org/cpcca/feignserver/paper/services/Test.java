package cn.org.cpcca.feignserver.paper.services;

import cn.org.cpcca.feignserver.paper.hystrics.TestServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="service.paper",fallback = TestServiceHystric.class)
public interface Test {
    @RequestMapping(value="/log",method= RequestMethod.POST)
    String getString();

}
