package com.paper.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class PortUtil {

    public static String getWeaterJson(String host, String path, String method, Map<String, String> querys) {
//        String host = "http://jisutqybmf.market.alicloudapi.com";
//        String path = "/weather/query";
        if(method == null || "".equals(method)) {
            method = "GET";
        }
        Map<String, String> headers = new HashMap<String, String>();

        String json = "";
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            json =  EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}
