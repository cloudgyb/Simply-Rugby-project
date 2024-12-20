package com.sg.simplyrugby.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class SysUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 主键 **/
	@ApiModelProperty(value = "主键")
	private String id;
		
	/** 用户账号 **/
	@ApiModelProperty(value = "用户账号")
	private String username;
		
	/** 用户密码 **/
	@ApiModelProperty(value = "用户密码")
	private String password;
		
	/** 昵称 **/
	@ApiModelProperty(value = "昵称")
	private String nickname;
		
	/** 部门id **/
	@ApiModelProperty(value = "部门id")
	private Integer depId;
		
	/** 岗位id **/
	@ApiModelProperty(value = "岗位id")
	private String posId;
    
}