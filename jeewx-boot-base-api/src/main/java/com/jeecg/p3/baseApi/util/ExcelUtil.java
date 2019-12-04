package com.jeecg.p3.baseApi.util;

import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ExcelUtil {
    /**
     * @param object   实体对象集合
     * @param clazz    实体对象
     * @return title   文件名称
     */
    public static ModelAndView exportXls(HttpServletRequest request, List<?> object, Class<?> clazz, String title) {
        // Step.3 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
        mv.addObject(NormalExcelConstants.CLASS, clazz);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(null, title));
        mv.addObject(NormalExcelConstants.DATA_LIST, object);
        return mv;
    }
}