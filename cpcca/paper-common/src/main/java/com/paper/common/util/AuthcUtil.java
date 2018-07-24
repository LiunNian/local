package com.paper.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthcUtil {

//    public static void main(String[] args) {
//        System.out.println(encryptionPassword("SHA-384","shuxuefu").toString());
//    }

    private static final String[]  hashAlgorithmNames = new String[]{"MD5","SHA1", "SHA-224","SHA-256","SHA-384"};
    /**
     * 加密方法-->直接获取
     * @return map
     *       hashAlgorithmName 加密方式
     *       code 原密码
     *       salt  盐
     *       result 加密后密码
     */
    public static  Map encryptionPassword( ){
        Object credentials = UUID.randomUUID().toString().replaceAll("-","");
        int index = (int)(Math.random()*50) % 5;
        String salt = UUID.randomUUID().toString().replaceAll("-","");
        return encryptionPassword(credentials, AuthcUtil.hashAlgorithmNames[index], salt);
    }

    /**
     * 加密方法-->直接获取
     * @param salt 盐
     * @return map
     *       hashAlgorithmName 加密方式
     *       code 原密码
     *       salt  盐
     *       result 加密后密码
     */
    public static  Map encryptionPassword(String salt ){
        Object credentials = UUID.randomUUID().toString();
        int index = (int)(Math.random()*50) % 5;
        return encryptionPassword(credentials, AuthcUtil.hashAlgorithmNames[index], salt);
    }

    /**
     * 加密方法-->直接获取
     * @param salt 盐
     * @return map
     *       hashAlgorithmName 加密方式
     *       code 原密码
     *       salt  盐
     *       result 加密后密码
     */
    public static  Map encryptionPassword(String hashAlgorithmName, String salt ){
        Object credentials = UUID.randomUUID().toString().replaceAll("-","");;
        return encryptionPassword(credentials, hashAlgorithmName, salt);
    }

    /**
     * 加密方法
     * @param credentials 原密码
     * @param hashAlgorithmName 加密方式
     * @param salt 盐
     * @return map
     *       hashAlgorithmName 加密方式
     *       code 原密码
     *       salt  盐
     *       result 加密后密码
     */
    public static  Map encryptionPassword(Object credentials ,String hashAlgorithmName, String salt ){
        Map<String,String> map = new HashMap<String,String>();
        int hashIterations = 365; // 加密次数
        Object salttemp = ByteSource.Util.bytes("xizhuoruanjian");// 盐值
        // 这里是给客户的值
        Object temp = new SimpleHash("SHA1", credentials, salttemp,hashIterations);

        hashIterations = 512; // 加密次数
        Object salts = ByteSource.Util.bytes(salt);// 盐值
        // 这里是用来对比的
        Object result = new SimpleHash(hashAlgorithmName, temp, salts, hashIterations);
        map.put("hashAlgorithmName", hashAlgorithmName); // 加密方式
        map.put("code", temp.toString()); // 原密码
        map.put("salt", salt); // 盐
        map.put("result", result.toString()); // 加密后密码
        return map;
    }



    /**
     * 返回认证是否通过
     * @param hashAlgorithmName 加密算法
     * @param salt 盐
     * @param credentials 密码
     * @param authc 需要对比的密码
     * @return
     */
    public static Boolean getMyAuthc(String hashAlgorithmName, String salt, String credentials, String authc){
        if(credentials != null && authc != null && hashAlgorithmName != null) {
            int hashIterations = 512; // 加密次数
            Object salts = ByteSource.Util.bytes(salt);// 盐值
            // 这里是用来对比的
            try {
                Object result = new SimpleHash(hashAlgorithmName, credentials, salts, hashIterations);
                if (authc != null && result.equals(authc)) {
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }


//    public static void main(String[] args) {
//        int d = 152;
//        Double a = Double.valueOf(1050);
//        Double percent = d / a;
//        percent = percent * 100;
//        System.out.println(percent);
//
//        BigDecimal b = new BigDecimal(percent);
//        double df = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(df);
//        System.out.println(UUID.randomUUID().toString());
//    }
}
