package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.mappers.ItemMapper;
import cn.org.cpcca.paperserver.mappers.ItemTypeMapper;
import cn.org.cpcca.paperserver.models.ItemModel;
import cn.org.cpcca.paperserver.models.ItemTypeModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ItemService implements ItemServiceInterface {
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private ItemTypeMapper itemTypeMapper;
    @Override
    public int addItem(ItemModel itemModel) {
        itemMapper.addItem(itemModel);
        return itemModel.getId();
    }

    @Override
    public ItemModel selectItem(int id) {
        return itemMapper.selectItem(id);
    }

    @Override
    public int updateItem(ItemModel itemModel) {
        return itemMapper.updateItem(itemModel);
    }

    @Override
    public List<ItemModel> listItemAll() {
        return itemMapper.listItemAll();
    }

    @Override
    public List<ItemModel> listItemReview() {
        return itemMapper.listItemReview();
    }

    @Override
    public List<ItemModel> listItems() {
        return itemMapper.listItemNow();
    }

    @Override
    public List<ItemModel> listItemEnd() {
        return itemMapper.listItemEnd();
    }

    @Override
    public int updateState(Map<String, Object> itemMap) {
        return itemMapper.updateState(itemMap);
    }

    @Override
    public int deleteItems(List<Integer> itids) {
        return itemMapper.deleteItems(itids);
    }

    @Override
    public List<ItemTypeModel> listItemType() {
        return itemTypeMapper.getType();
    }

    @Override
    public List<ItemModel> listItemNow() {
        return itemMapper.listItemNow();
    }

    @Override
    public List<ItemModel> listItemBin() {
        return itemMapper.listItemBin();
    }

    @Override
    public String getTypeName(int id) {
        return itemTypeMapper.getTypeName(id);
    }

    @Override
    public String getFileList(int itemId) {
        List<Map> list = itemTypeMapper.getFilesByItem(itemId);
        return groupFile(list);
    }


    public String  groupFile(List<Map> list){

        String title = null;
        //        String path = "/wnmp/cpcca/docker/cpcca/temp/";
        String path = "F:\\论文\\cpcca\\";
        String oldPath = "F:\\论文\\papers\\word\\";
        //   String oldPath = "/wnmp/cpcca/docker/cpcca/uploads/papers/word/";

        Path newPath = null ;
        String temp = "";
            for (Map map : list) {
            if(title == null) {
                title = map.get("title").toString()+"投稿设计";
            }
            try {
//                temp = path + title+"\\" + map.get("unitname").toString().trim()+ "\\"+map.get("name").toString().trim();

                temp = path + title+"\\"+map.get("username").toString().trim()+ "\\" + map.get("unitname").toString().trim()+ "\\"+map.get("name").toString().trim();

                cretePath(temp);
                newPath = Paths.get(temp);

                FileInputStream input =  new FileInputStream(new File(oldPath + map.get("uri").toString()));
                Files.copy(input, newPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        path = "F:\\论文\\author\\";
        String oldPath2 = "F:\\论文\\papers\\pic\\";
            for (Map map : list) {
            title = map.get("title").toString()+"授权书";
            try {
//                temp = path + title+"\\" + map.get("themes").toString().trim()+ "\\"+ map.get("directions").toString().trim()+"\\" +
//                        map.get("unitname").toString().trim()+"\\"+map.get("papername").toString().trim()+"\\"+map.get("name2").toString().trim();

                temp = path + title+"\\"+map.get("username").toString().trim()+ "\\" + map.get("unitname").toString().trim()+ "\\"+map.get("name2").toString().trim();
                cretePath(temp);
                newPath = Paths.get(temp);

                FileInputStream input =  new FileInputStream(new File(oldPath2 + map.get("uri2").toString()));
                Files.copy(input, newPath, StandardCopyOption.REPLACE_EXISTING);

//                temp = path + title+"\\" + map.get("themes").toString().trim()+ "\\"+ map.get("directions").toString().trim()+"\\" +
//                        map.get("unitname").toString().trim()+"\\"+map.get("papername").toString().trim()+"\\"+map.get("name").toString().trim();
                temp = path + title+"\\"+map.get("username").toString().trim()+ "\\" + map.get("unitname").toString().trim()+ "\\"+map.get("name").toString().trim();
                newPath = Paths.get(temp);
                cretePath(temp);
                input =  new FileInputStream(new File(oldPath + map.get("uri").toString()));
                Files.copy(input, newPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    //        if(list != null || list.size() > 0) {
    //            String sourceFilePath = path + title;
    //            String zipFilePath = path;
    //            String fileName = title;
    //            cretePath(zipFilePath);
    //            boolean flag = fileToZip(sourceFilePath, zipFilePath, fileName);
    //        }else {
    //            return "无数据";
    //        }
        return "创建成功";
    }

        /**
         * 根据路径自动创建文件夹
         * @param path
         */
        public void cretePath(String path){
            String[] paths = path.split("\\\\");// windows
            if(paths.length == 1){paths = path.split("/");}// linux
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


        /**
         * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
         * @param sourceFilePath :待压缩的文件路径
         * @param zipFilePath :压缩后存放路径
         * @param fileName :压缩后文件的名称
         * @return
         */
        private boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){
            boolean flag = false;
            File sourceFile = new File(sourceFilePath);
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            FileOutputStream fos = null;
            ZipOutputStream zos = null;

            if(sourceFile.exists() == false){
                System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");
            }else{
                try {
                    File zipFile = new File(zipFilePath + "\\" + fileName +".zip");
                    if(zipFile.exists()){
                        System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");
                    }else{
                        File[] sourceFiles = sourceFile.listFiles();
                        if(null == sourceFiles || sourceFiles.length<1){
                            System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                        }else{
                            fos = new FileOutputStream(zipFile);
                            zos = new ZipOutputStream(new BufferedOutputStream(fos));
                            byte[] bufs = new byte[1024*10];
                            for(int i=0;i<sourceFiles.length;i++){
                                //创建ZIP实体，并添加进压缩包
                                ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                                zos.putNextEntry(zipEntry);
                                //读取待压缩的文件并写进压缩包里
                                fis = new FileInputStream(sourceFiles[i]);
                                bis = new BufferedInputStream(fis, 1024*10);
                                int read = 0;
                                while((read=bis.read(bufs, 0, 1024*10)) != -1){
                                    zos.write(bufs,0,read);
                                }
                            }
                            flag = true;
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } finally{
                    //关闭流
                    try {
                        if(null != bis) bis.close();
                        if(null != zos) zos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
            }
            return flag;
        }

}
