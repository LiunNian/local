package cn.org.cpcca.paperserver.controllers;

import cn.org.cpcca.paperserver.models.*;
import cn.org.cpcca.paperserver.servcie.*;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.deploy.util.StringUtils;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value="paperreview",method = RequestMethod.POST)
@SuppressWarnings("unchecked")
public class PaperReviewController extends BaseController{
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private ItemServiceInterface itemService;
    @Autowired
    private ThemeServiceInterface themeService;
    @Autowired
    private DirectionServiceInterface directionService;
    @Autowired
    private SynthesizeServiceInterface synthesizeService;
    @Autowired
    private PaperServiceInterface paperService;
    @Autowired
    private ReviewPaperService reviewPaperServiceImpl;
    @Autowired
    private ReviewItemService reviewItemServiceImpl;
    @Autowired
    private ReviewExpertItemService reviewExpertItemServiceImpl;
    @Autowired
    private UserServiceInterface userService;
    @Autowired
    private ReviewPaperExpertService reviewPaperExpertServiceImpl;
    //评审列表
    @RequestMapping(value="review")
    public ReturnDataModel review(
            @RequestParam(value="itid",defaultValue = "0") int itid
    ){

        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<Map<String,Object>> listData = new ArrayList<>();
        List<ReviewItemBean> resultList = new ArrayList<ReviewItemBean>();
        resultList = reviewItemServiceImpl.selectByItid(itid);

        for(int i=0,num=resultList.size();i<num;i++){
            Map<String,Object> tempMap = new HashMap<String, Object>();
            tempMap.put("itid",resultList.get(i).getItid());
            tempMap.put("revid",resultList.get(i).getRevid());
            tempMap.put("title",resultList.get(i).getTitle());
            tempMap.put("state",resultList.get(i).getState());
            listData.add(tempMap);
        }
        if(resultList.size()>0){
                this.returnData(returnDataModel,0, listData, "获取数据集合");
        }else if(resultList.size()==0){
            returnDataModel.setMessage("没有获取到相关数据集合");
        }else{
            returnDataModel.setMessage("网络繁忙请稍后再试");
        }
        return returnDataModel;
    }
    //评审论文列表
    @RequestMapping(value="list")
    public ReturnDataModel list(
            @RequestParam(value="itid",defaultValue = "0") int itid,
            @RequestParam(value="thid",defaultValue = "0") int thid,
            @RequestParam(value="did",defaultValue = "0") int did,
            @RequestParam(value="revid",defaultValue = "0") int revid
    ){
        List<Integer> ids = new LinkedList<Integer>();
        //获取方向id
        if(did != 0){
            ids.add(did);
        }else if(thid!=0){
            ids = synthesizeService.getDirectionByThemeId(thid);
        }else if(itid != 0){
            ids = synthesizeService.getDirectionByItemId(itid);
        }

//        System.out.println("<------list----->");
//        System.out.println(itid);
//        System.out.println(revid);
//        System.out.println(JSON.toJSONString(ids));
//        System.out.println("<------list end----->");
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
        List<ReviewPaperModel> resultList = new ArrayList<ReviewPaperModel>();
        if(ids.size()>0) {
            //PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit), true);
            if (revid == 0) {
                if (ids.size() > 0) {
                    resultList = paperService.searchPapers(ids);
                }
            } else {
                Map<String, Object> queryParam = new HashMap<>();
                queryParam.put("revid", revid);
                queryParam.put("ids", ids);
                resultList = reviewPaperServiceImpl.searchPapersByDirection(queryParam);
                //System.out.println(JSON.toJSONString(resultList));
            }
            for (int i = 0, num = resultList.size(); i < num; i++) {
                Map<String, Object> tempMap = new HashMap<String, Object>();
                tempMap.put("revpid", resultList.get(i).getRevpid());
                tempMap.put("revid", resultList.get(i).getRevid());
                tempMap.put("id", resultList.get(i).getId());
                tempMap.put("title", resultList.get(i).getTitle());
                tempMap.put("item", resultList.get(i).getItem());
                tempMap.put("theme", resultList.get(i).getTheme());
                tempMap.put("direction", resultList.get(i).getDirection());
                tempMap.put("progress", resultList.get(i).getProgress());
                tempMap.put("result", resultList.get(i).getResult());
                tempMap.put("ctime", df.format(resultList.get(i).getCtime()));
                //List<String> experts = reviewPaperExpertServiceImpl.selectByRevpid(resultList.get(i).getRevpid());
                //tempMap.put("experts",StringUtils.join(experts, ","));
                String expertStr = resultList.get(i).getExperts();
                if (expertStr == null) {
                    expertStr = "";
                }
                tempMap.put("experts", expertStr);
                listData.add(tempMap);
            }
        }
        if(resultList.size()>0){
            this.returnData(returnDataModel,0, listData, "获取数据集合");
        }else if(resultList.size()==0){
            returnDataModel.setMessage("没有获取到相关数据集合");
        }else{
            returnDataModel.setMessage("网络繁忙请稍后再试");
        }
        return returnDataModel;
    }

