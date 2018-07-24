package cn.org.cpcca.paperserver.excel;


import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class ExcelUtils {
    @Value("${web.upload}")
    public String LIB_PATH;
    public List<List<String>> readXlsx(String path){
        File dest=new File(path);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        InputStream is = null;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<String>> list = new ArrayList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                int cellTotalNum = xssfRow.getLastCellNum();
                if (xssfRow != null) {
                    List<String> tempList = new ArrayList<>();
                    for(int cellNum =0;cellNum<cellTotalNum; cellNum++){
                        XSSFCell cell = xssfRow.getCell(cellNum);
                        if(cell!=null) {
                            String tempData = this.getValue(cell);
                            if (tempData != null) {
                                tempList.add(tempData);
                            }else{
                                tempList.add("");
                            }
                        }else{
                            tempList.add("");
                        }
                    }
                    if(tempList!=null){
                        list.add(tempList);
                    }
                }
            }
        }
        return list;
    }
    public List<List<String>> readXls(String path){
        File dest=new File(path);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        InputStream is = null;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HSSFWorkbook hssfWorkbook = null;
        try {
            hssfWorkbook = new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<String>> list = new ArrayList<List<String>>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                int cellTotalNum = hssfRow.getLastCellNum();
                if (hssfRow != null) {
                    List<String> tempList = new LinkedList<String>();
                    for(int cellNum =0;cellNum<cellTotalNum; cellNum++){
                        HSSFCell cell = hssfRow.getCell(cellNum);
                        if(cell!=null) {
                            String tempData = this.getValue(cell);
                            if (tempData != null) {
                                tempList.add(tempData);
                            }else{
                                tempList.add("");
                            }
                        }else{
                            tempList.add("");
                        }
                    }
                    if(tempList!=null) {
                        list.add(tempList);
                    }
                }
            }
        }

        return list;
    }
    private String getValue(HSSFCell hssfCell) {
        String returnData = "";
        if(hssfCell==null){
            return "";
        }
        if (hssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
            returnData =  String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellTypeEnum() == CellType.NUMERIC) {
            returnData =  String.valueOf(hssfCell.getNumericCellValue());
        } else if(hssfCell.getCellTypeEnum() == CellType.ERROR){
            returnData =  String.valueOf("ERROR");
        }else{
            returnData =  String.valueOf(hssfCell.getStringCellValue());
        }
        return returnData.replaceAll("\\u00A0","");
    }
    private String getValue(XSSFCell xssfCell) {
        String returnData = "";
        //System.out.println(xssfCell.getCellTypeEnum());
        if(xssfCell==null){
            return "";
        }
        if (xssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
            returnData =  String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellTypeEnum() == CellType.NUMERIC) {
            returnData =  String.valueOf(xssfCell.getNumericCellValue());
        } else {
            returnData =  String.valueOf(xssfCell.getStringCellValue());
        }
        return returnData.replaceAll("\\u00A0","");
    }
    public String createXls(List<List<String>> listData,String filename){
        return this.createXls(listData,filename,"");
    }
    public String createXls(List<List<String>> listData,String filename,String title){
        return this.createXls(listData,filename,title,1);
    }
    public String createXls(List<List<String>> listData,String filename,String title,int num){
        HSSFWorkbook wb = this.createXlsReWorkbook(listData,title,num);
        String fileName = filename+".xls";
        String pathName = LIB_PATH+fileName;
        try {
            if(filename.equals("")){
                title = "temp";
            }
            //创建excel文件
            File file=new File(pathName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            //将excel写入
            FileOutputStream stream= FileUtils.openOutputStream(file);
            wb.write(stream);
            stream.close();
            if(wb!=null){
                wb.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //输出Excel文件
//        OutputStream output=response.getOutputStream();
//        response.reset();
//        response.setHeader("Content-disposition", "attachment; filename=details.xls");
//        response.setContentType("application/msexcel");
//        wb.write(output);
//        output.close();
        return pathName;
    }
    public HSSFWorkbook createXlsReWorkbook(List<List<String>> listData){
        return this.createXlsReWorkbook(listData,"");
    }
    public HSSFWorkbook createXlsReWorkbook(List<List<String>> listData,String title){
        return this.createXlsReWorkbook(listData,title,1);
    }
    public HSSFWorkbook createXlsReWorkbook(List<List<String>> listData,String title,int num){

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("sheet"+String.valueOf(num));
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        int rowCount = 0;
        if(title!=null&&!title.equals("")){
            HSSFRow row1=sheet.createRow(rowCount++);
            //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
            HSSFCell cell=row1.createCell(0);
            //设置单元格内容
            cell.setCellValue(title);
            //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        }
        List<Integer> columnWidthList = new ArrayList<Integer>();
        if(listData!=null&&listData.size()>0){
            if(listData.get(0)!=null) {
                HSSFRow row = sheet.createRow(rowCount++);
                for (int i = 0, inum = listData.get(0).size(); i < inum; i++) {
                    if(listData.get(0).get(i)!=null) {
                        row.createCell(i).setCellValue(listData.get(0).get(i));
                        columnWidthList.add(listData.get(0).get(i).toString().getBytes().length*256);
                        //System.out.println(listData.get(0).get(i));
                    }
                }
            }
        }
        if(listData!=null&&listData.size()>1) {
            for(int i =2,inum=listData.size();i<=inum;i++) {
                //System.out.println("<<<---"+ String.valueOf(inum)+"---"+String.valueOf(i)+"----"+JSON.toJSONString(listData.get(i)));
                if(listData.get(i-1)!=null) {
                    HSSFRow row = sheet.createRow(rowCount++);
                    for (int j = 0, jnum = listData.get(i-1).size(); j < jnum; j++) {
                        if(listData.get(i - 1).get(j)!=null) {
                            row.createCell(j).setCellValue(listData.get(i - 1).get(j));
                            Integer columnWidth = listData.get(i - 1).get(j).getBytes().length*256;
//                            System.out.println("<-------columnWidth-------->");
//                            System.out.println(listData.get(i - 1).get(j));
//                            System.out.println(columnWidth);
//                            System.out.println(columnWidthList.get(j));
//                            System.out.println("<-------columnWidth end-------->");
                            if(columnWidthList.get(j)<columnWidth){
                                columnWidthList.set(j,columnWidth);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(JSON.toJSONString(columnWidthList));
        for(int i = 0,tnum = columnWidthList.size();i<tnum;i++){
            if (columnWidthList.get(i) < 255 * 256){
                sheet.setColumnWidth(i, columnWidthList.get(i) < 3000 ? 3000 : columnWidthList.get(i));
            }else{
                sheet.setColumnWidth(i,columnWidthList.get(i));
            }
            //sheet.autoSizeColumn(i);
        }
        return wb;
    }
    public XSSFWorkbook createXlsxReWorkbook(List<List<String>> listData){
        return this.createXlsxReWorkbook(listData,"");
    }
    public XSSFWorkbook createXlsxReWorkbook(List<List<String>> listData,String title){
        return this.createXlsxReWorkbook(listData,title,1);
    }
    public XSSFWorkbook createXlsxReWorkbook(List<List<String>> listData,String title,int num){

        //创建XSSFWorkbook对象(excel的文档对象)
        XSSFWorkbook wb = new XSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        XSSFSheet sheet=wb.createSheet("sheet"+String.valueOf(num));
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        int rowCount = 0;
        if(title!=null&&!title.equals("")){
            XSSFRow row1=sheet.createRow(rowCount++);
            //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
            XSSFCell cell=row1.createCell(0);
            //设置单元格内容
            cell.setCellValue(title);
            //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        }
        List<Integer> columnWidthList = new ArrayList<Integer>();
        if(listData!=null&&listData.size()>0){
            if(listData.get(0)!=null) {
                XSSFRow row = sheet.createRow(rowCount++);
                for (int i = 0, inum = listData.get(0).size(); i < inum; i++) {
                    if(listData.get(0).get(i)!=null) {
                        row.createCell(i).setCellValue(listData.get(0).get(i));
                        columnWidthList.add(listData.get(0).get(i).toString().getBytes().length*256);
                        //System.out.println(listData.get(0).get(i));
                    }
                }
            }
        }
        if(listData!=null&&listData.size()>1) {
            for(int i =2,inum=listData.size();i<=inum;i++) {
                //System.out.println("<<<---"+ String.valueOf(inum)+"---"+String.valueOf(i)+"----"+JSON.toJSONString(listData.get(i)));
                if(listData.get(i-1)!=null) {
                    XSSFRow row = sheet.createRow(rowCount++);
                    for (int j = 0, jnum = listData.get(i-1).size(); j < jnum; j++) {
                        if(listData.get(i - 1).get(j)!=null) {
                            row.createCell(j).setCellValue(listData.get(i - 1).get(j));
                            Integer columnWidth = listData.get(i - 1).get(j).getBytes().length*256;
//                            System.out.println("<-------columnWidth-------->");
//                            System.out.println(listData.get(i - 1).get(j));
//                            System.out.println(columnWidth);
//                            System.out.println(columnWidthList.get(j));
//                            System.out.println("<-------columnWidth end-------->");
                            if(columnWidthList.get(j)<columnWidth){
                                columnWidthList.set(j,columnWidth);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(JSON.toJSONString(columnWidthList));
        for(int i = 0,tnum = columnWidthList.size();i<tnum;i++){
//            int colWidth = columnWidthList.get(i)*2;
//            if(colWidth<255*256){
//                sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
//            }else{
//                sheet.setColumnWidth(i,6000 );
//            }
            sheet.autoSizeColumn(i);
        }
        return wb;
    }
    public static void main(String[] args){
        ExcelUtils excelUtil = new ExcelUtils();
        String excel2010 = excelUtil.LIB_PATH+"1.xlsx";
        List<List<String>>  temp = excelUtil.readXlsx(excel2010);
        excelUtil.createXls(temp,"测试");
        System.out.println(JSON.toJSONString(temp));
    }
}
