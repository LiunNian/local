package cn.org.cpcaa.feignserver.hystrics;

import cn.org.cpcaa.feignserver.services.PaperService;
import org.springframework.stereotype.Component;

@Component
public class DefaultServiceHystric implements PaperService{
    @Override
    public String login(String username, String password) {
        return "Sorry "+"!!";
    }

    @Override
    public String logout() {
        return "Sorry "+"!!";
    }
}
