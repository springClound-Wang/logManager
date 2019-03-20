/**
 * 
 */
package com.wupao.log.mapper;

import com.wupao.log.pojo.CreditApplyBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @项目名称：lyd-admin
 * @类名称：CreditApplyMapper
 * @类描述：
 * @创建人：汪正章
 * @创建时间：2018年3月30日 下午19:15:10
 * @version
 */
@Mapper
public interface CreditApplyMapper {
	
	
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
	* @return  List<CreditApplyBean>
	* @author 汪正章 
	* @date  2018年4月2日 上午11:40:56
	* @throws
	 */
	List<CreditApplyBean> selectApplyList(@Param("cib")CreditApplyBean cib,@Param("startDate")String startDate,@Param("endDate")String endDate,@Param("check")int check);
	/**
	 * 
	* @Description 方法描述:查询单个申请信息 
	* @return  CreditApplyBean
	* @author 汪正章 
	* @date  2018年4月8日 上午11:15:45
	* @throws
	 */
	CreditApplyBean selectApplyByOrderId(@Param("orderid")String orderid);
	
	/**
	 * 
	 * @描述：根据订单id更换申请贷款信息的申请人id
	 * @创建人：樊诚
	 * @创建时间：2018年5月18日 下午2:22:22
	 * @param userId
	 * @param orderID
	 * @return
	 */
	int changeUserId(@Param("userId")String userId,@Param("orderID")String orderID,@Param("time")String time);
	
	/**
	 * 
	 * @描述：根据orderID查询申请信息
	 * @创建人：樊诚
	 * @创建时间：2018年5月18日 下午3:35:17
	 * @param orderID
	 * @return
	 */
	int selectByOrderID(@Param("orderID")String orderID);
    /*
     * @Author 汪正章
     * @Description 添加申请信息
     * @Date 2018/10/22 15:30
     * @Param
     * @**return**
     **/
    int addCreditApply(CreditApplyBean ecb);
}
