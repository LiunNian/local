package cn.org.cpcca.feignserver.paper.controllers;

import cn.org.cpcca.feignserver.paper.services.FileUploadService;
import cn.org.cpcca.feignserver.paper.services.ReviewService;
import cn.org.cpcca.feignserver.paper.util.FeignSpringFormEncoder;
import feign.Feign;
import feign.Response;
import io.swagger.annotations.*;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@Api(tags = "论文评审",value = "论文评审管理")
@RequestMapping(value="review",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
public class ReviewController {
    @Resource
    private ReviewService reviewService;
    @Resource
    private FileUploadService fileUploadService;
    @RequestMapping(value = "review")
    @ApiOperation(value = "评审列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itid", value = "论文项目id", defaultValue = "1", required = true, paramType = "query", dataType = "Integereger"),
    })
    public String review(
            @RequestParam(value = "itid", defaultValue = "0") Integer itid
    ) {
        return reviewService.review(itid);
    }

    @RequestMapping(value = "list")
    @ApiOperation(value = "评审论文列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itid", value = "论文项目id", defaultValue = "0", required = true, paramType = "query", dataType = "Integereger"),
            @ApiImplicitParam(name = "thid", value = "论文主题id", defaultValue = "0", required = false, paramType = "query", dataType = "Integereger"),
            @ApiImplicitParam(name = "did", value = "论文方向id", defaultValue = "0", required = false, paramType = "query", dataType = "Integereger"),
            @ApiImplicitParam(name = "revid", value = "评审id", defaultValue = "0", required = false, paramType = "query", dataType = "Integereger"),
    })
    public String list(
            @RequestParam(value = "itid", defaultValue = "0") Integer itid,
            @RequestParam(value = "thid", defaultValue = "0") Integer thid,
            @RequestParam(value = "did", defaultValue = "0") Integer did,
            @RequestParam(value = "revid", defaultValue = "0") Integer revid) {
        return reviewService.list(itid, thid, did, revid);
    }

    @RequestMapping(value = "linkage")
    @ApiOperation(value = "项目,主题,方向联动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tag", value = "参数标识itid/thid", defaultValue = "itid", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "id", defaultValue = "1", required = false, paramType = "query", dataType = "Integereger"),
    })
    public String linkage(
            @RequestParam(value = "tag", defaultValue = "") String tag,
            @RequestParam(value = "id", defaultValue = "0") Integer id
    ) {
        return reviewService.linkage(tag, id);
    }

    @RequestMapping(value = "ison")
    @ApiOperation(value = "判断是否有初审")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itid", value = "活动项目id", defaultValue = "1", required = false, paramType = "query", dataType = "Integereger"),
    })
    public String ison(
            @RequestParam(value = "itid", defaultValue = "") Integer itid
    ) {
        return reviewService.ison(itid);
    }

    @RequestMapping(value = "addreview")
    @ApiOperation(value = "创建审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itid", value = "论文活动项目", defaultValue = "1", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "ids", value = "论文列表列表", defaultValue = "1", required = false, paramType = "query", dataType = "Array[String]"),
            @ApiImplicitParam(name = "title", value = "评审名称", defaultValue = "1", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "exids", value = "专家列表", defaultValue = "1", required = false, paramType = "query", dataType = "Array[String]"),
    })
    public String addreview(
            @RequestParam(value = "itid", defaultValue = "") Integer itid,
            @RequestParam(value = "ids", defaultValue = "") Integer[] ids,
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "exids", defaultValue = "") Integer[] exids
    ) {
        return reviewService.addreview(itid, ids, title, exids);
    }

    @RequestMapping(value = "closereview")
    @ApiOperation(value = "结束审核和重新开启审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "revid", value = "评审id", defaultValue = "1", required = false, paramType = "query", dataType = "Integer"),
    })
    public String closereview(
            @RequestParam(value = "revid", defaultValue = "") int revid
    ) {
        return reviewService.closereview(revid);
    }

    //删除审核
    @RequestMapping(value = "deletereview")
    @ApiOperation(value = "删除审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "revid", value = "评审id", defaultValue = "1", required = false, paramType = "query", dataType = "Integer"),
    })
    public String deletereview(
            @RequestParam(value = "revid", defaultValue = "") int revid
    ) {
        return reviewService.deletereview(revid);
    }

    @RequestMapping(value = "expertall")
    @ApiOperation(value = "全部专家列表")
    public String expertAll() {
        return reviewService.expertAll();
    }


    @RequestMapping(value = "reviewexpert")
    @ApiOperation(value = "评审专家列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "revid", value = "评审id", defaultValue = "1", required = false, paramType = "query", dataType = "Integer"),
    })
    public String reviewexpert(
            @RequestParam(value = "revid", defaultValue = "") int revid
    ) {
        return reviewService.reviewexpert(revid);
    }


    @RequestMapping(value = "updateallot")
    @ApiOperation(value = "指定专家分配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "revpids", value = "评审论文id", defaultValue = "", required = false, paramType = "query", dataType = "Array[String]"),
            @ApiImplicitParam(name = "exids", value = "专家id", defaultValue = "", required = false, paramType = "query", dataType = "Array[String]"),
    })
    public String updateAllot(
            @RequestParam(value = "revpids", defaultValue = "") Integer[] revpids,
            @RequestParam(value = "exids", defaultValue = "") Integer[] exids
    ) {
        return reviewService.updateAllot(revpids, exids);
    }

    @RequestMapping("progressandesult")
    @ApiOperation(value = "审核进度和审核结果列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "revid", value = "评审id", defaultValue = "", required = true, paramType = "query", dataType = "Integer"),
    })
    public String progressAndResult(
            @RequestParam(value = "revid", defaultValue = "") Integer revid
    ) {
        return reviewService.progressAndResult(revid);
    }

    @RequestMapping(value = "reviewsearch")
    @ApiOperation(value = "检索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "revid", value = "评审id", defaultValue = "", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "thid", value = "主题id", defaultValue = "", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "did", value = "方向id", defaultValue = "", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "progress", value = "审核进程", defaultValue = "", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "result", value = "审核结果", defaultValue = "", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "keyword", value = "关键词", defaultValue = "", required = false, paramType = "query", dataType = "String"),
    })
    public String reviewSearch(
            @RequestParam(value = "revid", defaultValue = "") Integer revid,
            @RequestParam(value = "thid", defaultValue = "0") Integer thid,
            @RequestParam(value = "did", defaultValue = "0") Integer did,
            @RequestParam(value = "progress", defaultValue = "") String progress,
            @RequestParam(value = "result", defaultValue = "") String result,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        return reviewService.reviewSearch(revid, thid, did, progress, result, keyword);
    }

    @RequestMapping(value = "papers")
    @ApiOperation(value = "专家任务论文列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "每页条数", defaultValue = "10", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "thid", value = "主题id", defaultValue = "", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "did", value = "方向id", defaultValue = "", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "keyword", value = "关键词", defaultValue = "", required = false, paramType = "query", dataType = "String"),
    })
    public String papers(
            HttpServletRequest request,
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "limit", defaultValue = "10") String limit,
            @RequestParam(value = "thid", defaultValue = "0") Integer thid,
            @RequestParam(value = "did", defaultValue = "0") Integer did,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        int uid = (int) request.getSession().getAttribute("uid");
        return reviewService.papers(page, limit, uid, thid, did, keyword);
    }

    @RequestMapping(value = "paperinfo")
    @ApiOperation(value = "评审论文详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "revpid", value = "评审论文id", defaultValue = "", required = true, paramType = "query", dataType = "Integer"),
    })
    public String paperInfo(
            HttpServletRequest request,
            @RequestParam(value = "revpid", defaultValue = "") Integer revpid
    ) {
        int uid = (int) request.getSession().getAttribute("uid");
        return reviewService.paperInfo(uid, revpid);
    }

    @RequestMapping(value = "score")
    @ApiOperation(value = "评审论文打分")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pexid", value = "标识id", defaultValue = "", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "comment", value = "评语", defaultValue = "", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "score", value = "分数", defaultValue = "", required = false, paramType = "query", dataType = "Integer"),
    })
    public String score(
            HttpServletRequest request,
            @RequestParam(value = "pexid", defaultValue = "") Integer pexid,
            @RequestParam(value = "comment", defaultValue = "") String comment,
            @RequestParam(value = "score", defaultValue = "") Integer score
    ) {

        int uid = (int) request.getSession().getAttribute("uid");
        return reviewService.score(uid, pexid, comment, score);
    }

    @RequestMapping(value = "exportexcel", method = RequestMethod.GET)
    @ApiOperation(value = "导出结果列表(专家打分)和进程列表(当前进程和结果)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tag", value = "结果列表(result)/进程列表(progress)", defaultValue = "", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "revid", value = "评审id", defaultValue = "", required = false, paramType = "query", dataType = "Integer"),
    })
    public ResponseEntity exportExcel(
            @RequestParam(value = "tag", defaultValue = "") String tag,
            @RequestParam(value = "revid", defaultValue = "") int revid,
            HttpServletResponse response
    ) {
        Response responseData = reviewService.exportExcel(tag, revid);
        //System.out.println(JSON.toJSONString(responseData));
        Map<String, Collection<String>> headers = responseData.headers();
        HttpHeaders httpHeaders = new HttpHeaders();

        headers.forEach((key, values) -> {
            List<String> headerValues = new LinkedList<>();
            headerValues.addAll(values);
            httpHeaders.put(key, headerValues);
        });

        Response.Body body = responseData.body();
        try {
            InputStream inputStream = body.asInputStream();
            InputStreamResource resource = new InputStreamResource(inputStream);
                        System.out.println(ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .headers(httpHeaders)
                    .body(resource));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .headers(httpHeaders)
                    .body(resource);
        } catch (IOException e) {
            System.out.println("----error exportexcel---");
        }
        return null;
    }

    @RequestMapping(value="importexcel",method = RequestMethod.POST)
    @ApiOperation(value = "评审进程excel导入")
    public String excelUpload(
            @ApiParam(value = "excel文件", required = true) @RequestParam(value = "file") MultipartFile file,
            @ApiParam(value = "评审id", required = true) @RequestParam(value = "revid",defaultValue = "0") Integer revid,
            HttpServletResponse response
    ) {
        FileUploadService fileUploadServiceImpl = Feign.builder()
                //.decoder(new JacksonDecoder())
                .encoder(new FeignSpringFormEncoder())
                .contract(new SpringMvcContract())
                .target(FileUploadService.class, "http://localhost:8081");
        return fileUploadServiceImpl.excelUpload(file,revid);
    }
    @RequestMapping(value="exportpapers",method = RequestMethod.GET)
    @ApiOperation(value = "论文导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "论文id", defaultValue = "", required = true, paramType = "query", dataType = "Array[integer]"),
    })
    public ResponseEntity exportPapers(
            @RequestParam(value="ids",defaultValue = "") Integer[] ids,
            HttpServletResponse response
    ){
        Response responseData = reviewService.exportPapers(ids);
        //System.out.println(JSON.toJSONString(responseData));
        Map<String, Collection<String>> headers = responseData.headers();
        HttpHeaders httpHeaders = new HttpHeaders();

        headers.forEach((key, values) -> {
            List<String> headerValues = new LinkedList<>();
            headerValues.addAll(values);
            httpHeaders.put(key, headerValues);
        });

        Response.Body body = responseData.body();
        try {
            InputStream inputStream = body.asInputStream();//HttpURLInputStream
            InputStreamResource resource = new InputStreamResource(inputStream);
//            System.out.println(ResponseEntity
//                    .ok()
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .headers(httpHeaders)
//                    .body(resource));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .headers(httpHeaders)
                    .body(resource);
        } catch (IOException e) {
            System.out.println("----error exportwordszip---");
        }
        return null;
    }
}
