package bernie.ver1.berniever1.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriteUtil {
    public static byte[] exportToExcel(List<String> headers, List<Object[]> data) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("DataSheet");

        // Create styles for headers and data cells
        CellStyle headerStyle = createHeaderStyle(workbook);
        CellStyle dataStyle = createDataStyle(workbook);

        createHeaderRow(sheet, headers, headerStyle);
        createDataRows(sheet, data, dataStyle);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        return style;
    }

    private static CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short) 13);
        style.setFont(font);
        return style;
    }

    private static void createHeaderRow(Sheet sheet, List<String> headers, CellStyle style) {
        Row headerRow = sheet.createRow(0);
            int colIdx = 0;
        for (String header : headers) {
            Cell cell = headerRow.createCell(colIdx++);
            cell.setCellValue(header);
            cell.setCellStyle(style);
        }
    }

    private static void createDataRows(Sheet sheet, List<Object[]> data, CellStyle style) {
        int rowIdx = 1;
        for (Object[] rowData : data) {
            Row row = sheet.createRow(rowIdx++);
            int colIdx = 0;
            for (Object cellData : rowData) {
                Cell cell = row.createCell(colIdx++);
                setCellValue(cell, cellData);
                cell.setCellStyle(style);

                // Custom formatting based on data type
                if (cellData instanceof Double || cellData instanceof Float) {
                    DataFormat dataFormat = sheet.getWorkbook().createDataFormat();
                    CellStyle numberStyle = sheet.getWorkbook().createCellStyle();
                    numberStyle.cloneStyleFrom(style);
                    numberStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
                    cell.setCellStyle(numberStyle);
                }
            }
        }

        // Autosize columns after populating data
        for (int colIdx = 0; colIdx < sheet.getRow(0).getLastCellNum(); colIdx++) {
            sheet.autoSizeColumn(colIdx);
        }
        createDropdown(sheet, sheet.getRow(0).getLastCellNum());
    }

    private static void setCellValue(Cell cell, Object cellData) {
        if (cellData instanceof String) {
            cell.setCellValue((String) cellData);
        } else if (cellData instanceof Integer) {
            cell.setCellValue((Integer) cellData);
        } // Add more cases for other data types if needed
    }

    private static void createDropdown(Sheet sheet, int colIndex) {
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(
                new String[]{"Option 1", "Option 2", "Option 3"} // Replace with your options
        );

        CellRangeAddressList addressList = new CellRangeAddressList(1, sheet.getLastRowNum(), colIndex, colIndex);
        DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
        validation.setShowPromptBox(true);
        validation.setSuppressDropDownArrow(true);
        sheet.addValidationData(validation);
    }

}
