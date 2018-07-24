package cn.org.cpcca.feignserver.train.hystrics;

import cn.org.cpcca.feignserver.paper.hystrics.DefaultHystric;
import cn.org.cpcca.feignserver.train.services.TrainMemberUnitService;
import org.springframework.stereotype.Component;

@Component
public class TrainMemberUnitServiceHystric extends DefaultHystric implements TrainMemberUnitService {

    @Override
    public Object findPriceByUnit(String unitName, int messageId) {
        return this.defaultZone();
    }

}
