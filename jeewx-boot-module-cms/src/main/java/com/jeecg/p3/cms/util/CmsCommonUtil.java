package com.jeecg.p3.cms.util;

public class CmsCommonUtil {
	
	/**
	 * 文件存储业务包路径
	 */
	public static final String FILE_BIZ_PATH = "upload/img/cms";
	
	/**
	 * 文件上传重命名
	 * @author taoYan
	 * @since 2018年9月26日
	 */
	public static String getUploadFileName(String realFilename){
		 String fileExtension = realFilename.substring(realFilename.lastIndexOf("."));
	     String fileNoExtension = realFilename.substring(0,realFilename.lastIndexOf("."));
	     String filename=fileNoExtension+System.currentTimeMillis()+fileExtension;
	     return filename;
	}

}
