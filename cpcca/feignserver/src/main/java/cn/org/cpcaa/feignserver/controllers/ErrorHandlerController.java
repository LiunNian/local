package cn.org.cpcaa.feignserver.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandlerController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/error")
    public String error() {
        return "出现异常，确认您做的操作为常规操作";
    }
}
