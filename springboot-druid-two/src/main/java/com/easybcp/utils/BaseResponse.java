package com.easybcp.utils;

import java.util.Date;

/**
 * @param <T>
 */
public class BaseResponse<T> {
	/**
	 * 返回码
	 */
	private int code;

	/**
	 * 返回信息描述
	 */
	private String message;

	/**
	 * 返回数据
	 */
	private T data;

	private long exp;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getCurrentTime() {
		return exp;
	}

	public void setCurrentTime(long currentTime) {
		this.exp = currentTime;
	}

	public BaseResponse() {
	}

	/**
	 *
	 * @param code
	 *            错误码
	 * @param message
	 *            信息
	 * @param data
	 *            数据
	 */
	public BaseResponse(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.exp = new Date().getTime();
	}

	/**
	 * 不带数据的返回结果
	 * 
	 * @param resultStatus
	 */
	public BaseResponse(ResultStatus resultStatus) {
		this.code = resultStatus.getErrorCode();
		this.message = resultStatus.getErrorMsg();
		this.data = null;
		this.exp = new Date().getTime();
	}

	/**
	 * 带数据的返回结果
	 * 
	 * @param resultStatus
	 * @param data
	 */
	public BaseResponse(ResultStatus resultStatus, T data) {
		this.code = resultStatus.getErrorCode();
		this.message = resultStatus.getErrorMsg();
		this.data = data;
		this.exp = new Date().getTime();
	}

	public BaseResponse(T data) {
		this(ResultStatus.SUCCESS, data);
	}
}
