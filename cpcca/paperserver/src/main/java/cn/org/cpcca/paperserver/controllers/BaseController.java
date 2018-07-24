package cn.org.cpcca.paperserver.controllers;

import cn.org.cpcca.paperserver.mappers.FilesMapper;
import cn.org.cpcca.paperserver.models.FilesModel;
import cn.org.cpcca.paperserver.models.ReturnDataModel;
import cn.org.cpcca.paperserver.servcie.FilesService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BaseController {
    public ReturnDataModel start(){
        ReturnDataModel returnDataModel = new ReturnDataModel("操作失败");
        return returnDataModel;
    }
    //组装返回数据
    public void returnData(ReturnDataModel returnDataModel,int code,Object data,String message){
        returnDataModel.setCode(code);
        returnDataModel.setData(data);
        returnDataModel.setMessage(message);
    }
    public void returnData(ReturnDataModel returnDataModel,int code,Object data,String message,String redirect){
        returnDataModel.setCode(code);
        returnDataModel.setData(data);
        returnDataModel.setMessage(message);
        returnDataModel.setRedirect(redirect);
    }
    //反射设置对象属性值
    public void changeObjeceProperty(Class tempClass,Object object,Map<String,Object> tempMap){
        Field[] tempFields  = tempClass.getDeclaredFields();
        for (Field tempField:tempFields) {
            for (String key:tempMap.keySet()) {
                if(tempField.getName().equals(key)){
                    tempField.setAccessible(true);
                    try {
                        tempField.set(object,tempMap.get(key));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void fileUpload(
            ReturnDataModel returnDataModel,
            MultipartFile file,
            FilesService filesService,
            String flag){
        this.fileUpload(returnDataModel,file,filesService,flag,"");
    }
    public void fileUpload(
            ReturnDataModel returnDataModel,
            MultipartFile file,
            FilesService filesService,
            String flag,
            String fileUrl){
        Map<String,Object> dataMap = new HashMap<String, Object>();
        String originalFilename = file.getOriginalFilename();
        System.out.print(originalFilename);
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        //String filePath = "D:\\work\\project\\temp\\java\\cpcca\\";
        String basePath = "/wnmp/cpcca/docker/cpcca/uploads/";
        //String basePath = "D:/work/project/temp/java/cpcca/";

        String filePath = basePath+"other/";
        if(flag.equals("paperword")){
            filePath = basePath+"papers/word/";
        }else if(flag.equals("paperpic")){
            filePath = basePath+"/papers/pic/";
        }
        if(flag.equals("editsword")){
            filePath = basePath+"/edits/word/";
        }else if(flag.equals("editspic")){
            filePath = basePath+"/edits/pic/";
        }
        String fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        //Path path = Paths.get(filePath + fileName);
        //如果没有files文件夹，则创建
        //if (!Files.isWritable(path)) {
        //    try {
        //        Files.createDirectories(Paths.get(filePath));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            byte[] bytes = file.getBytes();
            file.transferTo(dest);
            //Files.write(path, bytes);
            returnDataModel.setCode(0);
            FilesModel filesModel = new FilesModel();
            filesModel.setUri(fileName);
            filesModel.setName(originalFilename);
            int fid = filesService.addFile(filesModel);
            dataMap.put("fileuri",fileUrl+fileName);
            dataMap.put("filename",originalFilename);
            dataMap.put("fid",String.valueOf(fid));
            returnDataModel.setData(dataMap);
            returnDataModel.setMessage("上传文件成功");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //判断是否为空
    public boolean isEmpty(Object obj){
        if(obj == null){
            return true;
        }else if((obj instanceof List)){
            return ((List) obj).size() == 0;
        }else if ((obj instanceof Map)){
            return ((Map) obj).size() == 0;
        }else if ((obj instanceof String)){
            return ((String) obj).trim().equals("");
        }
        return false;
    }
}
