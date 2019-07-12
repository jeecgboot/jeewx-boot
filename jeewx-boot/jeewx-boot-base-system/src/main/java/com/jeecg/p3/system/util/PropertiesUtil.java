package com.jeecg.p3.system.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 
 * @author 张代浩
 * 
 */
public class PropertiesUtil {
	private String properiesName = "";

	public PropertiesUtil() {

	}

	public PropertiesUtil(String fileName) {
		this.properiesName = fileName;
	}

	public String readProperty(String key) {
		String value = "";
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName);
			Properties p = new Properties();
			p.load(is);
			value = p.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	public Properties getProperties() {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName);
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	public void writeProperty(String key, String value) {
		InputStream is = null;
		OutputStream os = null;
		Properties p = new Properties();
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName);
			p.load(is);
			os = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(properiesName).getFile());

			p.setProperty(key, value);
			p.store(os, key);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
				if (null != os)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void writeProperties(String realPath,String key,String value){
		Properties prop = new Properties();
		InputStream is = null;
        try {
        	is = new FileInputStream(realPath);
			prop.load(is);
			prop.setProperty(key, value);
	        //文件输出流 
	        FileOutputStream fos = new FileOutputStream(realPath); 
	        //将Properties集合保存到流中 
	        prop.store(fos, "update for "+key); 
	        is.close();
	        fos.close();//关闭流 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readValue(String realPath,String key){
		Properties prop = new Properties();
		InputStream is = null;
		String value = "";
        try {
        	is = new FileInputStream(realPath);
			prop.load(is);
			value = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static void main(String[] args) {
		PropertiesUtil p = new PropertiesUtil("sysConfig.properties");
		p.writeProperty("namess", "wang");
	}

}
