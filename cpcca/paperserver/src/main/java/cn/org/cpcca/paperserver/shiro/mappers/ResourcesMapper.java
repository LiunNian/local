package cn.org.cpcca.paperserver.shiro.mappers;

import cn.org.cpcca.paperserver.shiro.models.ResourcesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResourcesMapper {
    @Select({"SELECT a.url as url,c.name as roles FROM `resources` as a join permissions_resources as b on b.reid = a.id join permissions as c on c.id=b.pid group by a.url,c.name"})
    List<ResourcesModel> getAll();

}
