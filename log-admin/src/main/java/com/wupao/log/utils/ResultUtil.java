package com.wupao.log.utils;
/**
 * 
 * @项目名称：wupao-job
 * @类名称：ResultUtil
 * @类描述：响应信息帮助类
 * @创建人：张亚军
 * @创建时间：2016年3月10日 下午3:30:56
 * @version
 */
public class ResultUtil {
	public static final String SUCCESS="success";
	public static final String ERROR="error";
	/**
	 * 响应编码
	 */
	private Integer code;
	/**
	 * 响应结果 success=成功 error=异常
	 */
	private String result;
	/**
	 * 响应消息
	 */
	private String message;
	/**
	 * 响应的数据
	 */
	private Object data;
	
	public ResultUtil(String result) {
		super();
		this.result = result;
	}


	public Integer getCode() {
		return code;
	}
	
	
	public ResultUtil(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}


	public ResultUtil(String result, String message, Object data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}


	public ResultUtil(Integer code, String result, String message, Object data) {
		super();
		this.code = code;
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
	public ResultUtil() {
		super();
		
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public void setData(Object data) {
		this.data = data;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	
	
	

}
