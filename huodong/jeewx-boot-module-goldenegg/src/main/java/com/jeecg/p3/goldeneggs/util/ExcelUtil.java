package com.jeecg.p3.goldeneggs.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.jeecg.p3.goldeneggs.annotation.Excel;







public class ExcelUtil {
	/** 
	 *  
	 * @param title     Sheet名字 
	 * @param pojoClass Excel对象Class 
	 * @param dataSet   Excel对象数据List 
	 * @param out       输出流 
	 */  
	private static void exportExcel(String title, Class<?> pojoClass,  
	        Collection<?> dataSet, OutputStream out) {  
	    // 使用userModel模式实现的，当excel文档出现10万级别的大数据文件可能导致OOM内存溢出  
	    exportExcelInUserModel(title, pojoClass, dataSet, out);  
	    // 使用eventModel实现，可以一边读一边处理，效率较高，但是实现复杂，暂时未实现  
	}  
	  
	private static void exportExcelInUserModel(String title, Class<?> pojoClass,  
	        Collection<?> dataSet, OutputStream out) {
	    try {  
	        // 首先检查数据看是否是正确的  
	    	//update-begin-Alex---Date:20180910---for:修改逻辑，数据为空时可导出表头------
	        if (dataSet == null) {  
	        //update-end-Alex---Date:20180910---for:修改逻辑，数据为空时可导出表头------
	            throw new Exception("导出数据为空！");  
	        }  
	        if (title == null || out == null || pojoClass == null) {  
	            throw new Exception("传入参数不能为空！");  
	        }  
	        // 声明一个工作薄  
	        Workbook workbook = new HSSFWorkbook();  
	        // 生成一个表格  
	        Sheet sheet = workbook.createSheet(title);  
	  
	        // 标题  
	        List<String> exportFieldTitle = new ArrayList<String>();  
	        List<Integer> exportFieldWidth = new ArrayList<Integer>();  
	        // 拿到所有列名，以及导出的字段的get方法  
	        List<Method> methodObj = new ArrayList<Method>();  
	        Map<String, Method> convertMethod = new HashMap<String, Method>();  
	        // 得到所有字段  
	        Field fileds[] = pojoClass.getDeclaredFields();  
	        // 遍历整个filed  
	        for (int i = 0; i < fileds.length; i++) {  
	            Field field = fileds[i];  
	            Excel excel = field.getAnnotation(Excel.class);  
	            // 如果设置了annottion  
	            if (excel != null) {  
	                // 添加到标题  
	                exportFieldTitle.add(excel.exportName());  
	                // 添加标题的列宽  
	                exportFieldWidth.add(excel.exportFieldWidth());  
	                // 添加到需要导出的字段的方法  
	                String fieldname = field.getName();  
	                // System.out.println(i+"列宽"+excel.exportName()+" "+excel.exportFieldWidth());  
	                StringBuffer getMethodName = new StringBuffer("get");  
	                getMethodName.append(fieldname.substring(0, 1)  
	                        .toUpperCase());  
	                getMethodName.append(fieldname.substring(1));  
	  
	                Method getMethod = pojoClass.getMethod(getMethodName  
	                        .toString(), new Class[] {});  
	  
	                methodObj.add(getMethod);  
	                if (excel.exportConvertSign() == 1) {  
	                    StringBuffer getConvertMethodName = new StringBuffer(  
	                            "get");  
	                    getConvertMethodName.append(fieldname.substring(0, 1)  
	                            .toUpperCase());  
	                    getConvertMethodName.append(fieldname.substring(1));  
	                    getConvertMethodName.append("Convert");  
	                    // System.out.println("convert: "+getConvertMethodName.toString());  
	                    Method getConvertMethod = pojoClass  
	                            .getMethod(getConvertMethodName.toString(),  
	                                    new Class[] {});  
	                    convertMethod.put(getMethodName.toString(),  
	                            getConvertMethod);  
	                }  
	            }  
	        }  
	        int index = 0;  
	        // 产生表格标题行  
	        Row row = sheet.createRow(index);  
	        for (int i = 0, exportFieldTitleSize = exportFieldTitle.size(); i < exportFieldTitleSize; i++) {  
	            Cell cell = row.createCell(i);  
	            // cell.setCellStyle(style);  
	            RichTextString text = new HSSFRichTextString(exportFieldTitle  
	                    .get(i));  
	            cell.setCellValue(text);  
	        }  
	  
	        // 设置每行的列宽  
	        for (int i = 0; i < exportFieldWidth.size(); i++) {  
	            // 256=65280/255  
	            sheet.setColumnWidth(i, 256 * exportFieldWidth.get(i));  
	        }  
	        @SuppressWarnings("rawtypes")
			Iterator its = dataSet.iterator();  
	        // 循环插入剩下的集合  
	        while (its.hasNext()) {  
	            // 从第二行开始写，第一行是标题  
	            index++;  
	            row = sheet.createRow(index);  
	            Object t = its.next();  
	            for (int k = 0, methodObjSize = methodObj.size(); k < methodObjSize; k++) {  
	                Cell cell = row.createCell(k);  
	                Method getMethod = methodObj.get(k);  
	                Object value = null;  
	                if (convertMethod.containsKey(getMethod.getName())) {  
	                    Method cm = convertMethod.get(getMethod.getName());  
	                    value = cm.invoke(t, new Object[] {});  
	                } else {  
	                    value = getMethod.invoke(t, new Object[] {});  
	                }  
	                cell.setCellValue(value == null ? "" : value.toString());  
	            }  
	        }  
	  
	        workbook.write(out);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	  
	}  

	/**
	 * 导入 excel
	 * 
	 * @param file
	 * @param pojoClass
	 * @param pattern
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Collection importExcel(File file, Class pojoClass) {
		Collection dist = new ArrayList<Object>();
		try {
			// 得到目标目标类的所有的字段列表
			Field filed[] = pojoClass.getDeclaredFields();
			// 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中
			Map<String, Method> fieldSetMap = new HashMap<String, Method>();
			Map<String, Method> fieldSetConvertMap = new HashMap<String, Method>();
			// 循环读取所有字段
			for (int i = 0; i < filed.length; i++) {
				Field f = filed[i];
				// 得到单个字段上的Annotation
				Excel excel = f.getAnnotation(Excel.class);
				// 如果标识了Annotationd的话
				if (excel != null) {
					// 构造设置了Annotation的字段的Setter方法
					String fieldname = f.getName();
					String setMethodName = "set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
					// 构造调用的method，
					Method setMethod = pojoClass.getMethod(setMethodName, new Class[] { f.getType() });
					// 将这个method以Annotaion的名字为key来存入。
					// 对于重名将导致 覆盖 失败，对于此处的限制需要
					fieldSetMap.put(excel.exportName(), setMethod);
					if (excel.importConvertSign() == 1) {
						StringBuffer setConvertMethodName = new StringBuffer("set");
						setConvertMethodName.append(fieldname.substring(0, 1).toUpperCase());
						setConvertMethodName.append(fieldname.substring(1));
						setConvertMethodName.append("Convert");
						Method getConvertMethod = pojoClass.getMethod(setConvertMethodName.toString(), new Class[] { String.class });
						fieldSetConvertMap.put(excel.exportName(), getConvertMethod);
					}
				}
			}
			// 将传入的File构造为FileInputStream;
			FileInputStream in = new FileInputStream(file);
			// // 得到工作表
			HSSFWorkbook book = new HSSFWorkbook(in);
			// // 得到第一页
			HSSFSheet sheet = book.getSheetAt(0);
			// // 得到第一面的所有行
			Iterator<Row> row = sheet.rowIterator();
			// 得到第一行，也就是标题行
			Row title = row.next();
			// 得到第一行的所有列
			Iterator<Cell> cellTitle = title.cellIterator();
			// 将标题的文字内容放入到一个map中。
			Map titlemap = new HashMap();
			// 从标题第一列开始
			int i = 0;
			// 循环标题所有的列
			while (cellTitle.hasNext()) {
				Cell cell = cellTitle.next();
				String value = cell.getStringCellValue();
				titlemap.put(i, value);
				i = i + 1;
			}
			// 用来格式化日期的DateFormat
			@SuppressWarnings("unused")
			SimpleDateFormat sf;
			while (row.hasNext()) {
				// 标题下的第一行
				Row rown = row.next();
				// 行的所有列
				Iterator<Cell> cellbody = rown.cellIterator();
				// 得到传入类的实例
				Object tObject = pojoClass.newInstance();
				// 遍历一行的列
				while (cellbody.hasNext()) {
					Cell cell = cellbody.next();
					// 这里得到此列的对应的标题
					String titleString = (String) titlemap.get(cell.getColumnIndex());
					// 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值
					if (fieldSetMap.containsKey(titleString)) {
						Method setMethod = (Method) fieldSetMap.get(titleString);
						// 得到setter方法的参数
						Type[] ts = setMethod.getGenericParameterTypes();
						// 只要一个参数
						String xclass = ts[0].toString();
						// 判断参数类型
						if (fieldSetConvertMap.containsKey(titleString)) {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							fieldSetConvertMap.get(titleString).invoke(tObject, cell.getStringCellValue());
						} else {
							if (xclass.equals("class java.lang.String")) {
								// 先设置Cell的类型，然后就可以把纯数字作为String类型读进来了：
								cell.setCellType(Cell.CELL_TYPE_STRING);
								setMethod.invoke(tObject, cell.getStringCellValue());
							} else if (xclass.equals("class java.util.Date")) {
								try {
									setMethod.invoke(tObject, cell.getDateCellValue());
								} catch (Exception e) {
								}
							} else if (xclass.equals("class java.lang.Boolean")) {
								setMethod.invoke(tObject, cell.getBooleanCellValue());
							} else if (xclass.equals("class java.lang.Integer")) {
								Double numericCellValue = cell.getNumericCellValue();
								if (numericCellValue != null) {
									setMethod.invoke(tObject, numericCellValue.intValue());
								}
							} else if (xclass.equals("class java.lang.Long")) {
								setMethod.invoke(tObject, new Long(cell.getStringCellValue()));
							} else if (xclass.equals("class java.math.BigDecimal")) {
								// 先设置Cell的类型，然后就可以把纯数字作为String类型读进来了
								cell.setCellType(Cell.CELL_TYPE_STRING);
								String numericCell=cell.getStringCellValue();
								setMethod.invoke(tObject, new BigDecimal(numericCell));
							}
						}
					}
				}
				dist.add(tObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dist;
	}
    /**
     * 
     * @param request
     * @param response
     * @param listUser导出数据集
     * @param pojoClass 导出数据的实体
     * @param title 导出文件名
     * @throws Exception
     */
    public static void exportExcel(HttpServletRequest request,HttpServletResponse response, Collection<?> listUser,Class<?> pojoClass,String title) throws Exception {
    	response.setContentType("application/vnd.ms-excel;charset=utf-8");
    	String fileName = title+".xls";
    	
    	//update-begin-Alex----Date:20180910----for:解决IE浏览器导出时名称乱码问题---
    	String finalFileName = null;
    	String userAgent = request.getHeader("user-agent");

    	if (userAgent != null && userAgent.indexOf("MSIE") >= 0 || userAgent.indexOf("Trident") >= 0 || userAgent.indexOf("Edge") >= 0) {
    		//IE（Trident）内核
    		finalFileName =URLEncoder.encode(fileName,"UTF-8");
    	} else if(userAgent != null && userAgent.indexOf("chrome") >= 0 || userAgent.indexOf("safari") >= 0 || userAgent.indexOf("Firefox") >= 0) {
    		//谷歌、火狐等浏览器
    		finalFileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
    	}else{
    		finalFileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
    	}
    	//这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
    	response.setHeader("Content-Disposition", "attachment; filename="+ finalFileName);
    	//update-end-Alex----Date:20180910----for:解决IE浏览器导出时名称乱码问题---
    	File file = new File(new Date(0).getTime()+".xls");
    	OutputStream outputStream = new FileOutputStream(file);
    	ExcelUtil.exportExcel(title,pojoClass, listUser, outputStream);
    	InputStream is = new BufferedInputStream(new FileInputStream(file.getPath()));
    	file.delete();
    	outputStream.close();
    	OutputStream os = response.getOutputStream();
    	byte[] b = new byte[2048];
    	int length;
    	while ((length = is.read(b)) > 0) {
    		os.write(b, 0, length);
    	}
    	os.close();
    	is.close();
	}
    
}
