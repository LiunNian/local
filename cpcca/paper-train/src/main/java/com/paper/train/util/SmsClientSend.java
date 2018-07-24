package com.paper.train.util;

import java.net.URLEncoder;

public class SmsClientSend {
    /*
    * @param url
    * ：必填--发送连接地址URL——http://sms.kingtto.com:9999/sms.aspx
    * @param userid
    * ：必填--用户ID，为数字
    * @param account
    * ：必填--用户帐号
    * @param password
    * ：必填--用户密码
    * @param mobile
    * ：必填--发送的手机号码，多个可以用逗号隔比如>130xxxxxxxx,131xxxxxxxx
    * @param content
    * ：必填--实际发送内容，
    * @param action
    * ：选填--访问的事件，默认为send
    * @param sendType
    * ：选填--发送方式，默认为POST
    * @param codingType
    * ：选填--发送内容编码方式，默认为UTF-8
    * @param backEncodType
    * ：选填--返回内容编码方式，默认为UTF-8
    * @return 返回发送之后收到的信息
    */
    public static String sendSms(String url, String userid, String account,
                                  String password, String mobile, String content, String action,
                                  String sendType, String codingType, String backEncodType) {

        try {
            if (codingType == null || codingType.equals("")) {
                codingType = "UTF-8";
            }
            if (backEncodType == null || backEncodType.equals("")) {
                backEncodType = "UTF-8";
            }
            StringBuffer send = new StringBuffer();
            if (action != null && !action.equals("")) {
                send.append("action=").append(action);
            } else {
                send.append("action=send");
            }

            send.append("&userid=").append(userid);
            send.append("&account=").append(
                    URLEncoder.encode(account, codingType));
            send.append("&password=").append(
                    URLEncoder.encode(password, codingType));
            send.append("&mobile=").append(mobile);
            send.append("&content=").append(
                    URLEncoder.encode(content, codingType));
            if (sendType != null && (sendType.toLowerCase()).equals("get")) {
                return SmsClientAccessTool.getInstance().doAccessHTTPGet(
                        url + "?" + send.toString(), backEncodType);
            } else {
                return SmsClientAccessTool.getInstance().doAccessHTTPPost(url,
                        send.toString(), backEncodType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "未发送，编码异常";
        }
    }

}
