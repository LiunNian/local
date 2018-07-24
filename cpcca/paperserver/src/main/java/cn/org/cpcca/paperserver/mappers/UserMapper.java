package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.UserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper {
    @Select("select * from users where state=1 order by ctime DESC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username")
    })
    List<UserModel> listUsers();
    @Select("<script>" +
            "SELECT * FROM `users` where id in " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"+
            "#{item}"+
            "</foreach>"+
            "</script>")
    List<UserModel> listUser(@Param("ids") List<Integer> ids);
    @Select("SELECT uid FROM `roles` as a join users_roles as b on a.id=b.rid where a.name in (#{role})")
    List<Integer> listUserId(@Param("role") String role);
    @Select("SELECT * FROM users where username=#{username}")
    UserModel getNameByInfo(@Param("username") String username);
    @Update("update users set password=#{password} where username=#{username}")
    int updatePassword(@Param("password") String password,@Param("username") String username);
    @Select("SELECT * FROM users where id=#{uid}")
    UserModel selectUser(@Param("uid") int uid);
    @Update("update users set flag=0 where id=#{uid}")
    int updateFlag(@Param("uid") int uid);
    //获取用户角色列表
    @Select("SELECT b.name FROM `users_roles` AS a join roles as b ON a.rid = b.id where a.uid = #{uid}")
    List<String> getRoles(@Param("uid") int uid);
    //获取用户角色列表
    @Select("SELECT b.id FROM `users_roles` AS a join roles as b ON a.rid = b.id where a.uid = #{uid}")
    List<Integer> getRoleIds(@Param("uid") int uid);
    //获取专家角色用户列表
    @Select("SELECT a.username,a.id FROM `users` as a join `users_roles` as b on a.id=b.uid AND b.rid=#{rid}")
    List<Map<String,Object>> getRoleNameByIds(@Param("rid") int rid);
    @Select("<script>" +
            "SELECT pid FROM `roles_permissions` where rid in " +
            "<foreach item='item' index='index' collection='rids' open='(' separator=',' close=')'>"+
            "#{item}"+
            "</foreach>"+
            "</script>")
    List<Integer> getPermissionIds(@Param("rids") List<Integer> rids);
    //获取用户权限列表
    @Select("<script>" +
            "SELECT name FROM `permissions` where id in " +
            "<foreach item='item' index='index' collection='pids' open='(' separator=',' close=')'>"+
            "#{item}"+
            "</foreach>"+
            "</script>")
    List<String> getPermissions(@Param("pids") List<Integer> pids);
}
