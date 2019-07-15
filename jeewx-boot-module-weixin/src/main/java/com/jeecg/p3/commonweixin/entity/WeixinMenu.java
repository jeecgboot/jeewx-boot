package com.jeecg.p3.commonweixin.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：菜单对象
 * @author：<a href="mailto:zhoujunfeng@jd.com">周俊峰</a>
 * @since：2013年11月27日 上午9:23:08
 * @version:1.0
 */
public class WeixinMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/***
	 * 用户编码
	 */
	private String userId;
	/***
	 * 权限编码
	 */
	private String authId;
	/***
	 * 权限名称
	 */
	private String authName;
	/***
	 * 权限控制
	 */
	private String authContr;
	/***
	 * 上一级权限编码
	 */
	private String parentAuthId;
	/**
	 * 权限类型
	 */
	private String authType;
    /**
     * 子菜单
     */
    private List<WeixinMenu> childMenu;
    /**
     * 是否选中
     */
    private boolean selected;  
    
    private String authDesc;
    
    
	/**
	 *角色编码
	 */
	private String roleId;
	
	/**
	 *角色名称
	 */
	private String roleName;
	
	/**
	 *菜单图标
	 */
	private String iconType;
    
	public String getAuthDesc() {
		return authDesc;
	}
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getAuthContr() {
		return authContr;
	}
	public void setAuthContr(String authContr) {
		this.authContr = authContr;
	}
	public String getParentAuthId() {
		return parentAuthId;
	}
	public void setParentAuthId(String parentAuthId) {
		this.parentAuthId = parentAuthId;
	}
    public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public List<WeixinMenu> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<WeixinMenu> childMenu) {
        this.childMenu = childMenu;
    }
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Menu [userId=");
		builder.append(userId);
		builder.append(", authId=");
		builder.append(authId);
		builder.append(", authName=");
		builder.append(authName);
		builder.append(", authContr=");
		builder.append(authContr);
		builder.append(", parentAuthId=");
		builder.append(parentAuthId);
		builder.append(", authType=");
		builder.append(authType);
		builder.append(", childMenu=");
		builder.append(childMenu);
		builder.append(", selected=");
		builder.append(selected);
		builder.append(", authDesc=");
		builder.append(authDesc);
		builder.append(", roleId=");
		builder.append(roleId);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", iconType=");
		builder.append(iconType);
		builder.append("]");
		return builder.toString();
	}
	
	public void setIconType(String iconType) {
		this.iconType = iconType;
	}
	public String getIconType() {
		return iconType;
	}
	
}
