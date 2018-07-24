package cn.org.cpcca.feignserver.train.services;

import cn.org.cpcca.feignserver.train.hystrics.TrainMemberUnitServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="service.train",fallback = TrainMemberUnitServiceHystric.class)
public interface TrainMemberUnitService {

    @PostMapping("/train/system/message/findUnit")
    Object findPriceByUnit(@RequestParam("name") String unitName , @RequestParam("messageid")int messageId);

}
