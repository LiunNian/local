package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.FilesModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilesServiceInterface {
    int addFile(FilesModel filesModel);
    FilesModel getFileInfo(int id);
    List<FilesModel> getFileById(List<Integer> ids);
}
