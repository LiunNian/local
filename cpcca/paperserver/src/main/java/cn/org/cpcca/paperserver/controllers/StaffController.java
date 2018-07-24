package cn.org.cpcca.paperserver.controllers;

import cn.org.cpcca.paperserver.mappers.ItemMapper;
import cn.org.cpcca.paperserver.models.*;
import cn.org.cpcca.paperserver.servcie.*;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

/**
 * 工作人员控制器
 */
@RestController
@RequestMapping(value="staff",method = RequestMethod.POST)
public class StaffController extends BaseController {
    @Autowired
    private FilesServiceInterface filesService;
    @Autowired
    private ItemServiceInterface itemService;
    @Autowired
    private ThemeServiceInterface themeService;
    @Autowired
    private DirectionServiceInterface directionService;
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
    //审核
    //获取用户列表
    @RequestMapping(value="listuser")
    public ReturnDataModel listUser(){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<Integer> ids = userService.listUserId("senioradmin");
        List<UserModel> listData = userService.listUser(ids);
        if(listData.size()>0){
            this.returnData(returnDataModel,0,listData,"获取数据集合");
        }else{

            returnDataModel.setMessage("没有获取到相关数据集合");
        }
        return returnDataModel;
    }
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
    //创建项目
    @RequestMapping(value="additem")
    public ReturnDataModel addItem(
            @RequestParam("uid") int uid,
            @RequestParam("fuid") String fuid,
            @RequestParam("stid") String stid,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("instruction") String instruction
    ){
        //初始化返回对象和data
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        ItemModel itemModel = new ItemModel();
        int type =1;
        itemModel.setUid(uid);
        itemModel.setFuid(Integer.valueOf(fuid));
        itemModel.setStid(stid);
        itemModel.setType(type);
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setInstruction(instruction);
        if(itemService.addItem(itemModel)>0){
            dataMap.put("itid",String.valueOf(itemModel.getId()));
            this.returnData(returnDataModel,0,dataMap,"创建项目成功");
        }else{
            returnDataModel.setMessage("创建项目失败");
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
    //修改项目
    @RequestMapping(value="updateitem")
    public ReturnDataModel updateItem(@RequestBody Map<String,Object> itemMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        ItemModel  itemModel = new ItemModel();
        itemModel.setType(1);
        //System.out.println(itemMap);
        //反射修改对象属性值
        changeObjeceProperty(ItemModel.class,itemModel,itemMap);
        int resultData = itemService.updateItem(itemModel);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"修改论文活动成功");
        }else if(resultData==0){
            returnDataModel.setMessage("论文活动未修改");
        }else{
            returnDataModel.setMessage("修改论文活动失败");
        }
        return returnDataModel;
    }
    //项目列表
    @RequestMapping(value="listitem")
    public ReturnDataModel listItem(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value="limit",defaultValue = "12") String limit ){
        //初始化返回对象和data
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        if(Integer.valueOf(limit)>0) {
            PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit), true);
        }
        List<ItemModel> listItem = itemService.listItemAll();
        List<Map<String,Object>> listData = new ArrayList<>();
        for(int i=0,num=listItem.size();i<num;i++){

            Map<String,Object> tempMap = new HashMap<String, Object>();
            tempMap.put("id",listItem.get(i).getId());
            if(listItem.get(i).getUid()>0){
                tempMap.put("creater",userService.selectUser(listItem.get(i).getUid()).getUsername());
            }else{
                tempMap.put("creater","异常");
            }
            tempMap.put("uid",listItem.get(i).getUid());
            tempMap.put("stid",listItem.get(i).getStid());
            tempMap.put("type",itemService.getTypeName(listItem.get(i).getType()));

            if(listItem.get(i).getType()>0){
                tempMap.put("type",itemService.getTypeName(listItem.get(i).getType()));
            }else{
                tempMap.put("type","未指定");
            }
            if(listItem.get(i).getFuid()>0){
                tempMap.put("fuid",userService.selectUser(listItem.get(i).getFuid()).getUsername());
            }else{
                tempMap.put("fuid","未指定");
            }
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

    //项目回收站
    @RequestMapping(value="listitembin")
    public ReturnDataModel listItemBin(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value="limit",defaultValue = "12") String limit ){
        //初始化返回对象和data
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        if(Integer.valueOf(limit)>0) {
            PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit), true);
        }
        List<ItemModel> listItem = itemService.listItemBin();
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
    //修改项目状态
    @RequestMapping(value="itemstate")
    public ReturnDataModel updataItemState(@RequestBody Map<String,Object> itemMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        int resultData = itemService.updateState(itemMap);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"修改项目状态成功");
        }else if(resultData==0){
            returnDataModel.setMessage("项目状态未修改");
        }else{
            returnDataModel.setMessage("修改项目状态失败");
        }
        return returnDataModel;
    }
    //删除项目
    @RequestMapping(value="deleteitem")
    public ReturnDataModel deleteItem(@RequestBody List<Integer> itids){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        int resultData = itemService.deleteItems(itids);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"删除项目成功");
        }else{
            returnDataModel.setMessage("删除项目失败");
        }
        return returnDataModel;
    }
    //创建主题
    @RequestMapping(value="addtheme")
    public ReturnDataModel addTheme(@RequestBody Map<String,Object> themeMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        ThemeModel themeModel = new ThemeModel();
        Map<String,Object> theme = (Map<String,Object>)themeMap.get("theme");
        List<String> directions = (List<String>)themeMap.get("directions");
        themeModel.setItid(Integer.valueOf(String.valueOf(theme.get("itid"))));
        themeModel.setTitle(String.valueOf(theme.get("title")));
        themeModel.setDescription(String.valueOf(theme.get("description")));
        List<DirectionModel> directionModels = new ArrayList<>();
        for(String direction:directions){
            DirectionModel directionModel = new DirectionModel();
            directionModel.setTitle(direction);
            directionModels.add(directionModel);
        }
        dataMap = synthesizeService.addTheme(themeModel,directionModels);
        if(dataMap.size()>0){
            this.returnData(returnDataModel,0,dataMap,"创建主题成功");
        }else{
            returnDataModel.setMessage("创建主题失败");
        }
        return returnDataModel;
    }


    //查看主题信息
    @RequestMapping(value="selecttheme")
    public ReturnDataModel selectTheme(@RequestParam("id") int id){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        ThemeModel themeModel = themeService.selectTheme(id);
        List<DirectionModel> directions = directionService.listDircetion(id);
        dataMap.put("theme",themeModel);
        dataMap.put("directions",directions);
        if(!isEmpty(themeModel)){
            this.returnData(returnDataModel,0,dataMap,"获取数据集成功");
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }
    //修改主题
    @RequestMapping(value="updatetheme")
    public ReturnDataModel updateTheme(@RequestBody Map<String,Object> themeMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        ThemeModel themeModel = new ThemeModel();
        Map<String,Object> theme = (Map<String,Object>)themeMap.get("theme");
        List<String> directions = (List<String>)themeMap.get("directions");
        List<Integer> dids = (List<Integer>)themeMap.get("dids");
        themeModel.setId(Integer.valueOf(String.valueOf(theme.get("id"))));
        themeModel.setItid(Integer.valueOf(String.valueOf(theme.get("itid"))));
        themeModel.setTitle(String.valueOf(theme.get("title")));
        themeModel.setDescription(String.valueOf(theme.get("description")));
        List<DirectionModel> directionModels = new ArrayList<>();
        for(String direction:directions){
            DirectionModel directionModel = new DirectionModel();
            directionModel.setTitle(direction);
            directionModels.add(directionModel);
        }
        dataMap = synthesizeService.updateTheme(themeModel,directionModels,dids);
        if(dataMap.size()>0){
            this.returnData(returnDataModel,0,dataMap,"修改主题信息成功");
        }else{
            returnDataModel.setMessage("修改主题信息失败");
        }
        return returnDataModel;
    }
    //主题列表
    @RequestMapping(value="listtheme")
    public ReturnDataModel lisTheme(
            @RequestParam("itid") String itid,
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "10") String limit
    ){
        //初始化返回对象和data
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        if(Integer.valueOf(limit)>0){
            PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit),true);
        }
        List<ThemeModel> listData = themeService.listTheme(Integer.valueOf(itid));
        if(Integer.valueOf(limit)>0) {
            PageInfo<ThemeModel> pageInfo = new PageInfo<>(listData);
            long total = pageInfo.getTotal();     //获取总个数
            int pagenow = pageInfo.getPages();      //获取当前页
            dataMap.put("total", total);
            dataMap.put("pagenum", pagenow);
            dataMap.put("list", listData);
        }
        if(listData.size()>0){
            if(Integer.valueOf(limit)>0) {
                this.returnData(returnDataModel,0, dataMap, "获取数据集合");
            }else{
                this.returnData(returnDataModel,0, listData, "获取数据集合");
            }
        }else{
            returnDataModel.setMessage("没有获取到相关数据集合");
        }
        return returnDataModel;
    }
    @RequestMapping(value="listthemebin")
    public ReturnDataModel lisThemeBin(
            @RequestParam("itid") String itid,
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "10") String limit
    ){
        //初始化返回对象和data
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        if(Integer.valueOf(limit)>0){
            PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit),true);
        }
        List<ThemeModel> listData = themeService.listThemeBin(Integer.valueOf(itid));

        if(Integer.valueOf(limit)>0) {
            PageInfo<ThemeModel> pageInfo = new PageInfo<>(listData);
            long total = pageInfo.getTotal();     //获取总个数
            int pagenow = pageInfo.getPages();      //获取当前页
            dataMap.put("total", total);
            dataMap.put("pagenum", pagenow);
            dataMap.put("list", listData);
        }
        if(listData.size()>0){
            if(Integer.valueOf(limit)>0) {
                this.returnData(returnDataModel,0, dataMap, "获取数据集合");
            }else{
                this.returnData(returnDataModel,0, listData, "获取数据集合");
            }
        }else{
            returnDataModel.setMessage("没有获取到相关数据集合");
        }
        return returnDataModel;
    }
    //修改主题状态
    @RequestMapping(value="themestate")
    public ReturnDataModel updateThemeState(@RequestBody Map<String,Object> themeMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        int resultData = themeService.updateState(themeMap);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"修改主题状态成功");
        }else if(resultData==0){
            returnDataModel.setMessage("主题状态未修改");
        }else{
            returnDataModel.setMessage("修改主题状态失败");
        }
        return returnDataModel;
    }
    //彻底删除主题
    @RequestMapping(value="deletetheme")
    public ReturnDataModel delectTheme(@RequestBody List<Integer> thids){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        int resultData = themeService.deleteTheme(thids);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"删除主题成功");
        }else{
            returnDataModel.setMessage("删除主题失败");
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



    //添加论文信息
    @RequestMapping(value="paper",produces = {"application/json"})
    public ReturnDataModel addpaper(@RequestBody PaperModel paperModel){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        if(paperService.addPaper(paperModel)>0){
            dataMap.put("pid",String.valueOf(paperModel.getId()));
            this.returnData(returnDataModel,0,dataMap,"添加论文成功");
        }else{
            returnDataModel.setMessage("添加论文失败");
        }
        return returnDataModel;
    }


    //删除论文信息
    @RequestMapping(value="deletepaper")
    public ReturnDataModel deletepaper(){
        return new ReturnDataModel("");
    }
    //上传论文
    @RequestMapping(value="upload")
    @CrossOrigin("*")
    public ReturnDataModel upload(@RequestParam("file") MultipartFile file){
        System.out.println(JSON.toJSONString(file, true));
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
    //上传论文
    @RequestMapping(value="eidtfile")
    @CrossOrigin("*")
    public ReturnDataModel editFileupload(@RequestParam("file") MultipartFile file){

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
            String sign = "edits";
            String fileUrl = "http://file.cqshusheng.cn/";
            if(".doc".equals(suffixName)||".docx".equals(suffixName)){
                fileUrl += sign+"/word/";
                this.fileUpload(returnDataModel,file,(FilesService) filesService,"editsword",fileUrl);
            }else if(".png".equals(suffixName)||".jpg".equals(suffixName)){
                fileUrl += sign+"/pic/";
                this.fileUpload(returnDataModel,file,(FilesService) filesService,"editspic",fileUrl);
            }else{
                returnDataModel.setMessage("文件类型错误");
            }
        }
        return returnDataModel;
    }
    @RequestMapping(value="getnewstype")
    public ReturnDataModel getNewstype(){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<NewsTypeModel> listData = newsService.newsType();
        if(listData.size()>0){
            this.returnData(returnDataModel,0,listData,"获取数据集成功");
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }
    //添加资讯信息
    @RequestMapping(value="addnews")
    public ReturnDataModel addNews(@RequestBody Map<String,Object> newsMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        NewsModel newsModel = new NewsModel();
        this.changeObjeceProperty(NewsModel.class,newsModel,newsMap);
        if(newsService.addNews(newsModel)>0){
            dataMap.put("nid",String.valueOf(newsModel.getId()));
            this.returnData(returnDataModel,0,dataMap,"添加信息成功");
        }else{
            returnDataModel.setMessage("添加信息失败");
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
    //资讯回收站
    @RequestMapping(value="listnewsbin")
    public ReturnDataModel listNewsBin(
            @RequestParam("type") String type,
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "5") String limit
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<NewsModel> listData = newsService.listNewsBin(Integer.valueOf(type));
        if(listData.size()>0){
            PageInfo<NewsModel> pageInfo = new PageInfo<>(listData);
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
    //修改资讯
    @RequestMapping(value="updatenews")
    public ReturnDataModel updateNews(@RequestBody Map<String,Object> newsMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        NewsModel newsModel = new NewsModel();
        changeObjeceProperty(NewsModel.class,newsModel,newsMap);
        int resultData = newsService.updateNews(newsModel);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"修改资讯信息成功");
        }else if(resultData==0){
            returnDataModel.setMessage("资讯信息未修改");
        }else{
            returnDataModel.setMessage("修改资讯信息失败");
        }
        return returnDataModel;
    }
    //修改项目状态
    @RequestMapping(value="newsstate")
    public ReturnDataModel updataNewsState(@RequestBody Map<String,Object> newsMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        int resultData = newsService.updateState(newsMap);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"修改项目状态成功");
        }else if(resultData==0){
            returnDataModel.setMessage("项目状态未修改");
        }else{
            returnDataModel.setMessage("修改项目状态失败");
        }
        return returnDataModel;
    }
    //删除项目
    @RequestMapping(value="deletenews")
    public ReturnDataModel deleteNews(@RequestBody List<Integer> nids){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        int resultData = newsService.deleteNews(nids);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"删除项目成功");
        }else{
            returnDataModel.setMessage("删除项目失败");
        }
        return returnDataModel;
    }
    //添加资讯信息
    @RequestMapping(value="addstatement")
    public ReturnDataModel addStatement(@RequestBody Map<String,Object> statementMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        StatementModel statementModel = new StatementModel();
        this.changeObjeceProperty(StatementModel.class,statementModel,statementMap);
        if(statementService.addStatement(statementModel)>0){
            dataMap.put("nid",String.valueOf(statementModel.getId()));
            this.returnData(returnDataModel,0,dataMap,"添加声明成功");
        }else{
            returnDataModel.setMessage("添加声明失败");
        }
        return returnDataModel;
    }

    //查看资讯信息
    @RequestMapping(value="selectstatement")
    public ReturnDataModel selectStatement(@RequestParam("id") int id){
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

    //声明列表
    @RequestMapping(value="liststatement")
    public ReturnDataModel listStatment(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "5") String limit
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<StatementModel> listData = statementService.listStatement();
        if(listData.size()>0){
            PageInfo<StatementModel> pageInfo = new PageInfo<StatementModel>(listData);
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
    //声明列表
    @RequestMapping(value="liststatementall")
    public ReturnDataModel listStatmentAll(){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<StatementModel> listData = statementService.listStatementAll();
        if(listData.size()>0){
            this.returnData(returnDataModel,0,listData,"获取数据集合");
        }else{
            returnDataModel.setMessage("没有获取到相关数据集合");
        }
        return returnDataModel;
    }

    @RequestMapping(value="getstatements")
    public ReturnDataModel getStatements(@RequestBody List<Integer> stids){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<StatementModel> listData = statementService.getStatementById(stids);
        if(stids.size()>0){
            if(listData.size()>0){
                this.returnData(returnDataModel,0,listData,"获取数据集合");
            }else{
                returnDataModel.setMessage("获取数据集失败");
            }
        }else{
            returnDataModel.setMessage("获取数据集失败");
        }
        return returnDataModel;
    }
    //声明回收站
    @RequestMapping(value="liststatementbin")
    public ReturnDataModel listStatmentBin(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value = "limit",defaultValue = "5") String limit
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        PageHelper.startPage(Integer.valueOf(page),Integer.valueOf(limit));
        List<StatementModel> listData = statementService.listStatementBin();
        if(listData.size()>0){
            PageInfo<StatementModel> pageInfo = new PageInfo<StatementModel>(listData);
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
    //修改声明
    @RequestMapping(value="updatestatement")
    public ReturnDataModel updateStatement(@RequestBody Map<String,Object> statementMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        StatementModel statementModel = new StatementModel();
        changeObjeceProperty(StatementModel.class,statementModel,statementMap);
        int resultData = statementService.updateStatement(statementModel);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"修改声明信息成功");
        }else if(resultData==0){
            returnDataModel.setMessage("声明信息未修改");
        }else{
            returnDataModel.setMessage("修改声明信息失败");
        }
        return returnDataModel;
    }
    //修改声明状态
    @RequestMapping(value="statementstate")
    public ReturnDataModel updataStatementState(@RequestBody Map<String,Object> statementMap){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        int resultData = statementService.updateState(statementMap);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"修改声明状态成功");
        }else if(resultData==0){
            returnDataModel.setMessage("声明状态未修改");
        }else{
            returnDataModel.setMessage("修改声明状态失败");
        }
        return returnDataModel;
    }
    //删除声明
    @RequestMapping(value="deletestatement")
    public ReturnDataModel deleteStatement(@RequestBody List<Integer> ids){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        int resultData = statementService.deleteStatement(ids);
        if(resultData>0){
            this.returnData(returnDataModel,0,dataMap,"删除声明成功");
        }else{
            returnDataModel.setMessage("删除声明失败");
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
}
