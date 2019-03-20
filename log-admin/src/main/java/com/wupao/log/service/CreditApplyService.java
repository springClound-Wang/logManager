/**
 * 
 */
package com.wupao.log.service;

import com.wupao.log.pojo.CreditApplyBean;
import com.wupao.log.utils.PageDataResult;

/**
 * @项目名称：lyd-admin
 * @类名称：CreditApplyService
 * @类描述：贷款申请服务类
 * @创建人：汪正章
 * @创建时间：2018年4月3日 上午8:57:53
 * @version
 */
public interface CreditApplyService {

	/**
	 * 
	 * @描述：更新记录（审核）
	 * @创建人：汪正章
	 * @创建时间：2018年4月3日 上午9:07:45
	 * @param cib
	 * @return
	 */
	int updateApplyInfo(CreditApplyBean cib);
	/**
	 * 
	* @Description 方法描述: 查询申请信息 
	* @return  PageDataResult
	* @author 汪正章 
	* @date  2018年4月2日 上午11:40:34
	* @throws
	 */
	PageDataResult selectApplyInfoList(CreditApplyBean cib,int page, int limit,String startDate,String endDate);
	
	/**
	 * 
	* @Description 方法描述: 根据订单编号查询申请信息
	* @return  CreditApplyBean
	* @author 汪正章 
	* @date  2018年4月8日 上午11:01:37
	* @throws
	 */
	CreditApplyBean selectApplyByOrderId(String orderid);
	
	/**
	 * 
	 * @描述：根据订单id更换申请贷款信息的申请人id
	 * @创建人：樊诚
	 * @创建时间：2018年5月18日 下午2:23:42
	 * @param userId
	 * @param orderID
	 * @return
	 */
	int changeUserId(String userId,String orderID);
	
	/**
	 * 
	 * @描述：根据手机号查询用户信息
	 * @创建人：樊诚
	 * @创建时间：2018年5月18日 下午2:42:44
	 * @param mobile
	 * @return
	 */
	//User selectByMobile(String mobile);
	
	/**
	 * 根据orderID查询申请信息
	 */
	int selectByOrderID(String orderID);
    /*
     * @Author 汪正章
     * @Description 添加贷款申请
     * @Date 2018/10/22 15:32
     * @Param [ecb]
     * @**return** boolean
     **/
	boolean addCreditApply(CreditApplyBean ecb);

}
