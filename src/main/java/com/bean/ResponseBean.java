package com.bean;

import java.io.Serializable;

/**
 * 
 * @author joel
 *
 */
public class ResponseBean<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 响应成功码
	 */
	public static final String SUCCESS = "00";

	/**
	 * 系统错误,需人工判断
	 */
	public static final String FAIL_99 = "99";

	/**
	 * 业务异常码 例如：数据错误、或调用接口方返回错误码
	 */
	public static final String FAIL_98 = "98";

	/**
	 * 请求超时、请求未响应、请求失败
	 */
	public static final String FAIL_97 = "97";

	/**
	 * 未登录
	 */
	public static final String FAIL_01 = "01";

	/**
	 * 未授权
	 */
	public static final String FAIL_401 = "401";

	/**
	 * 响应成功码数量为0
	 */
	public static final String SUC_NO = "06";
	/**
	 * 响应成功码数量为0
	 */
	public static final String SUC_BING = "77";

	/**
	 * 处理中
	 */
	public static final String FAIL_02 = "02";

	protected String code;
	protected String msg;
	protected T data;

	public ResponseBean() {
		super();
	}

	public ResponseBean(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseBean(String code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static ResponseBean<String> getSuccessBean() {
		ResponseBean<String> responseBean = new ResponseBean<String>();
		responseBean.setCode(SUCCESS);
		return responseBean;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseBean [");
		if (code != null) {
			builder.append("code=");
			builder.append(code);
			builder.append(", ");
		}
		if (msg != null) {
			builder.append("msg=");
			builder.append(msg);
			builder.append(", ");
		}
		if (data != null) {
			builder.append("data=");
			builder.append(data);
		}
		builder.append("]");
		return builder.toString();
	}

}
