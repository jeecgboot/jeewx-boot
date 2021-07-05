package com.jeecg.p3.baseApi.util;

/**
 * @Description: TODO
 * @author: lsq
 * @date: 2021年07月02日 10:54
 */
public class commonUtil {
    /**
     * 判断文件名是否带盘符，重新处理
     * @param fileName
     * @return
     */
    public static String getFileName(String fileName){
        //替换上传文件名字的特殊字符
        fileName = fileName.replace("=","").replace(",","").replace("&","")
                .replace("#", "").replace("“", "").replace("”", "")
                .replace("/../","").replace("..","") .replace("/./","") .replace("./","");
        //替换上传文件名字中的空格
        fileName=fileName.replaceAll("\\s","");
        return fileName;
    }
}
