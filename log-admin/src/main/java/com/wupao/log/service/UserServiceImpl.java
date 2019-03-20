package com.wupao.log.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wupao.log.entity.UserDTO;
import com.wupao.log.entity.UserRoleDTO;
import com.wupao.log.entity.UserRolesVO;
import com.wupao.log.entity.UserSearchDTO;
import com.wupao.log.mapper.AdminUserMapper;
import com.wupao.log.mapper.RoleMapper;
import com.wupao.log.mapper.UserMapper;
import com.wupao.log.mapper.UserRoleMapper;
import com.wupao.log.pojo.AdminUser;
import com.wupao.log.pojo.Role;
import com.wupao.log.pojo.User;
import com.wupao.log.pojo.UserRoleKey;
import com.wupao.log.utils.DateUtil;
import com.wupao.log.utils.PageDataResult;
import com.wupao.sms.tool.LydSendMsg;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @项目名称：lyd-admin
 * @包名：com.lyd.admin.service
 * @类描述：
 * @创建人：wyait
 * @创建时间：2017-12-20 15:53
 * @version：V1.0
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	@Autowired
	private AdminUserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	UserMapper uMapper;

	@Override
	public PageDataResult getUsers(UserSearchDTO userSearch, int page, int limit) {
		// 时间处理
		if (null != userSearch) {
			if (StringUtils.isNotEmpty(userSearch.getInsertTimeStart())
					&& StringUtils.isEmpty(userSearch.getInsertTimeEnd())) {
				userSearch.setInsertTimeEnd(DateUtil.format(new Date()));
			} else if (StringUtils.isEmpty(userSearch.getInsertTimeStart())
					&& StringUtils.isNotEmpty(userSearch.getInsertTimeEnd())) {
				userSearch.setInsertTimeStart(DateUtil.format(new Date()));
			}
			if (StringUtils.isNotEmpty(userSearch.getInsertTimeStart())
					&& StringUtils.isNotEmpty(userSearch.getInsertTimeEnd())) {
				if (userSearch.getInsertTimeEnd().compareTo(
						userSearch.getInsertTimeStart()) < 0) {
					String temp = userSearch.getInsertTimeStart();
					userSearch
							.setInsertTimeStart(userSearch.getInsertTimeEnd());
					userSearch.setInsertTimeEnd(temp);
				}
			}
		}
		PageDataResult pdr = new PageDataResult();
		PageHelper.startPage(page, limit);
		List<UserRoleDTO> urList = userMapper.getUsers(userSearch);
		// 获取分页查询后的数据
		PageInfo<UserRoleDTO> pageInfo = new PageInfo<>(urList);
		// 设置获取到的总记录数total：
		pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
		// 将角色名称提取到对应的字段中
		if (null != urList && urList.size() > 0) {
			for (UserRoleDTO ur : urList) {
				List<Role> roles = roleMapper.getRoleByUserId(ur.getId());
				if (null != roles && roles.size() > 0) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < roles.size(); i++) {
						Role r = roles.get(i);
						sb.append(r.getRoleName());
						if (i != (roles.size() - 1)) {
							sb.append("，");
						}
					}
					ur.setRoleNames(sb.toString());
				}
			}
		}
		pdr.setList(urList);
		return pdr;
	}

	@Override
	public String setDelUser(Integer id, Integer isDel, Integer insertUid,
			Integer version) {
		AdminUser dataUser = this.userMapper.selectByPrimaryKey(id);
		// 版本不一致
		if (null != dataUser
				&& null != dataUser.getVersion()
				&& !String.valueOf(version).equals(
						String.valueOf(dataUser.getVersion()))) {
			return "操作失败，请您稍后再试";
		}
		return this.userMapper.setDelUser(id, isDel, insertUid) == 1 ? "ok"
				: "删除失败，请您稍后再试";
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {
			RuntimeException.class, Exception.class })
	public String setUser(AdminUser user, String roleIds) {
		int userId;
		if (user.getId() != null) {
			// 判断用户是否已经存在
			AdminUser existUser = this.userMapper.findUserByMobile(user
					.getMobile());
			if (null != existUser
					&& !String.valueOf(existUser.getId()).equals(
							String.valueOf(user.getId()))) {
				return "该手机号已经存在";
			}
			AdminUser exist = this.userMapper
					.findUserByName(user.getUsername());
			if (null != exist
					&& !String.valueOf(exist.getId()).equals(
							String.valueOf(user.getId()))) {
				return "该用户名已经存在";
			}
			AdminUser dataUser = this.userMapper.selectByPrimaryKey(user
					.getId());
			// 版本不一致
			if (null != dataUser
					&& null != dataUser.getVersion()
					&& !String.valueOf(user.getVersion()).equals(
							String.valueOf(dataUser.getVersion()))) {
				return "操作失败，请您稍后再试";
			}
			// 更新用户
			userId = user.getId();
			user.setUpdateTime(new Date());
			// 设置加密密码
			if (StringUtils.isNotBlank(user.getPassword())) {
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			}
			this.userMapper.updateByPrimaryKeySelective(user);
			// 删除之前的角色
			List<UserRoleKey> urs = this.userRoleMapper.findByUserId(userId);
			if (null != urs && urs.size() > 0) {
				for (UserRoleKey ur : urs) {
					this.userRoleMapper.deleteByPrimaryKey(ur);
				}
			}
			// 如果是自己，修改完成之后，直接退出；重新登录
			AdminUser adminUser = (AdminUser) SecurityUtils.getSubject()
					.getPrincipal();
			if (adminUser != null
					&& adminUser.getId().intValue() == user.getId().intValue()) {
				logger.debug("更新自己的信息，退出重新登录！adminUser=" + adminUser);
				SecurityUtils.getSubject().logout();
			}
		} else {
			// 判断用户是否已经存在
			AdminUser existUser = this.userMapper.findUserByMobile(user
					.getMobile());
			if (null != existUser) {
				return "该手机号已经存在";
			}
			AdminUser exist = this.userMapper
					.findUserByName(user.getUsername());
			if (null != exist) {
				return "该用户名已经存在";
			}
			// 新增用户
			user.setInsertTime(new Date());
			user.setIsDel(false);
			user.setIsJob(false);
			// 设置加密密码
			if (StringUtils.isNotBlank(user.getPassword())) {
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			} else {
				user.setPassword(DigestUtils.md5Hex("654321"));
			}
			this.userMapper.insert(user);
			userId = user.getId();
		}
		// 给用户授角色
		String[] arrays = roleIds.split(",");
		for (String roleId : arrays) {
			UserRoleKey urk = new UserRoleKey();
			urk.setRoleId(Integer.valueOf(roleId));
			urk.setUserId(userId);
			this.userRoleMapper.insert(urk);
		}
		return "ok";
	}

	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("654321"));
	}

	@Override
	public String setJobUser(Integer id, Integer isJob, Integer insertUid,
			Integer version) {
		AdminUser dataUser = this.userMapper.selectByPrimaryKey(id);
		// 版本不一致
		if (null != dataUser
				&& null != dataUser.getVersion()
				&& !String.valueOf(version).equals(
						String.valueOf(dataUser.getVersion()))) {
			return "操作失败，请您稍后再试";
		}
		return this.userMapper.setJobUser(id, isJob, insertUid) == 1 ? "ok"
				: "删除失败，请您稍后再试";
	}

	@Override
	public UserRolesVO getUserAndRoles(Integer id) {
		// 获取用户及他对应的roleIds
		return this.userMapper.getUserAndRoles(id);
	}

	@Override
	public String sendMsg(UserDTO user) {
		// 校验用户名和密码 是否正确
		AdminUser existUser = this.userMapper.findUser(user.getUsername(),
				DigestUtils.md5Hex(user.getPassword()));
		if (null != existUser && existUser.getMobile().equals(user.getMobile())) {
			String mobileCode = "";
			if (existUser.getSendTime() != null) {
				long beginTime = existUser.getSendTime().getTime();
				long endTime = new Date().getTime();
				// 1分钟以内有效
				if (((endTime - beginTime) < 60000)) {
					logger.debug("发送短信验证码【lyd-admin-->UserServiceImpl.sendMsg】用户信息=existUser:"
							+ existUser);
					mobileCode = existUser.getMcode();
				}
			}
			if (StringUtils.isBlank(mobileCode)) {
				// 1分钟以内，有效
				mobileCode = String
						.valueOf((int) ((Math.random() * 9 + 1) * 100000));
				// 保存短信
				existUser.setMcode(mobileCode);
			}
			// 更新验证码时间，延长至当前时间
			existUser.setSendTime(new Date());
			this.userMapper.updateByPrimaryKeySelective(existUser);
			// 发送短信验证码 ok、no
			/*return LydSendMsgServer.SendMsg(mobileCode + "(验证码)，如不是本人操作，请忽略此消息。",
					user.getMobile());*/
			return LydSendMsg.sendTemplateMsg(user.getMobile(),"lydLoan",mobileCode);
		} else {
			return "您输入的用户信息有误，请您重新输入";
		}
	}

	@Override
	public AdminUser findUserByMobile(String mobile) {
		return this.userMapper.findUserByMobile(mobile);
	}

	@Override
	public String sendMessage(int userId, String mobile) {
		String mobile_code = String
				.valueOf((int) ((Math.random() * 9 + 1) * 100000));
		// 保存短信
		AdminUser user = new AdminUser();
		user.setId(userId);
		user.setMcode(mobile_code);
		user.setSendTime(new Date());
		this.userMapper.updateByPrimaryKeySelective(user);
		// 发送短信验证码 ok、no
		/*return LydSendMsgServer.SendMsg(mobile_code + "(验证码)，如不是本人操作，请忽略此消息。",
				mobile);*/
		return LydSendMsg.sendTemplateMsg(user.getMobile(),"lydLoan",mobile_code);
    }

	@Override
	public int updatePwd(Integer id, String password) {
		return this.userMapper.updatePwd(id, password);
	}

	@Override
	public int setUserLockNum(Integer id, int isLock) {
		return this.userMapper.setUserLockNum(id, isLock);
	}

	@Override
	public PageDataResult selectAllUser(String username, String mobile,
			String insertTimeStart, String insertTimeEnd, Integer status,
			Integer repeat, Integer pageNum, Integer pageSize) {
		PageDataResult pr = new PageDataResult();
		PageHelper.startPage(pageNum, pageSize);
		List<User> users = this.uMapper.selectAllUser(username, mobile,
				insertTimeStart, insertTimeEnd, status, repeat);
		PageInfo<User> info = new PageInfo<>(users);
		pr.setTotals(Long.valueOf(info.getTotal()).intValue());
		pr.setList(users);
		return pr;
	}

	/**
	 * 删除用户需要：根据手机号查询所有用户信息，包括被删除用户
	 */
	@Override
	public List<User> selectByPhone(String mobile) {
		return this.uMapper.selectByPhone(mobile);
	}

	/**
	 * 删除客户信息
	 */
	@Override
	public String updCustomer(Integer status, String userID) {
		int count = this.uMapper.updCustomer(status, userID);
		if (count > 0) {
			return "success";
		}
		return "删除失败！";
	}

	@Override
	public int updcreditApply(String newUserID, String oldUserID) {
		return this.uMapper.updcreditApply(newUserID, oldUserID);
	}

	@Override
	public int updcreditInfo(String newUserID, String oldUserID) {
		return this.uMapper.updcreditInfo(newUserID, oldUserID);
	}

	@Override
	public int updcreditLink(String newUserID, String oldUserID) {
		return this.uMapper.updcreditLink(newUserID, oldUserID);
	}

	@Override
	public int updcreditPic(String newUserID, String oldUserID) {
		return this.uMapper.updcreditPic(newUserID, oldUserID);
	}

	@Override
	public int updshopEstimate(String newUserID, String oldUserID) {
		return this.uMapper.updshopEstimate(newUserID, oldUserID);
	}

	/**
	 * 删除客户需要，查询credit_apply；credit_info；credit_link；credit_pic；shop_estimate是否有数据存在
	 */
	@Override
	public int selectCreditApply(String userID) {
		return this.uMapper.selectCreditApply(userID);
	}

	@Override
	public int selectCreditInfo(String userID) {
		return this.uMapper.selectCreditInfo(userID);
	}

	@Override
	public int selectCreditLink(String userID) {
		return this.uMapper.selectCreditLink(userID);
	}

	@Override
	public int selectCreditPic(String userID) {
		return this.uMapper.selectCreditPic(userID);
	}

	@Override
	public int selectShopEstimate(String userID) {
		return this.uMapper.selectShopEstimate(userID);
	}

	@Override
	public User selectByMobile(String mobile) {
		return this.uMapper.selectByMobile(mobile);
	}

	@Override
	public int updateUser(String uid, String userId, String userphone) {
		return this.uMapper.updateUser(uid, userId, userphone);
	}
}
