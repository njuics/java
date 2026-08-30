package com.example.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {
    public static void main(String[] args) throws Exception {
        // 1. 读取文件
        try (FileInputStream fis = new FileInputStream("成绩单.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            
            // 使用 DataFormatter 自动处理不同类型的数据
            DataFormatter formatter = new DataFormatter();

            // 2. 遍历行
            for (Row row : sheet) {
                for (Cell cell : row) {
                    // DataFormatter 会自动将数字、日期等转换为字符串
                    String cellValue = formatter.formatCellValue(cell);
                    System.out.print(cellValue + "\t");
                }
                System.out.println();
            }
        }
    }
}
