package com.registered.member.controller;


import com.paper.common.controller.BaseController;
import com.paper.common.model.Result;
import com.registered.member.dao.MemberRegisteredMapper;
import com.registered.member.model.MemberInformation;
import com.registered.member.service.MemberRegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/member")
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class MemberRegisteredController extends BaseController {

    @Autowired
    private MemberRegisteredService memberRegistered;
    @Autowired
    private MemberRegisteredMapper memberRegisteredMapper;
    /*
    会员注册
     */
    @PostMapping("/registered")
    @ResponseBody
    public Object registered(@RequestBody MemberInformation obj){
        return memberRegistered.memberRegistered(obj);
    }
    /**
     * 获取验证码
     * @param phonenum
     * @return
     */
    @PostMapping("/verification")
    @ResponseBody
    public Object getVerifyCode(@RequestParam("phonenum") String phonenum){
        return memberRegistered.getVerifyCode(phonenum);
    }

    /**
     * 会员更新
     */
    @PostMapping("/update")
    @ResponseBody
    public Object update(@RequestBody MemberInformation obj){
        return memberRegistered.update(obj);
    }

    /*
    上传证件
     */
    @RequestMapping(value="/upload")
    @CrossOrigin("*")
    @ResponseBody
    public Object uploadImg( @RequestParam("file") MultipartFile file,@RequestParam("id") String id) {
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if(".png".equals(suffixName)||".jpg".equals(suffixName)){
          Object obj=memberRegistered.fileUpload(file,"member");
            Result ko=(Result) obj;
           if("文件上传成功".equals(ko.getMsg())){
             MemberInformation info=memberRegisteredMapper.memberinfo(id);
               Map map=(Map) ko.getData();
             info.setAttachmentAddress(String.valueOf(map.get("fileName")));
               memberRegisteredMapper.updateMember(info);
           }
            return obj;
        }else{
            return new Result("文件类型错误", 0, false);
        }
    }

}
