package com.wupao.log.utils;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @classDesc: 功能描述:(通用baseapi 父类)
 * @author: 汪正章
 * @createTime: 2018年4月3日 下午11:16:35
 * @version: v1.0
 * @copyright:上海舞泡网络科技有限公司
 */
public class BaseApiService {

	/**
	 * 
	 * @methodDesc: 功能描述:(返回错误)
	 * @param: @param
	 *             msg
	 * @param: @return
	 */
	public Map<String, Object> setResutError(String msg) {
		return setResut(Constants.HTTP_500_CODE, msg, null);
	}
	/**
	 * 
	 * @methodDesc: 功能描述:(返回成功)
	 * @param: @param
	 *             msg
	 * @param: @return
	 */
	public Map<String, Object> setResutSuccessData(Object data) {
		return setResut(Constants.HTTP_200_CODE, Constants.HTTP_SUCCESS_NAME, data);
	}
	/**
	 * 
	 * @methodDesc: 功能描述:(返回成功)
	 * @param: @param
	 *             msg
	 * @param: @return
	 */
	public Map<String, Object> setResutSuccess() {
		return setResut(Constants.HTTP_200_CODE, Constants.HTTP_SUCCESS_NAME, null);
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(返回成功)
	 * @param: @param
	 *             msg
	 * @param: @return
	 */
	public Map<String, Object> setResutSuccess(String msg) {
		return setResut(Constants.HTTP_200_CODE, msg, null);
	}

	/**
	 * 
	 * @methodDesc: 功能描述:(自定义返回)
	 * @param: @param
	 *             code
	 * @param: @param
	 *             msg
	 * @param: @param
	 *             data
	 * @param: @return
	 */
	public Map<String, Object> setResut(Integer code, String msg, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.HTTP_CODE_NAME, code);
		result.put(Constants.HTTP_200_NAME, msg);
		if (data != null)
			result.put(Constants.HTTP_DATA_NAME, data);
		return result;
	}

}
