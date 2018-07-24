package cn.org.cpcca.feignserver.paper.shiro.controllers;


import cn.org.cpcca.feignserver.paper.models.ReturnDataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "",hidden = true)
@RestController
public class ErrorHandlerController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/handle/error";
    }

    @ApiOperation(value = "",hidden = true)
    @RequestMapping(value="/handle/error")
    public ReturnDataModel error() {
        ReturnDataModel returnDataModel = new ReturnDataModel("出现异常，确认您做的操作为常规操作");
        returnDataModel.setCode(4);
        returnDataModel.setData("");
        returnDataModel.setRedirect("login");
        return returnDataModel;
    }
}
