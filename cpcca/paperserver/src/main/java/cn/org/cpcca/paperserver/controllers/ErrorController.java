package cn.org.cpcca.paperserver.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @RequestMapping(value="/log")
    public String error(){
        return "您没有权限，请联系管理员";
    }

}
