package cn.org.cpcca.feignserver.train.services;

import cn.org.cpcca.feignserver.train.hystrics.TrainUserServiceHystric;
import cn.org.cpcca.feignserver.train.models.TrainUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="service.train",fallback = TrainUserServiceHystric.class)
public interface TrainUserService {

    @PostMapping("/train/system/user/regist")
    Object trainRegist(@RequestBody TrainUser user);

    @PostMapping("/train/system/user/login")
    TrainUser trainLogin(@RequestParam("phone") String phone);

    @PostMapping("/train/system/user/verification")
    Object getVerifyCode(@RequestParam("phonenum") String phonenum);

    @PostMapping("/train/system/user/call")
    Object checkPhone(@RequestParam("phone") String phone);

    @PostMapping("/train/system/user/reset")
    Object resetPassword(@RequestBody TrainUser user);



}
