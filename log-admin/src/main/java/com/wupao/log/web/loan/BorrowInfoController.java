/**
 * 
 */
package com.wupao.log.web.loan;

import com.wupao.log.pojo.BorrowLoanInfoBean;
import com.wupao.log.pojo.BorrowLoanRegion;
import com.wupao.log.pojo.User;
import com.wupao.log.service.BorrowInfoService;
import com.wupao.log.service.CreditApplyService;
import com.wupao.log.service.CreditInfoService;
import com.wupao.log.service.UserService;
import com.wupao.log.utils.BaseApiService;
import com.wupao.log.utils.Constants;
import com.wupao.log.utils.PageDataResult;
import com.wupao.tools.utils.ValidateUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * @项目名称：lyd-admin
 * @类名称：BorrowInfoController
 * @类描述：极速借极速借贷款记录信息
 * @创建人：汪正章
 * @创建时间：2018年9月5日 下午6:11:27
 * @version
 */
@Controller
@RequestMapping("/borrow")
public class BorrowInfoController extends BaseApiService {

	@Autowired
	private CreditInfoService creditInfoService;
	@Autowired
	private CreditApplyService creditApplyService;
	@Autowired
	private UserService userService;
    @Autowired
    private BorrowInfoService borrowInfoService;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BorrowInfoController.class);

	/**
	 * 
	* @Description 方法描述: 查询极速借贷款记录详情 
	* @return  PageDataResult
	* @author 汪正章 
	* @date  2018年4月3日 下午1:54:20
	* @throws
	 */
	@RequestMapping(value = "/selectCreditInfo")
	@ResponseBody
	public PageDataResult selectCreditInfo(BorrowLoanInfoBean cib,
                                           String startDate, String endDate,
                                           @RequestParam("page") Integer page,
                                           @RequestParam("limit") Integer limit) {
		long startTime = System.currentTimeMillis();
		PageDataResult pdr = new PageDataResult();
		try {
			if (null == page) {
				page = 1;
			}
			if (null == limit) {
				limit = 10;
			}
			LOGGER.debug("根据参数获取极速借贷款记录列表【lyd-admin-BorrowInfoController->selectCreditInfo()】请求参数==>cib="+cib+"，startDate="+startDate+"，endDate="+endDate+
					"，page="+page+"，limit="+limit);
			// 获取申请列表
			pdr = borrowInfoService.selectCreditInfoList(cib, startDate,
					endDate, page, limit);
			LOGGER.debug("根据参数获取极速借贷款记录列表【lyd-admin--BorrowInfoController>selectCreditInfo()】成功==>"
					+ pdr, startTime, System.currentTimeMillis(), LOGGER);
		} catch (Exception e) {
			pdr.setCode(Constants.HTTP_500_CODE);
			e.printStackTrace();
			LOGGER.error("根据参数获取极速借贷款记录列表【lyd-admin-BorrowInfoController->selectCreditInfo()】失败原因为："
					+ e, startTime, System.currentTimeMillis(), LOGGER);
		}
		return pdr;
	}

	/**
	 * 
	* @Description 方法描述: 跳转极速借极速借贷款记录列表页面
	* @return  String
	* @author 汪正章 
	* @date  2018年9月5日 下午5:58:31
	* @throws
	 */
	@RequestMapping("/toBorrowInfoList")
	public String toCreditList(String curr, HttpServletRequest request) {
		request.setAttribute(Constants.CURRENTPAGE, curr);
		return "/loan/borrowinfolist";
	}

	/**
	 * 
	* @Description 方法描述: 查看极速借贷款详情资料信息 
	* @return  String
	* @author 汪正章 
	* @date  2018年9月6日 下午6:54:43
	* @throws
	 */
	@RequestMapping("/toBorrowDetailPage")
	public String toBorrowDetailPage(HttpServletRequest request,
			@RequestParam("orderid") String orderid,
			@RequestParam("userid") String userid, String curr) {
		LOGGER.debug("查看极速借贷款详情资料信息【lyd-admin.BorrowInfoController.toBorrowDetailPage】===请求参数：orderid="+orderid+
				"，userid="+userid+"，curr="+curr);
		// 根据订单号和用户编号查询相关信息
        BorrowLoanInfoBean bean = borrowInfoService.selectBorrowDetailInfo(orderid, userid);
		LOGGER.debug("查看极速借贷款详情资料信息【lyd-admin.BorrowInfoController.toBorrowDetailPage】===执行结果：bean="+bean);
		request.setAttribute(Constants.CREDITDEATILINFO, bean);
		request.setAttribute(Constants.CURRENTPAGE, curr);
		return "/loan/borrowdetail";
	}

	/**
	 * 
	 * @描述：审批
	 * @创建人：樊诚
	 * @创建时间：2018年5月21日 下午4:57:24
	 * @param status
	 * @param mark
	 * @param orderID
	 * @return
	 */
	@RequestMapping(value = "/examineCreditInfo", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("borrowCreditInfo")
	public String examineCreditInfo(@RequestParam("status") Integer status,
			@RequestParam("mark") String mark,
			@RequestParam("orderID") String orderID) {
		String msg = "";
		try {
			if ((null == status && status != 1) || StringUtils.isEmpty(mark)
					|| StringUtils.isEmpty(orderID)) {
				LOGGER.debug("审批【lyd-admin.BorrowInfoController.examineCreditInfo】===参数有误！请求参数:status="
					+ status + "   ,mark=" + mark + "  ,orderID=" + orderID);
				msg = "参数有误！";
				return msg;
			}
			LOGGER.info("审批【lyd-admin.BorrowInfoController.examineCreditInfo】===请求参数:status="
					+ status + "   ,mark=" + mark + "  ,orderID=" + orderID);
			msg = this.borrowInfoService.updateCreditInfo(status, mark, orderID);
			LOGGER.info("审批【lyd-admin.BorrowInfoController.examineCreditInfo】===请求结果：msg="+msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "执行异常!";
			LOGGER.error(
					"审批【lyd-admin.BorrowInfoController.examineCreditInfo】，执行异常！", e);
		}
		return msg;
	}

    /*
     * @Author 汪正章
     * @Description 绑定用户编号
     * @Date 2018/9/6 11:12
     * @Param [orid, mobile]
     * @**return** java.lang.String
     **/
	@RequestMapping(value = "/bindingUserId", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("borrowBindUser")
	public String bindingUserId(@RequestParam("orid") String orid,
			@RequestParam("mobile") String mobile) {
		LOGGER.info("绑定极速借贷款信息的用户id【" + this.getClass().getName()
				+ ".bindingUserId==绑定用户id，请求参数：orid=" + orid + "，mobile="
				+ mobile);
		try {
			if (StringUtils.isEmpty(orid)
					|| !ValidateUtil.isMobile(mobile)) {
				LOGGER.debug("绑定极速借贷款信息的用户id【" + this.getClass().getName()
						+ ".bindingUserId==绑定用户id，参数有误，请稍后再试，请求参数：orid=" + orid + "，mobile="
				+ mobile);
				return "参数有误，请稍后再试";
			}
			// 验证手机号对应用户是否存在
			User user = this.userService.selectByMobile(mobile);
			if (null == user) {
				LOGGER.debug("绑定极速借贷款信息的用户id【" + this.getClass().getName()
						+ ".bindingUserId==绑定用户id，手机号不存在，请求参数：orid=" + orid + "，mobile="
				+ mobile);
				return "手机号不存在！";
			}
			// 验证申请极速借贷款信息是否存在
			int applyCount = this.creditApplyService.selectByOrderID(orid);
			if (applyCount <= 0) {
				LOGGER.debug("绑定极速借贷款信息的用户id【" + this.getClass().getName()
						+ ".bindingUserId==绑定用户id，极速借贷款申请不存在，请求参数：orid=" + orid + "，mobile="
				+ mobile);
				return "极速借贷款申请不存在！";
			}
			// 绑定极速借贷款信息的用户id
			int count = this.borrowInfoService.bindingUserId(user.getUserID(),
					orid);
			LOGGER.info("绑定极速借贷款信息的用户id【" + this.getClass().getName()
					+ ".bindingUserId】===执行结果：" + count + "！请求参数：orid=" + orid
					+ "   ，mobile=" + mobile);
			if (count > 0) {
				return "success";
			} else {
				LOGGER.info("绑定极速借贷款信息的用户id【" + this.getClass().getName()
						+ ".bindingUserId==绑定用户id，操作失败，请您稍后再试，请求参数：orid=" + orid + "，mobile="
				+ mobile);
				return "操作失败，请您稍后再试";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("绑定极速借贷款信息的用户id【" + this.getClass().getName()
					+ ".bindingUserId】，执行异常！", e);
			return "操作异常，请您稍后再试";
		}
	}
	
	/**
	 * 
	 * @描述：修改渠道商
	 * @创建人：樊诚
	 * @创建时间：2018年6月5日 上午11:33:59
	 * @param bankid
	 * @param orderID
	 * @return
	 */
	@RequestMapping(value="/updateQuDao",method=RequestMethod.POST)
	@ResponseBody
	@RequiresRoles("superman")
	public String updateQuDao(@RequestParam("bankid")Integer bankid,@RequestParam("orderID")String orderID){
		try {
			if(bankid==null || StringUtils.isEmpty(orderID)){
				LOGGER.debug("修改渠道商【lyd-admin.BorrowInfoController.updateQuDao】参数有误！===请求参数：bankid="+bankid+"，orderID="+orderID); 
				return "参数有误！";
			}
			LOGGER.info("修改渠道商【lyd-admin.BorrowInfoController.updateQuDao】===请求参数：bankid="+bankid+"，orderID="+orderID);
			int count = this.creditInfoService.updateQuDao(bankid, orderID);
			if(count>0){
				LOGGER.info("修改渠道商【lyd-admin.BorrowInfoController.updateQuDao】===执行成功！");
				return "success";
			}else {
				LOGGER.info("修改渠道商【lyd-admin.BorrowInfoController.updateQuDao】===操作失败，请您稍后再试");
				return "操作失败，请您稍后再试";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("修改渠道商【lyd-admin.BorrowInfoController.updateQuDao】===执行异常！", e);
			return "操作异常，请您稍后再试";
		}
	};
	
	/**
	 * 
	 * @描述： 极速借查询地区信息 
	 * @创建人：樊诚
	 * @创建时间：2018年10月10日 下午5:16:30
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAttrById", method = RequestMethod.POST)
	public Map<String, Object> getAttrById(@RequestParam("id")Integer id,@RequestParam("pid")Integer pid) {
		LOGGER.debug("【lyd-admin-BorrowInfoController】===>>>进入getAttrById()方法，编号:" + id);
		if (null == id || null == pid) {
			LOGGER.debug("【lyd-admin-BorrowInfoController】===>>>getAttrById()方法，参数有误！id="+id);
			return setResutError("查询失败");
		}
		try {
			BorrowLoanRegion result = this.borrowInfoService.getAttrById(id,pid);
			LOGGER.debug("【lyd-admin-BorrowInfoController】===>>>请求地区信息返回的结果是:" + result);
			return setResutSuccessData(result);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("【lyd-admin-BorrowInfoController】===>>>请求地区信息异常原因是:" + e);
		}
		return setResutError("查询失败");
	}
}
