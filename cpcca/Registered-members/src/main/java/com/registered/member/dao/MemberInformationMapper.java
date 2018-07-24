package com.registered.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface MemberInformationMapper {

    /*
    获取老会员的信息
     */
    Map findOldMember(@Param("code") String code);
    /*
    查询新会员系统的信息
     */
    Map Cheakmember(@Param("id") String id);

    String findMess(@Param("unitName") String unitName, @Param("legalName") String  legalName,
                               @Param("contactName") String contactName,@Param("contactPhone") String contactPhone);
}
