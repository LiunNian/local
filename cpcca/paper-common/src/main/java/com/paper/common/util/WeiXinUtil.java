package com.paper.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RefreshScope
public class WeiXinUtil {

    @Value("${file.path.QRCodePath}")
    private static String filePath = "D://QrCode/";

    @Value("${file.path.QRCodePath.is_hyaline}")
    private static Boolean is_hyaline = false;

    /**
     * 获取 Access_token
     * @return
     */
    public static String getWeaterAccess_token(String appid, String secret) {
        String host = "https://api.weixin.qq.com";
        String path = "/cgi-bin/token";
        String method = "GET";
        Map<String, String> headers = new HashMap<String, String>();

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("grant_type", "client_credential");
//        querys.put("appid", "wx492274337224e649");
//        querys.put("secret", "4d76bdaa51733ab5cb5ca48e366f21e2");
        if(appid == null || "".equals(appid) ){
            querys.put("appid", "wx3d83a4c71f36555b");
        }else {
            querys.put("appid", appid);
        }

        if(secret == null || "".equals(secret)) {
            querys.put("secret", "95dbd02cccbb8cff9f20d111299dba45");
        }else {
            querys.put("secret", secret);
        }

        String json = "";
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            json =  EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }



    /**
     * 获取小程序二维码
     * @param accessToken token
     * @param sceneStr  传参 例a=123456
     * @param page page= 页面路径
     * @param toPath 文件夹
     * @return
     */
    public static void getminiqrQr( String accessToken, String sceneStr, String page, String toPath) {
        if(toPath == null || "".equals(toPath)){
            toPath = "defualt";
        }
        if(sceneStr == null || "".equals(sceneStr)){
            sceneStr = "";
        }

        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken;
            Map<String,Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", page);
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String,Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            byte[] result = entity.getBody();
            inputStream = new ByteArrayInputStream(result);

            String path = filePath+"/"+toPath+"-"+sceneStr+".png";
            FileUtil.cretePath(path);
            File file = new File(path);
            if (!file.exists()){
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 获取小程序二维码
     * @param accessToken token
     * @param sceneStr  传参 例a=123456
     * @param page page= 页面路径
     * @param toPath 文件夹-->如book  /booke/a=5000.png 用book来区分业务
     * @param
     */
    public static void getQRCode(String accessToken, String sceneStr, String page, String toPath) {
        if(toPath == null || "".equals(toPath)){
            toPath = "defualt";
        }
        if(sceneStr == null || "".equals(sceneStr)){
            sceneStr = "";
        }
        try {
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", sceneStr); // bookeID = 123456
            paramJson.put("page", page); // page/index/index
            paramJson.put("width", 430);
            paramJson.put("auto_color", false);
            paramJson.put("is_hyaline", is_hyaline);

            //line_color生效
//            paramJson.put("auto_color", false);
//            JSONObject lineColor = new JSONObject();
//            lineColor.put("r", 0);
//            lineColor.put("g", 0);
//            lineColor.put("b", 0);
//            paramJson.put("line_color", lineColor);

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            String path = filePath+"/"+toPath+"/"+sceneStr+".png";
            FileUtil.cretePath(path);
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            OutputStream os = new FileOutputStream( new File(path) );

            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1)
            {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
