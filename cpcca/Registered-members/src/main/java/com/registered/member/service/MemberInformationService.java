package com.registered.member.service;

public interface MemberInformationService {
    Object Cheakmember(String id);
    Object initiate(String attachmentCode,String memberId );
    Object inprocess(String contactName,String contactPhone,String id,String Verify);

}
