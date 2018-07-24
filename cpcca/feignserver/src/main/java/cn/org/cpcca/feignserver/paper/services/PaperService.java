package cn.org.cpcca.feignserver.paper.services;

import cn.org.cpcca.feignserver.paper.hystrics.PaperServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@FeignClient(value="service.paper",fallback = PaperServiceHystric.class)
public interface PaperService {
    @RequestMapping(value="/login",method= RequestMethod.POST)
    String login(@RequestParam("username") String username, @RequestParam("password") String password);
    @RequestMapping(value="/logout",method= RequestMethod.POST)
    String logout();
    @RequestMapping(value="/paperfrontend/listitems",method= RequestMethod.POST)
    String listItems(@RequestParam(value = "page",defaultValue = "1") String page,@RequestParam(value="limit",defaultValue = "12") String limit );
    @RequestMapping(value="/paperfrontend/listitemend",method= RequestMethod.POST)
    String listItemEnd(@RequestParam(value = "page",defaultValue = "1") String page,@RequestParam(value="limit",defaultValue = "12") String limit );
    @RequestMapping(value="/paperfrontend/getthemes",method= RequestMethod.POST)
    String getThemes(@RequestParam("itid") String itid);
    @RequestMapping(value="/paperfrontend/addauthor",method = RequestMethod.POST)
    String addAuthor(
            @RequestParam("name") String name,
            @RequestParam("sex") int sex,
            @RequestParam("cardnum") String cardnum,
            @RequestParam("phonenum") String phonenum,
            @RequestParam("unitname") String unitname,
            @RequestParam("deptname") String deptname,
            @RequestParam("region") String region);
    @RequestMapping(value = "/paperfrontend/getauthors",method = RequestMethod.POST)
    String getAuthors(@RequestBody Map<String,List<Integer>> authorMap);

    @RequestMapping(value="/paperfrontend/selectitem",method = RequestMethod.POST)
    String selectItem(@RequestParam("id") int id);

    @RequestMapping(value="/paperfrontend/upload",method = RequestMethod.POST)
    String upload(@RequestParam("fileObject") MultipartFile file);

    @RequestMapping(value="/paperfrontend/addpaper", method = RequestMethod.POST)
    String addPaper(@RequestBody Map<String,Object> paperMap);
    @RequestMapping(value="/paperfrontend/listpaper", method = RequestMethod.POST)
    String listpaper(
            @RequestParam(value = "uid") int uid ,
            @RequestParam(value = "page",defaultValue = "1") int page ,
            @RequestParam(value = "limit",defaultValue = "5") int limit);
    @RequestMapping(value="/paperfrontend/listpaperbyitid", method = RequestMethod.POST)
    String listpaperByItid(
            @RequestParam("uid") int uid,
            @RequestParam("itid") int itid,
            @RequestParam(value = "page",defaultValue = "1") int page ,
            @RequestParam(value = "limit",defaultValue = "5") int limit);
    @RequestMapping(value="/paperfrontend/selectpaper", method = RequestMethod.POST)
    String selectPaper(@RequestParam("id") int id);
    @RequestMapping(value="/paperfrontend/updatepaper", method = RequestMethod.POST)
    public String updatepaper(@RequestBody Map<String,Object> paperMap);
    @RequestMapping(value="/paperfrontend/stamppaper", method = RequestMethod.POST)
    String stampPaper(@RequestParam("id") String id,@RequestParam("sign") String sign);
    @RequestMapping(value="/paperfrontend/getuserinfo",method = RequestMethod.POST)
    String getUserInfo(@RequestParam("uid") int uid);
    @RequestMapping(value="/paperfrontend/setuserinfo", method = RequestMethod.POST)
    String updateUserInfo(@RequestBody Map<String,Object> userInfoMap);

    @RequestMapping(value="/paperfrontend/listnews", method = RequestMethod.POST)
    String listNews(
            @RequestParam(value = "type") String type,
            @RequestParam(value = "page") String page,
            @RequestParam(value = "limit") String limit);

    @RequestMapping(value="/paperfrontend/selectnews",method = RequestMethod.POST)
    String selectNews(@RequestParam("id") int id);
    //获取文件信息
    @RequestMapping(value="/paperfrontend/getfileinfo",method = RequestMethod.POST)
    String getFileInfo(@RequestParam("id") int id);
    //查看声明信息
    @RequestMapping(value="/paperfrontend/searchstatement",method = RequestMethod.POST)
    String searchStatement(@RequestParam("id") int id);

    @RequestMapping(value="/paperfrontend/getstatementbyids",method = RequestMethod.POST)
    String getStatements(@RequestBody List<Integer> stids);

    @RequestMapping(value="/paperfrontend/getfilebyids",method = RequestMethod.POST)
    String getFiles(@RequestBody List<Integer> ids);

    @RequestMapping(value="/paperfrontend/getFiles",method = RequestMethod.POST)
    String getItem(@RequestParam("itemId") int id);

}
