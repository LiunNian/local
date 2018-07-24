package com.registered.member.dao;

import com.registered.member.model.MemberInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@Mapper
public interface MemberRegisteredMapper {

    Integer memberRegistered(MemberInformation obj);

    Integer memberType(@RequestParam("name") String name);

    String getMemberType(@RequestParam("name") String name);

    Integer selectCountByPhone(@RequestParam("contactPhone") String contactPhone);

    MemberInformation selectInfo(@RequestParam("id") Integer id);

    Integer updateMember(MemberInformation obj);

    MemberInformation memberinfo(@RequestParam("uid") String uid);

}
