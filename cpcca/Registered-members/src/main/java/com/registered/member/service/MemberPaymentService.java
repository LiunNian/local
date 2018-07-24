package com.registered.member.service;

import com.registered.member.model.Invoice;


public interface MemberPaymentService {
    Object PaymentInfo(String id);
    Object paystandard();
    Object eInvoice(Invoice obj,String standard,String id,String time);
}
