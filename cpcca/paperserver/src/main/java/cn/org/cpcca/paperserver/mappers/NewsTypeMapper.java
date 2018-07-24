package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.NewsTypeModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface NewsTypeMapper {
    @Select("select * from news_type where state=1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    List<NewsTypeModel> getType();
    @Select("select name from news_type where state=1 and id=#{id}")
    String getTypeName(@Param("id") int id);
}
