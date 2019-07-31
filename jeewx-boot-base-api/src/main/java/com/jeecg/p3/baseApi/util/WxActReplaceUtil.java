package com.jeecg.p3.baseApi.util;

import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

import org.jeecgframework.p3.core.utils.common.StringUtils;
  
/**
 * 替换工具
 * @author huangqingquan
 * @since 2017-2-24 14:35:28
 * @version 1.0
 *
 */
public class WxActReplaceUtil {
	/**
	 * 替换script,input,doucument.write
	 * @param str 原始字符串
	 * @return 修改后的字符串
	 */
	public static String replace(String str){
		if(StringUtils.isEmpty(str)){
			return "";
		}else{
			//删除javascript代码
			str=delScriptTag(str);
			//删除doucumentwrite
			str=delDocumentWriteTag(str);
			//删除input框
			str=delInputTag(str);
			return str;
		}
	}
	
    /** 
     * 删除script标签 
     * @param str 
     * @return 
     */  
    private static String delScriptTag(String str){  
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";  
        Pattern p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(str);   
        str = m_script.replaceAll("");  
        return str.trim();  
    }  
    
    /**
     * 删除doucument write
     * @param str
     * @return
     */
    private static String delDocumentWriteTag(String str){  
        String regEx_script = "document.write([\\s\\S]*?)";  
        Pattern p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(str);   
        str = m_script.replaceAll("");  
        return str.trim();  
    }
    
    /**
     * 删除doucument write
     * @param str
     * @return
     */
    private static String delInputTag(String str){  
    	String regEx_script = "<input[\\s\\S]*?>";  
    	Pattern p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);  
    	Matcher m_script = p_script.matcher(str);   
    	str = m_script.replaceAll("");  
    	return str.trim();  
    }
    
}  