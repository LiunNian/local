package cn.org.cpcca.feignserver.paper.services;

import cn.org.cpcca.feignserver.paper.hystrics.ReviewServiceHystric;
import feign.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="service.paper",fallback = ReviewServiceHystric.class)
public interface ReviewService {

    @RequestMapping(value="/paperreview/review",method = RequestMethod.POST)
    String review(
            @RequestParam(value="itid",defaultValue = "0") int itid
    );
    @RequestMapping(value="/paperreview/list",method = RequestMethod.POST)
    String list(
           @RequestParam(value="itid",defaultValue = "0") int itid,
            @RequestParam(value="thid",defaultValue = "0") int thid,
            @RequestParam(value="did",defaultValue = "0") int did,
            @RequestParam(value="revid",defaultValue = "0") int revid
    );
    @RequestMapping(value="/paperreview/linkage",method = RequestMethod.POST)
    public String linkage(
            @RequestParam(value="tag",defaultValue = "") String tag,
            @RequestParam(value="id",defaultValue = "0") int id
    );
    @RequestMapping(value="/paperreview/ison",method = RequestMethod.POST)
    public String ison(
            @RequestParam(value="itid",defaultValue = "") int itid
    );
    @RequestMapping(value="/paperreview/addreview",method = RequestMethod.POST)
    public String addreview(
            @RequestParam(value="itid",defaultValue = "") int itid,
            @RequestParam(value="ids",defaultValue = "") Integer[] ids,
            @RequestParam(value="title",defaultValue = "") String title,
            @RequestParam(value="exids",defaultValue = "") Integer[] exids
    );
    @RequestMapping(value="/paperreview/closereview",method = RequestMethod.POST)
    public String closereview(
            @RequestParam(value="revid",defaultValue = "") int revid
    );
    @RequestMapping(value="/paperreview/deletereview",method = RequestMethod.POST)
    public String deletereview(
            @RequestParam(value="revid",defaultValue = "") int revid
    );
    @RequestMapping(value="/paperreview/expertall",method = RequestMethod.POST)
    public String expertAll();
    @RequestMapping(value="/paperreview/reviewexpert",method = RequestMethod.POST)
    public String reviewexpert(
            @RequestParam(value="revid",defaultValue = "") int revid
    );
    @RequestMapping(value="/paperreview/updateallot",method = RequestMethod.POST)
    public String updateAllot(
            @RequestParam(value="revpids",defaultValue = "") Integer[] revpids,
            @RequestParam(value="exids",defaultValue = "") Integer[] exids
    );
    @RequestMapping(value="/paperreview/progressandesult",method = RequestMethod.POST)
    public String progressAndResult(
            @RequestParam(value="revid",defaultValue = "") Integer revid
    );
    @RequestMapping(value="/paperreview/reviewsearch",method = RequestMethod.POST)
    public String reviewSearch(
            @RequestParam(value="revid",defaultValue = "") Integer revid,
            @RequestParam(value="thid",defaultValue = "0") Integer thid,
            @RequestParam(value="did",defaultValue = "0") Integer did,
            @RequestParam(value="progress",defaultValue = "") String progress,
            @RequestParam(value="result",defaultValue = "") String result,
            @RequestParam(value="keyword",defaultValue = "") String keyword
    );
    @RequestMapping(value="/paperreview/papers",method = RequestMethod.POST)
    public String papers(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value="limit",defaultValue = "10") String limit,
            @RequestParam(value="uid",defaultValue = "") Integer uid,
            @RequestParam(value="thid",defaultValue = "0") Integer thid,
            @RequestParam(value="did",defaultValue = "0") Integer did,
            @RequestParam(value="keyword",defaultValue = "") String keyword
    );
    @RequestMapping(value="/paperreview/paperinfo",method = RequestMethod.POST)
    public String paperInfo(
            @RequestParam(value="uid",defaultValue = "") Integer uid,
            @RequestParam(value="revpid",defaultValue = "") Integer revpid
    );
    @RequestMapping(value="/paperreview/score",method = RequestMethod.POST)
    public String score(
            @RequestParam(value="uid",defaultValue = "") Integer uid,
            @RequestParam(value="pexid",defaultValue = "") Integer pexid,
            @RequestParam(value="comment",defaultValue = "") String comment,
            @RequestParam(value="score",defaultValue = "") Integer score
    );
    @RequestMapping(value="/download/exportexcel",method = RequestMethod.POST)
    public Response exportExcel(
            @RequestParam(value="tag",defaultValue = "") String tag,
            @RequestParam(value="revid",defaultValue = "") int revid
    );
    @RequestMapping(value="/download/exportpapers",method = RequestMethod.POST)
    public Response exportPapers(
            @RequestParam(value="ids",defaultValue = "") Integer[] ids
    );
}
