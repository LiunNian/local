package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.ItemModel;
import cn.org.cpcca.paperserver.models.PaperModel;
import cn.org.cpcca.paperserver.models.ThemeModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ThemeMapper {
    //添加主题
    @Insert("insert into themes(itid,title,description)values(" +
            "#{themeModel.itid}," +
            "#{themeModel.title}," +
            "#{themeModel.description})")
    @Options(useGeneratedKeys = true,keyProperty = "themeModel.id")
    int addTheme(@Param("themeModel")ThemeModel themeModel);
    //查看主题信息
    @Select("select * from themes where id=#{id}")
    ThemeModel selectTheme(@Param("id") int id);
    //查看主题列表
    @Select("select * from themes where itid=#{itid} and state=1 order by ctime ASC")
    List<ThemeModel> listTheme(@Param("itid") int itid);
    //修改主题
    @Update("update themes set " +
            "itid=#{themeModel.itid}," +
            "title=#{themeModel.title}," +
            "description=#{themeModel.description} where id=#{themeModel.id}")
    int updateTheme(@Param("themeModel") ThemeModel themeModel);
    //修改主题状态(回收站和删除)
    @Update("<script>"
            +"update themes set state=#{themeMap.state} where id in"
            +"<foreach item='item' index='index' collection='themeMap.thids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int updateState(@Param("themeMap") Map<String,Object> themeMap);
    //彻底删除
    @Delete("<script>"
            +"delete from themes WHERE id IN "
            +"<foreach item='item' index='index' collection='thids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int deleteTheme(@Param("thids") List<Integer> thids);
    //主题回收站
    @Select("select * from themes where itid=#{itid} and state<1 order by ctime DESC")
    List<ThemeModel> listThemeBin(@Param("itid") int itid);
}
