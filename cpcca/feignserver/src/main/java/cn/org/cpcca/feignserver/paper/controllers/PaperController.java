package cn.org.cpcca.feignserver.paper.controllers;

import cn.org.cpcca.feignserver.paper.models.ReturnDataModel;
import cn.org.cpcca.feignserver.paper.services.PaperService;
import cn.org.cpcca.feignserver.paper.shiro.controllers.VerifyController;
import cn.org.cpcca.feignserver.paper.shiro.models.AccountModel;
import cn.org.cpcca.feignserver.paper.shiro.services.AccountServiceInterface;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@Api(tags = "论文上报",value = "论文上报平台")
@RequestMapping(value="frontend",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
public class PaperController {
    @Resource
    private PaperService paperService;
    @Resource
    private VerifyController verifyController;
    @Autowired
    private AccountServiceInterface accountService;

    //登录
    @ResponseBody
    @RequestMapping(value = "login")
    @ApiOperation(value = "登录", notes = "文化馆管理系统登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "zhang", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", defaultValue = "123", required = true, paramType = "query", dataType = "String")
    })
    public ReturnDataModel login(
            HttpServletRequest request,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password) {
        //String returnData = paperService.login(username,password);
        String platform = "paperfrontend";
        ReturnDataModel returnData = verifyController.login(request, username, password, platform);
        /*String tempData = (String)request.getSession().getAttribute("uid");
        System.out.println(tempData);*/
        return returnData;
    }

    @RequestMapping("loginsuc")
    public ReturnDataModel loginSuccess(HttpServletRequest request) {
        System.out.println("--------success-----------");
        ReturnDataModel returnData = new ReturnDataModel(1, new HashMap<>(), "认证失败");
        if (request.getSession().getAttribute("uid") != null) {
            int uid = (int) request.getSession().getAttribute("uid");
            AccountModel accountModel = accountService.selectUser(uid);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("username", accountModel.getUsername());
            dataMap.put("flag", accountModel.getFlag());
            returnData.setCode(0);
            returnData.setData(dataMap);
            returnData.setData("获取数据成功");
        }
        return returnData;
    }

    //登出
    @RequestMapping(value = "logout")
    @ApiOperation(value = "退出", notes = "文化馆系统退出")
    public ReturnDataModel logout(HttpServletRequest request) {
        return verifyController.logout(request);
    }

    //修改密码
    @RequestMapping(value = "updatepassword")
    @ApiOperation(value = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldpassword", value = "旧密码", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "新密码", defaultValue = "", required = true, paramType = "query", dataType = "String"),
    })
    public ReturnDataModel updatePassword(
            HttpServletRequest request,
            @RequestParam("oldpassword") String oldpassword,
            @RequestParam("password") String password) {
        int uid = (int) request.getSession().getAttribute("uid");
        return verifyController.updatePassword(uid, oldpassword, password);
    }

    //论文活动列表
    @RequestMapping(value = "listitem")
    @ApiOperation(value = "论文活动列表", notes = "获取活动信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "限制返回条数", defaultValue = "12", required = true, paramType = "query", dataType = "Integer")
    })
    public String listItem(@RequestParam(value = "page", defaultValue = "1") String page, @RequestParam(value = "limit", defaultValue = "12") String limit) {
        return paperService.listItems(page, limit);
    }

    /*//论文活动列表
    @RequestMapping(value="listitems")
    @ApiOperation(value="论文活动列表",notes="获取活动信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true,paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "限制返回条数", defaultValue = "12", required = true,paramType = "query", dataType = "Integer")
    })
    public String listItems(@RequestParam(value = "page",defaultValue = "1") String page,@RequestParam(value="limit",defaultValue = "12") String limit ){
        return paperService.listItems(page,limit);
    }*/
    //已完结论文活动列表
    @RequestMapping(value = "listitemend")
    @ApiOperation(value = "已完结论文活动列表", notes = "获取已完结活动信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "限制返回条数", defaultValue = "12", required = true, paramType = "query", dataType = "Integer")
    })
    public String listItemEnd(@RequestParam(value = "page", defaultValue = "1") String page, @RequestParam(value = "limit", defaultValue = "12") String limit) {
        return paperService.listItemEnd(page, limit);
    }

    //主题方向列表
    @RequestMapping(value = "getthemes")
    @ApiOperation(value = "获取主题列表", notes = "通过项目id获取主题列表及相关方向列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itid", value = "项目id", defaultValue = "1", required = true, paramType = "query", dataType = "Integer"),
    })
    public String getThemes(@RequestParam("itid") String itid) {
        return paperService.getThemes(itid);
    }

    //添加作者
    @RequestMapping(value = "addauthor", produces = {"application/json"})
    @ApiOperation(value = "添加作者", notes = "添加论文作者")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sex", value = "性别", defaultValue = "1", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "cardnum", value = "身份证号", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "phonenum", value = "联系电话", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "unitname", value = "单位", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "deptname", value = "部门名称", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "region", value = "地区", defaultValue = "", required = true, paramType = "query", dataType = "String")
    })
    public String addAuthor(
            @RequestParam("name") String name,
            @RequestParam("sex") int sex,
            @RequestParam("cardnum") String cardnum,
            @RequestParam("phonenum") String phonenum,
            @RequestParam("unitname") String unitname,
            @RequestParam("deptname") String deptname,
            @RequestParam("region") String region
    ) {
        return paperService.addAuthor(name, sex, cardnum, phonenum, unitname, deptname, region);

    }

    //auids获取作者列表
    @RequestMapping(value = "getauthors1", produces = {"application/json"})
    public String getAuthors1(@RequestBody Map<String, List<Integer>> authorMap) {

        return paperService.getAuthors(authorMap);
    }

    //auids获取作者列表
    @RequestMapping(value = "getauthors", produces = {"application/json"})
    @ApiOperation(value = "获取作者列表", notes = "获取作者列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "auids", value = "作者id", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    public String getAuthors(@RequestParam(value = "auids") String auids) {
        String[] tempData = auids.split(",");
        List<Integer> tempList = new ArrayList<Integer>();
        for (String tempStr : tempData) {
            tempList.add(Integer.valueOf(tempStr));
        }
        Map<String, List<Integer>> authorMap = new HashMap<String, List<Integer>>();
        authorMap.put("auids", tempList);
        System.out.println(JSON.toJSONString(authorMap));
        return paperService.getAuthors(authorMap);
    }

    //上传
    @RequestMapping(value = "upload", produces = {"application/json"})
    @ApiOperation(value = "文件上传", notes = "上传文件，限制文件doc，docx，png，jpg")
    public String upload(@ApiParam(value = "上传文件", required = true) @RequestParam("file") MultipartFile file) {
        return paperService.upload(file);
    }

    //添加论文
    @RequestMapping(value = "addpaper", produces = {"application/json"})
    @ApiOperation(value = "添加论文", notes = "提交论文信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "did", value = "论文方向关联id", defaultValue = "1", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "fid", value = "论文文件id", defaultValue = "1", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "prove", value = "证明关联id", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "auids", value = "作者", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "subtitle", value = "次标题", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "概述", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "keyword", value = "关键词", defaultValue = "文化,艺术", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reference", value = "参考文献", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "编辑状态", defaultValue = "0", required = true, paramType = "query", dataType = "String")
    })
    public String addPaper(
            HttpServletRequest request,
            @RequestParam(value = "did", defaultValue = "0") int did,
            @RequestParam(value = "fid", defaultValue = "0") int fid,
            @RequestParam(value = "prove", defaultValue = "") String prove,
            @RequestParam(value = "auids", defaultValue = "") String auids,
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "subtitle", defaultValue = "") String subtitle,
            @RequestParam(value = "summary", defaultValue = "") String summary,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "content", defaultValue = "") String content,
            @RequestParam(value = "reference", defaultValue = "") String reference,
            @RequestParam(value = "status", defaultValue = "0") String status) {
        Map<String, Object> paperMap = new HashMap<String, Object>();
        int uid = (int) request.getSession().getAttribute("uid");
        paperMap.put("uid", uid);
        paperMap.put("did", did);
        paperMap.put("fid", fid);
        paperMap.put("prove", prove);
        paperMap.put("auids", auids);
        paperMap.put("title", title);
        paperMap.put("subtitle", subtitle);
        paperMap.put("summary", summary);
        paperMap.put("keyword", keyword);
        paperMap.put("content", content);
        paperMap.put("reference", reference);
        paperMap.put("status", Integer.valueOf(status));
        return paperService.addPaper(paperMap);
    }

    @RequestMapping(value = "listpaper", produces = {"application/json"})
    @ApiOperation(value = "论文列表", notes = "论文列表")
    String listpaper(
            HttpServletRequest request,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "50") int limit) {
        int uid = (int) request.getSession().getAttribute("uid");
        return paperService.listpaper(uid, page, limit);
    }

    @RequestMapping(value = "listpaperbyitid", produces = {"application/json"})
    @ApiOperation(value = "根据活动关联获取论文列表", notes = "论文活动关联获取论文列表")
    String listpaperByItid(
            HttpServletRequest request,
            @RequestParam(value = "itid") int itid,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "5") int limit) {
        int uid = (int) request.getSession().getAttribute("uid");
        return paperService.listpaperByItid(uid, itid, page, limit);
    }

    //修改论文信息
    @RequestMapping(value = "updatepaper", produces = {"application/json"})
    @ApiOperation(value = "修改论文信息", notes = "修改论文信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "论文id", defaultValue = "1", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "fid", value = "论文文件id", defaultValue = "1", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "prove", value = "证明关联id", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "auids", value = "作者", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "subtitle", value = "次标题", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "概述", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "keyword", value = "关键词", defaultValue = "文化,艺术", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reference", value = "参考文献", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "编辑状态", defaultValue = "0", required = true, paramType = "query", dataType = "String")
    })
    public String updatepaper(
            @RequestParam(value = "id", defaultValue = "0") int id,
            @RequestParam(value = "fid", defaultValue = "0") int fid,
            @RequestParam(value = "prove", defaultValue = "") String prove,
            @RequestParam(value = "auids", defaultValue = "") String auids,
            @RequestParam(value = "title", defaultValue = "") String title,
            @RequestParam(value = "subtitle", defaultValue = "") String subtitle,
            @RequestParam(value = "summary", defaultValue = "") String summary,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "content", defaultValue = "") String content,
            @RequestParam(value = "reference", defaultValue = "") String reference,
            @RequestParam(value = "status", defaultValue = "0") String status
    ) {
        Map<String, Object> paperMap = new HashMap<String, Object>();
        paperMap.put("id", id);
        paperMap.put("fid", fid);
        paperMap.put("prove", prove);
        paperMap.put("auids", auids);
        paperMap.put("title", title);
        paperMap.put("subtitle", subtitle);
        paperMap.put("summary", summary);
        paperMap.put("keyword", keyword);
        paperMap.put("content", content);
        paperMap.put("reference", reference);
        paperMap.put("status", Integer.valueOf(status));
        return paperService.updatepaper(paperMap);
    }

    @RequestMapping(value = "selectpaper", produces = {"application/json"})
    @ApiOperation(value = "选择论文信息", notes = "选择论文信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "论文id", defaultValue = "", required = true, paramType = "query", dataType = "Integer"),
    })
    String selectPaper(@RequestParam("id") int id) {
        return paperService.selectPaper(id);
    }

    @RequestMapping(value = "paperstate", produces = {"application/json"})
    @ApiOperation(value = "修改论文状态", notes = "修改论文状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "论文id", defaultValue = "1", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "state", value = "状态", defaultValue = "1", required = true, paramType = "query", dataType = "Integer"),
    })
    String stampPaper(@RequestParam("id") String id, @RequestParam("state") String state) {
        return paperService.stampPaper(id, state);
    }

    //获取用户信息
    @RequestMapping(value = "getuserinfo", produces = {"application/json"})
    @ApiOperation(value = "获取用户信息", notes = "获取登录账号用户信息，通过session内uid，无需传参")
    public String getUserInfo(HttpServletRequest request) {
        int uid = (int) request.getSession().getAttribute("uid");
        return paperService.getUserInfo(uid);
    }

    //修改用户信息
    @RequestMapping(value = "setuserinfo", produces = {"application/json"})
    @ApiOperation(value = "修改用户信息", notes = "修改登录用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "region", value = "地区", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "地址", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "phonenum", value = "联系电话", defaultValue = "137XXXXXXXX", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "linkman", value = "联系人", defaultValue = "", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", defaultValue = "123@qq.com", required = true, paramType = "query", dataType = "String"),
    })
    public String setUserInfo(HttpServletRequest request,
                              @RequestParam(value = "region", defaultValue = "") String region,
                              @RequestParam(value = "address", defaultValue = "") String address,
                              @RequestParam(value = "phonenum", defaultValue = "") String phonenum,
                              @RequestParam(value = "linkman", defaultValue = "") String linkman,
                              @RequestParam(value = "email", defaultValue = "") String email
    ) {
        int uid = (int) request.getSession().getAttribute("uid");
        System.out.println(uid);
        Map<String, Object> paperMap = new HashMap<String, Object>();
        paperMap.put("uid", uid);
        paperMap.put("region", region);
        paperMap.put("address", address);
        paperMap.put("linkman", linkman);
        paperMap.put("phonenum", phonenum);
        paperMap.put("email", email);
        System.out.println(paperMap.toString());
        return paperService.updateUserInfo(paperMap);
    }

    @RequestMapping(value = "listnews", produces = {"application/json"})
    @ApiOperation(value = "资讯列表", notes = "通过资讯类型查询资讯列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "资讯类型", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "返回条数", defaultValue = "5", required = true, paramType = "query", dataType = "String")
    })
    public String listNews(
            @RequestParam(value = "type") String type,
            @RequestParam(value = "page", defaultValue = "1") String page,
            @RequestParam(value = "limit", defaultValue = "10") String limit
    ) {
        return paperService.listNews(type, page, limit);
    }

    @RequestMapping(value = "selectitem", produces = {"application/json"})
    @ApiOperation(value = "论文活动信息 ", notes = "通过论文活动id查看论文信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "论文活动id", defaultValue = "", required = true, paramType = "query", dataType = "String"),
    })
    public String selectItem(
            @RequestParam(value = "id") String id
    ) {
        System.out.println(id);
        return paperService.selectItem(Integer.valueOf(id));
    }

    @RequestMapping(value = "selectnews", method = RequestMethod.POST)
    @ApiOperation(value = "查看资讯信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资讯id", defaultValue = "", required = true, paramType = "query", dataType = "String"),
    })
    String selectNews(@RequestParam("id") int id) {

        return paperService.selectNews(id);
    }

    //获取文件信息
    @RequestMapping(value = "getfileinfo")
    @ApiOperation(value = "获取文件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文件id", defaultValue = "", required = true, paramType = "query", dataType = "Integer")
    })
    String getFileInfo(@RequestParam("id") int id) {
        return paperService.getFileInfo(id);
    }

    @RequestMapping(value = "selectstatement")
    @ApiOperation(value = "查看声明信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "声明id", defaultValue = "", required = true, paramType = "query", dataType = "String"),
    })
    String searchStatement(@RequestParam("id") int id) {

        return paperService.searchStatement(id);
    }

    @RequestMapping(value = "getstatements", produces = {"application/json"})
    @ApiOperation(value = "获取声明列表", notes = "获取声明列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stids", value = "声明id", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    public String getStatements(@RequestParam(value = "stids") String stids) {
        String[] tempData = stids.split(",");
        List<Integer> tempList = new ArrayList<Integer>();
        for (String tempStr : tempData) {
            tempList.add(Integer.valueOf(tempStr));
        }
        return paperService.getStatements(tempList);
    }

    @RequestMapping(value = "getsfiles", produces = {"application/json"})
    @ApiOperation(value = "获取文件列表", notes = "获取文件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "文件id", defaultValue = "1,2", required = true, paramType = "query", dataType = "String"),
    })
    public String getiles(
            HttpServletRequest request,
            @RequestParam(value = "ids") String ids
    ) {
        String[] tempData = ids.split(",");
        List<Integer> tempList = new ArrayList<Integer>();
        for (String tempStr : tempData) {
            tempList.add(Integer.valueOf(tempStr));
        }
        return paperService.getFiles(tempList);
    }

    @ApiOperation(value = "获取压缩后论文文件", notes = "获取压缩后论文文件")
    @RequestMapping(value = "getFiles")
    public String getItem(@RequestParam("itemId") int id) {
        return paperService.getItem(id);
    }
}