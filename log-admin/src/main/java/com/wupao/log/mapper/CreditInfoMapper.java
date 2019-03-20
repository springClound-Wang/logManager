/**
 * 
 */
package com.wupao.log.mapper;

import com.wupao.log.pojo.EcCreditInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @项目名称：lyd-admin
 * @类名称：CreditInfoMapper
 * @类描述：贷款信息接口
 * @创建人：汪正章
 * @创建时间：2018年4月3日 下午7:15:06
 * @version
 */
@Mapper
public interface CreditInfoMapper {

	/**
	 * 
	* @Description 方法描述: 查询所有贷款记录信息 
	* @return  List<EcCreditInfoBean>
	* @author 汪正章 
	* @date  2018年4月3日 下午1:37:41
	* @throws
	 */
	List<EcCreditInfoBean>  selectCreditInfoList(@Param("cib") EcCreditInfoBean cib, @Param("startDate") String startDate,@Param("endDate") String endDate);
	/**
	 * 
	* @Description 方法描述: 获取市区城市 
	* @return  List<Map<String,Object>>
	* @author 汪正章 
	* @date  2018年4月3日 下午6:48:27
	* @throws
	 */
	Map<String, Object> getProCityArea(@Param("tbName")String tbName,@Param("upName")String upName,@Param("id")int id);
	/**
	 * 
	* @Description 方法描述: 根据订单号和用户编号查询贷款详细资料 
	* @return  List<Map<String,Object>>
	* @author 汪正章 
	* @date  2018年4月3日 下午10:53:08
	* @throws
	 */
	Map<String, Object> selectCreditDetailInfo(@Param("rid") String rid,@Param("uid") String uid);
	
	int updateCreditInfo(@Param("status")Integer status,@Param("mark")String mark,@Param("updateTime")String updateTime,@Param("orderID")String orderID);
	/**
	 * 
	 * @描述：绑定贷款信息用户id
	 * @创建人：wyait
	 * @创建时间：2018年6月4日 下午3:25:28
	 * @param userId
	 * @param orderID
	 * @return
	 */
	int bindingUserId(@Param("userId")String userId, @Param("orderID")String orderID);
	
	/**
	 * 
	 * @描述：修改渠道商
	 * @创建人：樊诚
	 * @创建时间：2018年6月5日 上午11:24:36
	 * @param bankid
	 * @param orderID
	 * @return
	 */
	int updateQuDao(@Param("bankid")Integer bankid,@Param("orderID")String orderID);

	/*
	 * @Author 汪正章
	 * @Description 根据订单编号查询信息是否存在
	 * @Date 2018/10/22 14:05
	 * @Param String orderId
	 * @**return** Integer
	 **/
	Integer selectCountInfoByOrderId(@Param("orderId") String orderId);
}
