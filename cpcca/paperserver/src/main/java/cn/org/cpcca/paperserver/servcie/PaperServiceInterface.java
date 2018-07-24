package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.PaperModel;
import cn.org.cpcca.paperserver.models.ReviewPaperModel;

import java.util.List;
import java.util.Map;

public interface PaperServiceInterface {
    int addPaper(PaperModel papersModel);
    PaperModel selectPaper(int id);
    List<PaperModel> selectPapers(List<Integer> ids);
    List<PaperModel> searchPaper(List<Integer> ids,int uid);
    List<ReviewPaperModel> searchPapers(List<Integer> ids);
    int updatePaper(PaperModel paperModel);
    int stampPaper(PaperModel paperModel);
    int countPaperByStatus(List<Integer> ids,int uid,int status);
    List<String> selectPapersFile(List<Integer> ids);
}
