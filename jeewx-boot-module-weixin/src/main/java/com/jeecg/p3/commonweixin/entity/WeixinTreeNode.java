package com.jeecg.p3.commonweixin.entity;

/**
 * Created with IntelliJ IDEA.
 * User: lianweijun
 * Date: 13-11-27
 * Time: 下午5:19
 * To change this template use File | Settings | File Templates.
 */
public class WeixinTreeNode {
    private String id;
    private String pId;
    private String name;
    private boolean open;
    private boolean checked;
    private boolean doCheck;
    private boolean halfCheck;
    private boolean isParent;
    private boolean chkDisabled;
    private boolean nocheck;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public boolean isChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public boolean isDoCheck() {
        return doCheck;
    }

    public void setDoCheck(boolean doCheck) {
        this.doCheck = doCheck;
    }

    public boolean isHalfCheck() {
        return halfCheck;
    }

    public void setHalfCheck(boolean halfCheck) {
        this.halfCheck = halfCheck;
    }

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", pId=" + pId + ", name=" + name
				+ ", open=" + open + ", checked=" + checked + ", doCheck="
				+ doCheck + ", halfCheck=" + halfCheck + ", isParent="
				+ isParent + ", chkDisabled=" + chkDisabled + ", nocheck="
				+ nocheck + "]";
	}
    
}
