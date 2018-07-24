package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.UserInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserInfoMappr {
    @Update("update userinfo set region=#{userInfoModel.region},address=#{userInfoModel.address},linkman=#{userInfoModel.linkman},phonenum=#{userInfoModel.phonenum},email=#{userInfoModel.email} where uid=#{userInfoModel.uid}")
    int updateUserInfo(@Param("userInfoModel") UserInfoModel userInfoModel);
    @Select("select * from userinfo where id = #{id}")
    UserInfoModel getUserInfo(@Param("id") int id);
}
