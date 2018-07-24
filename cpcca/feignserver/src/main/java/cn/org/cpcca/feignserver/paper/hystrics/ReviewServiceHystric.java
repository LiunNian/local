package cn.org.cpcca.feignserver.paper.hystrics;

import cn.org.cpcca.feignserver.paper.models.ReturnDataModel;
import cn.org.cpcca.feignserver.paper.services.ReviewService;
import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class ReviewServiceHystric  extends DefaultHystric implements ReviewService  {
    @Override
    public String review(int itid) {
        return this.defaultZone();
    }

    @Override
    public String list( int itid, int thid, int did, int revid) {
        return this.defaultZone();
    }

    @Override
    public String linkage(String tag, int id) {
        return this.defaultZone();
    }

    @Override
    public String ison(int itid) {
        return this.defaultZone();
    }

    @Override
    public String addreview(int itid, Integer[] ids, String title, Integer[] exids) {
        return this.defaultZone();
    }

    @Override
    public String closereview(int revid) {
        return this.defaultZone();
    }

    @Override
    public String deletereview(int revid) {
        return this.defaultZone();
    }

    @Override
    public String expertAll() {
        return this.defaultZone();
    }

    @Override
    public String reviewexpert(int revid) {
        return this.defaultZone();
    }

    @Override
    public String updateAllot(Integer[] revpids, Integer[] exids) {
        return this.defaultZone();
    }

    @Override
    public String progressAndResult(Integer revid) {
        return this.defaultZone();
    }

    @Override
    public String reviewSearch(Integer revid, Integer thid, Integer did, String progress, String result, String keyword) {
        return this.defaultZone();
    }

    @Override
    public String papers(String page, String limit, Integer uid, Integer thid, Integer did, String keyword) {
        return this.defaultZone();
    }

    @Override
    public String paperInfo(Integer uid, Integer revpid) {
        return this.defaultZone();
    }

    @Override
    public String score(Integer uid, Integer pexid, String comment, Integer score) {
        return this.defaultZone();
    }

    @Override
    public Response exportExcel(String tag, int revid) {
        System.out.println("----download excel error---");
        return null;
    }

    @Override
    public Response exportPapers(Integer[] ids) {
        return null;
    }
}
