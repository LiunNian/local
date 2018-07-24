package cn.org.cpcca.paperserver.controllers;

import cn.org.cpcca.paperserver.excel.ExcelUtils;
import cn.org.cpcca.paperserver.models.ReturnDataModel;
import cn.org.cpcca.paperserver.models.ReviewPaperBean;
import cn.org.cpcca.paperserver.servcie.ReviewPaperService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.NestingKind;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 数据报送上传文件
 * @author Guardpure
 */
@RestController
@RequestMapping("/fileupload")
public class FileUploadController extends BaseController {
    @Value("${web.upload}")
    private String UploadPath;
    @Autowired
    private ExcelUtils excelUtil;
    @Autowired
    private ReviewPaperService reviewPaperServiceImpl;
    /*****************************************************************************
     * 普通上传excel文件 及文件归档
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/excelupload")
    public ReturnDataModel fileUpload(
            HttpServletRequest request,
            @RequestParam(value = "file") MultipartFile file
    ) throws ServletException, IOException {
        ReturnDataModel returnDataModel= this.start();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        //System.out.println(JSON.toJSONString(file, true));
        // 判断文件是否为空
        if (!file.isEmpty()) {
            //接收json数据
            String postData = request.getParameter("revid");
            int revid = 0;
            if(postData != null){
                revid = Integer.valueOf(postData);
            }
            //将json字符串转为json对象
            //JSONObject jsonObj = JSONObject.parseObject(postData);
            String fileName = file.getOriginalFilename();
            // 文件的后缀
            String suffix = "";
            if (fileName.indexOf(".") >= 0) {
                int indexdot = fileName.lastIndexOf(".");
                // 得到文件后缀名
                suffix = fileName.substring(indexdot + 1);
            }
            int totalCount = 0;
            int sucCount = 0;
            System.out.println(suffix);
            if(suffix.equals("xls")||suffix.equals("xlsx")){
               if(revid != 0) {
                   String taskid = String.valueOf(revid);
                   Map<String,String> tempMap = new LinkedMap<String,String>();
                   tempMap = this.archive(file);

                   System.out.println(taskid);
                   System.out.println(JSON.toJSONString(tempMap));
                   //操作数据库 将文件归档数据存储再数据库
                   //******
                   String tempFilePath = tempMap.get("filePath")+tempMap.get("fileName");
                   List<List<String>> listData = null;
                   if(suffix.equals("xls")){
                       listData = excelUtil.readXls(tempFilePath);
                   }else if(suffix.equals("xlsx")){
                       listData = excelUtil.readXlsx(tempFilePath);
                   }
                   System.out.println(JSON.toJSONString(listData));
                   totalCount = listData.size();
                   List<Integer> failList = new ArrayList<Integer>();
                   for (List<String> strList:listData) {
                       ReviewPaperBean reviewPaperBean = new ReviewPaperBean();
                       reviewPaperBean.setRevpid(Integer.valueOf(strList.get(0)));
                       //System.out.println(strList.size());
                       if(strList.size()>8) {
                           System.out.println(strList.get(8));
                           reviewPaperBean.setResult(strList.get(8));
                       }else{
                           reviewPaperBean.setResult("");
                       }
                       if(strList.size()>9) {
                           reviewPaperBean.setQuoteword(strList.get(9));
                       }else{
                           reviewPaperBean.setQuoteword("");
                       }
                       if(strList.size()>10) {
                           reviewPaperBean.setRepeatword(strList.get(10));
                       }else{
                           reviewPaperBean.setRepeatword("");
                       }
                       if(strList.size()>11) {
                           reviewPaperBean.setSelfword(strList.get(11));
                       }else{
                           reviewPaperBean.setSelfword("");
                       }
                       if(strList.size()>12) {
                           reviewPaperBean.setProgress(strList.get(12));
                       }else{
                           reviewPaperBean.setProgress("");
                       }
                       int resultInt = reviewPaperServiceImpl.updateByIdSelective(reviewPaperBean);
                       System.out.println("update-->"+String.valueOf(resultInt));
                       if(resultInt>0){
                           sucCount++;
                       }else{
                           failList.add(Integer.valueOf(strList.get(0)));
                       }
                   }
                   //System.out.println(JSON.toJSONString(failList));
               }else{
                   returnDataModel.setMessage("数据异常，未找到相关任务");
               }
            }else{
                returnDataModel.setMessage("上传文件类型有误，请确认文件无误。");
            }
            int failCount = totalCount-sucCount;
            dataMap.put("totalCount",totalCount);
            dataMap.put("sucCount",sucCount);
            dataMap.put("failCount",failCount);
            String message = "";
            if(failCount>0){
                message = "导入已完成，有"+String.valueOf(failCount)+"未导入成功";
            }else{
                message = "导入已完成";
            }
            this.returnData(returnDataModel,0, dataMap,message);
        }
        return returnDataModel;
    }

    /**
     * 文件归档
     */
    public Map<String,String> archive(MultipartFile file){
        return this.archive(file,"");
    }
    public Map<String,String> archive(MultipartFile file, String path){
        Map<String,String> tempMap = new LinkedMap<String, String>();
        //原文件名
        String originalFilename = file.getOriginalFilename();
        tempMap.put("originalname",originalFilename);
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        String filePath = UploadPath;
        if(!path.equals("")){
            filePath = UploadPath+path+"/";
        }
        tempMap.put("filePath",filePath);
        //服务器存储文件名
        String fileName = UUID.randomUUID() + suffixName;
        tempMap.put("fileName",fileName);
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        FileOutputStream out = null;
        try {
//            byte[] bytes = file.getBytes();
//            file.transferTo(dest);
            out = new FileOutputStream(dest);
            //将字符串转化为字节
            byte[] bytes = file.getBytes();
            out.write(bytes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempMap;
    }
}
