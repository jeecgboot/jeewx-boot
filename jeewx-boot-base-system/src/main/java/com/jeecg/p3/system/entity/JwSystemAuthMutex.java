package com.jeecg.p3.system.entity;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemAuthMutex:权限互斥表; InnoDB free: 9216 kB<br>
 * @author junfeng.zhou
 * @since：2015年12月21日 10时28分28秒 星期一 
 * @version:1.0
 */
public class JwSystemAuthMutex implements Entity<Long> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private Long id;	/**	 *权限编码	 */	private String authId;	/**	 *互斥权限编码	 */	private String mutexAuthId;	public Long getId() {	    return this.id;	}	public void setId(Long id) {	    this.id=id;	}	public String getAuthId() {	    return this.authId;	}	public void setAuthId(String authId) {	    this.authId=authId;	}	public String getMutexAuthId() {	    return this.mutexAuthId;	}	public void setMutexAuthId(String mutexAuthId) {	    this.mutexAuthId=mutexAuthId;	}
	@Override
	public String toString() {
		return "JwSystemAuthMutex [id=" + id + ", authId=" + authId
				+ ", mutexAuthId=" + mutexAuthId + "]";
	}
	
}

