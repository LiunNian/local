package cn.org.cpcca.feignserver.paper.controllers;

import cn.org.cpcca.feignserver.paper.models.ReturnDataModel;
import cn.org.cpcca.feignserver.paper.services.BackstageService;
import cn.org.cpcca.feignserver.paper.shiro.controllers.VerifyController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@Api(tags = "论文上报后台管理",value = "论文上报后台管理")
@RequestMapping(value="backstage",method = RequestMethod.POST,produces = {"application/json"})
public class BackstageController {
    @Resource
    private BackstageService backstageService;
    @Resource
    private VerifyController verifyController;
    //登录
    @ResponseBody
    @RequestMapping(value="login")
    @ApiOperation(value="登录",notes="文化馆管理系统登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "zhang", required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", defaultValue = "123", required = true, paramType = "query", dataType = "String")
    })
    public ReturnDataModel login(
            HttpServletRequest request,
            @RequestParam(value="username",required = true) String username,
            @RequestParam(value = "password",required = true) String password){
        //String returnData = paperService.login(username,password);
        String platform = "backstage";
        ReturnDataModel returnData = verifyController.login(request,username,password,platform);
        /*String tempData = (String)request.getSession().getAttribute("uid");
        System.out.println(tempData);*/
        return returnData;
    }
    //登出
    @RequestMapping(value="logout")
    @ApiOperation(value="退出",notes = "文化馆系统退出")
    public ReturnDataModel logout(HttpServletRequest request){
        return verifyController.logout(request);
    }
    @RequestMapping(value = "listuser")
    @ApiOperation(value = "用户列表")
    public String listUser() {
        return backstageService.listUser();
    }

    @RequestMapping(value = "listitemtype")
    @ApiOperation(value = "论文活动所属类型")
    public String listItemType() {
        return backstageService.listItemType();
    }

    @RequestMapping(value = "additem")
    @ApiOperation(value = "添加论文活动", notes = "添加论文活动信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fuid", value = "责任人", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "stid", value = "声明id", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "instruction", value = "说明", defaultValue = "", required = true, paramType = "query", dataType = "String"),
    })
    public String addItem(
            HttpServletRequest request,
            @RequestParam(value = "fuid", defaultValue = "1") String fuid,
            @RequestParam(value = "stid",defaultValue = "1") String stid,
            @RequestParam(value = "title",defaultValue = "") String title,
            @RequestParam(value = "description",defaultValue = "") String description,
            @RequestParam(value = "instruction",defaultValue = "") String instruction) {

        System.out.println(request.getSession().getAttribute("uid"));
        int uid = (int)request.getSession().getAttribute("uid");
        return backstageService.addItem(uid,fuid,stid,title, description, instruction);
    }

    @RequestMapping(value = "selectitem")
    @ApiOperation(value = "查看论文活动信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "论文活动id", defaultValue = "", required = true, paramType = "query", dataType = "String"),
    })
    public String selectItem(
            @RequestParam("id") String id
    ) {
        return backstageService.selectItem(id);
    }

    @RequestMapping(value = "updateitem")
    @ApiOperation(value = "修改论文活动信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "论文活动id", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "fuid", value = "责任人", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "stid", value = "声明id", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "instruction", value = "说明", defaultValue = "", required = true, paramType = "query", dataType = "String"),

    })
    public String updataItem(
            @RequestParam("id") String id,
            @RequestParam(value = "fuid", defaultValue = "1") String fuid,
            @RequestParam("title") String title,
            @RequestParam("stid") String stid,
            @RequestParam("description") String description,
            @RequestParam(value = "instruction",defaultValue = "") String instruction) {
        Map<String, Object> itemMap = new HashMap<String, Object>();
        itemMap.put("fuid", Integer.valueOf(fuid));
        itemMap.put("id", Integer.valueOf(id));
        itemMap.put("stid",stid);
        itemMap.put("title", title);
        itemMap.put("description", description);
        itemMap.put("instruction", instruction);
        return backstageService.updateItem(itemMap);
    }

    @RequestMapping(value = "listitem")
    @ApiOperation(value = "论文活动列表", notes = "获取论文活动列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "返回条数", defaultValue = "5", required = true, paramType = "query", dataType = "String")
    })
    public String listItem(
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "limit", defaultValue = "10") String limit) {
        return backstageService.listItem(page, limit);
    }

    @RequestMapping(value = "listitembin")
    @ApiOperation(value = "论文活动回收站", notes = "获取论文活动回收站列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "返回条数", defaultValue = "5", required = true, paramType = "query", dataType = "String")
    })
    public String listItemBin(
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "limit", defaultValue = "10") String limit) {
        return backstageService.listItemBin(page, limit);
    }

    @RequestMapping(value = "itemstate")
    @ApiOperation(value = "修改论文活动状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "修改状态(-1,0,1)", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "itids", value = "论文活动id", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    public String updataItemState(@RequestParam("state") String state, @RequestParam("itids") String itids) {
        String[] tempItids = itids.split(",");
        List<String> tempList = Arrays.asList(tempItids);
        List<Integer> itidsList = new ArrayList<Integer>();
        for (String temp : tempList) {
            itidsList.add(Integer.valueOf(temp));
        }
        Map<String, Object> itemMap = new HashMap<String, Object>();
        itemMap.put("itids", itidsList);
        itemMap.put("state", state);
        return backstageService.updateItemState(itemMap);
    }

    @RequestMapping(value = "deleteitem")
    @ApiOperation(value = "删除活动信息数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itids", value = "论文活动id", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    public String deleteItem(@RequestParam("itids") String itids) {
        String[] tempItids = itids.split(",");
        List<String> tempList = Arrays.asList(tempItids);
        List<Integer> itidsList = new ArrayList<Integer>();
        for (String temp : tempList) {
            itidsList.add(Integer.valueOf(temp));
        }
        return backstageService.deleteItem(itidsList);
    }

    @RequestMapping(value = "addtheme")
    @ApiOperation(value = "添加主题", notes = "添加论文主题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itid", value = "论文活动关联id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "主题名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "directions", value = "主题方向", required = true, paramType = "query", dataType = "String")
    })
    public String addTheme(
            @RequestParam("itid") String itid,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("directions") String directions) {
        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("itid", itid);
        tempMap.put("title", title);
        tempMap.put("description", description);
        Map<String, Object> themeMap = new HashMap<>();
        themeMap.put("theme", tempMap);
        String[] tempString = directions.split(",");
        List<String> listDirection = Arrays.asList(tempString);
        themeMap.put("directions", listDirection);
        return backstageService.addTheme(themeMap);
    }

    @RequestMapping(value = "selecttheme")
    @ApiOperation(value = "查看主题信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主题id", defaultValue = "", required = true, paramType = "query", dataType = "String"),
    })
    public String selectTheme(
            @RequestParam("id") String id
    ) {
        return backstageService.selectTheme(id);
    }

    @RequestMapping(value = "listtheme")
    @ApiOperation(value = "主题列表", notes = "获取主题列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itid", value = "论文活动id", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "返回条数", defaultValue = "5", required = true, paramType = "query", dataType = "String")
    })
    public String listTheme(
            @RequestParam(value = "itid", required = true) String itid,
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "limit", defaultValue = "10") String limit) {
        return backstageService.listTheme(itid, page, limit);
    }

    @RequestMapping(value = "listthemebin")
    @ApiOperation(value = "主题回收站", notes = "获取主题回收站列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itid", value = "论文活动id", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "返回条数", defaultValue = "5", required = true, paramType = "query", dataType = "String")
    })
    public String listThemeBin(
            @RequestParam(value = "itid", required = true) String itid,
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "limit", defaultValue = "10") String limit) {
        return backstageService.listThemeBin(itid, page, limit);
    }
    @RequestMapping(value = "updatetheme")
    @ApiOperation(value = "修改主题信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主题id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "itid", value = "论文活动关联id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "主题名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "directions", value = "主题方向", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dids", value = "删除方向id", required = true, paramType = "query", dataType = "String")
    })
    public String updateTheme(
            @RequestParam("id") String id,
            @RequestParam("itid") String itid,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam(value = "directions", defaultValue = "") String directions,
            @RequestParam(value = "dids", defaultValue = "") String dids) {
        Map<String, Object> tempMap = new HashMap<>();
        tempMap.put("id", id);
        tempMap.put("itid", itid);
        tempMap.put("title", title);
        tempMap.put("description", description);
        Map<String, Object> themeMap = new HashMap<>();
        themeMap.put("theme", tempMap);
        List<String> listDirection = new ArrayList<>();
        if (!"".equals(directions)) {
            String[] tempString = directions.split(",");
            listDirection = Arrays.asList(tempString);
        }
        themeMap.put("directions", listDirection);
        List<Integer> didList = new ArrayList<>();
        if (!"".equals(dids)) {
            String[] didsString = dids.split(",");
            List<String> listDid = Arrays.asList(didsString);
            didList = new ArrayList<>();
            for (String temp : listDid) {
                didList.add(Integer.valueOf(temp));
            }
        }
        themeMap.put("dids", didList);
        return backstageService.updateTheme(themeMap);

    }

    @RequestMapping(value = "themestate")
    @ApiOperation(value = "修改主题状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "修改状态(-1,0,1)", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "thids", value = "主题id", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    public String updataThemeState(@RequestParam("state") String state, @RequestParam("thids") String thids) {
        String[] tempThids = thids.split(",");
        List<String> tempList = Arrays.asList(tempThids);
        List<Integer> thidList = new ArrayList<Integer>();
        for (String temp : tempList) {
            thidList.add(Integer.valueOf(temp));
        }
        Map<String, Object> itemMap = new HashMap<String, Object>();
        itemMap.put("thids", thidList);
        itemMap.put("state", state);
        return backstageService.updateThemeState(itemMap);
    }

    @RequestMapping(value = "deletetheme")
    @ApiOperation(value = "删除论文主题数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "thids", value = "论文活动id", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    public String deleteTheme(@RequestParam("thids") String thids) {
        String[] tempThids = thids.split(",");
        List<String> tempList = Arrays.asList(tempThids);
        List<Integer> thidList = new ArrayList<Integer>();
        for (String temp : tempList) {
            thidList.add(Integer.valueOf(temp));
        }
        return backstageService.deleteTheme(thidList);
    }

    @RequestMapping(value = "getnewstype")
    @ApiOperation(value = "获取资讯类型", notes = "获取资讯类型列表")
    public String getNewsType() {
        return backstageService.getNewsType();
    }

    @RequestMapping(value = "addnews")
    @ApiOperation(value = "添加资讯", notes = "添加资讯")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "资讯类型", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creater", value = "创建者", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", defaultValue = "", paramType = "query", dataType = "String"),
    })
    String addNews(
            @RequestParam("title") String title,
            @RequestParam("type") String type,
            @RequestParam("creater") String creater,
            @RequestParam(value = "source", defaultValue = "") String source,
            @RequestParam("content") String content) {
        Map<String, Object> newsMap = new HashMap<String, Object>();
        newsMap.put("title", title);
        newsMap.put("type", Integer.valueOf(type));
        newsMap.put("creater", creater);
        newsMap.put("source", source);
        newsMap.put("content", content);
        return backstageService.addNews(newsMap);
    }

    @RequestMapping(value = "selectnews")
    @ApiOperation(value = "查看资讯信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资讯id", defaultValue = "", required = true, paramType = "query", dataType = "String"),
    })
    String selectNews(@RequestParam("id") int id) {

        return backstageService.selectNews(id);
    }

    @RequestMapping(value = "listnews")
    @ApiOperation(value = "资讯列表", notes = "获取资讯类型关联下列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "新闻类型关联id", defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "返回条数", defaultValue = "5", required = true, paramType = "query", dataType = "String")
    })
    String listNews(
            @RequestParam("type") String type,
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "limit", defaultValue = "5") String limit
    ) {
        return backstageService.listNews(type, page, limit);
    }
    @RequestMapping(value = "listnewsbin")
    @ApiOperation(value = "资讯回收站", notes = "获取资讯回收站列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "新闻类型关联id", defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "返回条数", defaultValue = "5", required = true, paramType = "query", dataType = "String")
    })
    String listNewsBin(
            @RequestParam("type") String type,
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "limit", defaultValue = "5") String limit
    ) {
        return backstageService.listNewsBin(type, page, limit);
    }
    @RequestMapping(value = "updatenews")
    @ApiOperation(value = "修改资讯信息", notes = "修改资讯信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资讯id", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "资讯类型", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creater", value = "创建者", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", defaultValue = "", paramType = "query", dataType = "String"),
    })
    String updateNews(
            @RequestParam("id") String id,
            @RequestParam("title") String title,
            @RequestParam("type") String type,
            @RequestParam("creater") String creater,
            @RequestParam(value = "source", defaultValue = "") String source,
            @RequestParam("content") String content
    ) {
        Map<String, Object> newsMap = new HashMap<String, Object>();
        newsMap.put("id", Integer.valueOf(id));
        newsMap.put("title", title);
        newsMap.put("type", Integer.valueOf(type));
        newsMap.put("creater", creater);
        newsMap.put("source", source);
        newsMap.put("content", content);
        return backstageService.updateNews(newsMap);
    }

    @RequestMapping(value = "newsstate")
    @ApiOperation(value = "修改资讯状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "修改状态(-1,0,1)", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "nids", value = "资讯id", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    String updataNewsState(
            @RequestParam("state") String state,
            @RequestParam("nids") String nids) {
        String[] tempNids = nids.split(",");
        List<String> tempList = Arrays.asList(tempNids);
        List<Integer> nidList = new ArrayList<Integer>();
        for (String temp : tempList) {
            nidList.add(Integer.valueOf(temp));
        }
        Map<String, Object> itemMap = new HashMap<String, Object>();
        itemMap.put("nids", nidList);
        itemMap.put("state", state);
        return backstageService.updataNewsState(itemMap);
    }

    @RequestMapping(value = "deletenews")
    @ApiOperation(value = "删除资讯数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nids", value = "资讯id数组", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    String deleteNews(@RequestParam(value = "nids") String nids) {
        String[] tempNids = nids.split(",");
        List<String> tempList = Arrays.asList(tempNids);
        List<Integer> nidList = new ArrayList<Integer>();
        for (String temp : tempList) {
            nidList.add(Integer.valueOf(temp));
        }
        return backstageService.deleteNews(nidList);
    }

    @RequestMapping(value = "addstatement")
    @ApiOperation(value = "添加声明", notes = "添加声明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creater", value = "创建者", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", defaultValue = "", paramType = "query", dataType = "String"),
    })
    String addStatement(
            @RequestParam("title") String title,
            @RequestParam("creater") String creater,
            @RequestParam("content") String content) {
        Map<String, Object> statementMap = new HashMap<String, Object>();
        statementMap.put("title", title);
        statementMap.put("creater", creater);
        statementMap.put("content", content);
        return backstageService.addStatement(statementMap);
    }

    @RequestMapping(value = "selectstatement")
    @ApiOperation(value = "查看声明信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "声明id", defaultValue = "", required = true, paramType = "query", dataType = "String"),
    })
    String selectStatement(@RequestParam("id") int id) {

        return backstageService.selectStatement(id);
    }

    @RequestMapping(value = "liststatement")
    @ApiOperation(value = "声明列表", notes = "获取声明类型关联下列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "返回条数", defaultValue = "5", required = true, paramType = "query", dataType = "String")
    })
    String listStatement(
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "limit", defaultValue = "5") String limit
    ) {
        return backstageService.listStatement(page, limit);
    }
    @RequestMapping(value = "liststatementall")
    @ApiOperation(value = "声全部明列表", notes = "获取全部声明列表")
      String listStatementAll() {
        return backstageService.listStatementAll();
    }
    //stid声明列表
    @RequestMapping(value = "getstatements",produces = {"application/json"})
    @ApiOperation(value="获取声明列表",notes="获取声明列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stids", value = "声明id", defaultValue = "1,2", required = true,paramType = "query", dataType = "String"),
    })
    public String getStatements(@RequestParam(value="stids",defaultValue = "") String stids){
        if(!"".equals(stids)) {
            String[] tempData = stids.split(",");
            List<Integer> tempList = new ArrayList<Integer>();
            for (String tempStr : tempData) {
                tempList.add(Integer.valueOf(tempStr));
            }
            return backstageService.getStatements(tempList);
        }else{
            return "{\"code\":0,\"data\":{},\"message\":\"未获取相关数据集\"}";
        }
    }
    @RequestMapping(value = "liststatementbin")
    @ApiOperation(value = "声明回收站", notes = "获取声明回收站列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "返回条数", defaultValue = "5", required = true, paramType = "query", dataType = "String")
    })
    String listStatementBin(
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "limit", defaultValue = "5") String limit
    ) {
        return backstageService.listStatementBin(page, limit);
    }

    @RequestMapping(value = "updatestatement")
    @ApiOperation(value = "修改声明信息", notes = "修改声明信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "声明id", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creater", value = "创建者", defaultValue = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", defaultValue = "", paramType = "query", dataType = "String"),
    })
    String updateStatement(
            @RequestParam("id") String id,
            @RequestParam("title") String title,
            @RequestParam("creater") String creater,
            @RequestParam("content") String content
    ) {
        Map<String, Object> statementMap = new HashMap<String, Object>();
        statementMap.put("id", Integer.valueOf(id));
        statementMap.put("title", title);
        statementMap.put("creater", creater);
        statementMap.put("content", content);
        return backstageService.updateStatement(statementMap);
    }

    @RequestMapping(value = "statementstate")
    @ApiOperation(value = "修改声明状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "state", value = "修改状态(-1,0,1)", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "ids", value = "声明id", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    String updataStatementState(
            @RequestParam("state") String state,
            @RequestParam("ids") String ids) {
        String[] tempIds = ids.split(",");
        List<String> tempList = Arrays.asList(tempIds);
        List<Integer> idList = new ArrayList<Integer>();
        for (String temp : tempList) {
            idList.add(Integer.valueOf(temp));
        }
        Map<String, Object> statementMap = new HashMap<String, Object>();
        statementMap.put("ids", idList);
        statementMap.put("state", state);
        return backstageService.updataStatementState(statementMap);
    }

    @RequestMapping(value = "deletestatement")
    @ApiOperation(value = "删除声明数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "声明id数组", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    String deleteStatement(@RequestParam(value = "ids") String ids) {
        String[] tempIds = ids.split(",");
        List<String> tempList = Arrays.asList(tempIds);
        List<Integer> idList = new ArrayList<Integer>();
        for (String temp : tempList) {
            idList.add(Integer.valueOf(temp));
        }
        return backstageService.deleteStatement(idList);
    }
}