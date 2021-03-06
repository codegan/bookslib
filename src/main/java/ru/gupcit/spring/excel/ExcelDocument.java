package ru.gupcit.spring.excel;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import ru.gupcit.spring.model.Books;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by zaur on 30.01.17.
 */
public class ExcelDocument extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(
            Map<String, Object> model,
            HSSFWorkbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //New Excel sheet
        HSSFSheet excelSheet = workbook.createSheet("Simple excel example");
        //Excel file name change
        response.setHeader("Content-Disposition", "attachment; filename=books.xls");

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);

        //Create Style for header
        CellStyle styleHeader = workbook.createCellStyle();
        styleHeader.setFillForegroundColor(HSSFColor.BLUE.index);
        styleHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styleHeader.setFont(font);

        //Set excel header
        setExcelHeader(excelSheet, styleHeader);

        //Get data from model
        List<Books> applicationses = (List<Books>) model.get("modelObject");
        int rowCount = 1;
        for (Books applications : applicationses) {
            HSSFRow row = excelSheet.createRow(rowCount++);
            row.createCell(0).setCellValue(applications.getId());
            row.createCell(1).setCellValue(applications.getTitle());
            row.createCell(2).setCellValue(applications.getText());
            row.createCell(3).setCellValue(applications.getAuthor());
            row.createCell(4).setCellValue(applications.getYear());
            row.createCell(5).setCellValue(applications.getName_category());
        }

    }
    public void setExcelHeader(HSSFSheet excelSheet, CellStyle styleHeader) {
        //set Excel Header names
        HSSFRow header = excelSheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Наименование");
        header.createCell(2).setCellValue("Описание");
        //header.getCell(2).setCellStyle(styleHeader);
        header.createCell(3).setCellValue("Автор");
        header.createCell(4).setCellValue("Год выпуска");
        header.createCell(5).setCellValue("Категория");
    }
}
