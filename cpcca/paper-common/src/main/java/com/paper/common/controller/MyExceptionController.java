package com.paper.common.controller;

import com.paper.common.model.Result;
import com.paper.common.util.MyException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ControllerAdvice
public class MyExceptionController extends Exception {

    @ExceptionHandler(value = UnauthorizedException.class)
    @GetMapping(value = "/unAuthorized")
    @ResponseBody
    public Object unAuthorizedUrl(){
        return  new Result("您没有该功能的权限，请向管理员申请！", 3, false);
    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    @GetMapping(value = "/noLogin")
    @ResponseBody
    public Object noLogin(){
        return  new Result("您未登陆，请登陆后重试！", 2, false);
    }

//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Object unException(){
//        return  new Result("系统出错", 404, false);
//    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Object myException(){
        return  new Result("系统出错", 500, false);
    }
}
