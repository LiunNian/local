package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.AuthorModel;
import cn.org.cpcca.paperserver.models.ItemModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthorMapper {
    @Insert("insert into authors (name,sex,cardnum,email,unitname,phonenum,deptname,region)values(#{authorModel.name},#{authorModel.sex},#{authorModel.cardnum},#{authorModel.email},#{authorModel.unitname},#{authorModel.phonenum},#{authorModel.deptname},#{authorModel.region})")
    @Options(useGeneratedKeys = true,keyProperty = "authorModel.id")
    int addAuthor(@Param("authorModel") AuthorModel authorModel);
    @Select("<script>"
            +"SELECT * FROM authors WHERE state=1 " +
            "and id in"
            +"<foreach item='item' index='index' collection='auids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
//            +" order by FIELD("
//            +"<foreach item='item' index='index' collection='auids' open='id,' separator=',' close=')'>"
//            +"#{item}"
//            +"</foreach>"
            +"</script>")
    List<AuthorModel> listAuthor(@Param("auids") List<Integer> auids);
}
