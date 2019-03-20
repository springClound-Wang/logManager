package com.wupao.log.utils;


/**
 * @项目名称： wupao-microservice
 * @类名称： ExceptionUtils
 * @类描述：用于统一解决异常返回值中的response对象问题
 * @创建人： 李瑞兆
 * @创建时间： 2018年1月6日 	下午12:26:38
 * @version:
 */
public class ExceptionUtils<T>{

	/**
	 * @描述：解决response对象中result泛型为String类型的返回值问题(不带参数)
	 * @创建人：李瑞兆
	 * @创建时间：2018年1月6日下午12:32:14
	 * @return
	 */
	public static Response<String> exceptionBackUtil() {
		// 异常
		Response<String> response = new Response<String>();
		response.setStatus(DictionaryConstants.E002);
		response.setMessage("网络异常，请稍后尝试~");
		return response;
	}
	
	/**
	 * @描述：解决response对象中result泛型为String类型的返回值问题(带参数)
	 * @创建人：李瑞兆
	 * @创建时间：2018年1月6日下午12:32:33
	 * @param response
	 * @return
	 */
	public static<T> Response<T> exceptionBackUtil(Response<T> response) {
		// 异常
		response.setStatus(DictionaryConstants.E002);
		response.setMessage("网络异常，请稍后尝试~");
		return response;
	}
}
