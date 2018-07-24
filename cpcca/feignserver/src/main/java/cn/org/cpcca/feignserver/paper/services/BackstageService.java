package cn.org.cpcca.feignserver.paper.services;

import cn.org.cpcca.feignserver.paper.hystrics.BackstageServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value="service.paper",fallback = BackstageServiceHystric.class)
public interface BackstageService {
    @RequestMapping(value="/login",method= RequestMethod.POST)
    String login(@RequestParam("username") String username, @RequestParam("password") String password);
    @RequestMapping(value="/logout",method= RequestMethod.POST)
    String logout();

    //用户列表
    @RequestMapping(value="/staff/listuser",method= RequestMethod.POST)
    String listUser();

    //用户列表
    @RequestMapping(value="/staff/listitemtype",method= RequestMethod.POST)
    String listItemType();

    //论文活动
    @RequestMapping(value="/staff/additem",method= RequestMethod.POST)
    String addItem(
            @RequestParam("uid") int uid,
            @RequestParam("fuid") String fuid,
            @RequestParam("stid") String stid,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("instruction") String instruction);

    @RequestMapping(value="/staff/selectitem",method= RequestMethod.POST)
    String selectItem(@RequestParam("id") String id);
    @RequestMapping(value="/staff/updateitem",method= RequestMethod.POST)
    String updateItem(@RequestBody Map<String,Object> itemMap);

    @RequestMapping(value="/staff/itemstate",method= RequestMethod.POST)
    String updateItemState(@RequestBody Map<String,Object> itemMap);

    @RequestMapping(value="/staff/deleteitem",method = RequestMethod.POST)
    String deleteItem(@RequestBody List<Integer> itids);
    @RequestMapping(value="/staff/listitem",method = RequestMethod.POST)
    String listItem(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "10") String limit
    );
    @RequestMapping(value="/staff/listitembin",method = RequestMethod.POST)
    String listItemBin(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "10") String limit
    );
    //主题
    @RequestMapping(value="/staff/addtheme",method = RequestMethod.POST)
    public String addTheme(@RequestBody Map<String,Object> themeMap);

    @RequestMapping(value="/staff/selecttheme",method= RequestMethod.POST)
    String selectTheme(@RequestParam("id") String id);
    @RequestMapping(value="/staff/listtheme",method = RequestMethod.POST)
    String listTheme(
            @RequestParam("itid") String thids,
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "10") String limit
    );
    @RequestMapping(value="/staff/listthemebin",method = RequestMethod.POST)
    String listThemeBin(
            @RequestParam("itid") String thids,
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "10") String limit
    );

    @RequestMapping(value="/staff/updatetheme",method= RequestMethod.POST)
    String updateTheme(@RequestBody Map<String,Object> themeMap);

    @RequestMapping(value="/staff/themestate",method= RequestMethod.POST)
    String updateThemeState(@RequestBody Map<String,Object> themeMap);

    @RequestMapping(value="/staff/deletetheme",method = RequestMethod.POST)
    String deleteTheme(@RequestBody List<Integer> thids);

    //方向
    @RequestMapping(value="/staff/adddirection",method = RequestMethod.POST)
    String addDirection(
            @RequestParam("thid") String thid,
            @RequestParam("title") String title,
            @RequestParam("description") String description);
    @RequestMapping(value="/staff/selectdirection",method= RequestMethod.POST)
    String selectDirection(@RequestParam("id") String id);
    @RequestMapping(value="/staff/listdirection",method = RequestMethod.POST)
    String listDirection(
            @RequestParam("thid") String thid,
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "10") String limit);

    @RequestMapping(value="/staff/updatedirection",method= RequestMethod.POST)
    String updateDirection(@RequestBody Map<String,Object> directionMap);

    @RequestMapping(value="/staff/directionstate",method= RequestMethod.POST)
    String updateDirecitonState(@RequestBody Map<String,Object> directionMap);

    @RequestMapping(value="/staff/deletedireciton",method = RequestMethod.POST)
    String deleteDirection(@RequestBody List<Integer> dids);
    //资讯
    @RequestMapping(value="/staff/getnewstype",method = RequestMethod.POST)
    String getNewsType();

    @RequestMapping(value="/staff/addnews",method = RequestMethod.POST)
    String addNews(@RequestBody Map<String,Object> newsMap);
    @RequestMapping(value="/staff/selectnews",method = RequestMethod.POST)
    String selectNews(@RequestParam("id") int id);
    @RequestMapping(value="/staff/listnews",method = RequestMethod.POST)
    String listNews(
            @RequestParam("type") String type,
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "5") String limit
    );
    @RequestMapping(value="/staff/listnewsbin",method = RequestMethod.POST)
    String listNewsBin(
            @RequestParam("type") String type,
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "5") String limit
    );
    @RequestMapping(value="/staff/updatenews",method = RequestMethod.POST)
    String updateNews(@RequestBody Map<String,Object> newsMap);

    @RequestMapping(value="/staff/newsstate",method = RequestMethod.POST)
    String updataNewsState(@RequestBody Map<String,Object> newsMap);

    @RequestMapping(value="/staff/deletenews",method = RequestMethod.POST)
    String deleteNews(@RequestBody List<Integer> nids);

    //声明
    @RequestMapping(value="/staff/addstatement",method = RequestMethod.POST)
    String addStatement(@RequestBody Map<String,Object> statementMap);

    @RequestMapping(value="/staff/selectstatement",method = RequestMethod.POST)
    String selectStatement(@RequestParam("id") int id);

    @RequestMapping(value="/staff/liststatement",method = RequestMethod.POST)
    String listStatement(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "5") String limit
    );
    @RequestMapping(value="/staff/liststatementall",method = RequestMethod.POST)
    String listStatementAll();
    @RequestMapping(value="/staff/getstatements",method = RequestMethod.POST)
    String getStatements(@RequestBody List<Integer> stids);
    @RequestMapping(value="/staff/liststatementbin",method = RequestMethod.POST)
    String listStatementBin(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "5") String limit
    );
    @RequestMapping(value="/staff/updatestatement",method = RequestMethod.POST)
    String updateStatement(@RequestBody Map<String,Object> statementMap);

    @RequestMapping(value="/staff/statementstate",method = RequestMethod.POST)
    String updataStatementState(@RequestBody Map<String,Object> statementMap);

    @RequestMapping(value="/staff/deletestatement",method = RequestMethod.POST)
    String deleteStatement(@RequestBody List<Integer> nids);
}
