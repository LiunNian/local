package com.paper.common.model;

import java.io.Serializable;

public class Result implements Serializable{
	private String msg = "" ;

	private Object data ;

	private Integer code;

	private boolean success = false;

	public Result(){};

	public Result(String msg, Integer code, boolean success) {
		this.msg = msg;
		this.code = code;
		this.success = success;
	}

	public Result(String msg, Object data, Integer code, boolean success) {
		this.msg = msg;
		this.data = data;
		this.code = code;
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "msg:"+msg + " code:" +code + " data:"+data ;
	}
}
