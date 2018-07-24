package cn.org.cpcca.paperserver.controllers;

import cn.org.cpcca.paperserver.models.*;
import cn.org.cpcca.paperserver.servcie.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@RestController
@RequestMapping(value="paperfrontend",method = RequestMethod.POST,produces = {"application/json"})
public class PaperFrontendController extends BaseController{
    @Autowired
    private FilesServiceInterface filesService;
    @Autowired
    private ItemServiceInterface itemService;
    @Autowired
    private PaperServiceInterface paperService;
    @Autowired
    private SynthesizeServiceInterface synthesizeService;
    @Autowired
    private AuthorServiceInterface authorService;
    @Autowired
    private UserInfoServiceInterface userInfoService;
    @Autowired
    private NewsServiceInterface newsService;
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private StatementServiceInterface statementService;
    //获取项目所属
    @RequestMapping(value="listitemtype")
    ReturnDataModel listItemType(){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<ItemTypeModel> listData = itemService.listItemType();
        if(listData.size()>0){
            this.returnData(returnDataModel,0,listData,"获取数据集合");
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }
    //项目列表
    @RequestMapping(value="listitems")
    public ReturnDataModel listItems(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value="limit",defaultValue = "12") String limit ){
        //初始化返回对象和data
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        if(Integer.valueOf(limit)>0) {
            PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit), true);
        }
        List<ItemModel> listItem = itemService.listItems();
        List<Map<String,Object>> listData = new ArrayList<>();
        for(int i=0,num=listItem.size();i<num;i++){

            Map<String,Object> tempMap = new HashMap<String, Object>();
            tempMap.put("id",listItem.get(i).getId());
            if(listItem.get(i).getUid()>0){
                tempMap.put("creater",userService.selectUser(listItem.get(i).getUid()).getUsername());
            }else{
                tempMap.put("creater","");
            }
            tempMap.put("uid",listItem.get(i).getUid());
            tempMap.put("stid",listItem.get(i).getStid());
            tempMap.put("type",itemService.getTypeName(listItem.get(i).getType()));
            tempMap.put("title",listItem.get(i).getTitle());
            //tempMap.put("description",listItem.get(i).getDescription());
            //tempMap.put("instruction",listItem.get(i).getInstruction());
            tempMap.put("ctime",listItem.get(i).getCtime());
            tempMap.put("state",listItem.get(i).getState());
            listData.add(tempMap);
        }
        if(Integer.valueOf(limit)>0){
            PageInfo<ItemModel> pageInfo = new PageInfo<>(listItem);
            long total = pageInfo.getTotal();     //获取总个数
            int pagenow = pageInfo.getPages();      //获取当前页
            dataMap.put("total",total);
            dataMap.put("pagenum",pagenow);
            dataMap.put("list",listData);
        }
        if(listItem.size()>0){
            if(Integer.valueOf(limit)>0) {
                this.returnData(returnDataModel,0, dataMap, "获取数据集合");
            }else{
                this.returnData(returnDataModel,0, listData, "获取数据集合");
            }
        }else if(listItem.size()==0){
            returnDataModel.setMessage("没有获取到相关数据集合");
        }else{
            returnDataModel.setMessage("网络繁忙请稍后再试");
        }
        return returnDataModel;
    }
    //已完结项目列表
    @RequestMapping(value="listitemend")
    public ReturnDataModel listItemEnd(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value="limit",defaultValue = "12") String limit ){
        //初始化返回对象和data
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        if(Integer.valueOf(limit)>0) {
            PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit), true);
        }
        List<ItemModel> listItem = itemService.listItemEnd();
        if(Integer.valueOf(limit)>0){
            PageInfo<ItemModel> pageInfo = new PageInfo<>(listItem);
            long total = pageInfo.getTotal();     //获取总个数
            int pagenow = pageInfo.getPages();      //获取当前页
            dataMap.put("total",total);
            dataMap.put("pagenum",pagenow);
            dataMap.put("list",listItem);
        }
        if(listItem.size()>0){
            if(Integer.valueOf(limit)>0) {
                this.returnData(returnDataModel,0, dataMap, "获取数据集合");
            }else{
                this.returnData(returnDataModel,0, listItem, "获取数据集合");
            }
        }else if(listItem.size()==0){
            returnDataModel.setMessage("没有获取到相关数据集合");
        }else{
            returnDataModel.setMessage("网络繁忙请稍后再试");
        }
        return returnDataModel;
    }
    //根据项目返回主题和方向列表
    @RequestMapping(value="getthemes")
    public ReturnDataModel getThemes(@RequestParam("itid") String itid){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<Map<String,Object>> listData = synthesizeService.themes(Integer.valueOf(itid));
        if(listData.size()>0){
            this.returnData(returnDataModel,0,listData,"获取数据集合");
        }else{
            returnDataModel.setMessage("没有获取到相关数据集合");
        }
        return returnDataModel;
    }
    @RequestMapping(value="addauthor1",produces = {"application/json"})
    public  ReturnDataModel addAuthor1(@RequestBody Map<String,Object> authorMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        System.out.println(authorMap);
        System.out.println(authorMap.toString());
        AuthorModel authorModel = new AuthorModel();
        changeObjeceProperty(AuthorModel.class,authorModel,authorMap);
        if(authorService.addAuthor(authorModel)>0){
            dataMap.put("auid",String.valueOf(authorModel.getId()));
            this.returnData(returnDataModel,0,dataMap,"添加作者成功");
        }else{
            returnDataModel.setMessage("添加作者失败");
        }
        return returnDataModel;
    }
    @RequestMapping(value="getauthors")
    public ReturnDataModel getAuthors(@RequestBody Map<String,Object> authorMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();

        System.out.println(JSON.toJSONString(authorMap));
        List<AuthorModel> listData = authorService.listAuthor((List<Integer>) authorMap.get("auids"));
        if(listData.size()>0){
            this.returnData(returnDataModel,0,listData,"获取数据集合");
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }
    //论文列表
    @RequestMapping(value="listpaper")
    public ReturnDataModel listpaper(
            @RequestParam(value = "uid") int uid ,
            @RequestParam(value = "page",defaultValue = "1") int page ,
            @RequestParam(value = "limit",defaultValue = "5") int limit){
        ReturnDataModel returnDataModel= this.start();
        List<ItemModel> itemModels = itemService.listItemNow();
        List<Object> returnData = new ArrayList<>();
        for(ItemModel itemModel:itemModels) {
            //System.out.println(itemModel.getId());
            Map<String,Object> dataMap = new HashMap<String, Object>();
            List<Integer> ids = synthesizeService.getDirectionByItemId(itemModel.getId());
            if(ids.size()>0) {
                PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit), true);
                List<PaperModel> listPaper = paperService.searchPaper(ids,uid);
                System.out.println();
                int draftCount = paperService.countPaperByStatus(ids,uid,1);
                int normalCount = paperService.countPaperByStatus(ids,uid,0);
                List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
                dataMap.put("id", itemModel.getId());
                dataMap.put("title", itemModel.getTitle());
                dataMap.put("state", itemModel.getState());
                for(int i=0,num = listPaper.size();i<num;i++){
                    //System.out.println(listData.get(i).getAuids());
                    String[] temps = listPaper.get(i).getAuids().split(",");
                    List<String> aids = Arrays.asList(temps);
                    List<Integer> aidList = new ArrayList<>();
                    for(String aid :aids){
                        if(!"".equals(aid)) {
                            aidList.add(Integer.valueOf(aid));
                        }
                    }
                    if(aidList.size()>0) {
                        List<AuthorModel> authorModels = authorService.listAuthor(aidList);
                        listPaper.get(i).setAuids(authorModels.get(0).getName());
                    }else{
                        listPaper.get(i).setAuids("");
                    }
                    Map<String,Object> tempMap = new HashMap<String, Object>();
                    tempMap.put("id",listPaper.get(i).getId());
                    tempMap.put("title",listPaper.get(i).getTitle());
                    tempMap.put("auids",listPaper.get(i).getAuids());
                    tempMap.put("status",listPaper.get(i).getStatus());
                    tempMap.put("ctime",listPaper.get(i).getCtime());
                    listData.add(tempMap);
                }
                PageInfo<PaperModel> pageInfo = new PageInfo<PaperModel>(listPaper);
                long total = pageInfo.getTotal();     //获取总个数
                int pagenow = pageInfo.getPages();      //获取当前页
                dataMap.put("total",total);
                dataMap.put("draftCount",draftCount);
                dataMap.put("normalCount",normalCount);
                dataMap.put("pagenum",pagenow);
                dataMap.put("list", listData);
                if(listData.size()>0) {
                    returnData.add(dataMap);
                }
            }
        }
        if(returnData.size()>0){
            this.returnData(returnDataModel,0,returnData,"获取数据集合");
        }else{
            returnDataModel.setMessage("没有获取到相关数据集合");
        }
        return returnDataModel;
    }
    //根据itemid查询论文列表
    @RequestMapping(value="listpaperbyitid")
    public ReturnDataModel listpaperByItid(
            @RequestParam("uid") int uid,
            @RequestParam("itid") int itid,
            @RequestParam(value = "page",defaultValue = "1") int page ,
            @RequestParam(value = "limit",defaultValue = "5") int limit){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<Object> returnData = new ArrayList<>();
        ItemModel itemModel = itemService.selectItem(itid);
        if(!isEmpty(itemModel)){
            List<Integer> ids = synthesizeService.getDirectionByItemId(itemModel.getId());
            if(ids.size()>0) {
                PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit), true);
                List<PaperModel> listPaper = paperService.searchPaper(ids,uid);
                int draftCount = paperService.countPaperByStatus(ids, uid,1);
                int normalCount = paperService.countPaperByStatus(ids, uid,0);
                List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
                dataMap.put("id", itemModel.getId());
                dataMap.put("title", itemModel.getTitle());
                for (int i = 0, num = listPaper.size(); i < num; i++) {
                    System.out.println(listPaper.get(i).getAuids());
                    String[] temps = listPaper.get(i).getAuids().split(",");
                    List<String> aids = Arrays.asList(temps);
                    List<Integer> aidList = new ArrayList<>();
                    for (String aid : aids) {
                        if (!"".equals(aid)) {
                            aidList.add(Integer.valueOf(aid));
                        }
                    }
                    if (aidList.size() > 0) {
                        List<AuthorModel> authorModels = authorService.listAuthor(aidList);
                        listPaper.get(i).setAuids(authorModels.get(0).getName());
                    } else {
                        listPaper.get(i).setAuids("");
                    }
                    Map<String, Object> tempMap = new HashMap<String, Object>();
                    tempMap.put("id", listPaper.get(i).getId());
                    tempMap.put("title", listPaper.get(i).getDid());
                    tempMap.put("title",listPaper.get(i).getTitle());
                    tempMap.put("auids",listPaper.get(i).getAuids());
                    tempMap.put("status", listPaper.get(i).getStatus());
                    tempMap.put("ctime", listPaper.get(i).getCtime());
                    listData.add(tempMap);
                }
                PageInfo<PaperModel> pageInfo = new PageInfo<PaperModel>(listPaper);
                long total = pageInfo.getTotal();     //获取总个数
                int pagenow = pageInfo.getPages();      //获取当前页
                dataMap.put("total", total);
                dataMap.put("draftCount", draftCount);
                dataMap.put("normalCount", normalCount);
                dataMap.put("pagenum", pagenow);
                dataMap.put("list", listData);
                if (listData.size() > 0) {
                    returnData.add(dataMap);
                }
            }
        }
        if(returnData.size()>0){
            this.returnData(returnDataModel,0,returnData,"获取数据集合");
        }else{
            returnDataModel.setMessage("没有获取到相关数据集合");
        }
        return returnDataModel;
    }
    //创建作者
    @RequestMapping(value="addauthor")
    public ReturnDataModel addAuthor(
            @RequestParam("name") String name,
            @RequestParam("sex") int sex,
            @RequestParam("cardnum") String cardnum,
            @RequestParam("phonenum") String phonenum,
            @RequestParam("unitname") String unitname,
            @RequestParam("deptname") String deptname,
            @RequestParam("region") String region

    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        Map<String,Object> authorMap = new HashMap<String,Object>();
        authorMap.put("name",name);
        authorMap.put("sex",sex);
        authorMap.put("cardnum",cardnum);
        authorMap.put("phonenum",phonenum);
        authorMap.put("unitname",unitname);
        authorMap.put("deptname",deptname);
        authorMap.put("region",region);
        AuthorModel authorModel = new AuthorModel();
        changeObjeceProperty(AuthorModel.class,authorModel,authorMap);
        if(authorService.addAuthor(authorModel)>0){
            dataMap.put("auid",String.valueOf(authorModel.getId()));
            this.returnData(returnDataModel,0,dataMap,"添加作者成功");
        }else{
            returnDataModel.setMessage("添加作者失败");
        }
        return returnDataModel;
    }
    //查看项目信息
    @RequestMapping(value="selectitem")
    public ReturnDataModel selectItem(@RequestParam("id") int id){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        ItemModel itemModel = itemService.selectItem(id);
        if(!isEmpty(itemModel)){
            this.returnData(returnDataModel,0,itemModel,"获取数据集成功");
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }
    //上传论文
    @RequestMapping(value="upload")
    @CrossOrigin("*")
    public ReturnDataModel upload(@RequestParam("file") MultipartFile file){
        //System.out.println(JSON.toJSONString(file, true));
        //初始化返回对象和data
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        if (file.isEmpty()) {
            returnDataModel.setMessage("文件为空");
        }else {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            //System.out.print(fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            //System.out.print(suffixName);
            if(".doc".equals(suffixName)||".docx".equals(suffixName)){
                this.fileUpload(returnDataModel,file,(FilesService) filesService,"paperword");
            }else if(".png".equals(suffixName)||".jpg".equals(suffixName)){

                this.fileUpload(returnDataModel,file,(FilesService) filesService,"paperpic");
            }else{
                returnDataModel.setMessage("文件类型错误");
            }
        }
        return returnDataModel;
    }
    //添加论文信息
    @RequestMapping(value="addpaper")
    public ReturnDataModel addPaper(@RequestBody Map<String,Object> paperMap) throws NoSuchFieldException {
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        PaperModel paperModel =  new PaperModel();
        //反射修改对象属性值
        changeObjeceProperty(PaperModel.class,paperModel,paperMap);
        //System.out.println(paperModel.getTitle());
        if(paperService.addPaper(paperModel)>0){
            dataMap.put("pid",String.valueOf(paperModel.getId()));
            this.returnData(returnDataModel,0,dataMap,"添加论文成功");
        }else{
            returnDataModel.setMessage("添加论文失败");
        }
        return returnDataModel;
    }
    @RequestMapping(value="selectpaper")
    public ReturnDataModel selectPaper(@RequestParam("id") int id){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        PaperModel paperModel = paperService.selectPaper(id);
        if(!isEmpty(paperModel)){
            this.returnData(returnDataModel,0,paperModel,"获取数据集成功");
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }

    //修改论文信息
    @RequestMapping(value="updatepaper")
    public ReturnDataModel updatepaper(@RequestBody Map<String,Object> paperMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        String tempId = paperMap.get("id").toString();
        PaperModel paperModel = paperService.selectPaper(Integer.valueOf(tempId));
        //反射修改对象属性值
        this.changeObjeceProperty(PaperModel.class,paperModel,paperMap);
        int resultData = paperService.updatePaper(paperModel);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"修改论文成功");
        }else if(resultData==0){
            returnDataModel.setMessage("修改论文未修改");
        }else{
            returnDataModel.setMessage("修改论文失败");
        }
        return returnDataModel;
    }

    //标记论文状态
    @RequestMapping(value="stamppaper")
    public ReturnDataModel stampPaper(@RequestParam("id") String id,@RequestParam("sign") String sign){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        PaperModel paperModel = new PaperModel();
        paperModel.setId(Integer.valueOf(id));
        paperModel.setState(Integer.valueOf(sign));
        String messagePrefix = "";
        switch (Integer.valueOf(sign)){
            case 0: messagePrefix = "删除"; break;
            default: messagePrefix = "还原";
        }
        if(paperService.stampPaper(paperModel)>0){
            this.returnData(returnDataModel,0,dataMap,messagePrefix+"论文成功");
        }else if(paperService.updatePaper(paperModel)==0){
            returnDataModel.setMessage(messagePrefix+"论文未修改");
        }else{
            returnDataModel.setMessage(messagePrefix+"论文失败");
        }
        return returnDataModel;
    }
    //修改账号信息
    @RequestMapping(value="getuserinfo")
    public ReturnDataModel getUserInfo(@RequestParam("uid") int uid){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();

        UserModel userModel = userService.selectUser(uid);
        UserInfoModel userInfoModel = userInfoService.getUserInfo(uid);
        userInfoModel.setUnitname(userModel.getUsername());
        if(!isEmpty(userInfoModel)){
            this.returnData(returnDataModel,0,userInfoModel,"获取数据集合");
        }else {
            returnDataModel.setMessage("没有获取到相关数据集合");
        }
        return returnDataModel;
    }
    //修改用户信息
    @RequestMapping(value="setuserinfo")
    public ReturnDataModel updateUserInfo(@RequestBody Map<String,Object> userInfoMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        UserInfoModel userInfoModel = new UserInfoModel();
        //HttpSession session =  request.getSession();
        // int id = Integer.valueOf((String)session.getAttribute("uid"));
        //userInfoMap.put("uid",1);
        //反射修改对象属性值
        changeObjeceProperty(UserInfoModel.class,userInfoModel,userInfoMap);
        //UserModel userModel = new UserModel();
        //反射修改对象属性值
        //changeObjeceProperty(UserModel.class,userModel,userInfoMap);
        if(synthesizeService.updateUserInfo(userInfoModel)>0){
            UserModel userModel = userService.selectUser(userInfoModel.getUid());
            if(userModel.getFlag()==1) {
                userService.updateFlag(userModel.getId());
            }
            this.returnData(returnDataModel,0,"","修改数据成功");
        }else{
            returnDataModel.setMessage("修改数据失败");
        }
        return returnDataModel;
    }
    //资讯列表
    @RequestMapping(value="listnews")
    public ReturnDataModel listNews(
            @RequestParam("type") String type,
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "5") String limit
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<NewsModel> newsList = newsService.listNews(Integer.valueOf(type));
        List<Map<String,Object>> listData = new ArrayList<>();
        for(int i=0,num=newsList.size();i<num;i++){
            Map<String,Object> tempMap = new HashMap<String, Object>();
            tempMap.put("id",newsList.get(i).getId());
            tempMap.put("title",newsList.get(i).getTitle());
            tempMap.put("creater",newsList.get(i).getCreater());
            tempMap.put("type",newsService.getTypeName(newsList.get(i).getType()));
            tempMap.put("content",newsList.get(i).getContent());
            tempMap.put("ctime",newsList.get(i).getCtime());
            tempMap.put("state",newsList.get(i).getState());
            listData.add(tempMap);
        }
        if(newsList.size()>0){
            PageInfo<NewsModel> pageInfo = new PageInfo<>(newsList);
            long total = pageInfo.getTotal();     //获取总个数
            int pagenow = pageInfo.getPages();      //获取当前页
            dataMap.put("total",total);
            dataMap.put("pagenum",pagenow);
            dataMap.put("list",listData);
            this.returnData(returnDataModel,0,dataMap,"获取数据集合");
        }else{
            returnDataModel.setMessage("没有获取到相关数据集合");
        }
        return returnDataModel;
    }
    //查看资讯信息
    @RequestMapping(value="selectnews")
    public ReturnDataModel selectNews(@RequestParam("id") int id){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        NewsModel newsModel = newsService.selectNews(id);
        if(!isEmpty(newsModel)){
            this.returnData(returnDataModel,0,newsModel,"获取数据集成功");
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }


    //获取文件信息
    @RequestMapping(value="getfileinfo")
    public ReturnDataModel getFileInfo(@RequestParam("id") int id){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        FilesModel filesModel = filesService.getFileInfo(id);
        if(!isEmpty(filesModel)){
            this.returnData(returnDataModel,0,filesModel,"获取数据集成功");
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }
    //查看资讯信息
    @RequestMapping(value="searchstatement")
    public ReturnDataModel searchStatement(@RequestParam("id") int id){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        StatementModel statementModel= statementService.selectStatement(id);
        if(!isEmpty(statementModel)){
            this.returnData(returnDataModel,0,statementModel,"获取数据集成功");
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }
    @RequestMapping(value="getstatementbyids")
    public ReturnDataModel getStatementByIds(@RequestBody List<Integer> stids){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<StatementModel> listData = statementService.getStatementById(stids);
        if(listData.size()>0){
            this.returnData(returnDataModel,0,listData,"获取数据集合");
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }
    @RequestMapping(value="getfilebyids")
    public ReturnDataModel getFileByIds(@RequestBody List<Integer> ids){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        if(ids.size()>0) {
            List<FilesModel> listData = filesService.getFileById(ids);
            if (listData.size() > 0) {
                this.returnData(returnDataModel, 0, listData, "获取数据集合");
            } else {
                returnDataModel.setMessage("获取数据集失败");
            }
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }

    @RequestMapping(value="getFiles")
    @ResponseBody
    public Object getItem(@RequestParam("itemId") int id){
      return  itemService.getFileList(id);
    }

}
