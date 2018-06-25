package com.gasq.bdp.logn.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	private Logger logger = Logger.getLogger(this.getClass());
	// 创建工作本
	private Workbook workBook;
	private Sheet sheet;

	public ExcelUtil() {
		super();
		try {
            // XSSFWork used for .xslx (>= 2007), HSSWorkbook for 03 .xsl
            this.workBook = new XSSFWorkbook();//HSSFWorkbook();//WorkbookFactory.create(inputStream);
        }catch(Exception e) {
            logger.debug("It cause Error on CREATING excel workbook:");
            logger.info(e.getMessage(),e);
        }
	}

	/**
	 * 34.* 创建表头 35.
	 * @param setkeys *
	 * 
	 * @return 36.
	 */
	public void createTableHeader(Set<String> setkeys) {
		sheet = workBook.createSheet("数据列表");
		// 创建一行
		Row row0 = sheet.createRow(0);
		Object[] keys = setkeys.toArray();
		for (int i = 0; i < keys.length; i++) {
//			sheet.setColumnWidth(i, keys[i].toString().getBytes().length*2*256);
			// 创建一个单元格
			Cell cell_1 = row0.createCell(i, CellType.STRING);
            CellStyle style = getStyle(workBook);
            cell_1.setCellStyle(style);
            // 设置cell的值
            cell_1.setCellValue(keys[i].toString());
            sheet.autoSizeColumn(i);
		}
	}

	/**
	 * 50.* 创建行 51.*
	 * 
	 * @param datalist
	 *            52.*
	 * @param rowIndex
	 */
	public void createTableRow(Sheet sheet,List<String> datalist, int rowIndex) {
		// 创建第rowIndex行
		Row row = sheet.createRow(rowIndex);
		for (int i = 0; i < datalist.size(); i++) {
			// 创建第i个单元格
			 Cell cell = row.createCell(i,CellType.STRING);
			 cell.setCellValue(datalist.get(i));
		}
	}

	/**
	 * 68.* 创建整个Excel表 69.*
	 */
	public void createExcelSheeet(List<Map<String, Object>> datalist,String path) throws Exception {
		Map<String, Object> keys = datalist.listIterator().next();
		Set<String> setkeys = keys.keySet();
		createTableHeader(setkeys); // --->创建一个表头行
		Iterator<Map<String, Object>> itmaps = datalist.iterator();
		List<String> importdatalist = null;
		int index = 1;
		while (itmaps.hasNext()) {
			importdatalist = new ArrayList<String>();
			for (Entry<String, Object> entry : itmaps.next().entrySet()) {
				importdatalist.add(entry.getValue().toString());
			}
			createTableRow(sheet,importdatalist,index);
			index++;
		}
//		FileOutputStream outputStream = new FileOutputStream(path);
//		workBook.write(outputStream);
//        outputStream.flush();
//        outputStream.close();
	}

	private CellStyle getStyle(Workbook workbook){
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); 
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置单元格字体
        Font headerFont = workbook.createFont(); // 字体
        headerFont.setFontHeightInPoints((short)14);
        headerFont.setColor(HSSFColor.RED.index);
        headerFont.setFontName("宋体");
        style.setFont(headerFont);
        style.setWrapText(true);

        // 设置单元格边框及颜色
        style.setBorderBottom(BorderStyle.valueOf((short)1));
        
        style.setBorderLeft(BorderStyle.valueOf((short)1));
        style.setBorderRight(BorderStyle.valueOf((short)1));
        style.setBorderTop(BorderStyle.valueOf((short)1));
        style.setWrapText(true);
        return style;
    }

	public Workbook getWorkBook() {
		return workBook;
	}

	
}
