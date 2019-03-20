/**
 * 
 */
package com.wupao.log.service;

import com.wupao.log.pojo.EcCreditInfoBean;
import com.wupao.log.utils.PageDataResult;

import java.util.Map;

/**
 * @项目名称：lyd-admin
 * @类名称：CreditInfoService
 * @类描述：
 * @创建人：汪正章
 * @创建时间：2018年4月3日 下午7:11:16
 * @version
 */
public interface CreditInfoService {

	/**
	 * 
	* @Description 方法描述: 查新所有贷款记录信息 
	* @return  PageDataResult
	* @author 汪正章 
	* @date  2018年4月3日 下午1:33:24
	* @throws
	 */
	PageDataResult selectCreditInfoList(EcCreditInfoBean cib, String startDate,
			String endDate, int page, int limit);

	/**
	 * 
	* @Description 方法描述: 查询城市
	* @return  List<Map<String,Object>>
	* @author 汪正章 
	* @date  2018年4月3日 下午6:44:58
	* @throws
	 */
	Map<String, Object> getProCityArea(int type, int id);

	/**
	 * 
	* @Description 方法描述: 查询贷款详情 
	* @return  List<Map<String,Object>>
	* @author 汪正章 
	* @date  2018年4月3日 下午10:55:00
	* @throws
	 */
	Map<String, Object> selectCreditDetailInfo(String orderid, String userid);

	/**
	 * 
	 * @描述：审批
	 * @创建人：樊诚
	 * @创建时间：2018年5月21日 下午4:52:11
	 * @param status
	 * @param mark
	 * @param orderID
	 * @return
	 */
	String updateCreditInfo(Integer status, String mark, String orderID);

	/**
	 * 
	 * @描述：绑定用户id
	 * @创建人：wyait
	 * @创建时间：2018年6月4日 下午3:14:04
	 * @param userID
	 * @param orid
	 * @return
	 */
	int bindingUserId(String userID, String orid);
	
	/**
	 * 
	 * @描述：修改渠道商
	 * @创建人：樊诚
	 * @创建时间：2018年6月5日 上午11:37:54
	 * @param bankid
	 * @param orderID
	 * @return
	 */
	int updateQuDao(Integer bankid,String orderID);

	/*
	 * @Author 汪正章
	 * @Description 根据订单编号查询信息是否存在
	 * @Date 2018/10/22 14:05
	 * @Param String orderId
	 * @**return** Integer
	 **/
	Integer selectCountInfoByOrderId(String orderId);
}
