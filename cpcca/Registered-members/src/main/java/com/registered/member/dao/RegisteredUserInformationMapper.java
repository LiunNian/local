package com.registered.member.dao;



import com.registered.member.model.UserInformation;
import com.registered.member.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RegisteredUserInformationMapper {

    Integer registeredUser(Users user);

    Users selectUser(int id);

    Integer insertUserinfo(UserInformation userInfo);

    String login(@Param("username") String username,@Param("password") String password);

    int selectCountByPhone(String phone);
    int update(Users user);

}
