package com.poi.utils;

import com.poi.common.MasterTable;
import com.poi.common.Title;
import com.poi.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.CollectionUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 解析excel
 */
public class TableExcelBuilder {

    private static final String DEFAULT_SHEET_NAME = "数据";

    public static String emptyFile(List<String> titleData, List<Short> titleWidths, String suffix){
        MasterTable table = new MasterTable();
        Title title = new Title();
        title.setHeader(titleData);
        title.setWidths(titleWidths);
        table.setTitle(title);
        table.setRows(Collections.EMPTY_LIST);
        return TableExcelBuilder.buildToFile(table, UUID.randomUUID().toString().replace("-", StringUtils.EMPTY) + suffix);

    }

    /**
     * 构建文件
     * @param table
     * @param filename
     * @return
     */
    public static String buildToFile(MasterTable table, String filename){
        return buildToFile(Arrays.asList(table), filename);
    }

    public static String buildToFile(List<MasterTable> tables, String filename){
        //判断数据是否为空
        if(CollectionUtils.isEmpty(tables)){
            throw new BaseException("数据不能为空");
        }
        //创建工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置默认的单元格样式
        Map<Class<?>, CellStyle> styles = defaultStyles(workbook);
        //
        CellStyle columnStyle = workbook.createCellStyle();
        //格式化数据
        DataFormat format = workbook.createDataFormat();
        //格式化单元格
        columnStyle.setDataFormat(format.getFormat("@"));
        //遍历表
        tables.forEach(table -> {
            //如果没有数据，则直接设置空数据
            if(Objects.isNull(table.getRows())){
                table.setRows(Collections.EMPTY_LIST);
            }
            //表名称
            String sheetName = DEFAULT_SHEET_NAME;
            //如果表名为空，则设置表名
            if(StringUtils.isNotEmpty(table.getTableName())){
                sheetName = table.getTableName();
            }
            //创建工作表对象
            HSSFSheet sheet = workbook.createSheet(sheetName);
            //创建工作表的行
            HSSFRow row = sheet.createRow(0);//设置第一行，从零开始
            if(Objects.nonNull(table.getTitle().getWidths())){
                for(int i = 0; i < table.getTitle().getWidths().size(); i++){
                    if(table.getTitle().getWidths().size() > i && Objects.nonNull(table.getTitle().getWidths().get(i))){
                        sheet.setColumnWidth(i, table.getTitle().getWidths().get(i));
                    }
                }
            }
            for (int i = 0; i < table.getTitle().getHeader().size(); i++) {
                Cell subCell = row.createCell(i);
                subCell.setCellStyle(styles.get(Title.class));
                subCell.setCellValue(table.getTitle().getHeader().get(i));
                sheet.setDefaultColumnStyle(i, columnStyle);
            }
            for (int i = 0; i < table.getRows().size(); i++) {
                HSSFRow bodyRow = sheet.createRow(i + 1);
                if(Objects.nonNull(table.getRows().get(i).getHeight())){
                    bodyRow.setHeight(table.getRows().get(i).getHeight().shortValue());
                }
                for (int j = 0; j < table.getRows().get(i).getData().size(); j++) {
                    Cell subCell = bodyRow.createCell(j);
                    // subCell.setCellType(CellType.STRING);
                    subCell.setCellStyle(styles.get(Row.class));
                    subCell.setCellValue(table.getRows().get(i).getData().get(j));
                }
            }
        });
        try {
            OutputStream out = new FileOutputStream(FileUploadUtils.getAbsoluteFile(filename));
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;
    }

    //设置单元格格式
    private static Map<Class<?>, CellStyle> defaultStyles(Workbook wb) {
        //
        Map<Class<?>, CellStyle> styles = new HashMap<>();
        /**
         * 使用工作簿创建一个单元格格式
         */
        //设置填充方式。style.setFillPattern(CellStyle.SOLID_FOREGROUND)
        CellStyle style = wb.createCellStyle();
        //setAlignment(HorizontalAlignment.ALIGN_CENTER)  设置水平方向字体居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //setVerticalAlignment(VerticalAlignment.VERTICAL_CENTER)设置垂直方向字体居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put(Row.class, style);

        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get(Row.class));
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        styles.put(Title.class, style);

        return styles;
    }

}
