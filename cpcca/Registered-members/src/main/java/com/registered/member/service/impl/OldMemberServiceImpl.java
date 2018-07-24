package com.registered.member.service.impl;


import com.paper.common.model.Result;
import com.registered.member.dao.MemberInformationMapper;
import com.registered.member.dao.MemberRegisteredMapper;
import com.registered.member.dao.OldMemberValidationMapper;
import com.registered.member.dao.RegisteredUserInformationMapper;
import com.registered.member.model.MemberInformation;
import com.registered.member.model.OldMenberValidation;
import com.registered.member.model.UserInformation;
import com.registered.member.model.Users;
import com.registered.member.service.MemberRegisteredService;
import com.registered.member.service.OldMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("oldMemberService")
@Transactional
public class OldMemberServiceImpl implements OldMemberService {
    @Resource
    OldMemberValidationMapper oldMemberMapper;

    @Resource
    MemberInformationMapper memberInformationMapper;

    @Resource
    RegisteredUserInformationMapper registeredUserInformationMapper;

    @Resource
    MemberRegisteredMapper memberRegisteredMapper;

    @Autowired
    MemberRegisteredService memberRegistered;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟*/

    /*
   验证老会员是否存在
    */
    @Override
    public Object checkOldMember(OldMenberValidation obj) {
        //判断该用户是否已经完成了转新
        Map map = new HashMap();
        map.put("loginname", obj.getUnitName());
        map.put("password", obj.getLoginPassword());
        String newinfo = memberInformationMapper.findMess(obj.getUnitName(), obj.getLegalName(), obj.getContactName(),obj.getContactPhone());
        if (newinfo== null) {
            String lo = oldMemberMapper.checkOldMember(obj.getUnitName(), obj.getLegalName(), obj.getContactName(),obj.getContactPhone());
            String ko = oldMemberMapper.checkOldMemberUser(obj.getLoginName(), obj.getLoginPassword());


            if ("empty".equals(lo) || "empty".equals(ko) || lo == null || ko == null) {

                return new Result("认证失败",  0, false);
            } else {
            /*
            获取老会员会员信息，注册到新系统登录用户表
             */
                Map info = memberInformationMapper.findOldMember(lo);
            /*
            创建user信息，将用户名，密码注册到登录表中
             */
                Users user = new Users();
                user.setUsername(obj.getUnitName());
                user.setPassword_salt(obj.getLoginPassword());
                Date date = new Date();
                user.setCtime(date);
                user.setPassword(DigestUtils.md5DigestAsHex(obj.getLoginPassword().getBytes()));
                Integer res = registeredUserInformationMapper.registeredUser(user);
           /*
           将老会员信息注册到user信息表中
            */
                UserInformation Uinfo = new UserInformation();
                Uinfo.setUid(user.getId());
                Uinfo.setUnitname(obj.getUnitName());
                if (info.containsKey("所在地区")) {
                    Uinfo.setRegion(String.valueOf(info.get("所在地区")));
                } else {
                    Uinfo.setRegion(null);
                }
                if (info.containsKey("详细地址")) {
                    Uinfo.setAddress(String.valueOf(info.get("详细地址")));
                } else {
                    Uinfo.setAddress(null);
                }
                if (info.containsKey("联络人姓名")) {
                    Uinfo.setLinkman(String.valueOf(info.get("联络人姓名")));
                } else {
                    Uinfo.setLinkman(null);
                }
                if (info.containsKey("联络人手机")) {
                    Uinfo.setPhonenum(String.valueOf(info.get("联络人手机")));
                } else {
                    Uinfo.setPhonenum(null);
                }
                if (info.containsKey("成立日期")) {
                    try {
                        Uinfo.setCtime(sdf.parse(String.valueOf(info.get("成立日期"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    Uinfo.setCtime(null);
                }
                Uinfo.setState(1);

            /*
            持久化用户信息数据到数据库
             */
                Integer rest = registeredUserInformationMapper.insertUserinfo(Uinfo);

            /*
            持久化会员信息到新系统的会员信息表中
             */
                MemberInformation member = new MemberInformation();

                if (info.containsKey("会员编码")) {
                    member.setCode(String.valueOf(info.get("会员编码")));
                } else {
                    member.setCode(null);
                }
                if (info.containsKey("法人代表姓名")) {
                    member.setLegalName(String.valueOf(info.get("法人代表姓名")));
                } else {
                    member.setLegalName(null);
                }
            /*
            法人性别
             */
                member.setLegalSex(null);
            /*
            法人职务
             */
                member.setLegalPosition(null);
            /*
            法人民族
             */
                member.setLegalNational(null);
            /*
            法人手机号码
             */
                if (info.containsKey("法人电话")) {
                    member.setLegalPhone(String.valueOf(info.get("法人电话")));
                } else {
                    member.setLegalPhone(null);
                }
                if (info.containsKey("联络人姓名")) {
                    member.setContactName(String.valueOf(info.get("联络人姓名")));
                } else {
                    member.setContactName(null);
                }

                if (info.containsKey("联络人性别")) {
                    member.setContactSex(String.valueOf(info.get("联络人性别")));
                } else {
                    member.setContactSex(null);
                }

                if (info.containsKey("联络人职务")) {
                    member.setContactPostion(String.valueOf(info.get("联络人职务")));
                } else {
                    member.setContactPostion(null);
                }
           /*
            联络人民族
            */
                member.setContactNational(null);

                if (info.containsKey("联络人手机")) {
                    member.setContactPhone(String.valueOf(info.get("联络人手机")));
                } else {
                    member.setContactPhone(null);
                }
            /*
            联络人有限
             */
                member.setContactEmall(null);

                if (info.containsKey("联络人业务QQ")) {
                    member.setContactQQ(String.valueOf(info.get("联络人业务QQ")));
                } else {
                    member.setContactQQ(null);
                }
            /*
            统一信用代码
             */
                member.setAttachmentCode(null);
            /*
            附件地址
             */
                member.setAttachmentAddress(null);

                if (info.containsKey("单位名称")) {
                    member.setUnitName(String.valueOf(info.get("单位名称")));
                } else {
                    member.setUnitName(null);
                }
                if (info.containsKey(" 成立日期")) {
                    try {
                        member.setUnitCreateTime(sdf.parse(String.valueOf(info.get("成立日期"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    member.setUnitCreateTime(null);
                }

                if (info.containsKey("员工人数")) {
                    member.setUnitNumber(Integer.parseInt(String.valueOf(info.get("员工人数"))));
                } else {
                    member.setUnitNumber(null);
                }

                if (info.containsKey("所在地区")) {
                    member.setUnitRegion(String.valueOf(info.get("所在地区")));
                } else {
                    member.setUnitRegion(null);
                }
                if (info.containsKey("详细地址")) {
                    member.setUintAddress(String.valueOf(info.get("详细地址")));
                } else {
                    member.setUintAddress(null);
                }

                if (info.containsKey("单位网址")) {
                    member.setUnitUrl(String.valueOf(info.get("单位网址")));
                } else {
                    member.setUnitUrl(null);
                }
                if (info.containsKey("邮编")) {
                    member.setUnitMall(String.valueOf(info.get("邮编")));
                } else {
                    member.setUnitMall(null);
                }
                if (info.containsKey("传真")) {
                    member.setUnitFax(String.valueOf(info.get("传真")));
                } else {
                    member.setUnitFax(null);
                }
            /*
            单位介绍
             */
                member.setUnitDescribe(null);

         /*
         会员类型：根据获取的单位名称，查询到此单位名称下的会员类型，获取到会员类型查询会员类型表的id，进行赋值
          */
                String TypeName = memberRegistered.getMemberType(String.valueOf(info.get("单位名称")));
                if (TypeName != null) {
                    int typeId = memberRegistered.memberType(TypeName);
                    member.setUnitType(typeId);
                }
                if (info.containsKey("理事候选人")) {
                    member.setUnitCandidate(String.valueOf(info.get("理事候选人")));
                } else {
                    member.setUnitCandidate(null);
                }
                if (info.containsKey("所在单位")) {
                    member.setUnitDepartment(String.valueOf(info.get("所在单位")));
                } else {
                    member.setUnitDepartment(null);
                }
                if (info.containsKey("职务")) {
                    member.setUnitPosition(String.valueOf(info.get("职务")));
                } else {
                    member.setUnitPosition(null);
                }
                if (info.containsKey("手机号码")) {
                    member.setUnitPhone(String.valueOf(info.get("手机号码")));
                } else {
                    member.setUnitPhone(null);
                }
            /*
            认证状态
             */
                member.setStatus("initiate");

            /*
            关联user表
             */
                member.setUid(String.valueOf(user.getId()));
            /*
            老会员系统没有会员申请时间
             */
                member.setCtime(date);
            /*
            老会员注册迁移到新系统当中
             */

                memberRegistered.oldmemberRegistered(member);
                return new Result("认证成功", map, 1, true);
            }
        } else {
            return new Result("已经认证过",map,0, false);
        }
    }
}