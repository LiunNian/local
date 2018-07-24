package cn.org.cpcca.feignserver.paper.shiro.controllers;


import cn.org.cpcca.feignserver.paper.models.ReturnDataModel;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class BaseController {
    public ReturnDataModel start(){
        ReturnDataModel returnDataModel = new ReturnDataModel("操作失败");
        return returnDataModel;
    }
    //组装返回数据
    public void returnData(ReturnDataModel returnDataModel,int code,Object data,String message){
        returnDataModel.setCode(code);
        returnDataModel.setData(data);
        returnDataModel.setMessage(message);
    }
    public void returnData(ReturnDataModel returnDataModel,int code,Object data,String message,String redirect){
        returnDataModel.setCode(code);
        returnDataModel.setData(data);
        returnDataModel.setMessage(message);
        returnDataModel.setRedirect(redirect);
    }
    //反射设置对象属性值
    public void changeObjeceProperty(Class tempClass,Object object,Map<String,Object> tempMap){
        Field[] tempFields  = tempClass.getDeclaredFields();
        for (Field tempField:tempFields) {
            for (String key:tempMap.keySet()) {
                if(tempField.getName().equals(key)){
                    tempField.setAccessible(true);
                    try {
                        tempField.set(object,tempMap.get(key));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //判断是否为空
    public boolean isEmpty(Object obj){
        if(obj == null){
            return true;
        }else if((obj instanceof List)){
            return ((List) obj).size() == 0;
        }else if ((obj instanceof Map)){
            return ((Map) obj).size() == 0;
        }else if ((obj instanceof String)){
            return ((String) obj).trim().equals("");
        }
        return false;
    }
}
