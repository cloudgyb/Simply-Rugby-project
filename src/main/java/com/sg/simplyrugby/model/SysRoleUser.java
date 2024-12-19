package com.sg.simplyrugby.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysRoleUser implements Serializable {
    private String id;

    private String sysUserId;

    private String sysRoleId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId == null ? null : sysUserId.trim();
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId == null ? null : sysRoleId.trim();
    }

	public SysRoleUser() {
		super();
	}

	public SysRoleUser(String id, String sysUserId, String sysRoleId) {
		super();
		this.id = id;
		this.sysUserId = sysUserId;
		this.sysRoleId = sysRoleId;
	}
    
    
}