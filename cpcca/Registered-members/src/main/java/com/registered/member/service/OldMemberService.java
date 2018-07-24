package com.registered.member.service;

import com.registered.member.model.OldMenberValidation;

public interface OldMemberService {

    /*
    验证老会员是否存在
     */
    Object checkOldMember(OldMenberValidation obj);
}
