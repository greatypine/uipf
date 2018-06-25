package com.gasq.bdp.logn.utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gasq.bdp.logn.iexception.WorkFlowJobException;
import com.gasq.bdp.logn.model.TSysDataColumns;


public class ImportExcelUtil {
	
	private final static String excel2003L =".xls";    //2003- 版本的excel
	private final static String excel2007U =".xlsx";   //2007+ 版本的excel
	
	/**
	 * 描述：获取IO流中的数据，组装成List<List<Object>>对象
	 * @param columns 
	 * @param in,fileName
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> getBankListByExcel(List<TSysDataColumns> columns, String fileName,Integer startindex) throws WorkFlowJobException{
		if(startindex==null) startindex = 0;
		InputStream is = null;
		try {
			is = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			throw new WorkFlowJobException("导入excel时出错，文件没找到。",e);
		}
		List<Map> list = null;
		//创建Excel工作薄
		Workbook work = null;
		try {
			work = getWorkbook(is,fileName);
		} catch (Exception e) {
			throw new WorkFlowJobException("导入excel时出错，获取工作簿失败。",e);
		}
		if(null == work){
			throw new WorkFlowJobException("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		list = new ArrayList<Map>();
		sheet = work.getSheetAt(0);
		//遍历当前sheet中的所有行
		for (int j = sheet.getFirstRowNum()+startindex; j <= sheet.getLastRowNum(); j++) {
			row = sheet.getRow(j);
			if(row==null||row.getFirstCellNum()==j){continue;}
			//遍历所有的列
			List<Object> li = new ArrayList<Object>();
			for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
				cell = row.getCell(y);
				if(cell == null)li.add(null);
				else li.add(getCellValue(cell));
			}
			Map map1 = new HashMap();
			if(columns!=null && columns.size()>0) {
				int x = 0;
				for (TSysDataColumns dataColumns : columns) {
					String key = dataColumns.getCode();
					map1.put(key, li.get(x));
					x++;
				}
			}else {
				throw new WorkFlowJobException("输出参数不能为空！");
			}
			list.add(map1);
		}
		try {
			is.close();
		} catch (IOException e) {
			throw new WorkFlowJobException("导入excel时失败！关闭输入流失败！",e);
		}
		return list;
	}
	
	
	/**
	 * 描述：根据文件后缀，自适应上传文件的版本 
	 * @param inStr,fileName
	 * @return
	 * @throws Exception
	 */
	private static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if(excel2003L.equals(fileType)){
			wb = new HSSFWorkbook(inStr);  //2003-
		}else if(excel2007U.equals(fileType)){
			wb = new XSSFWorkbook(inStr);  //2007+
		}else{
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 描述：对表格中数值进行格式化
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static Object getCellValue(Cell cell){
		Object value = null;
		DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			if(value.toString().length()==13 && Long.parseLong(value.toString())>0) {
				Date d = new Date(Long.parseLong(value.toString()));
				value = d;
			}
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if("General".equals(cell.getCellStyle().getDataFormatString())){
				value = df.format(cell.getNumericCellValue());
			}else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
				value = sdf.format(cell.getDateCellValue());
			}else{
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			break;
		}
		return value;
	}
	
}