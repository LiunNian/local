package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.ItemModel;
import cn.org.cpcca.paperserver.models.StatementModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StatementMapper {
    //添加声明
    @Insert("insert into statements (title,creater,content)values(" +
           "#{statementModel.title}," +
           "#{statementModel.creater}," +
           "#{statementModel.content})")
    @Options(useGeneratedKeys = true,keyProperty = "statementModel.id")
    int addStatement(@Param("statementModel")StatementModel statementModel);
    //查看声明信息
    @Select("select * from statements where id=#{id}")
    StatementModel selectStatement(@Param("id") int id);
    //修改声明信息
    @Update("update statements set " +
            "title=#{statementModel.title}," +
            "creater=#{statementModel.creater}," +
            "content=#{statementModel.content} where id=#{statementModel.id}")
    int updateStatement(@Param("statementModel") StatementModel statementModel);
    //声明列表
    @Select("select * from statements where state=1 order by ctime DESC")
    List<StatementModel> listStatement();
    //全部声明列表
    @Select("select * from statements where state=1 order by ctime DESC")
    List<StatementModel> listStatementAll();
    //修改项目状态(回收站和删除)
    @Update("<script>"
            +"update statements set state=#{statementMap.state} where id in"
            +"<foreach item='item' index='index' collection='statementMap.ids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int updateState(@Param("statementMap") Map<String,Object> statementMap);
    //彻底删除
    @Delete("<script>"
            +"delete from statements WHERE id IN "
            +"<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int deleteStatement(@Param("ids") List<Integer> ids);

    //声明回收站
    @Select("select * from statements where state<1 order by ctime DESC")
    List<StatementModel> listStatementBin();

    @Select("<script>"
            +"SELECT * FROM statements WHERE state=1 and id IN "
            +"<foreach item='item' index='index' collection='stids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    List<StatementModel> getStatementById(@Param("stids") List<Integer> stids);
}
