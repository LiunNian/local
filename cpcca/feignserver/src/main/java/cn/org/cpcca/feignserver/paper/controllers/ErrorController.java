package cn.org.cpcca.feignserver.paper.controllers;

import cn.org.cpcca.feignserver.paper.models.ReturnDataModel;
import cn.org.cpcca.feignserver.paper.shiro.controllers.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "错误信息", hidden = true)
@RestController
public class ErrorController extends BaseController {
    @ApiOperation(value = "",hidden = true)
    @RequestMapping(value="/shiro/error")
    public ReturnDataModel error(){
        ReturnDataModel returnDataModel = this.start();
        returnDataModel.setCode(3);
        returnDataModel.setMessage("您没有权限，使用本应用功能");
        return returnDataModel;
    }

}
