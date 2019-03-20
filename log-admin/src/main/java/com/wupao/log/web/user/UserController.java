package com.wupao.log.web.user;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wupao.log.entity.ResponseResult;
import com.wupao.log.entity.UserDTO;
import com.wupao.log.entity.UserRolesVO;
import com.wupao.log.entity.UserSearchDTO;
import com.wupao.log.pojo.AdminUser;
import com.wupao.log.pojo.Role;
import com.wupao.log.pojo.User;
import com.wupao.log.service.AuthService;
import com.wupao.log.service.UserService;
import com.wupao.log.utils.Constants;
import com.wupao.log.utils.DateUtil;
import com.wupao.log.utils.IStatusMessage;
import com.wupao.log.utils.PageDataResult;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wupao.tools.utils.ValidateUtil;

/**
 * @项目名称：wyait-manage
 * @包名：com.lyd.channel.controller.user
 * @类描述：
 * @创建人：wyait
 * @创建时间：2017-12-31 14:22
 * @version：V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;
	@Autowired
	private EhCacheManager ecm;

	@RequestMapping("/userList")
	public String toUserList() {
		return "/auth/userList";
	}

	/**
	 * 分页查询用户列表
	 * @return ok/fail
	 */
	@RequestMapping(value = "/getUsers", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = "usermanage")
	public PageDataResult getUsers(@RequestParam("page") Integer page,
                                   @RequestParam("limit") Integer limit, UserSearchDTO userSearch,
                                   HttpServletRequest request) {
		logger.debug("分页查询用户列表！搜索条件：userSearch：" + userSearch + ",page:" + page
				+ ",每页记录数量limit:" + limit + "，请求编码："
				+ request.getCharacterEncoding());
		PageDataResult pdr = new PageDataResult();
		try {
			// 设置请求编码
			// request.setCharacterEncoding("UTF-8");
			if (null == page) {
				page = 1;
			}
			if (null == limit) {
				limit = 10;
			}
			// 获取用户和角色列表
			pdr = userService.getUsers(userSearch, page, limit);
			logger.debug("用户列表查询=pdr:" + pdr);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户列表查询异常！", e);
		}
		return pdr;
	}

	/**
	 * 设置用户是否离职
	 * @return ok/fail
	 */
	@RequestMapping(value = "/setJobUser", method = RequestMethod.POST)
	@ResponseBody
	public String setJobUser(@RequestParam("id") Integer id,
			@RequestParam("job") Integer isJob,
			@RequestParam("version") Integer version) {
		logger.debug("设置用户是否离职！id:" + id + ",isJob:" + isJob + ",version:"
				+ version);
		String msg = "";
		try {
			AdminUser existUser = (AdminUser) SecurityUtils.getSubject()
					.getPrincipal();
			if (null == existUser) {
				logger.debug("设置用户是否离职【lyd-admin.UserController.setJobUser】===未登录或登录超时！");
				return "您未登录或登录超时，请您登录后再试";
			}
			if (null == id || null == isJob || null == version) {
				logger.debug("设置用户是否离职【lyd-admin.UserController.setJobUser】===参数有误！id:" + id + ",isJob:" + isJob + 
						",version:"+ version);
				return "请求参数有误，请您稍后再试";
			}
			// System.out.println(1/0);
			// 设置用户是否离职
			msg = userService.setJobUser(id, isJob, existUser.getId(), version);
			logger.debug("设置用户是否离职成功！userID=" + id + ",isJob:" + isJob
					+ "，操作的用户ID=" + existUser.getId() + ",version:" + version);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("设置用户是否离职异常！", e);
			msg = "操作异常，请您稍后再试";
		}
		return msg;
	}

	/**
	 * 设置用户[新增或更新]
	 * @return ok/fail
	 */
	@RequestMapping(value = "/setUser", method = RequestMethod.POST)
	@ResponseBody
	public String setUser(@RequestParam("roleIds") String roleIds,
			AdminUser user) {
		logger.debug("设置用户[新增或更新]！用户user:" + user + ",roleIds:" + roleIds);
		try {
			AdminUser existUser = (AdminUser) SecurityUtils.getSubject()
					.getPrincipal();
			if (null == existUser) {
				logger.debug("设置用户[新增或更新]【lyd-admin.UserController.setUser】===未登录或登录超时！");
				return "您未登录或登录超时，请您登录后再试";
			}
			if (null == user || StringUtils.isEmpty(roleIds)) {
				logger.debug("设置用户[新增或更新]【lyd-admin.UserController.setUser】===参数有误！用户user:" + user + ",roleIds:" + roleIds);
				return "请求参数有误，请您稍后再试";
			}
			// 设置用户[新增或更新]
			user.setInsertUid(existUser.getId());
			logger.debug("设置用户[新增或更新]操作！用户user=" + user + ",roleIds=" + roleIds
					+ "，操作的用户ID=" + existUser.getId());
			return userService.setUser(user, roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("设置用户[新增或更新]异常！", e);
			return "操作异常，请您稍后再试";
		}
	}

	/**
	 * 删除用户
	 * @return ok/fail
	 */
	@RequestMapping(value = "/delUser", method = RequestMethod.POST)
	@ResponseBody
	public String delUser(@RequestParam("id") Integer id,
			@RequestParam("version") Integer version) {
		logger.debug("删除用户！id:" + id + ",version:" + version);
		String msg = "";
		try {
			AdminUser existUser = (AdminUser) SecurityUtils.getSubject()
					.getPrincipal();
			if (null == existUser) {
				logger.debug("删除用户【lyd-admin.UserController.delUser】===未登录或登录超时！");
				return "您未登录或登录超时，请您登录后再试";
			}
			if (null == id || null == version) {
				logger.debug("删除用户【lyd-admin.UserController.delUser】===参数有误！id:" + id + ",version:" + version);
				return "请求参数有误，请您稍后再试";
			}
			// 删除用户
			msg = userService.setDelUser(id, 1, existUser.getId(), version);
			logger.debug("删除用户" + msg + "！用户userId=" + id + "，操作的用户ID="
					+ existUser.getId() + ",version:" + version);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户异常！", e);
			msg = "操作异常，请您稍后再试";
		}
		return msg;
	}

	/**
	 * 
	 * @描述：恢复用户
	 * @创建人：wyait
	 * @创建时间：2018年4月27日 上午9:49:14
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/recoverUser", method = RequestMethod.POST)
	@ResponseBody
	public String recoverUser(@RequestParam("id") Integer id,
			@RequestParam("version") Integer version) {
		logger.debug("恢复用户！id:" + id + ",version:" + version);
		String msg = "";
		try {
			AdminUser existUser = (AdminUser) SecurityUtils.getSubject()
					.getPrincipal();
			if (null == existUser) {
				logger.debug("恢复用户【lyd-admin.UserController.recoverUser】===未登录或登录超时！");
				return "您未登录或登录超时，请您登录后再试";
			}
			if (null == id || null == version) {
				logger.debug("恢复用户【lyd-admin.UserController.recoverUser】===参数有误！id:" + id + ",version:" + version);
				return "请求参数有误，请您稍后再试";
			}
			// 删除用户
			msg = userService.setDelUser(id, 0, existUser.getId(), version);
			logger.debug("恢复用户【" + this.getClass().getName()
					+ ".recoverUser】成功！用户userId=" + id + "，操作的用户ID="
					+ existUser.getId() + ",version:" + version);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("恢复用户【" + this.getClass().getName()
					+ ".recoverUser】用户异常！", e);
			msg = "操作异常，请您稍后再试";
		}
		return msg;
	}

	/**
	 * 查询用户数据
	 * @return map
	 */
	@RequestMapping(value = "/getUserAndRoles", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserAndRoles(@RequestParam("id") Integer id,
			HttpServletRequest request) {
		logger.debug("查询用户数据！id:" + id);
		Map<String, Object> map = new HashMap<>();
		logger.debug("session设置的有效时间："
				+ request.getSession().getMaxInactiveInterval());
		logger.debug("shiro中session设置的有效时间："
				+ SecurityUtils.getSubject().getSession().getTimeout());
		try {
			if (null == id) {
				logger.debug("查询用户数据==请求参数有误，请您稍后再试");
				map.put("msg", "请求参数有误，请您稍后再试");
				return map;
			}
			// 查询用户
			UserRolesVO urvo = userService.getUserAndRoles(id);
			logger.debug("查询用户数据！urvo=" + urvo);
			if (null != urvo) {
				map.put("user", urvo);
				// 获取全部角色数据
				List<Role> roles = this.authService.getRoles();
				logger.debug("查询角色数据！roles=" + roles);
				if (null != roles && roles.size() > 0) {
					map.put("roles", roles);
				}
				map.put("msg", "ok");
			} else {
				map.put("msg", "查询用户信息有误，请您稍后再试");
			}
			logger.debug("查询用户数据成功！map=" + map);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "查询用户错误，请您稍后再试");
			logger.error("查询用户数据异常！", e);
		}
		return map;
	}

	/**
	 * 发送短信验证码
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "sendMsg", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult sendMsg(UserDTO user) {
		logger.debug("登录功能【lyd-admin->sendMsg】，发送短信验证码！user:" + user);
		ResponseResult responseResult = new ResponseResult();
		try {
			if (null == user) {
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("请求参数有误，请您稍后再试");
				logger.debug("发送短信验证码，结果=responseResult:" + responseResult);
				return responseResult;
			}
			if (!validatorRequestParam(user, responseResult)) {
				logger.debug("发送短信验证码，结果=responseResult:" + responseResult);
				return responseResult;
			}
			// 送短信验证码
			String msg = userService.sendMsg(user);
			// String msg="ok";
			if (msg != "ok") {
				responseResult.setCode(IStatusMessage.SystemStatus.ERROR
						.getCode());
				responseResult.setMessage(msg == "no" ? "发送验证码失败，请您稍后再试" : msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
			responseResult.setMessage("发送短信验证码失败，请您稍后再试");
			logger.error("发送短信验证码异常！", e);
		}
		logger.debug("发送短信验证码，结果=responseResult:" + responseResult);
		return responseResult;
	}

	/**
	 * 登录【使用shiro中自带的HashedCredentialsMatcher结合ehcache（记录输错次数）配置进行密码输错次数限制】
	 * </br>缺陷是，无法友好的在后台提供解锁用户的功能，当然，可以直接提供一种解锁操作，清除ehcache缓存即可，不记录在用户表中；
	 * </br>
	 * @param user
	 * @param rememberMe
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult login(
			UserDTO user,
			@RequestParam(value = "rememberMe", required = false) boolean rememberMe) {
		logger.debug("用户登录，请求参数=user:" + user + "，是否记住我：" + rememberMe);
		ResponseResult responseResult = new ResponseResult();
		responseResult.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
		if (null == user) {
			responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
					.getCode());
			responseResult.setMessage("请求参数有误，请您稍后再试");
			logger.debug("用户登录，结果=responseResult:" + responseResult);
			return responseResult;
		}
		if (!validatorRequestParam(user, responseResult)) {
			logger.debug("用户登录，结果=responseResult:" + responseResult);
			return responseResult;
		}
		// 用户是否存在
		AdminUser existUser = this.userService.findUserByMobile(user
				.getMobile());
		if (existUser == null) {
			responseResult.setMessage("该用户不存在，请您联系管理员");
			logger.debug("用户登录，结果=responseResult:" + responseResult);
			return responseResult;
		} else {
			// 是否离职
			if (existUser.getIsJob() || existUser.getIsDel()) {
				responseResult.setMessage("登录用户已离职，请您联系管理员");
				logger.debug("用户登录，结果=responseResult:" + responseResult);
				return responseResult;
			}
			// 校验验证码
			if (!existUser.getMcode().equals(user.getSmsCode())) {
				// 不等
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("短信验证码输入有误");
				logger.debug("用户登录，结果=responseResult:" + responseResult);
				return responseResult;
			}
			// 1分钟
			long beginTime = existUser.getSendTime().getTime();
			long endTime = new Date().getTime();
			if (((endTime - beginTime) - 60000 > 0)) {
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("短信验证码超时");
				logger.debug("用户登录，结果=responseResult:" + responseResult);
				return responseResult;
			}
		}
		// 用户登录
		try {
			// 1、 封装用户名、密码、是否记住我到token令牌对象 [支持记住我]
			AuthenticationToken token = new UsernamePasswordToken(
					user.getMobile(), DigestUtils.md5Hex(user.getPassword()),
					rememberMe);
			// 2、 Subject调用login
			Subject subject = SecurityUtils.getSubject();
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.debug("用户登录，用户验证开始！user=" + user.getMobile());
			subject.login(token);
			responseResult.setCode(IStatusMessage.SystemStatus.SUCCESS
					.getCode());
			// session有效时间1天（毫秒）*3
			SecurityUtils.getSubject().getSession().setTimeout(86400000 * 3);
			// SecurityUtils.getSubject().getSession().setTimeout(300000);
			logger.debug("登录后session设置的有效时间："
					+ SecurityUtils.getSubject().getSession().getTimeout());
			logger.debug("用户登录，用户验证通过！user=" + user.getMobile());
		} catch (UnknownAccountException uae) {
			logger.error("用户登录，用户验证未通过：未知用户！user=" + user.getMobile(), uae);
			responseResult.setMessage("该用户不存在，请您联系管理员");
		}/*
		 * catch(IncorrectCredentialsException ice){ //获取输错次数
		 * logger.error("用户登录，用户验证未通过：错误的凭证，密码输入错误！user=" +
		 * user.getMobile(),ice); responseResult.setMessage("用户名或密码不正确");
		 * }catch(LockedAccountException lae){
		 * logger.error("用户登录，用户验证未通过：账户已锁定！user=" + user.getMobile(),lae);
		 * responseResult.setMessage("账户已锁定"); }catch(ExcessiveAttemptsException
		 * eae){ logger.error("用户登录，用户验证未通过：错误次数大于5次,账户已锁定！user=.getMobile()" +
		 * user,eae); responseResult.setMessage(
		 * "用户名或密码错误次数大于5次,账户已锁定!</br><span style='color:red;font-weight:bold; '>2分钟后可再次登录，或联系管理员解锁</span>"
		 * ); //这里结合了，另一种密码输错限制的实现，基于redis或mysql的实现；
		 * 也可以直接使用RetryLimitHashedCredentialsMatcher限制5次 }
		 */catch (DisabledAccountException sae) {
			logger.error("用户登录，用户验证未通过：帐号已经禁止登录！user=" + user.getMobile(), sae);
			responseResult.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
			responseResult.setMessage("帐号已经禁止登录");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.error("用户登录，用户验证未通过：认证异常，异常信息如下！user=" + user.getMobile(),
					ae);
			responseResult.setMessage("用户名或密码不正确");
		} catch (Exception e) {
			logger.error("用户登录，用户验证未通过：操作异常，异常信息如下！user=" + user.getMobile(), e);
			responseResult.setMessage("用户登录失败，请您稍后再试");
		}
		Cache<String, AtomicInteger> passwordRetryCache = ecm
				.getCache("passwordRetryCache");
		if (null != passwordRetryCache) {
			int retryNum = (passwordRetryCache.get(existUser.getMobile()) == null ? 0
					: passwordRetryCache.get(existUser.getMobile())).intValue();
			logger.debug("输错次数：" + retryNum);
			if (retryNum > 0 && retryNum < 6) {
				responseResult.setMessage("用户名或密码错误" + retryNum + "次,再输错"
						+ (6 - retryNum) + "次账号将锁定");
			}
		}
		logger.debug("用户登录，user=" + user.getMobile() + ",登录结果=responseResult:"
				+ responseResult);
		return responseResult;
	}

	/**
	 * 登录【使用redis和mysql实现，用户密码输错次数限制，和锁定解锁用户的功能//TODO】
	 * 该实现后续会提供！TODO
	 * @param user
	 * @param rememberMe
	 * @return
	 */
	@RequestMapping(value = "logina", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult logina(
			UserDTO user,
			@RequestParam(value = "rememberMe", required = false) boolean rememberMe) {
		logger.debug("用户登录，请求参数=user:" + user + "，是否记住我：" + rememberMe);
		ResponseResult responseResult = new ResponseResult();
		responseResult.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
		if (null == user) {
			responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
					.getCode());
			responseResult.setMessage("请求参数有误，请您稍后再试");
			logger.debug("用户登录，结果=responseResult:" + responseResult);
			return responseResult;
		}
		if (!validatorRequestParam(user, responseResult)) {
			logger.debug("用户登录，结果=responseResult:" + responseResult);
			return responseResult;
		}
		// 用户是否存在
		AdminUser existUser = this.userService.findUserByMobile(user
				.getMobile());
		if (existUser == null) {
			responseResult.setMessage("该用户不存在，请您联系管理员");
			logger.debug("用户登录，结果=responseResult:" + responseResult);
			return responseResult;
		} else {
			// 校验验证码
			if (!existUser.getMcode().equals(user.getSmsCode())) {
				// 不等
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("短信验证码输入有误");
				logger.debug("用户登录，结果=responseResult:" + responseResult);
				return responseResult;
			}
			// 1分钟
			long beginTime = existUser.getSendTime().getTime();
			long endTime = new Date().getTime();
			if (((endTime - beginTime) - 60000 > 0)) {
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("短信验证码超时");
				logger.debug("用户登录，结果=responseResult:" + responseResult);
				return responseResult;
			}
		}
		// 是否锁定
		boolean flag = false;
		// 用户登录
		try {
			// 1、 封装用户名和密码到token令牌对象 [支持记住我]
			AuthenticationToken token = new UsernamePasswordToken(
					user.getMobile(), DigestUtils.md5Hex(user.getPassword()),
					rememberMe);
			// 2、 Subject调用login
			Subject subject = SecurityUtils.getSubject();
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.debug("用户登录，用户验证开始！user=" + user.getMobile());
			subject.login(token);
			responseResult.setCode(IStatusMessage.SystemStatus.SUCCESS
					.getCode());
			logger.debug("用户登录，用户验证通过！user=" + user.getMobile());
		} catch (UnknownAccountException uae) {
			logger.error("用户登录，用户验证未通过：未知用户！user=" + user.getMobile(), uae);
			responseResult.setMessage("该用户不存在，请您联系管理员");
		} catch (IncorrectCredentialsException ice) {
			// 获取输错次数
			logger.error("用户登录，用户验证未通过：错误的凭证，密码输入错误！user=" + user.getMobile(),
					ice);
			responseResult.setMessage("用户名或密码不正确");
		} catch (LockedAccountException lae) {
			logger.error("用户登录，用户验证未通过：账户已锁定！user=" + user.getMobile(), lae);
			responseResult.setMessage("账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			logger.error(
					"用户登录，用户验证未通过：错误次数大于5次,账户已锁定！user=.getMobile()" + user, eae);
			responseResult.setMessage("用户名或密码错误次数大于5次,账户已锁定，2分钟后可再次登录或联系管理员解锁");
			// 这里结合了，另一种密码输错限制的实现，基于redis或mysql的实现；也可以直接使用RetryLimitHashedCredentialsMatcher限制5次
			flag = true;
		} catch (DisabledAccountException sae) {
			logger.error("用户登录，用户验证未通过：帐号已经禁止登录！user=" + user.getMobile(), sae);
			responseResult.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
			responseResult.setMessage("帐号已经禁止登录");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.error("用户登录，用户验证未通过：认证异常，异常信息如下！user=" + user.getMobile(),
					ae);
			responseResult.setMessage("用户名或密码不正确");
		} catch (Exception e) {
			logger.error("用户登录，用户验证未通过：操作异常，异常信息如下！user=" + user.getMobile(), e);
			responseResult.setMessage("用户登录失败，请您稍后再试");
		}
		if (flag) {
			// 已经输错6次了，将进行锁定！【也可以使用redis记录密码输错次数，然后进行锁定//TODO】
			int num = this.userService.setUserLockNum(existUser.getId(), 1);
			if (num < 1) {
				logger.debug("用户登录，用户名或密码错误次数大于5次,账户锁定失败！user="
						+ user.getMobile());
			}
		}
		logger.debug("用户登录，user=" + user.getMobile() + ",登录结果=responseResult:"
				+ responseResult);
		return responseResult;
	}

	/**
	 * 发送短信验证码
	 * @param mobile
	 * @param picCode
	 * @return
	 */
	@RequestMapping(value = "sendMessage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult sendMessage(@RequestParam("mobile") String mobile,
			@RequestParam("picCode") String picCode) {
		logger.debug("发送短信验证码！mobile:" + mobile + ",picCode=" + picCode);
		ResponseResult responseResult = new ResponseResult();
		try {
			if (!ValidateUtil.isMobile(mobile)) {
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("手机号格式有误，请您重新填写");
				logger.debug("发送短信验证码，结果=responseResult:" + responseResult);
				return responseResult;
			}
			if (!ValidateUtil.isPicCode(picCode)) {
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("图片验证码有误，请您重新填写");
				logger.debug("发送短信验证码，结果=responseResult:" + responseResult);
				return responseResult;
			}
			// 判断用户是否登录
			AdminUser existUser = (AdminUser) SecurityUtils.getSubject()
					.getPrincipal();
			if (null == existUser) {
				responseResult.setCode(IStatusMessage.SystemStatus.NO_LOGIN
						.getCode());
				responseResult.setMessage("您未登录或登录超时，请您重新登录后再试");
				logger.debug("发送短信验证码，结果=responseResult:" + responseResult);
				return responseResult;
			}
			if (!existUser.getMobile().equals(mobile)) {
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("手机号与登录账户不一致，请您重新填写");
				logger.debug("发送短信验证码，结果=responseResult:" + responseResult);
				return responseResult;
			}
			// 送短信验证码
			String msg = userService.sendMessage(existUser.getId(), mobile);
			// String msg="ok";
			if (msg != "ok") {
				responseResult.setCode(IStatusMessage.SystemStatus.ERROR
						.getCode());
				responseResult.setMessage(msg == "no" ? "发送验证码失败，请您稍后再试" : msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
			responseResult.setMessage("发送短信验证码失败，请您稍后再试");
			logger.error("发送短信验证码异常！", e);
		}
		logger.debug("发送短信验证码，结果=responseResult:" + responseResult);
		return responseResult;
	}

	/**
	 * 修改密码之确认手机号
	 * @param mobile
	 * @param picCode
	 * @return
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult updatePwd(@RequestParam("mobile") String mobile,
			@RequestParam("picCode") String picCode,
			@RequestParam("mobileCode") String mobileCode) {
		logger.debug("修改密码之确认手机号！mobile:" + mobile + ",picCode=" + picCode
				+ ",mobileCode=" + mobileCode);
		ResponseResult responseResult = new ResponseResult();
		responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
				.getCode());
		try {
			if (!ValidateUtil.isMobile(mobile)) {
				responseResult.setMessage("手机号格式有误，请您重新填写");
				logger.debug("修改密码之确认手机号，结果=responseResult:" + responseResult);
				return responseResult;
			}
			if (!ValidateUtil.isPicCode(picCode)) {
				responseResult.setMessage("图片验证码有误，请您重新填写");
				logger.debug("发修改密码之确认手机号，结果=responseResult:" + responseResult);
				return responseResult;
			}
			if (!ValidateUtil.isCode(mobileCode)) {
				responseResult.setMessage("短信验证码有误，请您重新填写");
				logger.debug("发修改密码之确认手机号，结果=responseResult:" + responseResult);
				return responseResult;
			}
			// 判断用户是否登录
			AdminUser existUser = (AdminUser) SecurityUtils.getSubject()
					.getPrincipal();
			if (null == existUser) {
				responseResult.setMessage("您未登录或登录超时，请您登录后再试");
				logger.debug("修改密码之确认手机号，结果=responseResult:" + responseResult);
				return responseResult;
			}
			if (!existUser.getMobile().equals(mobile)) {
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("手机号与登录账户不一致，请您重新填写");
				logger.debug("发送短信验证码，结果=responseResult:" + responseResult);
				return responseResult;
			}
			AdminUser user = userService
					.findUserByMobile(existUser.getMobile());
			if (user != null) {
				// 校验验证码
				if (!user.getMcode().equals(mobileCode)) {
					// 不等
					responseResult.setMessage("短信验证码输入有误");
					responseResult.setCode(IStatusMessage.SystemStatus.NO_LOGIN
							.getCode());
					logger.debug("用户登录，结果=responseResult:" + responseResult);
					return responseResult;
				}

				// 1分钟
				long beginTime = user.getSendTime().getTime();
				long endTime = new Date().getTime();
				if (((endTime - beginTime) - 60000 > 0)) {
					responseResult.setCode(IStatusMessage.SystemStatus.ERROR
							.getCode());
					responseResult.setMessage("短信验证码超时");
					logger.debug("用户登录，结果=responseResult:" + responseResult);
				} else {
					responseResult.setCode(IStatusMessage.SystemStatus.SUCCESS
							.getCode());
					responseResult.setMessage("SUCCESS");
					logger.debug("修改密码之确认手机号，结果=responseResult:"
							+ responseResult);
				}
			} else {
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("您未登录或登录超时，请您登录后再试");
				logger.debug("发送短信验证码，结果=responseResult:" + responseResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
			responseResult.setMessage("操作失败，请您稍后再试");
			logger.error("修改密码之确认手机号异常！", e);
		}
		return responseResult;
	}

	/**
	 * 修改密码
	 * @param pwd
	 * @param isPwd
	 * @return
	 */
	@RequestMapping(value = "setPwd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult setPwd(@RequestParam("pwd") String pwd,
			@RequestParam("isPwd") String isPwd) {
		logger.debug("修改密码！pwd:" + pwd + ",isPwd=" + isPwd);
		ResponseResult responseResult = new ResponseResult();
		try {
			if (!ValidateUtil.isPassword(pwd)
					|| !ValidateUtil.isPassword(isPwd)) {
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("密码格式有误，请您重新填写");
				logger.debug("修改密码，结果=responseResult:" + responseResult);
				return responseResult;
			}
			if (!pwd.equals(isPwd)) {
				responseResult.setCode(IStatusMessage.SystemStatus.PARAM_ERROR
						.getCode());
				responseResult.setMessage("两次密码输入不一致，请您重新填写");
				logger.debug("发修改密码，结果=responseResult:" + responseResult);
				return responseResult;
			}
			// 判断用户是否登录
			AdminUser existUser = (AdminUser) SecurityUtils.getSubject()
					.getPrincipal();
			if (null == existUser) {
				responseResult.setCode(IStatusMessage.SystemStatus.NO_LOGIN
						.getCode());
				responseResult.setMessage("您未登录或登录超时，请您重新登录后再试");
				logger.debug("修改密码，结果=responseResult:" + responseResult);
				return responseResult;
			}
			// 修改密码
			int num = this.userService.updatePwd(existUser.getId(),
					DigestUtils.md5Hex(pwd));
			if (num != 1) {
				responseResult.setCode(IStatusMessage.SystemStatus.ERROR
						.getCode());
				responseResult.setMessage("操作失败，请您稍后再试");
				logger.debug("修改密码失败，已经离职或该用户被删除！结果=responseResult:"
						+ responseResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseResult.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
			responseResult.setMessage("操作失败，请您稍后再试");
			logger.error("修改密码异常！", e);
		}
		logger.debug("修改密码，结果=responseResult:" + responseResult);
		return responseResult;
	}

	/**
	 * 
	 * @描述：退出登录，并清除cookie
	 * @创建人：wyait
	 * @创建时间：2018年5月22日 下午2:16:21
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug(this.getClass().getName() + ".logout】退出登录：开始");
		ModelAndView mv = new ModelAndView("/toLogin");
		try {
			// 用户退出
			SecurityUtils.getSubject().logout();
			// 清除cookie//删除cookie
			Cookie co = new Cookie("SHRIOSESSIONID", "");
			co.setMaxAge(0);// 设置立即过期
			co.setPath("/");// 根目录，整个网站有效
			response.addCookie(co);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName() + ".logout】退出登录异常！", e);
		}
		logger.debug(this.getClass().getName() + ".logout】退出登录！结果=mv:" + mv);
		return mv;
	}

	public static void main(String[] args) throws ParseException {
		Date date = DateUtil.parse("2018-01-04 20:15:21");
		Date date1 = DateUtil.parse("2018-01-04 20:11:21");
		Date date2 = DateUtil.parse("2018-01-04 20:12:21");
		long beginTime = date.getTime();
		long beginTime1 = date1.getTime();
		long end1 = date2.getTime();
		long endTime = System.currentTimeMillis();
		// main
		long betweenDays = endTime - beginTime;
		long betweenDays1 = endTime - beginTime1;
		long eq = end1 - beginTime1;
		boolean flag = betweenDays - (60000) > 0;// 超时
		boolean flag1 = betweenDays1 - (60000) > 0;// 超时
		boolean flag2 = eq - (60000) == 0;//
		System.out.println(betweenDays);
		System.out.println(betweenDays1);
		System.out.println(eq);
		System.out.println(flag);
		System.out.println(flag1);
		System.out.println(flag2);
	}

	/**
	 * @描述：校验请求参数
	 * @param obj
	 * @param response
	 * @return
	 */
	protected boolean validatorRequestParam(Object obj, ResponseResult response) {
		boolean flag = false;
		Validator validator = new Validator();
		List<ConstraintViolation> ret = validator.validate(obj);
		if (ret.size() > 0) {
			// 校验参数有误
			response.setCode(IStatusMessage.SystemStatus.PARAM_ERROR.getCode());
			response.setMessage(ret.get(0).getMessageTemplate());
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @描述：用户管理-用户列表页面
	 * @创建人：樊诚
	 * @创建时间：2018年5月28日 下午4:39:23
	 * @return
	 */
	@RequestMapping("/toUserDatas")
	public ModelAndView toUserDatas() {
		return new ModelAndView("/user/userDatas");
	}

	/**
	 * 
	 * @描述：查询用户信息
	 * @创建人：樊诚
	 * @创建时间：2018年5月28日 下午5:29:28
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "/selectUsers", method = RequestMethod.POST)
	@ResponseBody
	public PageDataResult selectUsers(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "insertTimeStart", required = false) String insertTimeStart,
			@RequestParam(value = "insertTimeEnd", required = false) String insertTimeEnd,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "repeat", required = false) Integer repeat,
			@RequestParam("page") Integer page,
			@RequestParam("limit") Integer limit) {
		PageDataResult pdr = new PageDataResult();
		try {
			if (null == page) {
				page = 1;
			}
			if (null == limit) {
				limit = 10;
			}
			logger.debug("查询用户信息【lyd-admin.UserController.selectUsers()】==请求参数：username="+username+"，mobile="+mobile+
					"，insertTimeStart="+insertTimeStart +"，insertTimeEnd="+insertTimeEnd +"，status="+status +"，repeat="+repeat+
					"，page="+page + "，limit="+limit);
			pdr = this.userService
					.selectAllUser(username, mobile, insertTimeStart,
							insertTimeEnd, status, repeat, page, limit);
			logger.debug("查询用户信息【lyd-admin.UserController.selectUsers()】==请求结果：result="
					+ pdr);
		} catch (Exception e) {
			e.printStackTrace();
			pdr.setCode(Constants.HTTP_500_CODE);
			logger.error("用户列表【lyd-admin.UserController.selectUsers】===执行异常！",
					e);
		}
		return pdr;
	}

	/**
	 * 
	 * @描述：删除用户（客户）信息
	 * @创建人：樊诚
	 * @创建时间：2018年5月29日 上午9:37:47
	 * @param userID
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/delCustomer", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("delCustomer")
	public String delCustomer(@RequestParam("userID") String userID,
			@RequestParam("mobile") String mobile,
			@RequestParam("flag") Integer flag) {
		String msg = "";
		try {
			if (StringUtils.isEmpty(userID) || StringUtils.isEmpty(mobile)) {
				logger.debug("删除用户信息【lyd-admin.UserController.delCustomer】参数有误===请求参数：userID="
						+ userID + "，mobile=" + mobile + "，flag=" + flag);
				msg = "参数有误！";
				return msg;
			}
			logger.debug("删除用户信息【lyd-admin.UserController.delCustomer】===请求参数：userID="
					+ userID + "，mobile=" + mobile + "，flag=" + flag);
			// 根据手机号查询用户信息
			List<User> users = this.userService.selectByPhone(mobile);
			if (null == users || users.size() == 0) {
				msg = "用户不存在！";
				logger.debug("删除用户信息【lyd-admin.UserController.delCustomer】用户不存在===请求参数：userID="
						+ userID + "，mobile=" + mobile + "，flag=" + flag);
				return msg;
			} else if (users.size() == 1) {
				msg = "该用户手机号未重复，不能删除！";
				logger.debug("删除用户信息【lyd-admin.UserController.delCustomer】该用户手机号未重复，不能删除===请求参数：userID="
						+ userID + "，mobile=" + mobile + "，flag=" + flag);
				return msg;
			} else if (users.size() == 2) {
				// 判断与当前待删除用户手机号相同的另一个账号状态status是否为0，如果不为0，则提示不能删除
				User delUser = null;
				User oUser = null;
				for (User user : users) {
					if (user.getUserID().equals(userID)) {
						delUser = user;
					} else {
						oUser = user;
					}
				}
				if (null != delUser && null != oUser) {
					if (oUser.getStatus() == 1) {
						msg = "该用户手机号未重复，不能删除！";
						logger.debug("删除用户信息【lyd-admin.UserController.delCustomer】该用户手机号未重复，不能删除===请求参数：userID="
								+ userID
								+ "，mobile="
								+ mobile
								+ "，flag="
								+ flag);
						return msg;
					}
					// 存在另一个手机号并且两个状态都是0
					if (delUser.getStatus() == 0 && oUser.getStatus() == 0) {
						// 查询userID相关的表credit_apply；credit_info；credit_link；credit_pic；shop_estimate是否存在数据
						int applynum = this.userService
								.selectCreditApply(userID);
						int infonum = this.userService.selectCreditInfo(userID);
						int linknum = this.userService.selectCreditLink(userID);
						int picnum = this.userService.selectCreditPic(userID);
						int shopnum = this.userService
								.selectShopEstimate(userID);
						if (flag == null) {
							if (applynum > 0 || infonum > 0 || linknum > 0
									|| picnum > 0 || shopnum > 0) {
								msg = delUser.getUsername() + "（"
										+ delUser.getUserID() + "），该账号中：";
								if (applynum > 0) {
									msg += "【贷款申请】";
								}
								if (shopnum > 0) {
									msg += "【额度预估】";
								}
								if (infonum > 0) {
									msg += "【贷款记录】";
								}
								if (linknum > 0) {
									msg += "【联系人信息】";
								}
								if (picnum > 0) {
									msg += "【图片信息】";
								}
								msg += "中有信息！确定要删除吗？";
								return msg;
							}
						}
						logger.debug("删除用户信息【lyd-admin.UserController.delCustomer】开始执行修改用户信息===请求参数：userID="
								+ userID
								+ "，mobile="
								+ mobile
								+ "，flag="
								+ flag);
						// 删除操作
						try {
							msg = this.userService.updCustomer(1, userID);
							// 如果删除成功，那么就把被删除的用户关联的信息全分配给手机号相同的另一个用户
							if (msg.equals("success")) {
								// 修改表格：credit_apply；credit_info；credit_link；credit_pic；shop_estimate
								int applyCount = this.userService
										.updcreditApply(oUser.getUserID(),
												userID);
								int infoCount = this.userService.updcreditInfo(
										oUser.getUserID(), userID);
								int cLinkCount = this.userService
										.updcreditLink(oUser.getUserID(),
												userID);
								int picCount = this.userService.updcreditPic(
										oUser.getUserID(), userID);
								int estimateCount = this.userService
										.updshopEstimate(oUser.getUserID(),
												userID);
							}
							logger.debug("删除用户信息【lyd-admin.UserController.delCustomer】结束执行修改用户信息===请求结果！result="
									+ msg);
						} catch (Exception e) {
							// 手动回滚
							TransactionAspectSupport.currentTransactionStatus()
									.setRollbackOnly();
							logger.error(
									"删除用户信息【lyd-admin.UserController.delCustomer】===执行异常！",
									e);
							msg = "执行异常！";
						}
					}
				}
			} else if (users.size() > 2) {
				msg = "该用户账户出错！";
				logger.debug("删除用户信息【lyd-admin.UserController.delCustomer】该用户账户出错！===请求参数：userID="
						+ userID + "，mobile=" + mobile + "，flag=" + flag);
				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"删除用户信息【lyd-admin.UserController.delCustomer】===执行异常！", e);
			msg = "执行异常！";
		}
		return msg;
	}

	/**
	 * 
	 * @描述：恢复用户信息
	 * @创建人：樊诚
	 * @创建时间：2018年5月29日 下午2:44:32
	 * @param userID
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/recoverCustomer", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("recoverCustomer")
	public String recoverCustomer(@RequestParam("userID") String userID,
			@RequestParam("mobile") String mobile) {
		String msg = "";
		try {
			if (StringUtils.isEmpty(userID) || StringUtils.isEmpty(mobile)) {
				logger.debug("恢复用户信息【lyd-admin.UserController.recoverCustomer】参数有误！===请求参数：userID="
						+ userID + "，mobile=" + mobile);
				msg = "参数有误！";
				return msg;
			}
			logger.debug("恢复用户信息【lyd-admin.UserController.recoverCustomer】===请求参数：userID="
					+ userID + "，mobile=" + mobile);
			msg = this.userService.updCustomer(0, userID);
			logger.debug("恢复用户信息【lyd-admin.UserController.recoverCustomer】===请求结果：result="
					+ msg);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"恢复用户信息【lyd-admin.UserController.recoverCustomer】===执行异常！",
					e);
			msg = "执行异常！";
		}
		return msg;
	}

	/**
	 * 
	 * @描述：更新用户信息
	 * @创建人：wyait
	 * @创建时间：2018年6月4日 下午5:59:29
	 * @param uid
	 * @param userId
	 * @param userphone
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("delCustomer")
	public String updateUser(@RequestParam("uid") String uid,
			@RequestParam("userId") String userId,
			@RequestParam("userphone") String userphone) {
		logger.debug("更新用户信息【" + this.getClass().getName()
				+ ".updateUser==请求参数：uid=" + uid + "，userphone=" + userphone
				+ "，userId=" + userId);
		try {
			if (StringUtils.isBlank(uid) || StringUtils.isBlank(userId)
					|| !ValidateUtil.isMobile(userphone)) {
				logger.debug("更新用户信息【" + this.getClass().getName()
						+ ".updateUser==参数有误，请稍后再试");
				return "参数有误，请稍后再试";
			}
			// 验证手机号对应用户是否存在
			User user = this.userService.selectByMobile(userphone);
			if (null == user) {
				logger.debug("更新用户信息【" + this.getClass().getName()
						+ ".updateUser==绑定用户id，手机号不存在");
				return "手机号不存在！";
			}else if(!user.getUserID().equals(uid)){
				//和原id保持一致，说明手机号不重复
				logger.debug("更新用户信息【" + this.getClass().getName()
						+ ".updateUser==绑定用户id，手机号重复");
				return "手机号已经存在";
			}
			// 更新用户信息
			int count = this.userService.updateUser(uid,userId,
					userphone);
			logger.debug("更新用户信息【" + this.getClass().getName()
					+ ".updateUser】===执行结果：" + count + "！请求参数：uid=" + uid
					+ "   ，userId=" + userId+ "   ，userphone=" + userphone);
			if (count > 0) {
				return "success";
			} else {
				logger.debug("更新用户信息【" + this.getClass().getName()
						+ ".updateUser==绑定用户id，操作失败，请您稍后再试");
				return "操作失败，请您稍后再试";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新用户信息【" + this.getClass().getName()
					+ ".updateUser】，执行异常！", e);
			return "操作异常，请您稍后再试";
		}
	}

}