    //创建评审
    @RequestMapping(value="addreview")
    public ReturnDataModel addreview(
            @RequestParam(value="itid",defaultValue = "") int itid, //论文活动项目
            @RequestParam(value="ids",defaultValue = "") Integer[] ids, //论文列表id
            @RequestParam(value="title",defaultValue = "") String title, //评审名称
            @RequestParam(value="exids",defaultValue = "") Integer[] exids //专家列表
    ){
//        System.out.println("<------addreview----->");
//        System.out.println(itid);
//        System.out.println(JSON.toJSONString(ids));
//        System.out.println(title);
//        System.out.println(JSON.toJSONString(exids));
//        System.out.println("<------addreviewend----->");
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        ReviewItemBean reviewItemBean = new ReviewItemBean();
        reviewItemBean.setItid(itid);
        reviewItemBean.setTitle(title);
        int revid = reviewItemServiceImpl.insertSelective(reviewItemBean);
        if(revid>0){
            List<PaperModel> paperList = paperService.selectPapers(Arrays.asList(ids));
            for(int i=0,num=paperList.size();i<num;i++){
                Map<String,Object> tempMap = new HashMap<String, Object>();
                tempMap.put("id",paperList.get(i).getId());
                tempMap.put("title",paperList.get(i).getTitle());
                ReviewPaperBean reviewPaperBean = new ReviewPaperBean();
                reviewPaperBean.setDid(paperList.get(i).getDid());
                reviewPaperBean.setRevid(revid);
                reviewPaperBean.setId(paperList.get(i).getId());
                reviewPaperServiceImpl.insertSelective(reviewPaperBean);
            }
            List<Integer> exidsArr = Arrays.asList(exids);
            for(int i=0,num=exidsArr.size();i<num;i++){
                ReviewExpertItemBean reviewExpertItemBean = new ReviewExpertItemBean();
                reviewExpertItemBean.setRevid(revid);
                reviewExpertItemBean.setUid(exidsArr.get(i));
                reviewExpertItemServiceImpl.insertSelective(reviewExpertItemBean);
            }
            List<Integer> dids = new LinkedList<Integer>();
            dids = synthesizeService.getDirectionByItemId(itid);
            if(dids.size()>0) {
                Map<String, Object> queryParam = new LinkedHashMap<>();
                queryParam.put("revid", revid);
                queryParam.put("ids", dids);
               // System.out.println(JSON.toJSONString(queryParam));
                List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
                List<ReviewPaperModel> resultList = new ArrayList<ReviewPaperModel>();
                resultList = reviewPaperServiceImpl.searchPapersByDirection(queryParam);
                for(int i=0,num=resultList.size();i<num;i++){
                    Map<String,Object> tempMap = new HashMap<String, Object>();
                    tempMap.put("revpid", resultList.get(i).getRevpid());
                    tempMap.put("revid", resultList.get(i).getRevid());
                    tempMap.put("id", resultList.get(i).getId());
                    tempMap.put("title", resultList.get(i).getTitle());
                    tempMap.put("item", resultList.get(i).getItem());
                    tempMap.put("theme", resultList.get(i).getTheme());
                    tempMap.put("direction", resultList.get(i).getDirection());
                    tempMap.put("progress", resultList.get(i).getProgress());
                    tempMap.put("result", resultList.get(i).getResult());
                    tempMap.put("ctime", df.format(resultList.get(i).getCtime()));

                    listData.add(tempMap);
                }
                this.returnData(returnDataModel,0, listData, "获取数据集合");
            }else{
                //System.out.println("<<<-----itid"+JSON.toJSONString(itid));
                returnDataModel.setMessage("未获取到方向信息");
            }
        }else{
            //returnDataModel.setMessage("网络繁忙请稍后再试");
            returnDataModel.setMessage("网络超时，请稍后再试");
        }

        return returnDataModel;
    }
    //结束审核和重新开启
    @RequestMapping(value="closereview")
    public ReturnDataModel closereview(
            @RequestParam(value="revid",defaultValue = "") int revid
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        try{
            ReviewItemBean selfBean = reviewItemServiceImpl.selectById(revid);
           // System.out.println(JSON.toJSONString(selfBean));
            ReviewItemBean reviewItemBean = new ReviewItemBean();
            if(selfBean.getState()==1){
                reviewItemBean.setRevid(revid);
                reviewItemBean.setState(2);
                reviewItemServiceImpl.updateByIdSelective(reviewItemBean);
                this.returnData(returnDataModel,0, 2, "关闭成功");
            }else if(selfBean.getState()==2){
                reviewItemBean.setRevid(revid);
                reviewItemBean.setState(1);
                reviewItemServiceImpl.updateByIdSelective(reviewItemBean);
                this.returnData(returnDataModel,0, 1, "重新开启成功");
            }else{
                returnDataModel.setMessage("为不可操作状态");
            }
        }catch(Exception e){
            returnDataModel.setMessage("请重新操作");
        }
        return returnDataModel;
    }
    //删除审核
    @RequestMapping(value="deletereview")
    public ReturnDataModel deletereview(
            @RequestParam(value="revid",defaultValue = "") int revid
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        try{
            reviewExpertItemServiceImpl.deleteByRevId(revid);
            reviewPaperServiceImpl.deleteByRevId(revid);
            reviewItemServiceImpl.deleteById(revid);
            this.returnData(returnDataModel,0, "", "删除项目成功");
        }catch(Exception e){
            returnDataModel.setMessage("请重新操作");
        }
        return returnDataModel;
    }
    //项目 主题 方向 联动
    @RequestMapping(value="linkage")
    public ReturnDataModel listItem(
            @RequestParam(value="tag",defaultValue = "") String tag,
            @RequestParam(value="id",defaultValue = "0") int id
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<Map<String,Object>> listData = new ArrayList<>();
        if(listData.size()<1&&!tag.equals("")){
            Map<String,Object> tempMap = new HashMap<String, Object>();
            tempMap.put("id",0);
            tempMap.put("title","全部");
            listData.add(tempMap);
        }
        if(tag.equals("")) {
            List<ItemModel> resultList = new ArrayList<ItemModel>();
            resultList = itemService.listItemReview();
            for(int i=0,num=resultList.size();i<num;i++){
                Map<String,Object> tempMap = new HashMap<String, Object>();
                tempMap.put("id",resultList.get(i).getId());
                tempMap.put("title",resultList.get(i).getTitle());
                listData.add(tempMap);
            }
        }
        if(tag.equals("itid")) {
            List<ThemeModel> resultList = new ArrayList<ThemeModel>();
            resultList = themeService.listTheme(id);
            for(int i=0,num=resultList.size();i<num;i++){
                Map<String,Object> tempMap = new HashMap<String, Object>();
                tempMap.put("id",resultList.get(i).getId());
                tempMap.put("title",resultList.get(i).getTitle());
                listData.add(tempMap);
            }
        }
        if(tag.equals("thid")){
            List<DirectionModel> resultList = new ArrayList<DirectionModel>();
            resultList = directionService.listDircetion(id);
            for(int i=0,num=resultList.size();i<num;i++){
                Map<String,Object> tempMap = new HashMap<String, Object>();
                tempMap.put("id",resultList.get(i).getId());
                tempMap.put("title",resultList.get(i).getTitle());
                listData.add(tempMap);
            }
        }
        if(listData.size()>0){
            this.returnData(returnDataModel,0, listData, "获取数据集合");
        }else if(listData.size()==0){
            returnDataModel.setMessage("没有获取到相关数据集合");
        }else{
            returnDataModel.setMessage("网络繁忙请稍后再试");
        }
        return returnDataModel;
    }
    //判断是否存在评审
    @RequestMapping(value="ison")
    public ReturnDataModel listItem(
            @RequestParam(value="itid",defaultValue = "") int itid
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<ReviewItemBean> resultList = reviewItemServiceImpl.selectByItid(itid);
        List<Map<String,Object>> listData = new ArrayList<>();
        int flag = resultList.size();
        this.returnData(returnDataModel,0, flag, "data为0表示没有初审,大于0表示有多少个评审");
        return returnDataModel;
    }
    //全部专家列表
    @RequestMapping(value = "expertall")
    public ReturnDataModel expertAll(){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<Map<String,Object>> resultList= userService.getRoleNameByIds(6);
        if(resultList.size()>0){
            this.returnData(returnDataModel,0, resultList, "获取数据集合");
        }else if(resultList.size()==0){
            returnDataModel.setMessage("没有获取到相关数据集合");
        }else{
            returnDataModel.setMessage("网络繁忙请稍后再试");
        }
        return returnDataModel;
    }
    //评审分配专家列表

