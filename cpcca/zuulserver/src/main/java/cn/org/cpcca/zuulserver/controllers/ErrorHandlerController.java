package cn.org.cpcca.zuulserver.controllers;

import cn.org.cpcca.zuulserver.models.ReturnDataModel;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandlerController extends BaseController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/error")
    public ReturnDataModel error() {
        this.start();
        returnDataModel.setMessage("出现异常，确认您做的操作为常规操作");
        return returnDataModel;
    }
}
