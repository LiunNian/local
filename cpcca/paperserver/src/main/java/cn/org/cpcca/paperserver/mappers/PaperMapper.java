package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.PaperModel;
import cn.org.cpcca.paperserver.models.ReviewPaperModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PaperMapper {
    @Insert("insert into papers (uid,did,fid,prove,auids,title,subtitle,summary,keyword,content,reference,status)values(#{paperModel.uid},#{paperModel.did},#{paperModel.fid},#{paperModel.prove},#{paperModel.auids},#{paperModel.title},#{paperModel.subtitle},#{paperModel.summary},#{paperModel.keyword},#{paperModel.content},#{paperModel.reference},#{paperModel.status})")
    @Options(useGeneratedKeys = true,keyProperty = "paperModel.id")
    int addPaper(@Param("paperModel") PaperModel paperModel);
    @Select("select * from papers where id=#{id}")
    PaperModel selectPaper(@Param("id") int id);
    @Select("<script>select * from papers where state=1 and id in " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    List<PaperModel> selectPapers(@Param("ids") List<Integer> ids);
    @Select("<script>select b.uri from papers as a " +
            "join files as b on a.fid=b.id and a.state=1 " +
            "where a.id in"
            +"<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    List<String> selectPapersFile(@Param("ids") List<Integer> ids);
    @Select("<script>select count(*) from papers where state=1 and uid =#{uid} and status=#{status} and did in"
            +"<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int countPaperByStatus(@Param("ids") List<Integer> ids,@Param("uid") int uid,@Param("status") int status);
    @Select("<script>select * from papers where state=1 and uid = #{uid} and did in "
            +"<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    List<PaperModel> seachPaper(@Param("ids") List<Integer> ids,@Param("uid") int uid);
    @Select("<script>select a.id,a.title,b.title as direction,c.title as theme,d.title as item,a.did,a.ctime from papers as a " +
            "join directions as b on a.did=b.id " +
            "join themes as c on b.thid=c.id " +
            "join items as d on c.itid=d.id where a.state=1 and a.did in "
            +"<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    List<ReviewPaperModel> seachPapers(@Param("ids") List<Integer> ids);

    @Update("update papers set fid=#{paperModel.fid},prove=#{paperModel.prove},auids=#{paperModel.auids},title=#{paperModel.title},subtitle=#{paperModel.subtitle},summary=#{paperModel.summary},keyword=#{paperModel.keyword},content=#{paperModel.content},reference=#{paperModel.reference},status=#{paperModel.status} where id=#{paperModel.id}")
    int updatePaper(@Param("paperModel") PaperModel paperModel);
    @Update("update papers set state=#{paperModel.state} where id=#{paperModel.id}")
    int stampPaper(@Param("paperModel") PaperModel paperModel);
}