    @RequestMapping(value = "reviewexpert")
    public ReturnDataModel reviewexpert(
            @RequestParam(value="revid",defaultValue = "") int revid
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<ReviewExpertModel> resultList = reviewExpertItemServiceImpl.selectByRevid(revid);
        List<Map<String,Object>> listData = new ArrayList<>();
        //System.out.println(JSON.toJSONString(resultList));
        for(int i=0,num=resultList.size();i<num;i++){
            Map<String,Object> tempMap = new HashMap<String, Object>();
            tempMap.put("id",resultList.get(i).getId());
            tempMap.put("username",resultList.get(i).getUsername());
            listData.add(tempMap);
//            List<ReviewPaperBean> countNumList = reviewPaperServiceImpl.getReviewPapersListByUid(resultList.get(i).getId());
//            if(countNumList.size()<1){
//                listData.add(tempMap);
//            }
        }
        if(resultList.size()>0){
            this.returnData(returnDataModel,0, listData, "获取数据集合");
        }else if(resultList.size()==0){
            returnDataModel.setMessage("没有获取到相关数据集合");
        }else{
            returnDataModel.setMessage("网络繁忙请稍后再试");
        }
        return returnDataModel;
    }
    //专家分配
    @RequestMapping(value="allotexpert")
    public ReturnDataModel allotExpert(
            @RequestParam(value="revpid",defaultValue = "") Integer[] revpids,
            @RequestParam(value="exids",defaultValue = "") Integer[] exids

    ) {
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        ReviewPaperExpertBean reviewPaperExpertBean = new ReviewPaperExpertBean();
        List<Integer> revpidsArr = Arrays.asList(revpids);
        List<Integer> exidsArr = Arrays.asList(exids);
        //分配规则
        if(exidsArr.size()>0){

        }
        //分配
        //reviewPaperExpertBean.setRevpid();
        //reviewPaperExpertBean.setUid();
        //reviewPaperExpertServiceImpl.insertSelective(reviewPaperExpertBean);
        return returnDataModel;
    }
    //指定分配专家
    @RequestMapping(value="updateallot")
    public ReturnDataModel updateAllot(
            @RequestParam(value="revpids",defaultValue = "") Integer[] revpids,
            @RequestParam(value="exids",defaultValue = "") Integer[] exids
    ){

        //System.out.println(JSON.toJSONString(revpids));
        //System.out.println(JSON.toJSONString(exids));
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<Integer> revpidsArr = Arrays.asList(revpids);
        List<Integer> tempList = Arrays.asList(exids);
        List<Integer> exidsArr = new ArrayList<Integer>();
        for(Integer i : tempList){
            if(!exidsArr.contains(i)){//判断是否有重复数据，如果没有就将数据装进临时集合
                exidsArr.add(i);
            }
        }
        //System.out.println(JSON.toJSONString(revpidsArr));
        //System.out.println(JSON.toJSONString(exidsArr));
        List<Integer> resultExid = reviewPaperExpertServiceImpl.selectUidByRevpid(revpidsArr);
        Map<String,List<Integer>> params = new HashMap<String,List<Integer>>();
        params.put("revpids",revpidsArr);
        List<Integer> exidList = new ArrayList<Integer>();
        List<Integer> exidArr = new ArrayList<Integer>();
        if(exidsArr.size()>0) {
            for(Integer temp :resultExid){
                if(!exidsArr.contains(temp) && !exidList.contains(temp)){
                    exidList.add(temp);
                }
            }
            for(Integer temp :exidsArr){
                if(!resultExid.contains(temp) && !exidList.contains(temp)){
                    exidArr.add(temp);
                }
            }
            params.put("exids",exidList);
            if(exidList.size()>0) {
                //删除专家
                reviewPaperExpertServiceImpl.deleteByRevpid(params);
            }
        }
        //增加专家
        try{
            for(int i=0,num=revpidsArr.size();i<num;i++){
                for(int j=0,jnum=exidArr.size();j<jnum;j++){
                    ReviewPaperExpertBean reviewPaperExpertBean = new ReviewPaperExpertBean();
                    reviewPaperExpertBean.setRevpid(revpidsArr.get(i));
                    reviewPaperExpertBean.setUid(exidArr.get(j));
                    reviewPaperExpertServiceImpl.insertSelective(reviewPaperExpertBean);
                }
            }
            this.returnData(returnDataModel,0, "", "指定分配专家成功");
        }catch (Exception e){
            returnDataModel.setMessage("网络繁忙请稍后再试");
        }

        return returnDataModel;
    }
    @RequestMapping("progressandesult")
    public ReturnDataModel progressAndResult(
            @RequestParam(value="revid",defaultValue = "") Integer revid
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<String> progressList = reviewPaperServiceImpl.groupByProgress(revid);
        List<String> resultList = reviewPaperServiceImpl.groupByResult(revid);
        Map<String,Object> listData = new HashMap<String,Object>();
        listData.put("progress",progressList);
        listData.put("result",resultList);
        if(listData.size()>0){
            this.returnData(returnDataModel,0, listData, "获取数据集合");
        }else if(resultList.size()==0){
            returnDataModel.setMessage("没有获取到相关数据集合");
        }else{
            returnDataModel.setMessage("网络繁忙请稍后再试");
        }
        return returnDataModel;
    }
    //检索
    @RequestMapping(value = "reviewsearch")
    public ReturnDataModel reviewSearch(
        @RequestParam(value="revid",defaultValue = "") Integer revid,
        @RequestParam(value="thid",defaultValue = "0") Integer thid,
        @RequestParam(value="did",defaultValue = "0") Integer did,
        @RequestParam(value="progress",defaultValue = "") String progress,
        @RequestParam(value="result",defaultValue = "") String result,
        @RequestParam(value="keyword",defaultValue = "") String keyword
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put("revid",revid);
        if(!progress.equals("")){
            queryMap.put("progress",keyword);
        }
        if(!result.equals("")){
            queryMap.put("result",keyword);
        }
        if(thid!=0){
            queryMap.put("thid",thid);
        }
        if(did!=0){
            queryMap.put("did",thid);
        }
        if(!keyword.equals("")){
            queryMap.put("keyword",keyword);
        }

        List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
        List<ReviewPaperModel> resultList = new ArrayList<ReviewPaperModel>();
        resultList = reviewPaperServiceImpl.searchPapersByKeyword(queryMap);
        for(int i=0,num=resultList.size();i<num;i++){

            Map<String,Object> tempMap = new HashMap<String, Object>();
            tempMap.put("revpid",resultList.get(i).getRevpid());
            tempMap.put("revid",resultList.get(i).getRevid());
            tempMap.put("id",resultList.get(i).getId());
            tempMap.put("title",resultList.get(i).getTitle());
            tempMap.put("item",resultList.get(i).getItem());
            tempMap.put("theme",resultList.get(i).getTheme());
            tempMap.put("direction",resultList.get(i).getDirection());
            tempMap.put("progress",resultList.get(i).getProgress());
            tempMap.put("result",resultList.get(i).getResult());
            tempMap.put("ctime",df.format(resultList.get(i).getCtime()));
            String expertStr = resultList.get(i).getExperts();
            if(expertStr==null){
                expertStr = "";
            }
            tempMap.put("experts",expertStr);
            listData.add(tempMap);
        }
        if(resultList.size()>0){
            this.returnData(returnDataModel,0, listData, "获取数据集合");
        }else if(resultList.size()==0){
            returnDataModel.setMessage("没有获取到相关数据集合");
        }else{
            returnDataModel.setMessage("网络繁忙请稍后再试");
        }
        return returnDataModel;
    }
    //专家论文列表
    @RequestMapping(value = "papers")
    public ReturnDataModel papers(
            @RequestParam(value = "page",defaultValue = "1") String page,
            @RequestParam(value="limit",defaultValue = "10") String limit,
            @RequestParam(value="uid",defaultValue = "") Integer uid,
            @RequestParam(value="thid",defaultValue = "0") Integer thid,
            @RequestParam(value="did",defaultValue = "0") Integer did,
            @RequestParam(value="keyword",defaultValue = "") String keyword
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        List<ReviewPaperListModel> listData = new ArrayList<ReviewPaperListModel>();
        List<ReviewPaperListModel> resultList = new ArrayList<ReviewPaperListModel>();
        //获取专家任务列表
        //***
        PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit), true);
        List<ReviewPaperBean> revList = reviewPaperServiceImpl.getReviewPapersListByUid(uid);
        if(revList.size()>0){
            List<Integer> revpids = new ArrayList<>();
            List<Integer> revids = new ArrayList<>();//评审rvids数组
            for(ReviewPaperBean reviewPaperBean : revList){
                if(!revList.contains(reviewPaperBean.getRevpid())){//判断是否有重复数据，如果没有就将数据装进临时集合
                    revpids.add(reviewPaperBean.getRevpid());
                }
                if(!revids.contains(reviewPaperBean.getRevid())){//判断是否有重复数据，如果没有就将数据装进临时集合
                    revids.add(reviewPaperBean.getRevid());
                }
            }
            Map<String,Object> queryMap = new HashMap<String, Object>();
            queryMap.put("revpids",revpids);
            queryMap.put("uid",uid);
            if(thid!=0){
                queryMap.put("thid",thid);
            }
            if(did!=0){
                queryMap.put("did",thid);
            }
            if(!keyword.equals("")){
                queryMap.put("keyword",keyword);
            }
            resultList = reviewPaperServiceImpl.getReviewPapersList(queryMap);
            for(int i=0,num=resultList.size();i<num;i++){

                Map<String,Object> tempMap = new HashMap<String, Object>();

                listData.add(resultList.get(i));
            }

            if(Integer.valueOf(limit)>0){
                PageInfo<ReviewPaperListModel> pageInfo = new PageInfo<>(resultList);
                long total = pageInfo.getTotal();     //获取总个数
                int pagenow = pageInfo.getPages();      //获取当前页
                dataMap.put("total",total);
                dataMap.put("pagenum",pagenow);
                dataMap.put("list",listData);
                List<Integer> itids  = reviewPaperServiceImpl.getItidsByUid(uid);
                if(itids==null){
                    itids = new ArrayList<>();
                }
                dataMap.put("itid",itids);
            }
            if(resultList.size()>0){
                this.returnData(returnDataModel,0, dataMap, "获取数据集合");
            }else if(resultList.size()==0){
                returnDataModel.setMessage("没有获取到相关数据集合");
            }else{
                returnDataModel.setMessage("网络繁忙请稍后再试");
            }
        }else{
            this.returnData(returnDataModel,1, listData, "暂无评审任务");
        }
        return returnDataModel;
    }
    //专家论文详情
    @RequestMapping(value = "paperinfo")
    public ReturnDataModel paperInfo(
            @RequestParam(value="uid",defaultValue = "") Integer uid,
            @RequestParam(value="revpid",defaultValue = "") Integer revpid
    ){
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        //判断是否有权限查看数据
        ReviewPaperExpertBean reviewPaperExpertBean = new ReviewPaperExpertBean();
        reviewPaperExpertBean.setRevpid(revpid);
        reviewPaperExpertBean.setUid(uid);
        int verify = reviewPaperExpertServiceImpl.countSelective(reviewPaperExpertBean);
        List<String> roles = userService.getRoles(uid);
        if(verify>0||roles.contains("superadmin")||roles.contains("senioradmin")){
            List<Map<String,Object>> listData = new ArrayList<Map<String,Object>>();
            List<ReviewPaperInfoModel> resultList = new ArrayList<ReviewPaperInfoModel>();
            Map<String,Object> queryMap = new HashMap<String, Object>();
            queryMap.put("revpid",revpid);
            resultList = reviewPaperServiceImpl.getReviewPapersAll(queryMap);
            for(int i=0,num=resultList.size();i<num;i++){
                Map<String,Object> tempMap = new HashMap<String, Object>();
                tempMap.put("revpid",resultList.get(i).getRevpid());
                tempMap.put("revid",resultList.get(i).getRevid());
                tempMap.put("id",resultList.get(i).getId());
                tempMap.put("title",resultList.get(i).getTitle());
                tempMap.put("subtitle",resultList.get(i).getKeyword());
                tempMap.put("summary",resultList.get(i).getSummary());
                tempMap.put("keyword",resultList.get(i).getKeyword());
                tempMap.put("content",resultList.get(i).getContent());
                tempMap.put("reference",resultList.get(i).getReference());
                tempMap.put("direction",resultList.get(i).getDirection());
                tempMap.put("theme",resultList.get(i).getTheme());
                tempMap.put("item",resultList.get(i).getItem());
                tempMap.put("filename",resultList.get(i).getFilename());
                tempMap.put("uri",resultList.get(i).getUri());
                tempMap.put("authors",resultList.get(i).getAuthors());
                if((roles.contains("superadmin")||roles.contains("senioradmin"))){
                    List<Map<String,Object>> tempData = new ArrayList<Map<String,Object>>();
                    List<UserModel> expertList = reviewPaperExpertServiceImpl.selectByRevpid(resultList.get(i).getRevpid());
                    for(int j=0,jnum=expertList.size();j<jnum;j++) {
                        Map<String,Object> tpMap = new HashMap<String, Object>();
                        tpMap.put("exid",expertList.get(j).getId());
                        tpMap.put("username",expertList.get(j).getUsername());
                        Map<String,Object> queryParam = new HashMap<>();
                        queryParam.put("revpid",resultList.get(i).getRevpid());
                        queryParam.put("uid",expertList.get(j).getId());
                        ReviewPaperExpertBean repBean = reviewPaperExpertServiceImpl.selectByRevpidUid(queryParam);
                        tpMap.put("pexid",repBean.getPexid());
                        tpMap.put("score",repBean.getScore());
                        tpMap.put("comment",repBean.getComment());
                        tempData.add(tpMap);
                    }
                    tempMap.put("experts",tempData);
                }else{
                    Map<String,Object> tpMap = new HashMap<String, Object>();
                    tpMap.put("exid",uid);
                    tpMap.put("username",userService.selectUser(uid).getUsername());
                    Map<String,Object> queryParam = new HashMap<>();
                    queryParam.put("revpid",revpid);
                    queryParam.put("uid",uid);
                    ReviewPaperExpertBean repBean = reviewPaperExpertServiceImpl.selectByRevpidUid(queryParam);
                    tpMap.put("pexid",repBean.getPexid());
                    tpMap.put("score",repBean.getScore());
                    tpMap.put("comment",repBean.getComment());
                    tempMap.put("experts",tpMap);
                }
                listData.add(tempMap);
            }

            if(resultList.size()>0){
                this.returnData(returnDataModel,0, listData, "获取数据集合");
            }else if(resultList.size()==0){
                returnDataModel.setMessage("没有获取到相关数据集合");
            }else{
                returnDataModel.setMessage("网络繁忙请稍后再试");
            }
        }else{
            returnDataModel.setMessage("您无权限查看相关信息");
        }
        return returnDataModel;
    }
    //论文打分
    @RequestMapping(value = "score")
     public ReturnDataModel score(
            @RequestParam(value="uid",defaultValue = "") Integer uid,
            @RequestParam(value="pexid",defaultValue = "") Integer pexid,
            @RequestParam(value="comment",defaultValue = "") String comment,
            @RequestParam(value="score",defaultValue = "") Integer score
    ){

        ReturnDataModel returnDataModel = this.start();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //判断是否有权限操作本数据
        ReviewPaperExpertBean reviewPaperExpertBeanVerify = new ReviewPaperExpertBean();
        reviewPaperExpertBeanVerify.setPexid(pexid);
        reviewPaperExpertBeanVerify.setUid(uid);
        int verify = reviewPaperExpertServiceImpl.countSelective(reviewPaperExpertBeanVerify);
        List<String> roles = userService.getRoles(uid);
        if(verify>0||roles.contains("superadmin")||roles.contains("senioradmin")){
            ReviewPaperExpertBean reviewPaperExpertBean = new ReviewPaperExpertBean();
            reviewPaperExpertBean.setPexid(pexid);
            reviewPaperExpertBean.setScore(score);
            reviewPaperExpertBean.setComment(comment);
            int resultData = reviewPaperExpertServiceImpl.updateByIdSelective(reviewPaperExpertBean);
            if (resultData > 0) {
                this.returnData(returnDataModel, 0, dataMap, "提交数据成功");
            } else {
                returnDataModel.setMessage("提交数据失败");
            }
        }else{
            returnDataModel.setMessage("您无权限执行相关操作");
        }
        return returnDataModel;
    }

}
