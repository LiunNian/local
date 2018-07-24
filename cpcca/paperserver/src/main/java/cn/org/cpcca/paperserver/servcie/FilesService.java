package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.FilesMapper;
import cn.org.cpcca.paperserver.models.FilesModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FilesService implements FilesServiceInterface{
    @Resource
    private FilesMapper filesMapper;

    @Override
    public int addFile(FilesModel filesModel) {
        filesMapper.addFile(filesModel);
        return filesModel.getId();
    }

    @Override
    public FilesModel getFileInfo(int id) {
        return filesMapper.getFileInfo(id);
    }

    @Override
    public List<FilesModel> getFileById(List<Integer> ids) {
        return filesMapper.getFileById(ids);
    }
}
