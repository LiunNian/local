package cn.org.cpcca.feignserver.train.hystrics;

import cn.org.cpcca.feignserver.paper.hystrics.DefaultHystric;
import cn.org.cpcca.feignserver.train.services.TrainUserService;
import cn.org.cpcca.feignserver.train.models.TrainUser;
import org.springframework.stereotype.Component;

@Component
public class TrainUserServiceHystric extends DefaultHystric implements TrainUserService {

    @Override
    public Object trainRegist(TrainUser user) { return this.defaultZone(); }

    @Override
    public TrainUser trainLogin(String phone) {
        return null;
    }

    @Override
    public Object getVerifyCode(String phonenum) {
        return this.defaultZone();
    }

    @Override
    public Object checkPhone(String phone) {
        return this.defaultZone();
    }

    @Override
    public Object resetPassword(TrainUser user) {
        return this.defaultZone();
    }
}
