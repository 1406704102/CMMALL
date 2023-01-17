/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.ppjj.cmmall.common.utils;

import com.ppjj.cmmall.common.exception.BizCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static R error() {
		return error(BizCodeEnum.UNKNOWN_EXCEPTION);
	}

	public static R error(BizCodeEnum bizCodeEnum, Object value) {
		R r = new R();

		r.put("code",bizCodeEnum.getCode());
		r.put("msg", bizCodeEnum.getMsg());
		r.put("data", value);
		return r;
	}
	
	public static R error(String msg) {
		return error(500, msg);
	}
	public static R error(int code,String msg) {
		R r = new R();
		r.put("code",code);
		r.put("msg", msg);
		return r;
	}
	
	public static R error(BizCodeEnum bizCodeEnum) {
		R r = new R();
		r.put("code",bizCodeEnum.getCode());
		r.put("msg", bizCodeEnum.getMsg());
		return r;
	}
	public static R error(BizCodeEnum bizCodeEnum,String msg) {
		R r = new R();
		r.put("code",bizCodeEnum.getCode());
		r.put("msg", msg);
		return r;
	}


	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public static R ok(Object value) {
		R r = new R();
		r.put("data", value);
		return r;
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
