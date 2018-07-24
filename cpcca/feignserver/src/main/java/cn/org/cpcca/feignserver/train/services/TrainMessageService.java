package cn.org.cpcca.feignserver.train.services;

import cn.org.cpcca.feignserver.train.hystrics.TrainMessageServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="service.train",fallback = TrainMessageServiceHystric.class)
public interface TrainMessageService {

    @PostMapping("/train/system/message/all")
    Object findAll();

    @PostMapping("/train/system/message/findPrice")
    Object findPrice(@RequestParam("id") int messageId);

}
