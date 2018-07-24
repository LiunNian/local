package com.registered.member.service.impl;


import com.paper.common.model.Result;
import com.registered.member.dao.MemberPaymentMapper;
import com.registered.member.dao.MemberRegisteredMapper;
import com.registered.member.model.Invoice;
import com.registered.member.model.MemberInformation;
import com.registered.member.model.PayInfo;
import com.registered.member.model.Paystandard;
import com.registered.member.service.MemberPaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("memberPaymentService")
@Transactional
public class MemberPaymentServiceImpl implements MemberPaymentService {

    SimpleDateFormat format=new SimpleDateFormat("yyyy");
    @Resource
    MemberPaymentMapper  memberPaymentMapper;

    @Resource
    MemberRegisteredMapper memberRegisteredMapper;

    /*
    通过用户id获取到缴费信息，
    首先判断用户是不是会员
     */
    @Override
    public Object PaymentInfo(String id) {
        /*
        查看会员信息是否存在
         */
        MemberInformation obj= memberRegisteredMapper.memberinfo(id);
        if(obj==null){
            return new Result("该用户还不是会员，请先注册会员", 0, false);
        }
        else{
            /*
            根据会员信息获取近几年的缴费信息。从会员缴费表中查询，是否存在相应的会员编码，没有的话直接交当年的，
            如果是存在会员编码也只判断当前年是否缴费，缴费是否成功（1，成功，0，失败）
             */
            Date date=new Date();
            format.format(date);
            Map map=new HashMap();
            if(obj.getCode()==null){//是会员但是没有交过费，还不是正式会员，返回信息让用户缴纳当前年的费用
                map.put("time",format.format(date));
                map.put("memberId",obj.getId());
                return new Result("该用户还不是正式会员，请先缴纳费用",map , 0, false);
            }
            else{//存在会员编码，查看当前年的缴费信息是否存在缴费表中，会员编码-当前年份，获取所有的缴费信息
                List<PayInfo> list=memberPaymentMapper.getInfo(obj.getCode());

                /*
                循环遍历取出信息
                 */
                String time= format.format(date);
                int count=0;//计数，看看返回的信息中有没有当前年，有的话赋值1，没有的话，添加当前年的信息，返回
                for(int i=0;i< list.size();i++){
                    PayInfo info=list.get(i);
                    if(time.equals(info.getTime())){//存在今年的缴费，判断缴费的状态，1，完成
                        count=1;
                        if(info.getStatus()==1){//缴费完成
                            map.put(time,"缴费成功");
                        }
                        else if(info.getStatus()==2){//缴费驳回
                            map.put(time,"缴费驳回");
                        }
                        else{
                            map.put(time,"前往缴费");//缴费没有完成
                        }

                    }
                    else{//添加历史缴费信息
                        if(info.getStatus()==1){
                            map.put(info.getTime(),"缴费成功");
                        }
                        else{
                            map.put(info.getTime(),"没有缴费");
                        }
                    }
                }
                if(count==0){
                    map.put(time,"前往缴费");//缴费没有完成
                }
                map.put("memberId",obj.getId());
                return new Result("", map,1, true);
            }
        }
    }
    @Override
    public Object paystandard() {
        Map map=new HashMap();
       List<Paystandard> list= memberPaymentMapper.paystandard();
        return new Result("缴费标准", list,1, true);
    }

    /*
    电子发票
     */
    @Override
    public Object eInvoice(Invoice obj, String standard, String id,String time) {
        /*
        根据id获取会员信息，会员编码，会员单位
         */
        MemberInformation info=memberRegisteredMapper.selectInfo(Integer.valueOf(id));
        Date date=new Date();
        /*
        根据标准获取缴费金额
         */
        Paystandard st= memberPaymentMapper.getMoney(Integer.valueOf(standard));
        if(info.getCode()==null){//新会员第一次缴费，等待管理员确认信息后生成会员编码
               /*
        新建缴费信息
         */
            PayInfo pay=new PayInfo();
            pay.setPayamount(st.getPay());
            pay.setTime(time);
            pay.setPaytime(date);
            pay.setMembercode(null);
            pay.setUnitname(info.getUnitName());
            pay.setStatus(1);
            pay.setBatch(Integer.valueOf(time)-2014);
            pay.setType(Integer.valueOf(standard));
            /*
           保存缴费信息
            */
            int re=memberPaymentMapper.savePay(pay);
            if(re==1){
                obj.setPayId(pay.getId());
                /*
                保存发票信息
                 */
                memberPaymentMapper.saveInvoice(obj);
                return new Result("工作人员正在审核,请耐心等待", 1, true);
            }else{
                return new Result("缴费失败", 0, false);
            }

        }
        else{//老会员存在会员编码，并且查看是否存在当年没有完成的缴费信息
           PayInfo data= memberPaymentMapper.oldvalue(info.getCode(),time);
           if(data==null){//不存在
               PayInfo pay=new PayInfo();
               pay.setPayamount(st.getPay());
               pay.setTime(time);
               pay.setPayname(info.getUnitName());
               pay.setPaytime(date);
               pay.setMembercode(info.getCode());
               pay.setUnitname(info.getUnitName());
               pay.setStatus(1);
               pay.setBatch(Integer.valueOf(time)-2014);
               pay.setType(Integer.valueOf(standard));
            /*
           保存缴费信息
            */
               int re=memberPaymentMapper.savePay(pay);
               if(re==1){
                   obj.setPayId(pay.getId());
                /*
                保存发票信息
                 */
                   memberPaymentMapper.saveInvoice(obj);
                   return new Result("工作人员正在审核,请耐心等待", 1, true);
               }else{
                   return new Result("缴费失败", 0, false);
               }
           }
           else{//存在
               data.setPayamount(st.getPay());
               data.setTime(time);
               data.setPaytime(date);
               data.setStatus(1);
               data.setBatch(Integer.valueOf(time)-2014);
               data.setType(Integer.valueOf(standard));
               int ho=memberPaymentMapper.updateValue(data);
               if(ho==1){
                   obj.setPayId(data.getId());
                /*
                保存发票信息
                 */
                   memberPaymentMapper.saveInvoice(obj);
                   return new Result("工作人员正在审核,请耐心等待", 1, true);
               }
               else{
                   return new Result("缴费失败", 0, false);
               }
           }
        }
    }

}

