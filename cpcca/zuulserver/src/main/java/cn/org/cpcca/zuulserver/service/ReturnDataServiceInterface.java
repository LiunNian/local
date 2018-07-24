package cn.org.cpcca.zuulserver.service;

import cn.org.cpcca.zuulserver.models.ReturnDataModel;

public interface ReturnDataServiceInterface {
    ReturnDataModel returnErrorData(String message);
}
