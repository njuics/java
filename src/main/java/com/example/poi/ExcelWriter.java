package com.example.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class ExcelWriter {
    public static void main(String[] args) throws Exception {
        // 1. 创建工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("学生成绩");

        // 2. 创建表头
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("姓名");
        headerRow.createCell(1).setCellValue("成绩");

        // 3. 写入数据
        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("张三");
        row1.createCell(1).setCellValue(95);

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("李四");
        row2.createCell(1).setCellValue(88);

        // 4. 保存文件
        try (FileOutputStream out = new FileOutputStream("成绩单.xlsx")) {
            workbook.write(out);
        }
        workbook.close();
    }
}
