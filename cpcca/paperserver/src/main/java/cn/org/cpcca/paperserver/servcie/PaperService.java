package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.PaperMapper;
import cn.org.cpcca.paperserver.models.PaperModel;
import cn.org.cpcca.paperserver.models.ReviewPaperModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PaperService implements PaperServiceInterface {
    @Resource
    private PaperMapper paperMapper;
    @Override
    public int addPaper(PaperModel paperModel) {
        paperMapper.addPaper(paperModel);
        return paperModel.getId();
    }

    @Override
    public PaperModel selectPaper(int id) {
        return paperMapper.selectPaper(id);
    }

    @Override
    public List<PaperModel> selectPapers(List<Integer> ids) {
        return paperMapper.selectPapers(ids);
    }

    @Override
    public List<PaperModel> searchPaper(List<Integer> ids,int uid) {
        return paperMapper.seachPaper(ids,uid);
    }
    @Override
    public List<ReviewPaperModel> searchPapers(List<Integer> ids) {
        return paperMapper.seachPapers(ids);
    }

    @Override
    public int updatePaper(PaperModel paperModel) {
        return paperMapper.updatePaper(paperModel);
    }

    @Override
    public int stampPaper(PaperModel paperModel) {
        return paperMapper.stampPaper(paperModel);
    }

    @Override
    public int countPaperByStatus(List<Integer> ids,int uid,int status) {
        return paperMapper.countPaperByStatus(ids,uid,status);
    }

    @Override
    public List<String> selectPapersFile(List<Integer> ids) {
        return paperMapper.selectPapersFile(ids);
    }
}
