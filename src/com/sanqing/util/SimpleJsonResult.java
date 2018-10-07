package com.sanqing.util;

public class SimpleJsonResult {
	private boolean success;
	private String info;
	
	public SimpleJsonResult() {
	}
	public SimpleJsonResult(boolean success, String info) {
		this.success = success;
		this.info = info;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
