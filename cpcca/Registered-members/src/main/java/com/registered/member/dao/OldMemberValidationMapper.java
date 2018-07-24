package com.registered.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OldMemberValidationMapper {

    /*
    因为老系统user表和会员单位表之间没有联系，所以验证信用分为两步
     */
    /*
    会员单位表信息验证，单位名称，法人，联络员姓名
     */
    String checkOldMember(@Param("unitName") String unitName, @Param("legalName") String  legalName,
                          @Param("contactName") String contactName,@Param("contactPhone") String contactPhone);

    /*
    user表登录账号，密码
     */
    String checkOldMemberUser(@Param("loginName") String loginName,@Param("loginPassword") String  loginPassword);
}
