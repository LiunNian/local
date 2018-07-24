package com.paper.train.dao;

import com.paper.train.model.MemberUnit;

import java.util.Map;

public interface MemberUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberUnit record);

    int insertSelective(MemberUnit record);

    MemberUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberUnit record);

    int updateByPrimaryKey(MemberUnit record);

    int selectUnit(String unit);

    Map selectByName(String unit);
}