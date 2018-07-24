package cn.org.cpcca.feignserver.train.hystrics;

import cn.org.cpcca.feignserver.paper.hystrics.DefaultHystric;
import cn.org.cpcca.feignserver.train.services.TrainMessageService;
import org.springframework.stereotype.Component;

@Component
public class TrainMessageServiceHystric extends DefaultHystric implements TrainMessageService {

    @Override
    public Object findAll() {
        return this.defaultZone();
    }

    @Override
    public Object findPrice(int messageId) {
        return this.defaultZone();
    }
}
