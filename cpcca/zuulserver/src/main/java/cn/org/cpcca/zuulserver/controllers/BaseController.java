package cn.org.cpcca.zuulserver.controllers;


import cn.org.cpcca.zuulserver.models.ReturnDataModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
    protected ReturnDataModel returnDataModel;
    protected Map<String, String> dataMap;

    public void start() {
        if (!this.isEmpty(returnDataModel)) {
            returnDataModel = null;
        }
        returnDataModel = new ReturnDataModel("操作失败");

        if (!isEmpty(dataMap)) {
            dataMap = null;
        }
        dataMap = new HashMap<String, String>();
    }

    public void returnData(int code, Object data, String message) {
        returnDataModel.setCode(code);
        returnDataModel.setData(data);
        returnDataModel.setMessage(message);
    }

    public  boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }else if ((obj instanceof List)) {
            return ((List) obj).size() == 0;
        }else if ((obj instanceof Map)) {
            return ((Map) obj).size() == 0;
        }else if ((obj instanceof String)) {
            return ((String) obj).trim().equals("");
        }
        return false;
    }
}