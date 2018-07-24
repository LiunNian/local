package com.paper.train.service.impl;

import com.paper.common.model.Result;
import com.paper.train.dao.TrainMesaageMapper;
import com.paper.train.model.TrainMesaage;
import com.paper.train.service.TrainMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("trainMessageService")
@Transactional
public class TrainMessageServiceImpl implements TrainMessageService {

    @Autowired
    private TrainMesaageMapper trainMesaageMapper;

    @Override
    public Object findAll() {
        try {
            List list = trainMesaageMapper.seleceAll();
            return new Result("查询成功", list, 1, true);
        }catch (Exception e){
            e.printStackTrace();
            return new Result("查询失败", 0, false);
        }
    }

    @Override
    public Object findPrice(int messageId) {
        TrainMesaage trainMesaage = trainMesaageMapper.selectByPrimaryKey(messageId);
        return new Result("查询成功", trainMesaage.getPrice(), 1, true);
    }
}
