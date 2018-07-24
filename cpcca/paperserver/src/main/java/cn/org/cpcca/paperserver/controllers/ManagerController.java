package cn.org.cpcca.paperserver.controllers;

import cn.org.cpcca.paperserver.models.ReturnDataModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员处理器
 */
@RestController
@RequestMapping(value="manager")
public class ManagerController {
    //创建工作人员
    @RequestMapping(value="addstaff")
    public ReturnDataModel newstaff(){
        return new ReturnDataModel("");
    }
    //修改单位信息
    @RequestMapping(value="updateinfo")
    public ReturnDataModel updateinfo(){
        return new ReturnDataModel("");
    }
    //创建下一级管理员
    @RequestMapping("test")
    public String test(){
        return "test success!";
    }
}
