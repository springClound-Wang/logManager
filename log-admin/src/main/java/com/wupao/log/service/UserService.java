package com.wupao.log.service;

import java.util.List;

import com.wupao.log.entity.UserDTO;
import com.wupao.log.entity.UserRolesVO;
import com.wupao.log.entity.UserSearchDTO;
import com.wupao.log.pojo.AdminUser;
import com.wupao.log.pojo.User;
import com.wupao.log.utils.PageDataResult;

/**
 * @项目名称：lyd-admin
 * @包名：com.lyd.admin.service
 * @类描述：
 * @创建人：wyait
 * @创建时间：2017-12-20 15:52
 * @version：V1.0
 */
public interface UserService {
	/**
	 * 分页查询用户列表
	 * @param page
	 * @param limit
	 * @return
	 */
	PageDataResult getUsers(UserSearchDTO userSearch, int page, int limit);

	/**
	 *	设置用户【新增或更新】
	 * @param user
	 * @param roleIds
	 * @return
	 */
	String setUser(AdminUser user, String roleIds);

	/**
	 * 设置用户是否离职
	 * @param id
	 * @param isJob
	 * @return
	 */
	String setJobUser(Integer id, Integer isJob, Integer insertUid,
			Integer version);

	/**
	 * 删除用户
	 * @param id
	 * @param isDel
	 * @return
	 */
	String setDelUser(Integer id, Integer isDel, Integer insertUid,
			Integer version);

	/**
	 * 查询用户数据
	 * @param id
	 * @return
	 */
	UserRolesVO getUserAndRoles(Integer id);

	/**
	 * 发送短信验证码
	 * @param user
	 * @return
	 */
	String sendMsg(UserDTO user);

	/**
	 * 根据手机号查询用户数据
	 * @param mobile
	 * @return
	 */
	AdminUser findUserByMobile(String mobile);

	/**
	 * 根据手机号发送短信验证码
	 * @param mobile
	 * @return
	 */
	String sendMessage(int userId, String mobile);

	/**
	 * 修改用户手机号
	 * @param id
	 * @param password
	 * @return
	 */
	int updatePwd(Integer id, String password);

	/**
	 * 锁定用户
	 * @param id
	 * @param isLock  0:解锁；1：锁定
	 * @return
	 */
	int setUserLockNum(Integer id, int isLock);

	/**
	 * 
	 * @描述：查询所有用户信息
	 * @创建人：樊诚
	 * @创建时间：2018年5月28日 下午4:34:53
	 * @return
	 */
	PageDataResult selectAllUser(String username, String mobile,
			String insertTimeStart, String insertTimeEnd, Integer status,
			Integer repeat, Integer pageNum, Integer pageSize);

	/**
	 * 
	 * @描述：删除客户需要，查询credit_apply；credit_info；credit_link；credit_pic；shop_estimate是否有数据存在
	 * @创建人：樊诚
	 * @创建时间：2018年5月29日 下午4:08:05
	 * @param userID
	 * @return
	 */
	int selectCreditApply(String userID);

	int selectCreditInfo(String userID);

	int selectCreditLink(String userID);

	int selectCreditPic(String userID);

	int selectShopEstimate(String userID);

	/**
	 * 
	 * @描述：删除用户需要：根据手机号查询所有用户信息，包括被删除用户
	 * @创建人：樊诚
	 * @创建时间：2018年5月29日 上午9:45:39
	 * @param mobile
	 * @return
	 */
	List<User> selectByPhone(String mobile);

	/**
	 * 
	 * @描述：删除客户信息
	 * @创建人：樊诚
	 * @创建时间：2018年5月29日 上午10:11:54
	 * @param userID
	 * @return
	 */
	String updCustomer(Integer status, String userID);

	int updcreditApply(String newUserID, String oldUserID);

	int updcreditInfo(String newUserID, String oldUserID);

	int updcreditLink(String newUserID, String oldUserID);

	int updcreditPic(String newUserID, String oldUserID);

	int updshopEstimate(String newUserID, String oldUserID);

	/**
	 * 
	 * @描述：根据手机号获取用户数据
	 * @创建人：wyait
	 * @创建时间：2018年6月4日 下午2:13:51
	 * @param mobile
	 * @return
	 */
	User selectByMobile(String mobile);

	/**
	 * 
	 * @描述：更新用户部分信息（用户编号、手机号）
	 * @创建人：wyait
	 * @创建时间：2018年6月4日 下午6:09:32
	 * @param uid
	 * @param userId
	 * @param userphone
	 * @return
	 */
	int updateUser(String uid, String userId, String userphone);
}
