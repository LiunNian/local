package cn.org.cpcaa.feignserver.services;

import cn.org.cpcaa.feignserver.hystrics.TestServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="service.paper",fallback = TestServiceHystric.class)
public interface Test {
    @RequestMapping(value="/login",method= RequestMethod.POST)
    String getString();

}
