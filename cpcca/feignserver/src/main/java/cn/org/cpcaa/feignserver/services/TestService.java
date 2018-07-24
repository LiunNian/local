package cn.org.cpcaa.feignserver.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestService {
    @Resource
    private Test test;

    @RequestMapping(value="/test")
    public String login(){
        return test.getString();
    }
}
