package com.jeecg.p3.message.model;

/**
 * 用于设定图文消息的接收者
 * @author fumingxing
 *
 */
public class Filter {
	
	private boolean is_to_all;
	private String group_id;
	//-- update-begin--Author:gengjiajia  Date:2016-10-19 for：TASK #1433 【标签相关】群发功能，消息群发添加标签参数---
	private String tag_id;
	//-- update-end--Author:gengjiajia  Date:2016-10-19 for：TASK #1433 【标签相关】群发功能，消息群发添加标签参数---
	public boolean isIs_to_all() {
		return is_to_all;
	}
	/***
	 * 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，
	 * 选择false可根据group_id发送给指定群组的用户
	 * @param isToAll
	 */
	public void setIs_to_all(boolean isToAll) {
		is_to_all = isToAll;
	}
	
	public String getGroup_id() {
		return group_id;
	}
	/**
	 *群发到的分组的group_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写group_id
	 * @return
	 */
	public void setGroup_id(String groupId) {
		group_id = groupId;
	}
	/**
	 * 群发到的分组的tag_id，参加用户管理中用户标签接口，若is_to_all值为true，可不填写tag_id
	 * @return
	 */
	public String getTag_id() {
		return tag_id;
	}
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
	
}
