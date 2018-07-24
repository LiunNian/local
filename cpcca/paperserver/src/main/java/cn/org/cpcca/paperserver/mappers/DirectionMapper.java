package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.DirectionModel;
import cn.org.cpcca.paperserver.models.ThemeModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface DirectionMapper {
    //添加方向
    @Insert("insert into directions (thid,title,description)values(#{directionModel.thid},#{directionModel.title},#{directionModel.description})")
    @Options(useGeneratedKeys = true,keyProperty = "directionModel.id")
    int addDirection(@Param("directionModel") DirectionModel directionModel);
    //查看方向信息
    @Select("select * from directions where id=#{id}")
    DirectionModel selectDirection(@Param("id") int id);
    //方向列表
    @Select("select * from directions where thid=#{thid} and state=1 order by ctime ASC")
    List<DirectionModel> listDirection(@Param("thid") int thid);
    //修改主题
    @Update("update directions set " +
            "itid=#{directionModel.thid}," +
            "title=#{directionModel.title}," +
            "description=#{directionModel.description} where id=#{directionModel.id}")
    int updateDirection(@Param("directionModel") DirectionModel directionModel);
    //修改主题状态(回收站和删除)
    @Update("<script>"
            +"update directions set state=#{directionMap.state} where id in"
            +"<foreach item='item' index='index' collection='directionMap.thids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int updateState(@Param("directionMap") Map<String,Object> directionMap);
    //彻底删除
    @Delete("<script>"
            +"delete from directions WHERE id IN "
            +"<foreach item='item' index='index' collection='dids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int deleteDirection(@Param("dids") List<Integer> dids);
    /*@Select("<script>"
            +"SELECT IDFA FROM t_xxx WHERE thid IN "
            +"<foreach item='item' index='index' collection='strList' open='(' separator=',' close=')'>"
            +"#{thids}"
            +"</foreach>"
            +"</script>")
    List<DirectionModel> listDirection(@Param("thids") List<Integer> thids);*/
    @Select("SELECT a.id FROM `directions` as a JOIN themes as b on a.thid=b.id join items as c on b.itid=c.id where c.id=#{itid}")
    List<Integer> getDirectionByItemId(@Param("itid") int itid);

    @Select("SELECT id FROM `directions` where thid=#{thid}")
    List<Integer> getDirectionByThemeId(@Param("thid") int thid);
}
