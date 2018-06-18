package com.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Excel util, create excel sheet, cell and style.
 * @param <T> generic class.
 */
public class ExcelUtil<T> {

    public HSSFCellStyle getCellStyle(HSSFWorkbook workbook,boolean isHeader){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLocked(true);
        if (isHeader) {
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.BLACK.index);
            font.setFontHeightInPoints((short) 12);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(font);
        }
        return style;
    }


    public  void generateHeader(HSSFWorkbook workbook,HSSFSheet sheet,String[] headerColumns){
        HSSFCellStyle style = getCellStyle(workbook, true);
        Row row = sheet.createRow(0);
        row.setHeightInPoints(30);
        for(int i=0;i<headerColumns.length;i++){
            Cell cell = row.createCell(i);
            String[] column = headerColumns[i].split("_#_");
            sheet.setColumnWidth(i, Integer.valueOf(column[1]));
            cell.setCellValue(column[0]);
            cell.setCellStyle(style);
        }
    }

    public static InputStream writeExcelToStream(HSSFWorkbook workbook,HSSFSheet sheet) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
           workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //数据在bos中
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        return bis;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    public  HSSFSheet creatAuditSheet(HSSFWorkbook workbook,String sheetName,
                                      List<T> dataset,String[] headerColumns,String[] fieldColumns) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        HSSFSheet sheet = workbook.createSheet(sheetName);
        //sheet.protectSheet(""); //保护生成Excel文档，设置密码访问.
        //自动对生成的Excel 文档第一行标题栏设置成filter 过滤形式, 方便用户使用
        char[] endChar = Character.toChars( 'A' + (headerColumns.length - 1) );
        String rangeAddress = "A1:" + String.valueOf(endChar) + "1";
        sheet.setAutoFilter(CellRangeAddress.valueOf(rangeAddress));

        generateHeader(workbook,sheet,headerColumns);
        HSSFCellStyle style = getCellStyle(workbook,false);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        int rowNum = 0;
        for(T t:dataset){
            rowNum++ ;
            Row row = sheet.createRow(rowNum);
            row.setHeightInPoints(25);
            for(int i = 0; i < fieldColumns.length; i++){
                String fieldName = fieldColumns[i] ;

                String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
                try {
                    Class clazz = t.getClass();
                    Method getMethod;
                    getMethod = clazz.getMethod(getMethodName, new Class[]{} );
                    Object value = getMethod.invoke(t, new Object[]{});
                    String cellValue = "";
                    if (value instanceof Date){
                        Date date = (Date)value;
                        cellValue = sd.format(date);
                    }else{
                        cellValue = null != value ? value.toString() : "";
                    }
                    Cell cell = row.createCell(i);
                    cell.setCellStyle(style);
                    cell.setCellValue(cellValue);

                } catch (Exception e) {

                }
            }
        }
        return sheet;
    }

    /**
     * 得到Excel，并解析内容
     * @param file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<String[]> getExcelAsFile(String file) throws FileNotFoundException, IOException{
       List<String[]> lists = new ArrayList<String[]>();
        //1.得到Excel常用对象
//      POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("d:/FTP/test.xls"));
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
        //2.得到Excel工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        //3.得到Excel工作表对象
        HSSFSheet sheet = wb.getSheetAt(0);
        //总行数
        int trLength = sheet.getLastRowNum();
        //4.得到Excel工作表的行
        HSSFRow row = sheet.getRow(0);
        //总列数
        int tdLength = row.getLastCellNum();
        //5.得到Excel工作表指定行的单元格
        HSSFCell cell = row.getCell((short)1);
        //6.得到单元格样式
        CellStyle cellStyle = cell.getCellStyle();
        for(int i=0;i<trLength;i++){

            //得到Excel工作表的行
            HSSFRow row1 = sheet.getRow(i);
            String[] str = new String[tdLength];
            for(int j=0;j<tdLength;j++){
                //得到Excel工作表指定行的单元格
                HSSFCell cell1 = row1.getCell(j);
                /**
                 * 为了处理：Excel异常Cannot get a text value from a numeric cell
                 * 将所有列中的内容都设置成String类型格式
                 */
                if(cell1!=null){
                    cell1.setCellType(Cell.CELL_TYPE_STRING);
                }

                //获得每一列中的值
                System.out.print(cell1.getStringCellValue()+"\t\t\t");
                str[j] = cell1.getStringCellValue();
            }
            lists.add(str);
        }
        return lists;
    }
}