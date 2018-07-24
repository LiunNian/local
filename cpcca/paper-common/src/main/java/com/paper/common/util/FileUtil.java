package com.paper.common.util;


public class FileUtil {

    /**
     * 根据路径自动创建文件夹
     * @param path
     */
    public static void cretePath(String path){
        String[] paths = path.split("\\\\");//windows
        if(paths.length == 1){paths = path.split("/");}//linux
        String dir = paths[0];
        for (int i = 0; i < paths.length - 2; i++) {
            try {
                dir = dir + "/" + paths[i + 1];
                java.io.File dirFile = new java.io.File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                    System.out.println("创建目录为：" + dir);
                }
            } catch (Exception err) {
                System.err.println("文件夹创建发生异常!!!");
            }
        }
    }
}
