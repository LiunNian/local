package cn.org.cpcca.feignserver.paper.shiro.mappers;


import cn.org.cpcca.feignserver.paper.shiro.models.ResourcesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResourcesMapper {
    @Select("SELECT a.url as url,c.name as roles FROM `resources` as a join permissions_resources as b on b.reid = a.id join permissions as c on c.id=b.pid group by a.url,c.name")
    List<ResourcesModel> getAll();
    @Select("SELECT name FROM `permissions`")
    List<String> getPremAll();
    @Select("SELECT a.name as roles FROM permissions as a join roles_permissions as b on b.pid = a.id join users_roles as c on c.rid = b.rid and c.uid=#{uid}")
    List<String> getPrems(@Param("uid") int uid);
    @Select("SELECT a.pid FROM `roles_permissions` as a join roles as b on a.rid=b.id join users_roles as c on c.rid = b.id and c.uid = #{uid}")
    List<Integer> getPermsId(@Param("uid") int uid);
}
