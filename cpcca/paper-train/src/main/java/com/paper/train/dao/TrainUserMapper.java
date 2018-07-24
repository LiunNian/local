package com.paper.train.dao;

import com.paper.train.model.AddTrainUser;
import com.paper.train.model.TrainUser;
import org.apache.ibatis.annotations.Param;

public interface TrainUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AddTrainUser addTrainUser);

    int insertSelective(TrainUser record);

    TrainUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TrainUser record);

    int updateByPrimaryKey(TrainUser record);

    int selectCountByPhone(String phone);

    int updatePassword(@Param("phonenum") String phonenum, @Param("newPassword")String newPassword, @Param("pwd")String pwd);

    TrainUser selectByName(String username);

}