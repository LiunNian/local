package cn.org.cpcca.paperserver.controllers;

import cn.org.cpcca.paperserver.excel.ExcelUtils;
import cn.org.cpcca.paperserver.models.ReviewProgressModel;
import cn.org.cpcca.paperserver.models.ReviewResultModel;
import cn.org.cpcca.paperserver.servcie.PaperServiceInterface;
import cn.org.cpcca.paperserver.servcie.ReviewPaperExpertService;
import cn.org.cpcca.paperserver.servcie.ReviewPaperService;
import cn.org.cpcca.paperserver.zip.ZipCompressor;
import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "download",method = RequestMethod.GET)
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class DownloadController {
    @Value("${web.upload}")
    private String filePath;
    @Autowired
    private ExcelUtils excelUtil;

    @Autowired
    private ReviewPaperExpertService reviewPaperExpertServiceImpl;
    @Autowired
    private PaperServiceInterface paperService;

    @Autowired
    private ReviewPaperService reviewPaperServiceImpl;
    //@Value("${web.review.progress}")
    private String[] progressTop;
   // @Value("${web.review.result}")
    private String[] resultTop;
    @RequestMapping("exportexcel")
    public ResponseEntity<byte[]> exportExcel(
            @RequestParam(value="tag",defaultValue = "") String tag,
            @RequestParam(value="revid",defaultValue = "") int revid
            //HttpServletResponse response
    ) {
        ResponseEntity<byte[]> entity = null;
        String downloadName = "";
        List<List<String>> temp  = new ArrayList<List<String>>();
        if(tag.equals("result")){
            if(resultTop==null){
                //temp.add(this.getEncodeList(resultTop));
                temp.add(Arrays.asList("序号","论文活动项目","论文主题","论文方向","评审名称","论文标题","分数","评语","专家","单位名称","作者"));
            }
            List<ReviewResultModel>  reviewResultModels = reviewPaperExpertServiceImpl.reviewResult(revid);
            for(int i=0,num=reviewResultModels.size();i<num;i++){
                if(i==0) {
                    downloadName = reviewResultModels.get(i).getItem() + "_" + reviewResultModels.get(i).getReitem() + "_打分";
                }
                List<String> tempList = new ArrayList<String>();
                //标识序号,论文活动项目,论文主题,论文方向,评审名称,论文标题,分数,评语,专家
                tempList.add(reviewResultModels.get(i).getPexid().toString());
                tempList.add(reviewResultModels.get(i).getItem());
                tempList.add(reviewResultModels.get(i).getTheme());
                tempList.add(reviewResultModels.get(i).getDirection());
                tempList.add(reviewResultModels.get(i).getReitem());
                tempList.add(reviewResultModels.get(i).getTitle());
                tempList.add(reviewResultModels.get(i).getScore());
                tempList.add(reviewResultModels.get(i).getComment());
                tempList.add(reviewResultModels.get(i).getUsername());
                tempList.add(reviewResultModels.get(i).getCompany());
                tempList.add(reviewResultModels.get(i).getAuthors());
                temp.add(tempList);
            }
        }else if(tag.equals("progress")){
            if(resultTop==null){
                //temp.add(this.getEncodeList(progressTop));
                //temp.add(Arrays.asList("序号","论文活动项目","论文主题","论文方向","评审名称","论文标题","评审进程","评审结果","作者"));
                temp.add(Arrays.asList("序号","标题","原文件名","方向","主题","项目","作者","单位","检测结果","重合字数","去除引用","去除本人","是否通过"));
            }
            List<ReviewProgressModel> reviewProgressModels = reviewPaperServiceImpl.reviewProgress(revid);
            for(int i=0,num=reviewProgressModels.size();i<num;i++){
                if(i==0){
                    downloadName = reviewProgressModels.get(i).getItem()+"_"+reviewProgressModels.get(i).getReitem()+"_结果";
                }
                List<String> tempList = new ArrayList<String>();
                tempList.add(reviewProgressModels.get(i).getRevpid().toString());
                tempList.add(reviewProgressModels.get(i).getTitle());
                tempList.add(reviewProgressModels.get(i).getFilename());
                tempList.add(reviewProgressModels.get(i).getDirection());
                tempList.add(reviewProgressModels.get(i).getTheme());
                tempList.add(reviewProgressModels.get(i).getItem());
                tempList.add(reviewProgressModels.get(i).getAuthors());
                tempList.add(reviewProgressModels.get(i).getUnitname());
                tempList.add(reviewProgressModels.get(i).getResult());
                tempList.add(reviewProgressModels.get(i).getRepeatword());
                tempList.add(reviewProgressModels.get(i).getQuoteword());
                tempList.add(reviewProgressModels.get(i).getSelfword());
                tempList.add(reviewProgressModels.get(i).getProgress());
                temp.add(tempList);
            }
        }
        System.out.println(JSON.toJSONString(temp));
        XSSFWorkbook wb = excelUtil.createXlsxReWorkbook(temp);
        try {
            downloadName =  new String(downloadName.getBytes(),"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
        }
        //输出Excel文件
//        OutputStream output= null;
//        try {
//            output = response.getOutputStream();
//            response.reset();
//            //System.out.println(downloadName);
//            response.setHeader("Content-disposition", "attachment; filename="+downloadName+".xls");
//            response.setContentType("application/msexcel");
//            wb.write(output);
//            output.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type","application/msexcel");
            headers.add("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
            HttpStatus status = HttpStatus.OK;
            if(downloadName.equals("")){
                try {
                    downloadName =  new String("空表".getBytes(),"iso-8859-1");
                } catch (UnsupportedEncodingException e) {
                }
            }
            headers.add("Content-Disposition", "attachment;filename="+downloadName+".xlsx");
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wb.write(outByteStream);
            entity = new ResponseEntity<byte[]>(outByteStream.toByteArray(), headers, status);
//            System.out.println(JSON.toJSONString(entity));
//            System.out.println(revid);
//            System.out.println(tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
            return entity;
    }
    @RequestMapping("exportpapers")
    public ResponseEntity<byte[]> exportPapers(
            @RequestParam(value="ids",defaultValue = "") Integer[] ids
    ){

        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/octet-stream");
        headers.add("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
        HttpStatus status = HttpStatus.OK;
        String downloadName = UUID.randomUUID()+".zip";
        String pathName = filePath+ downloadName;
        headers.add("Content-Disposition", "attachment;filename="+downloadName);
        ZipCompressor zc = new ZipCompressor(pathName);
        //System.out.println(JSON.toJSONString(ids));
        List<String> paperList = paperService.selectPapersFile(Arrays.asList(ids));
        if(paperList.size()>0){
            String[] files = paperList.toArray(new String[paperList.size()]);
            for (int i=0,num=files.length;i<num;i++){
                files[i] = filePath+"papers/word/"+files[i];
            }
            System.out.println(JSON.toJSONString(files));
            zc.compress(files);
            File dist = new File(pathName);
            if(!dist.exists()){
                return entity;
            }
            InputStream in = null;
            try {
                in = new FileInputStream(pathName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] data = new byte[0];
            try {
                data = new byte[in.available()];
                in.read(data);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            entity = new ResponseEntity<byte[]>(data, headers, status);
        }
        //System.out.println(JSON.toJSONString(entity));
        return entity;
    }

    private byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
    List<String> getEncodeList(String[] excelTops){
        List<String> tempList = new ArrayList<String>();
        List<String> tempTops = Arrays.asList(excelTops);
        for(int i=0,num=tempTops.size();i<num;i++) {
            try {
                tempList.add(new String(tempTops.get(i).getBytes(),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        System.out.println(JSON.toJSONString(tempList));
        return tempList;
    }
}
