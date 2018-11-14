package com.gasq.bdp.logn.utils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

public class DataImportExportUtil {
	
	// 创建工作本
	private static Workbook workBook;
	private static Sheet sheet;

	/**
	 * 34.* 创建表头 35.
	 * @param setkeys *
	 * 
	 * @return 36.
	 */
	public static void createTableHeader(Set<String> setkeys) {
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
	public static void createTableRow(Sheet sheet,List<String> datalist, int rowIndex) {
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void createExcelSheeet(List<Map> datalist,String path) throws Exception {
		createWorkBook();
		Map<String, Object> keys = datalist.listIterator().next();
		Set<String> setkeys = keys.keySet();
		createTableHeader(setkeys); // --->创建一个表头行
		Iterator<Map> itmaps = datalist.iterator();
		List<String> importdatalist = null;
		int index = 1;
		while (itmaps.hasNext()) {
			importdatalist = new ArrayList<String>();
			Set<Entry> set = itmaps.next().entrySet();
			for (Entry entry : set) {
				if(entry.getValue()==null) {
					importdatalist.add(null);
				}else {
					if(entry.getValue() instanceof Date || (entry.getValue().toString().length()==13 && entry.getValue() instanceof Long)) {
						Date d = new Date((Long)entry.getValue());
						importdatalist.add(DateUtil.formatTime(d));
					}else {
						importdatalist.add(entry.getValue().toString());
					}
				}
			}
			createTableRow(sheet,importdatalist,index);
			index++;
		}
		FileOutputStream outputStream = new FileOutputStream(path);
		workBook.write(outputStream);
        outputStream.flush();
        outputStream.close();
	}

	private static CellStyle getStyle(Workbook workbook){
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

	public static Workbook createWorkBook() {
		workBook = new XSSFWorkbook();
		return workBook;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> paramsJsonImportHandle(List<String> columns,List<Map> rst){
		List<Map> rst1 = new ArrayList<Map>();
		Map map = null;
		for (Map map2 : rst) {
			map = new HashMap();
			Set<Entry> set = map2.entrySet();
			for (Entry entry : set) {
				String key = entry.getKey().toString();
				for (String column : columns) {
					if(key.equals(column)) {
						if(entry.getValue() instanceof Date) {
							map.put(key, DateUtil.formatTime((Date)entry.getValue()));
						}else {
							map.put(key, entry.getValue());
						}
						break;
					}
				}
			}
			rst1.add(map);
		}
		return rst1;
	}
}
