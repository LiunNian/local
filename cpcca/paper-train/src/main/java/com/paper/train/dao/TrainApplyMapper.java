package com.paper.train.dao;

import com.paper.train.model.TrainApply;
import org.apache.ibatis.annotations.Param;

public interface TrainApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TrainApply record);

    int insertSelective(TrainApply record);

    TrainApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainApply record);

    int updateByPrimaryKey(TrainApply record);

    int selectCountByUIDandMID(@Param("uid") int uid, @Param("mid") int mid);

    int selectBymid(int mid);
}