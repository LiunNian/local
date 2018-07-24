package cn.org.cpcca.feignserver.paper.hystrics;

import cn.org.cpcca.feignserver.paper.models.ReturnDataModel;
import com.alibaba.fastjson.JSON;

public class DefaultHystric {
    public String defaultZone(){
        ReturnDataModel returnDataModel = new ReturnDataModel("");
        returnDataModel.setMessage("网络繁忙，请稍后再试");
        return JSON.toJSONString(returnDataModel, true).toString();
    }
}
