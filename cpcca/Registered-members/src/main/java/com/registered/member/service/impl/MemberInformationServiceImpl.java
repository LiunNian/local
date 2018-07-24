package com.registered.member.service.impl;

import com.paper.common.model.Result;
import com.registered.member.dao.MemberInformationMapper;
import com.registered.member.dao.MemberRegisteredMapper;
import com.registered.member.model.MemberInformation;
import com.registered.member.redis.service.impl.SensitiveServiceImpl;
import com.registered.member.service.MemberInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("memberInformation")
@Transactional
public class MemberInformationServiceImpl implements MemberInformationService{
    @Resource
    MemberInformationMapper memberInformationMapper;

    @Resource
    MemberRegisteredMapper memberRegisteredMapper;


    @Autowired
    private SensitiveServiceImpl service;

    @Override
    public Object Cheakmember(String id) {

        Map map= memberInformationMapper.Cheakmember(id);
        Map res=new HashMap();
       if(map.size()>0&&map!=null){
           res.put("memberId",map.get("id"));
           /*
           当前用户时会员，判断认证流程是否已经完成
            */
           if(map.containsKey("status")){

               if("initiate".equals(map.get("status"))){
                   res.put("单位名称",map.get("unitName"));
                   res.put("法人名称",map.get("legalName"));
                   res.put("状态","initiate");
                   if(map.containsKey("attachmentAddress")){
                       res.put("attachmentAddress",map.containsKey("attachmentAddress"));
                   }
                   else{
                       res.put("attachmentAddress",null);
                   }
                   return new Result("完善单位信息", res,1, true);
               }
               else if("in_process".equals(map.get("status"))){
                   if(map.containsKey("contactName")){
                       res.put("联络人",map.get("contactName"));
                   }
                   else{
                       res.put("联络人","");
                   }
                   if(map.containsKey("contactPhone")){
                       res.put("联络人电话",map.get("contactPhone"));
                   }
                   else{
                       res.put("联络人电话","");
                   }
                   res.put("状态","in_process");
                   return new Result("认证联络人信息", res,1, true);
               }
               else if("completed".equals(map.get("status"))){
                   res.put("状态","completed");
                   res.put("用户信息",map);
                   return new Result("认证成功", res,1, true);
               }
               else{
                   res.put("userid",id);
                   return new Result("没有流程认证状态",res, 1, true);
               }
           }
           else{
               res.put("userid",id);
               return new Result("没有流程认证状态",res, 1, true);
           }
       }
       else{
           /*
           当前用户还不是会员
            */
           res.put("userid",id);
           return new Result("当前用户还不是会员，请注册", res,1, true);
       }
    }

    @Override
    public Object initiate(String attachmentCode,String memberId) {
        Integer id=Integer.valueOf(memberId);
        MemberInformation res=memberRegisteredMapper.selectInfo(id);
        res.setStatus("in_process");
        res.setAttachmentCode(attachmentCode);
        Integer rid=memberRegisteredMapper.updateMember(res);
        return new Result("进入认证联络人信息", res,1, true);

    }

    @Override
    public Object inprocess(String contactName, String contactPhone,String memberId,String Verify) {
        int count =memberRegisteredMapper.selectCountByPhone(contactPhone);
        if(count>0){
            return new Result("手机号码已存在",memberId, -1, false);
        }
        else if(!Verify.equals(service.get(contactPhone))){
            return new Result("验证码错误", memberId,-1, false);
        }
        else{
            Integer id=Integer.valueOf(memberId);
            MemberInformation res=memberRegisteredMapper.selectInfo(id);
            res.setStatus("completed");
            res.setContactName(contactName);
            res.setContactPhone(contactPhone);
            Integer rid=memberRegisteredMapper.updateMember(res);
            return new Result("认证完成展示信息", res,1, true);
        }

    }

}