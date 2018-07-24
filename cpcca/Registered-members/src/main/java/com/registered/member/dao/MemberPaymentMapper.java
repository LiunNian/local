package com.registered.member.dao;

import com.registered.member.model.Invoice;
import com.registered.member.model.PayInfo;
import com.registered.member.model.Paystandard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberPaymentMapper {

    List<PayInfo> getInfo(@Param("membercode")String membercode);

    @Select("select * from member_type")
    List<Paystandard> paystandard();

    @Select("select * from member_type where id=#{standard}")
    Paystandard getMoney(@Param("standard") int standard);

    @Select("select * from member_pay where membercode=#{code} and time=#{time} " )
    PayInfo  oldvalue(@Param("code") String code,@Param("time") String time);

    @Insert("insert into member_pay (time,paytime,payname,payamount,membercode,unitname,batch,status,type)values(" +
            "#{obj.time}," +
            "#{obj.paytime}," +
            "#{obj.payname},"+
            "#{obj.payamount},"+
            "#{obj.membercode},"+
            "#{obj.unitname},"+
            "#{obj.batch},"+
            "#{obj.status},"+
            "#{obj.type})")
    @Options(useGeneratedKeys = true,keyProperty = "obj.id")
    int savePay(@Param("obj") PayInfo obj);

    @Update("update member_pay set time=#{obj.time}," +
            "paytime=#{obj.paytime}," +
            "payname=#{obj.payname},payamount=#{obj.payamount}," +
            "membercode=#{obj.membercode}," +
            "unitname=#{obj.unitname}, " +
            "batch=#{obj.batch}, " +
            "status=#{obj.status}, " +
            "type=#{obj.type} where id=#{obj.id}")
    int updateValue(@Param("obj") PayInfo obj);

    @Insert("insert into Invoice_info (invoiceName,code,invoiceHead,email,payWay,payId)values(" +
            "#{obj.invoiceName}," +
            "#{obj.code}," +
            "#{obj.invoiceHead},"+
            "#{obj.email},"+
            "#{obj.payWay},"+
            "#{obj.payId})")
    @Options(useGeneratedKeys = true,keyProperty = "obj.id")
    int saveInvoice(@Param("obj")Invoice obj);
}
