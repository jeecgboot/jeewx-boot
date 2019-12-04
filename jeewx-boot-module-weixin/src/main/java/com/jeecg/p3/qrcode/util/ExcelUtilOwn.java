package com.jeecg.p3.qrcode.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelUtilOwn {
	public static void exportExcel(HttpServletRequest request,
			HttpServletResponse response, Collection<?> listUser,Class<?> pojoClass,String title) throws Exception {
		response.setContentType("application/x-msdownload;charset=utf-8");
		String fileName = title+".xls";
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		File file = new File(new Date(0).getTime()+".xls");
	    OutputStream outputStream = new FileOutputStream(file);
	    ExcelUtil.exportExcel(request, response, listUser, pojoClass, title);
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
