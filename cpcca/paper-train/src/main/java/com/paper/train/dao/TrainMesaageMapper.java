package com.paper.train.dao;

import com.paper.train.model.TrainMesaage;

import java.util.List;
import java.util.Map;

public interface TrainMesaageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainMesaage record);

    int insertSelective(TrainMesaage record);

    TrainMesaage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainMesaage record);

    int updateByPrimaryKey(TrainMesaage record);

    List<Map> seleceAll();
}