package cn.org.cpcca.paperserver.mappers;

import cn.org.cpcca.paperserver.models.FilesModel;
import cn.org.cpcca.paperserver.models.UserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.util.List;

@Repository
@Mapper
public interface FilesMapper {
    @Insert("insert into files(uri,name) values(#{filesModel.uri},#{filesModel.name})")
    @Options(useGeneratedKeys=true,keyProperty="filesModel.id")
    int addFile(@Param("filesModel") FilesModel  filesModel);
    //查看文件信息
    @Select("select * from files where id=#{id}")
    FilesModel getFileInfo(@Param("id") int id);
    @Select("<script>"
            +"SELECT * FROM files WHERE state=1 and id IN "
            +"<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>"
            +"#{item}"
            +"</foreach>"
            +"</script>")
    List<FilesModel> getFileById(@Param("ids") List<Integer> ids);
}
