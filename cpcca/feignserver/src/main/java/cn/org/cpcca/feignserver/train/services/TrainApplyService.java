package cn.org.cpcca.feignserver.train.services;

import cn.org.cpcca.feignserver.train.hystrics.TrainApplyServiceHystric;
import cn.org.cpcca.feignserver.train.models.TrainApply;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value="service.train",fallback = TrainApplyServiceHystric.class)
public interface TrainApplyService {

    @PostMapping("/train/system/apply/addapply")
    Object addApply(@RequestBody TrainApply apply);

    @PostMapping("/train/system/apply/checkapply")
    Object check(@RequestParam("uid") int uid , @RequestParam("mid")int mid);

    @PostMapping("/train/system/apply/upload")
    Object uploadImg( @RequestParam("file") MultipartFile file);
}
