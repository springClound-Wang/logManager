/**
 * 
 */
package com.wupao.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wupao.log.pojo.User;

/**
 * 
 * @项目名称：lyd-admin
 * @类名称：UserMapper
 * @类描述：
 * @创建人：樊诚
 * @创建时间：2018年5月18日 下午2:34:57 
 * @version：
 */
@Mapper
public interface UserMapper {

	/**
	 * 
	 * @描述：根据手机号查询用户
	 * @创建人：樊诚
	 * @创建时间：2018年5月28日 下午4:33:06
	 * @param mobile
	 * @return
	 */
	User selectByMobile(@Param("mobile")String mobile);
	
	/**
	 * 
	 * @描述：查询所有用户信息
	 * @创建人：樊诚
	 * @创建时间：2018年5月28日 下午4:32:56
	 * @return
	 */
	List<User> selectAllUser(@Param("username") String username,
			@Param("mobile") String mobile,
			@Param("insertTimeStart") String insertTimeStart,
			@Param("insertTimeEnd") String insertTimeEnd,
			@Param("status")Integer status,@Param("repeat")Integer repeat);
	
	/**
	 * 
	 * @描述：删除客户需要，查询credit_apply；credit_info；credit_link；credit_pic；shop_estimate是否有数据存在
	 * @创建人：樊诚
	 * @创建时间：2018年5月29日 下午4:06:18
	 * @param userID
	 * @return
	 */
	int selectCreditApply(@Param("userID")String userID);
	int selectCreditInfo(@Param("userID")String userID);
	int selectCreditLink(@Param("userID")String userID);
	int selectCreditPic(@Param("userID")String userID);
	int selectShopEstimate(@Param("userID")String userID);
	
	/**
	 * 
	 * @描述：删除用户需要：根据手机号查询所有用户信息，包括被删除用户
	 * @创建人：樊诚
	 * @创建时间：2018年5月29日 上午9:42:33
	 * @param mobile
	 * @return
	 */
	List<User> selectByPhone(@Param("mobile")String mobile);
	
	/**
	 * 
	 * @描述：删除客户信息
	 * @创建人：樊诚
	 * @创建时间：2018年5月29日 上午10:11:15
	 * @param status
	 * @param userID
	 * @return
	 */
	int updCustomer(@Param("status")Integer status,@Param("userID")String userID);
	
	/**
	 * 
	 * @描述：删除客户需要，对credit_apply；credit_info；credit_link；credit_pic；shop_estimate进行修改
	 * @创建人：樊诚
	 * @创建时间：2018年5月29日 下午4:07:09
	 * @param newUserID
	 * @param oldUserID
	 * @return
	 */
	int updcreditApply(@Param("newUserID")String newUserID,@Param("oldUserID")String oldUserID);
	int updcreditInfo(@Param("newUserID")String newUserID,@Param("oldUserID")String oldUserID);
	int updcreditLink(@Param("newUserID")String newUserID,@Param("oldUserID")String oldUserID);
	int updcreditPic(@Param("newUserID")String newUserID,@Param("oldUserID")String oldUserID);
	int updshopEstimate(@Param("newUserID")String newUserID,@Param("oldUserID")String oldUserID);
	/**
	 * 
	 * @描述：更新用户部分信息
	 * @创建人：wyait
	 * @创建时间：2018年6月4日 下午6:10:28
	 * @param uid
	 * @param userId
	 * @param userphone
	 * @return
	 */
	int updateUser(@Param("uid")String uid, @Param("userId")String userId, @Param("userphone")String userphone);
	
}
