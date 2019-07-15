package com.jeecg.p3.weixin.util;

/**
 * 项目参数工具类
 * 
 */
public class ResourceUtil {
	/**
	 * 获取tomcat项目的绝对路径
	 * @return
	 */
	public static String getWebProjectPath(){
		String classPath = ResourceUtil.getClassPath();
		String url = null;
		//服务器环境模式
		if(classPath.indexOf("WEB-INF")!=-1){
		   if("/".equals(getSeparator())){
			   url =  getSeparator()+ classPath.substring(0, classPath.indexOf("WEB-INF"));
		   }else{
			   url = classPath.substring(0, classPath.indexOf("WEB-INF"));
		   }
		}
		//本地开发模式
		if(classPath.indexOf("target")!=-1){
			url =  getPorjectPath();
		}
		
		
//		LogUtil.info("---------getWebProjectPath----------------------"+url);
		return url;
	}
	public static String getSeparator() {
		return System.getProperty("file.separator");
	}
	
	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}
	/**
	 * 获取项目根目录
	 * 
	 * @return
	 */
	public static String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}
}
