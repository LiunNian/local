package cn.org.cpcca.zuulserver.service;

import cn.org.cpcca.zuulserver.controllers.BaseController;
import cn.org.cpcca.zuulserver.models.ReturnDataModel;
import org.springframework.stereotype.Service;


@Service
public class ReturnDataService extends BaseController implements ReturnDataServiceInterface {
    @Override
    public ReturnDataModel returnErrorData(String message) {
        this.start();
        returnDataModel.setMessage(message);
        return returnDataModel;
    }
}
