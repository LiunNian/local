package cn.org.cpcca.feignserver.paper.hystrics;

import cn.org.cpcca.feignserver.paper.services.Test;
import org.springframework.stereotype.Component;

@Component
public class TestServiceHystric implements Test {
    @Override
    public String getString() {
        return "Sorry "+"!!";
    }
}
