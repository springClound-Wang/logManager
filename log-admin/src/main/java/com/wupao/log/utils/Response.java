package com.wupao.log.utils;

import java.io.Serializable;

/**
 * @项目名称： wupao-microservice
 * @类名称： Response
 * @类描述：封装返回结果
 * @创建人： 李瑞兆
 * @创建时间： 2018年1月6日 	上午10:47:18
 * @version:
 */
public class Response<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6525247837611398873L;
	
	/**
	 *响应成功
	 */
	public static final  String SUCCESS="200";
	/**
	 * 响应失败
	 */
	public static final String ERROR="500";

	private String status;// 响应状态编码
	private String message;// 响应信息
	private T result;// 返回成功信息

	public Response(){
		
	}
	public Response(String status,String message){
		this.status = status;
		this.message = message;
	}
	public Response(String status, String message, T result) {
		this.status = status;
		this.message = message;
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message
				+ ", result=" + result + "]";
	}
}