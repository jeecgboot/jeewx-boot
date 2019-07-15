package com.jeecg.p3.system.util;

import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.StringUtils;

import net.sf.json.JSONArray;

import com.jeecg.p3.system.vo.WeixinAccountDto;
import com.jeecg.p3.system.vo.MenuFunction;
import com.jeecg.p3.system.vo.TreeNode;

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
    public static String list2TreeWithCheck(List<MenuFunction> allList,List<MenuFunction> roleList) {
        if(allList!=null && allList.size()>0) {
            List<TreeNode> treeList = new ArrayList<TreeNode>();
            for(MenuFunction auth :allList) {
                TreeNode tn = new TreeNode();
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
                for(MenuFunction mf :roleList) {
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
     * 拼树：带checkbox
     * @param allList 所有可用的权限
     * @param roleList  当前角色的权限
     * @return
     */
    public static String list2TreeWithCheckToJwid(List<WeixinAccountDto> allList,List<WeixinAccountDto> roleList) {
        if(allList!=null && allList.size()>0) {
            List<TreeNode> treeList = new ArrayList<TreeNode>();
            for(WeixinAccountDto auth :allList) {
                TreeNode tn = new TreeNode();
                tn.setId(auth.getJwid());
                String pId = "0";
                tn.setpId(pId);
                tn.setName(auth.getName());
                tn.setChecked(false);
                //判断是否选中
                for(WeixinAccountDto mf :roleList) {
                    if(mf.getJwid().equals(auth.getJwid())){
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
    public static String listTreeToAuth(List<MenuFunction> allList,String authId) {
        if(allList!=null && allList.size()>0) {
            List<TreeNode> treeList = new ArrayList<TreeNode>();
            for(MenuFunction auth :allList) {
                TreeNode tn = new TreeNode();
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
                //如果分类的ID不为空，并且等于自己，则设置不可选中
				if(StringUtils.isNotEmpty(authId)&&auth.getAuthId().equals(authId)){
					tn.setChkDisabled(true);
				}
                treeList.add(tn);
            }
            JSONArray jsonArray = JSONArray.fromObject(treeList);
            return jsonArray.toString();
        }
        return "";
    }
    
}
