package cn.org.cpcca.feignserver.train.hystrics;

import cn.org.cpcca.feignserver.paper.hystrics.DefaultHystric;
import cn.org.cpcca.feignserver.train.models.TrainApply;
import cn.org.cpcca.feignserver.train.services.TrainApplyService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class TrainApplyServiceHystric extends DefaultHystric implements TrainApplyService {
    @Override
    public Object addApply(TrainApply apply) {
        return this.defaultZone();
    }

    @Override
    public Object check(int uid, int mid) {
        return this.defaultZone();
    }

    @Override
    public Object uploadImg(MultipartFile file) {
        return this.defaultZone();
    }
}
