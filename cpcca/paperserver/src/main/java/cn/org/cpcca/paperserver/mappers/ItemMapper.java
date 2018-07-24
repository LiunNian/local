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
public interface ItemMapper {
    //添加项目
    @Insert("insert into items (uid,fuid,stid,type,title,description,instruction)values(" +
            "#{itemModel.uid}," +
            "#{itemModel.fuid}," +
            "#{itemModel.stid}," +
            "#{itemModel.type}," +
            "#{itemModel.title}," +
            "#{itemModel.description}," +
            "#{itemModel.instruction})")
    @Options(useGeneratedKeys = true,keyProperty = "itemModel.id")
    int addItem(@Param("itemModel")ItemModel itemModel);
    //查看项目信息
    @Select("select * from items where id=#{id}")
    ItemModel selectItem(@Param("id") int id);
    //修改项目信息
    @Update("update items set " +
            "fuid=#{itemModel.fuid}," +
            "stid=#{itemModel.stid}," +
            "type=#{itemModel.type}," +
            "title=#{itemModel.title}," +
            "description=#{itemModel.description}," +
            "instruction=#{itemModel.instruction} where id=#{itemModel.id}")
    int updateItem(@Param("itemModel") ItemModel itemModel);
    //项目列表
    @Select("select * from items where state>0 order by ctime DESC")
    List<ItemModel> listItemAll();

    //评审项目排序
    @Select("select a.*," +
            "(case when c.state is null then 1.5 else c.state end) as rstate," +
            "c.ctime as rctime,c.revid " +
            "from items AS a " +
            "left join (" +
            "   select itid,max(revid) as revid " +
            "   from review_item group by itid " +
            ") b on " +
            "   a.id = b.itid " +
            "left join review_item c on " +
            "   b.revid=c.revid " +
            "where a.state>0 " +
            "order by  " +
            "   (case when c.state is null then 1.5 else c.state end) ASC, " +
            "   c.ctime DESC, " +
            "   a.ctime DESC")
    List<ItemModel> listItemReview();
    //已完结项目列表
    @Select("select * from items where state=2 order by ctime DESC")
    List<ItemModel> listItemEnd();
    //项目列表id
    @Select("select * from items where state=1 or state = 2 order by ctime DESC")
    List<ItemModel> listItemNow();
    //修改项目状态(回收站和删除)
    @Update("<script>"
            +"update items set state=#{itemMap.state} where id in"
            +"<foreach item='item' index='index' collection='itemMap.itids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int updateState(@Param("itemMap") Map<String,Object> itemMap);
    //彻底删除
    @Delete("<script>"
            +"delete from items WHERE id IN "
            +"<foreach item='item' index='index' collection='itids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    int deleteItems(@Param("itids") List<Integer> itids);

    //项目回收站
    @Select("select * from items where state<1 order by ctime DESC")
    List<ItemModel> listItemBin();

}
