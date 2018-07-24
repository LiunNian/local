package com.chinazxt.stdzfp.webservice.cxf.util;

import java.io.*;

public class FileIO {
    public static void write(String str){
        File file = new File("", "addfile.txt");
        try {
            file.createNewFile(); // 创建文件
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 向文件写入内容(输出流)
        if(str==null) {
            str = "";
        }
        byte bt[] = new byte[1024];
        bt = str.getBytes();
        try {
            FileOutputStream in = new FileOutputStream(file);
            try {
                in.write(bt, 0, bt.length);
                in.close();
                // boolean success=true;
                // System.out.println("写入文件成功");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String read(){
        // TODO Auto-generated method stub
        File file = new File("", "addfile.txt");
        try {
            file.createNewFile(); // 创建文件
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            // 读取文件内容 (输入流)
            FileInputStream out = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(out);
            int ch = 0;
            while ((ch = isr.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";
    }
}
