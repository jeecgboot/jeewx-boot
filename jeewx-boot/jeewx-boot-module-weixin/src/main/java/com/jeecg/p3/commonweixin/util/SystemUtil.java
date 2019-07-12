package com.jeecg.p3.commonweixin.util;

import java.util.ArrayList;
import java.util.List;

import com.jeecg.p3.commonweixin.entity.WeixinMenuFunction;
import com.jeecg.p3.commonweixin.entity.WeixinTreeNode;

import net.sf.json.JSONArray;


/**
 * Description: 系统工具
 * @author junfeng.zhou
 * @version V1.0
 */
public class SystemUtil {
	 /**
     * 拼树：带checkbox
     * @param allList 所有可用的权限
     * @param roleList  当前角色的权限
     * @return
     */
    public static String list2TreeWithCheck(List<WeixinMenuFunction> allList,List<WeixinMenuFunction> roleList) {
        if(allList!=null && allList.size()>0) {
            List<WeixinTreeNode> treeList = new ArrayList<WeixinTreeNode>();
            for(WeixinMenuFunction auth :allList) {
                WeixinTreeNode tn = new WeixinTreeNode();
                tn.setId(auth.getAuthId());
                String pId = "0";
                if(auth.getParentAuthId()!=null && !auth.getParentAuthId().equals("")){
                    pId = auth.getParentAuthId();
                }
                tn.setpId(pId);
                tn.setName(auth.getAuthName());
                if("1".equals(auth.getAuthType())||"Y".equals(auth.getLeafInd())){
                	tn.setOpen(false); //设置所有打开或所有闭关
                }else{
                	tn.setOpen(true); //设置所有打开或所有闭关
                }
                tn.setChecked(false);
                //判断是否选中
                for(WeixinMenuFunction mf :roleList) {
                    if(mf.getAuthId().equals(auth.getAuthId())){
                        tn.setChecked(true);
                        break;
                    }

                }
                tn.setDoCheck(false);
                tn.setHalfCheck(false);
                tn.setParent(false);
                tn.setChkDisabled(false);
                tn.setNocheck(false);
                treeList.add(tn);
            }
            JSONArray jsonArray = JSONArray.fromObject(treeList);
            return jsonArray.toString();
        }
        return "";
    }
    
    /**
     * 拼树：
     * @param allList 所有可用的权限
     * @return
     */
    public static String listTreeToAuth(List<WeixinMenuFunction> allList) {
        if(allList!=null && allList.size()>0) {
            List<WeixinTreeNode> treeList = new ArrayList<WeixinTreeNode>();
            for(WeixinMenuFunction auth :allList) {
                WeixinTreeNode tn = new WeixinTreeNode();
                tn.setId(auth.getAuthId());
                String pId = "0";
                if(auth.getParentAuthId()!=null && !auth.getParentAuthId().equals("")){
                    pId = auth.getParentAuthId();
                }
                tn.setpId(pId);
                tn.setName(auth.getAuthName());
                if("1".equals(auth.getAuthType())||"Y".equals(auth.getLeafInd())){
                	tn.setOpen(false); //设置所有打开或所有闭关
                }else{
                	tn.setOpen(true); //设置所有打开或所有闭关
                }
                tn.setChecked(false);
                tn.setDoCheck(false);
                tn.setHalfCheck(false);
                tn.setParent(false);
                tn.setChkDisabled(false);
                tn.setNocheck(false);
                treeList.add(tn);
            }
            JSONArray jsonArray = JSONArray.fromObject(treeList);
            return jsonArray.toString();
        }
        return "";
    }
    
}
