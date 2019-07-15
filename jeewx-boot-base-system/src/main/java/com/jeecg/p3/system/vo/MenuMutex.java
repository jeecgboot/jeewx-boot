package com.jeecg.p3.system.vo;

import java.io.Serializable;

/**
 * 描述：</b>RcAuthMutex:权限互斥<br>
 * @author junfeng.zhou
 * @since：2014年05月22日 14时09分04秒 星期四 
 * @version:1.0
 */
public class MenuMutex implements Serializable {
	private static final long serialVersionUID = 1L;
		/**	 *菜单权限	 */	private Menu menu;	/**	 *互斥菜单权限	 */	private Menu mutexMenu;
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Menu getMutexMenu() {
		return mutexMenu;
	}
	public void setMutexMenu(Menu mutexMenu) {
		this.mutexMenu = mutexMenu;
	}
	@Override
	public String toString() {
		return "MenuMutex [menu=" + menu + ", mutexMenu=" + mutexMenu + "]";
	}
	}

