package cn.org.cpcca.feignserver.paper.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "",hidden = true)
@RestController
public class TestService {
    @Resource
    private Test test;

    @ApiOperation(value = "",hidden = true)
    @RequestMapping(value="/test")
    public String login(){
        return test.getString();
    }
}
