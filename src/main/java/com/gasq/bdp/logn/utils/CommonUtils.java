package com.gasq.bdp.logn.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.StringJoiner;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gasq.bdp.logn.iexception.WorkFlowJobException;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonUtils {
	protected static Logger logger = Logger.getLogger(CommonUtils.class);
	static ObjectMapper mapper = new ObjectMapper();

	public static boolean is(Boolean b) {
		return b!=null && b.booleanValue();
	}
	
	public static boolean isEmpty(Object v) {
		return isEmpty(v, true);
	}
	
	public static boolean isEmpty(Object v, boolean trim) {
		if(v == null) return true;
		if(v instanceof String) {
			String sv = (String) v;
			return trim ? sv.trim().length()==0 : sv.length()==0;
		}else {
			return false;
		}
	}
	
	public static String getArrayToString(String[] array,String split){
		if(isEmpty(array))return "";
		String str = "";
		for (int i = 0; i < array.length; i++){ 
			if(isEmpty(array[i]))continue;
			str+=array[i]+split;
		}
		if(str.length()>0)str = str.substring(0,str.length()-split.length());
		return str;
	}
	
	
	public static String createUUID(){
		return DigestUtils.md5DigestAsHex(createUUID(1).toString().getBytes());
	}
	public static String[] createUUID(int size){
		if(size<=0) return null;
		String uuids[] = new String[size];
		for (int i = 0; i < size; i++) {
			uuids[i] = UUID.randomUUID().toString();
		}
		return uuids;
	} 
	/**
	 * 将字符串转换为数据库可执行的字符串
	 * 处理数据库字符串字段 eg:aaaaa/aaaaa,bbbbb
	 * @param str eg：123ace---->'123ace'
	 */
	public static String changeStrToSqlValue(String str,String tag){
		if(str.length()<=0) return null;
		if(str.indexOf(tag)!=-1){
			String[] s = str.split(tag);
			str="";
			for (int i = 0; i < s.length; i++) {
				if(i==0) str = "'"+s[i]+"'";
				else str += ",'" + s[i]+"'"; 
			}
		}else str = "'"+str+"'";
		return str;
	}
	
	/**
	 * 获取随机数
	 * @param digit 位数
	 * @return
	 */
	public static String getRandomcode(int digit){
		int max=10;
        int min=1;
        for (int i = 1; i < digit; i++) {
			max = max*10;
			min = min*10;
		}
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
		return s+"";
	}
	 public static String encodeStr(String str) {  
         try {  
             return new String(str.getBytes("ISO-8859-1"), "UTF-8");  
         } catch (UnsupportedEncodingException e) {  
             logger.info(e.getMessage(),e);  
             return null;  
         }  
     } 
	 
	 public static String BeanToJSON(Object obj){
		 String str = null;
		 try {
			 if(mapper == null){
				 mapper = new ObjectMapper();
			 }
			 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			 mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			 str = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			logger.info(e.getMessage(),e);
		} catch (JsonMappingException e) {
			logger.info(e.getMessage(),e);
		} catch (IOException e) {
			logger.info(e.getMessage(),e);
		}
		return str;
	 }
	 public static Object JsonToBean(String jsonstr,Class<?> cls) {
		try {
			 if(mapper == null){
				 mapper = new ObjectMapper();
			 }
			 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			 mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			 return mapper.readValue(jsonstr,cls);
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
		return null;
	}
	 public static <T> String bean2Json(T bean) {  
	        try {
	        	if(mapper == null){
					 mapper = new ObjectMapper();
				 }
	        	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	            return mapper.writeValueAsString(bean);  
	        } catch (Exception e) {  
	            logger.info(e.getMessage(),e);  
	        }  
	        return "";  
	    }  
	      
	    @SuppressWarnings("rawtypes")
		public static String map2Json(Map map) {  
	        try {
	        	if(mapper == null){
					 mapper = new ObjectMapper();
				 }
	        	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	            return mapper.writeValueAsString(map);  
	        } catch (Exception e) {  
	            logger.info(e.getMessage(),e);  
	        }  
	        return "";  
	    }  
	      
	    @SuppressWarnings("rawtypes")
		public static String list2Json(List list) {  
	        try {
	        	if(mapper == null){
					 mapper = new ObjectMapper();
				 }
	        	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	            return mapper.writeValueAsString(list);  
	        } catch (Exception e) {  
	            logger.info(e.getMessage(),e);  
	        }  
	        return "";  
	    }  
	      
	    public static <T> T json2Bean(String json, Class<T> beanClass) {  
	        try {
	        	if(mapper == null){
					 mapper = new ObjectMapper();
				 }
	        	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	            return mapper.readValue(json, beanClass);  
	        } catch (Exception e) {  
	            logger.info(e.getMessage(),e);  
	        }  
	        return null;  
	    }  
	      
	    @SuppressWarnings("unchecked")
		public static <T> List<T> json2List(String json, Class<T> beanClass) {  
	        try {
	        	if(mapper == null){
					 mapper = new ObjectMapper();
				 }
	        	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	            return (List<T>)mapper.readValue(json, getCollectionType(List.class, beanClass));  
	        } catch (Exception e) {  
	            logger.info(e.getMessage(),e);  
	        }  
	        return null;  
	    }  
	      
	    @SuppressWarnings("rawtypes")
		public static Map json2Map(String json) {  
	        try {  
	        	if(mapper == null){
					 mapper = new ObjectMapper();
				 }
	        	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        	mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	            return (Map)mapper.readValue(json, Map.class);  
	        } catch (Exception e) {  
	            logger.info(e.getMessage(),e);  
	        }  
	        return null;  
	    }  
	      
	    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
	    	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);     
	    }   
	 
	/**
	 * 两个数据相除 并保留两位小数
	 * @param str1
	 * @param str2
	 * @return result
	 * */
	 public static String divideTempResult(String str1, String str2){
		 String temp = "0";
		 try{
			 if(StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)){
				 
				 BigDecimal tempStr1 = new BigDecimal(str1);
				 BigDecimal tempStr2 = new BigDecimal(str2);
				 
				 BigDecimal tempResult = tempStr1.divide(tempStr2, 2, BigDecimal.ROUND_HALF_UP);
				 
				 temp = tempResult + "";
			 }
		 }catch(Exception e){
			 logger.info(e.getMessage(),e);
		 }
		 return temp;
	 }
	 
	 /**
	  * 加密 MD5
	  * @param str
	  * @return
	  */
	 public static String change2MD5(String str){
		 if(str == null || str.equals("")) return null;
		 return DigestUtils.md5DigestAsHex(str.getBytes());
	 }
	 
	 /**
	  * 解密 MD5
	  * @param md5
	  * @param str
	  * @return
	  */
	 public static Boolean MD5Check(String md5,String str){
		 if(md5 == null || md5.equals("")) return null;
		 if(str == null || str.equals("")) return null;
		 String newmd5 = DigestUtils.md5DigestAsHex(str.getBytes());
		 if(md5.equals(newmd5)) return true;
		 return false;
	 }
	 /**
	  * 转换DOUBLE类型保留后几位小数
	  * @param d
	  * @param subindex
	  * @return
	  */
	 public static double transformDouble(double d,int subindex){
		 BigDecimal bd = new BigDecimal(d);  
		 double f1 = bd.setScale(subindex,BigDecimal.ROUND_HALF_UP).doubleValue();
		 return f1;
	 }
	 
	 /**
	  * 转换DOUBLE类型保留后几位小数
	  * @param d
	  * @param subindex
	  * @return
	  */
	 public static double transformStringToDouble(String strd,int subindex){
		 BigDecimal bd = new BigDecimal(strd);  
		 double f1 = bd.setScale(subindex,BigDecimal.ROUND_HALF_UP).doubleValue();
		 return f1;
	 }

	public static Map<String, Object> checkSqlEnable(String sql){
		Map<String,Object> map = new HashMap<String,Object>();
		if(isEmpty(sql)){
			map.put("status", false);
			map.put("mssg", "语句不能为空！");
			return map;
		}
		else if(sql.toLowerCase().indexOf("drop")!=-1 || sql.toLowerCase().indexOf("database")!=-1 || sql.toLowerCase().indexOf("use")!=-1|| sql.toLowerCase().indexOf("alter")!=-1){
			map.put("status", false);
			map.put("mssg", "此语句没有权限执行！");
			return map;
		}else{
			map.put("status", true);
			return map;
		}
	}
	
	
	/**
     * 将字符串text中由openToken和closeToken组成的占位符依次替换为args数组中的值
     * @param openToken
     * @param closeToken
     * @param text
     * @param args
     * @return
     */
    public static String parse(String openToken, String closeToken, String text, Object... args) {
        if (args == null || args.length <= 0) {
            return text;
        }
        int argsIndex = 0;

        if (text == null || text.isEmpty()) {
            return "";
        }
        char[] src = text.toCharArray();
        int offset = 0;
        // search open token
        int start = text.indexOf(openToken, offset);
        if (start == -1) {
            return text;
        }
        final StringBuilder builder = new StringBuilder();
        StringBuilder expression = null;
        while (start > -1) {
            if (start > 0 && src[start - 1] == '\\') {
                // this open token is escaped. remove the backslash and continue.
                builder.append(src, offset, start - offset - 1).append(openToken);
                offset = start + openToken.length();
            } else {
                // found open token. let's search close token.
                if (expression == null) {
                    expression = new StringBuilder();
                } else {
                    expression.setLength(0);
                }
                builder.append(src, offset, start - offset);
                offset = start + openToken.length();
                int end = text.indexOf(closeToken, offset);
                while (end > -1) {
                    if (end > offset && src[end - 1] == '\\') {
                        // this close token is escaped. remove the backslash and continue.
                        expression.append(src, offset, end - offset - 1).append(closeToken);
                        offset = end + closeToken.length();
                        end = text.indexOf(closeToken, offset);
                    } else {
                        expression.append(src, offset, end - offset);
                        offset = end + closeToken.length();
                        break;
                    }
                }
                if (end == -1) {
                    // close token was not found.
                    builder.append(src, start, src.length - start);
                    offset = src.length;
                } else {
                    ///////////////////////////////////////仅仅修改了该else分支下的个别行代码////////////////////////

                    String value = (argsIndex <= args.length - 1) ?
                            (args[argsIndex] == null ? "" : args[argsIndex].toString()) : expression.toString();
                    builder.append(value);
                    offset = end + closeToken.length();
                    argsIndex++;
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                }
            }
            start = text.indexOf(openToken, offset);
        }
        if (offset < src.length) {
            builder.append(src, offset, src.length - offset);
        }
        return builder.toString();
    }

    public static String parse0(String text, Object... args) {
        return parse("${", "}", text, args);
    }

    public static String parse1(String text, Object... args) {
        return parse("{", "}", text, args);
    }
    public static <K, V> Map<K, V> list2Map3(List<V> list, String keyMethodName,Class<V> c) {  
        Map<K, V> map = new HashMap<K, V>();  
        if (list != null) {  
            try {  
                Method methodGetKey = c.getMethod(keyMethodName);  
                for (int i = 0; i < list.size(); i++) {  
                    V value = list.get(i);  
                    @SuppressWarnings("unchecked")  
                    K key = (K) methodGetKey.invoke(list.get(i));  
                    map.put(key, value);  
                }  
            } catch (Exception e) {  
                throw new IllegalArgumentException("field can't match the key!");  
            }  
        }  
        return map;  
    }
    /**
     * 将ResultSet转换为list<map>
     */
	public static List<Map<String,Object>> ResultSetToList(ResultSet rs) throws SQLException{
		List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
		if(rs==null) return results;
		ResultSetMetaData rsmd = rs.getMetaData();  
		int colCount=rsmd.getColumnCount();
		List<String> colNameList=new ArrayList<String>();
		for(int i=0;i<colCount;i++){
			colNameList.add(rsmd.getColumnName(i+1));
		} 
		while(rs.next()){
			for(int i=0;i<colCount;i++){
				Map<String, Object> map=new HashMap<String, Object>();
				String key=colNameList.get(i);
				Object value=rs.getString(colNameList.get(i));
				map.put(key, value);
				results.add(map);
			}
		}
		return results;
	}
	
	public static void ExportExcel(String filename,List<Map<String,Object>> datalist,HttpServletResponse response) throws WorkFlowJobException {
		String file = filename+DateUtil.getDateStr(DateUtil.DATE_TIME_ALL)+".xlsx";
		ExcelUtil pd = new ExcelUtil();
		try {
			String fileName = new String(file.getBytes("UTF-8"), "ISO-8859-1");
			pd.createExcelSheeet(datalist,fileName);
			response.setContentType("application/vnd.ms-excel; charset=utf-8");	
			response.setHeader("Content-Disposition","attachment;filename=" + fileName);
			response.setCharacterEncoding("utf-8");
			OutputStream os = response.getOutputStream();
			Workbook wb = pd.getWorkBook();
			wb.write(os);
		} catch (Exception e) {
			throw new WorkFlowJobException("表格导出出错，错误信息 ：\\n"+e.getMessage());  
		}
	}
	/**
	 * 获取list中最大数
	 */
	public static Object getArrayMax(List<Object> list) {
        Object[] array = list.toArray();
        Object maxIndex = array[0];//定义最大值为该数组的第一个数
        //遍历循环数组
        for (int i = 0; i < array.length; i++) {
            if(Double.parseDouble(maxIndex.toString()) < Double.parseDouble(array[i].toString())){
                maxIndex = array[i];
            }
        }
        return maxIndex;
    }
	/**
	 * 获取list中最小数
	 */
	public static Object getArrayMin(List<Object> list) {
        Object[] array = list.toArray();
        Object minIndex = array[0];//定义最大值为该数组的第一个数
        //遍历循环数组
        for (int i = 0; i < array.length; i++) {
            if(Double.parseDouble(minIndex.toString()) > Double.parseDouble(array[i].toString())){
            	minIndex = array[i];
            }
        }
        return minIndex;
    }
	/**
	 * 字符串链接
	 * 以字符串 逗号为分割
	 */
	public static String join(CharSequence... elements) {
		String delimiter= ",";
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        // Number of elements not likely worth Arrays.stream overhead.
        StringJoiner joiner = new StringJoiner(delimiter);
        for (CharSequence cs: elements) {
            joiner.add(cs);
        }
        return joiner.toString();
    }
	/**
     * 保存文件，直接以multipartFile形式
     * @param multipartFile
     * @param path 文件保存绝对路径
	 * @param postfix 
     * @return 返回文件名
     * @throws IOException
     */
    public static String saveImg(MultipartFile multipartFile,String path, String postfix) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = UUID.randomUUID().toString() + postfix;
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }
    /**
     * 使用示例
     * @param args
     */
    public static void main(String... args) {
    	//{}被转义，不会被替换
//        System.out.println(parse("{", "}", "我的名字是\\{},结果是{}，可信度是%{}", "雷锋", true, 100));
//        System.out.println(parse0("我的名字是'${}',结果是'${}'，可信度是%${}", "雷锋", true, 100));
//        System.out.println(parse1("我的名字是{},结果是{}，可信度是%{}", "雷锋", true, 100));
//        输出结果如下：
//        我的名字是{},结果是true，可信度是%100
//        我的名字是雷锋,结果是true，可信度是%100
//        我的名字是雷锋,结果是true，可信度是%100
    	
//    	System.out.println(CommonUtils.change2MD5("ltn1234"));
    }

	public static String saveSysUserImg(MultipartFile multipartFile, String path, String filename, String postfix) throws IOException {
		 File file = new File(path);
	        if (!file.exists()) {
	            file.mkdirs();
	        }
	        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
	        String fileName = filename + postfix;
	        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
	        byte[] bs = new byte[1024];
	        int len;
	        while ((len = fileInputStream.read(bs)) != -1) {
	            bos.write(bs, 0, len);
	        }
	        bos.flush();
	        bos.close();
	        return fileName;
	}
}
