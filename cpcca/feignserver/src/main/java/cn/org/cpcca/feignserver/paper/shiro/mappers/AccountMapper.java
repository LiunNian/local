package cn.org.cpcca.feignserver.paper.shiro.mappers;

import cn.org.cpcca.feignserver.paper.shiro.models.AccountModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountMapper {
    @Select("select * from users where state=1 order by ctime DESC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username")
    })
    List<AccountModel> listUsers();
    @Select("<script>" +
            "SELECT * FROM `users` where id in " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"+
            "#{item}"+
            "</foreach>"+
            "</script>")
    List<AccountModel> listUser(@Param("ids") List<Integer> ids);
    @Select("SELECT uid FROM `roles` as a join user_roles as b on a.id=b.rid where a.name in (#{role})")
    List<Integer> listUserId(@Param("role") String role);
    @Select("SELECT * FROM users where username=#{username}")
    AccountModel getNameByInfo(@Param("username") String username);
    @Update("update users set password=#{password} where username=#{username}")
    int updatePassword(@Param("password") String password, @Param("username") String username);
    @Select("SELECT * FROM users where id=#{uid}")
    AccountModel selectUser(@Param("uid") int uid);
    @Update("update users set flag=0 where id=#{uid}")
    int updateFlag(@Param("uid") int uid);
    //查看平台应用
    @Select("SELECT a.name FROM platforms as a join users_platforms as b on a.id = b.plid and b.uid=#{uid} group by a.name")
    List<String> getPlatforms(@Param("uid") int uid);
    //查看角色列表
    @Select("SELECT a.name FROM roles as a join users_roles as b on a.id = b.rid and b.uid=#{uid} group by a.name")
    List<String> getsRoleByUid(@Param("uid") int uid);
    @Select("SELECT plid FROM users_platforms where uid=#{uid} group by plid")
    List<Integer> getPlid(@Param("uid") int uid);
    /*//获取用户角色列表
    @Select("SELECT a.name as roles FROM permissions as a join roles_permissions as b on b.pid = a.id join users_roles as c on c.rid = b.rid and c.uid=#{uid}")
    List<String> getRoles(@Param("uid") int uid);
    //获取用户角色列表
    @Select("SELECT b.id FROM `user_roles` AS a join roles as b ON a.rid = b.id where a.uid = #{uid}")
    List<Integer> getRoleIds(@Param("uid") int uid);

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
*/
}
