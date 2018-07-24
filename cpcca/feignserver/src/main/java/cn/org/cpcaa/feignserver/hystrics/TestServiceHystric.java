package cn.org.cpcaa.feignserver.hystrics;

import cn.org.cpcaa.feignserver.services.Test;
import org.springframework.stereotype.Component;

@Component
public class TestServiceHystric implements Test {
    @Override
    public String getString() {
        return "Sorry "+"!!";
    }
}
