package com.demowebshop.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelUtility {
    public static XSSFWorkbook wb;
    public static XSSFSheet sh;
    public static FileInputStream f;

    public static List<ArrayList<String>> excelDataReader(String excelPath, String sheetName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        f = new FileInputStream(System.getProperty("user.dir") + excelPath);
        wb = new XSSFWorkbook(f);
        sh = wb.getSheet(sheetName);
        List<ArrayList<String>> excelRows = new ArrayList<>();
        int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
        for (int i = 0; i < rowCount + 1; i++) {
            int x = 0;
            Row row = sh.getRow(i);
            String[] columnList = new String[row.getLastCellNum()];
            for (int j = 0; j < columnList.length; j++) {
                columnList[j] = formatter.formatCellValue(row.getCell(x));
                x++;
            }
            excelRows.add(new ArrayList<>(Arrays.asList(columnList)));
        }
        return excelRows;
    }

}
