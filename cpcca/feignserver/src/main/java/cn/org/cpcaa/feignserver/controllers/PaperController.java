package cn.org.cpcaa.feignserver.controllers;

import cn.org.cpcaa.feignserver.services.PaperService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
@RequestMapping(value="frontend")
public class PaperController {
    @Resource
    private PaperService paperService;
    @RequestMapping(value="login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        return paperService.login(username,password);
    }
    @RequestMapping(value="logout")
    public String logout(){
        return paperService.logout();
    }
}
