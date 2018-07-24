package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.ItemTypeModel;
import cn.org.cpcca.paperserver.models.NewsTypeModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ItemTypeMapper {
    @Select("select * from item_type where state=1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    List<ItemTypeModel> getType();
    @Select("select name from item_type where state=1 and id=#{id}")
    String getTypeName(@Param("id") int id);

    @Select("SELECT DISTINCT fi.`name`,fi.uri, u.username , au.unitname unitname ,it.title, fi2.`name` name2 ," +
            " fi2.uri uri2, th.title themes, dr.title directions, pa.title papername\n" +
            "FROM papers pa\n" +
            "INNER JOIN directions dr ON pa.did = dr.id\n" +
            "INNER JOIN themes th ON th.id = dr.thid   \n" +
            "INNER JOIN items it ON th.itid = it.id\n" +
            "INNER JOIN files fi ON fi.id = pa.fid\n" +
            "LEFT JOIN files fi2 ON concat(',',pa.prove,',') LIKE concat('%,',fi2.id,',%')\n" +
            "INNER JOIN `authors` au ON au.id = pa.auids \n" +
            "INNER JOIN users u ON pa.uid = u.id \n" +
            "WHERE it.id = #{id} and pa.uid not in(1,2,35) and pa.state = 1 ")
    List<Map> getFilesByItem(@Param("id") int id);

// 统计哪个账号有多少篇论文  -->这段SQL是统计表格数据的执行以下就可以了
//    SELECT DISTINCT u.username, COUNT(*)
//    FROM papers pa
//    INNER JOIN directions dr ON pa.did = dr.id
//    INNER JOIN themes th ON th.id = dr.thid
//    INNER JOIN items it ON th.itid = it.id
//    INNER JOIN files fi ON fi.id = pa.fid
//    LEFT JOIN files fi2 ON concat(',',pa.prove,',') LIKE concat('%,',fi2.id,',%')
//    INNER JOIN `authors` au ON au.id = pa.auids
//    INNER JOIN users u ON pa.uid = u.id
//    WHERE it.id = 3 and pa.uid not in(1,2,35) and pa.state = 1            // it.id 是项目ID
//    GROUP BY u.username
}
