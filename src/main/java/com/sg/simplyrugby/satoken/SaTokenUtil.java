package com.sg.simplyrugby.satoken;

import cn.dev33.satoken.stp.StpUtil;
import com.sg.simplyrugby.model.SysUser;


public class SaTokenUtil {

	/**
	 * 获取登录用户model
	 */
	public static SysUser getUser() {
		return (SysUser) StpUtil.getSession().get("user");
	}

	/**
	 * set用户
	 */
	public static void setUser(SysUser user) {
		StpUtil.getSession().set("user", user);
	}

	/**
	 * 获取登录用户id
	 */
	public static String getUserId() {
		return StpUtil.getLoginIdAsString();
	}

	/**
	 * 获取登录用户name
	 */
	public static String getLoginName() {
		SysUser SysUser = getUser();
		if (SysUser == null) {
			throw new RuntimeException("用户不存在！");
		}
		return SysUser.getUsername();
	}

	/**
	 * 获取登录用户ip
	 * 
	 * @return
	 * @author fuce
	 * @Date 2019年11月21日 上午9:58:26
	 */
	public static String getIp() {
		return StpUtil.getTokenSession().getString("login_ip");
	}

	/**
	 * 判断是否登录
	 * 
	 * @return
	 * @author fuce
	 * @Date 2019年11月21日 上午9:58:26
	 */
	public static boolean isLogin() {
		return StpUtil.isLogin();
	}

}
