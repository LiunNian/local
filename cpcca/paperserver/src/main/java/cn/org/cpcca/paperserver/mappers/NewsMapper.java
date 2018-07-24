package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.NewsModel;
import cn.org.cpcca.paperserver.models.ThemeModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface NewsMapper {
    //添加资讯
    @Insert("insert into news (title,type,creater,source,content)value(" +
            "#{newsModel.title}," +
            "#{newsModel.type}," +
            "#{newsModel.creater}," +
            "#{newsModel.source}," +
            "#{newsModel.content})")
    @Options(useGeneratedKeys = true,keyProperty = "newsModel.id")
    int addNews(@Param("newsModel") NewsModel newsModel);
    //查看资讯信息
    @Select("select * from news where id=#{id}")
    NewsModel selectNews(@Param("id") int id);
    //查看资讯列表
    @Select("select * from news where type=#{type} and state=1 order by ctime DESC")
    List<NewsModel> listNews(@Param("type") int type);
    //修改资讯
    @Update("update news set " +
            "title=#{newsModel.title}," +
            "type=#{newsModel.type}," +
            "creater=#{newsModel.creater}," +
            "source=#{newsModel.source}," +
            "content=#{newsModel.content} where id=#{newsModel.id}")
    int updateNews(@Param("newsModel") NewsModel newsModel);
    //修改资讯状态(回收站和删除)
    @Update("<script>"
            +"update news set state=#{newsMap.state} where id in"
            +"<foreach item='item' index='index' collection='newsMap.nids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int updateState(@Param("newsMap") Map<String,Object> newsMap);
    //彻底删除
    @Delete("<script>"
            +"delete from news WHERE id IN "
            +"<foreach item='item' index='index' collection='nids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int deleteNews(@Param("nids") List<Integer> nids);

    //公告回收站
    @Select("select * from news where type=#{type} and state<1 order by ctime DESC")
    List<NewsModel> listNewsModelBin(@Param("type") int type);
}
