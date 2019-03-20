/**
 *
 */
package com.wupao.log.web.loan;

import com.wupao.log.pojo.AdminUser;
import com.wupao.log.pojo.CreditApplyBean;
import com.wupao.log.pojo.Role;
import com.wupao.log.pojo.User;

import com.wupao.log.utils.BaseApiService;
import com.wupao.log.utils.Constants;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @项目名称：lyd-admin
 * @类名称：CreditApplyController
 * @类描述：贷款申请
 * @创建人：wzz
 * @创建时间：2018年4月2日 下午18:09:10
 * @version
 */
@Controller
@RequestMapping("/apply")
public class CreditApplyController extends BaseApiService {

	@Autowired
	private CreditApplyService creditApplyService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;
	@Autowired
    private CreditInfoService creditInfoService;
    @Autowired
    private BorrowInfoService borrowInfoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CreditApplyController.class);

	/**
	 *
	* @Description 方法描述: 获取申请列表
	* @return  PageDataResult
	* @author 汪正章
	* @date  2018年4月2日 上午11:25:27
	* @throws
	 */
	@RequestMapping(value = "/selectApplyInfo")
	@ResponseBody
	public PageDataResult selectApplyInfo(CreditApplyBean cib, String startDate,
                                          String endDate, String reStatus, @RequestParam("page")Integer page, @RequestParam("limit") Integer limit, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		PageDataResult pdr =new PageDataResult();
		try {
			if(null==page){
				page=1;
			}
			if(null==limit){
				limit=10;
			}
			LOGGER.debug("【lyd-admin】根据参数获取贷款申请记录列表【lyd-admin->selectApplyInfo()】请求参数==>cib="+cib+"，startDate="+startDate+"，endDate="+endDate+
					"，reStatus="+reStatus+"，page="+page+"，limit="+limit);
			//得到当前用户信息
			AdminUser existUser = (AdminUser) SecurityUtils.getSubject().getPrincipal();
			if(existUser!=null) {
				Integer id=existUser.getId();
				//根据用户编号得到角色
				List<Role> roleList=authService.getRoleByUser(id);
				for (Role role : roleList) {
					//查询当前角色是客服人员
					if(Constants.ROLENAME_CUSTOMER_SERVIC.equals(role.getCode())) {
						cib.setUserID(id+"");
					}
				}

			}
			//获取申请列表
			pdr= creditApplyService.selectApplyInfoList(cib, page, limit, startDate, endDate);
			LOGGER.debug("【lyd-admin】根据参数获取贷款申请记录列表【lyd-admin->selectApplyInfo()】成功==>"+pdr,startTime, System.currentTimeMillis(), LOGGER);
		} catch (Exception e) {
			pdr.setCode(Constants.HTTP_500_CODE);
			e.printStackTrace();
			LOGGER.error("【lyd-admin】根据参数获取贷款申请记录列表【lyd-admin->selectApplyInfo()】失败原因为："+e,startTime, System.currentTimeMillis(), LOGGER);
		}
		return pdr;
	}

	/**
	 *
	* @Description 方法描述: 跳转申请列表
	* @return  String
	* @author 汪正章
	* @date  2018年3月30日 下午5:59:17
	* @thlimit
	 */
	@RequestMapping("/applylist")
	public String toApplylist(){
		return "/loan/creditapplylist";
	}

	/**
	 *
	* @Description 方法描述: 添加领取人
	* @return  Map<String,Object>
	* @author 汪正章
	* @date  2018年4月2日 下午7:57:39
	* @throws
	 */
	@RequestMapping("/addApplyReceiveInfo")
	public @ResponseBody Map<String, Object> addApplyReceiveInfo(@RequestParam("orderid")String orderid) {
		LOGGER.debug("【lyd-admin】添加领取人 addApplyReceiveInfo()==>>请求参数：orderid="+orderid);
		CreditApplyBean cib=new CreditApplyBean();
		cib.setOrderID(orderid);
		//查询当前是否领取
		CreditApplyBean credit=creditApplyService.selectApplyByOrderId(cib.getOrderID());
		if(credit!=null) {
			if(StringUtils.isNotEmpty(credit.getReceiver())) {
				LOGGER.info("【lyd-admin】添加领取人 addApplyReceiveInfo()==>>领取人为："+credit.getReceivername());
				return setResutError("当前信息已领取");
			}
		}
		//得到当前用户信息
		AdminUser existUser = (AdminUser) SecurityUtils.getSubject().getPrincipal();
		if(existUser!=null) {
			cib.setReceiver(existUser.getId()+"");
			try {
				int count=creditApplyService.updateApplyInfo(cib);
				LOGGER.info("【lyd-admin】操作领取贷款信息addApplyReceiveInfo()==>>成功的值为"+count);
				if(count>0) {
					return setResutSuccess();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("【lyd-admin】操作领取贷款信息addApplyReceiveInfo()==>>失败原因为"+e);
			}
		}else {
			LOGGER.info("【lyd-admin】操作删除申请贷款信息addApplyReceiveInfo()==>>失败原因为用户不存在"+existUser);
		}
		return setResutError("领取失败");
	}
	/**
	*
	* @Description 方法描述: 审核申请信息
	* @return  Map<String,Object>
	* @author 汪正章
	* @date  2018年4月3日 下午1:16:39
	* @throws
	*/
	@RequestMapping("/editApplyInfo")
	public @ResponseBody Map<String, Object> editApplyInfo(CreditApplyBean cib) {

		try {
			LOGGER.info("【lyd-admin】编辑审核申请信息 editApplyInfo()==>>请求参数：cib="+cib);
			//判断是否提交宜信贷款渠道申请表
            Integer creditCount= creditInfoService.selectCountInfoByOrderId(cib.getOrderID());
            LOGGER.info("【lyd-admin】编辑审核申请信息 editApplyInfo()==>>判断是否提交宜信贷款渠道申请表：结果{}",creditCount);
            if(creditCount!=null&&creditCount>0){
                LOGGER.info("【lyd-admin】编辑审核申请信息 editApplyInfo()==>>判断是否提交宜信贷款渠道申请表：该条申请信息,用户已经提交了宜信渠道的申请表");
                return setResut(417,"该条申请信息,用户已经提交了宜信渠道的申请表",cib.getBankID());
            }
            //判断是否提交极速借贷款渠道申请表
            Integer borrowCount= borrowInfoService.selectCountInfoByOrderId(cib.getOrderID());
            LOGGER.info("【lyd-admin】编辑审核申请信息 editApplyInfo()==>>判断是否提交极速借贷款渠道申请表：结果{}",borrowCount);
            if(borrowCount!=null&&borrowCount>0){
                LOGGER.info("【lyd-admin】编辑审核申请信息 editApplyInfo()==>>判断是否提交极速借贷款渠道申请表：该条申请信息,用户已经提交了极速借渠道的申请表");
                return setResut(417,"该条申请信息,用户已经提交了极速借渠道的申请表",cib.getBankID());
            }
			//查询当前是否领取
			CreditApplyBean credit=creditApplyService.selectApplyByOrderId(cib.getOrderID());
			if(credit!=null) {
				if(credit.getReceiver()!=null&&!"".equals(credit.getReceiver())) {
					LOGGER.info("【lyd-admin】编辑审核申请信息 editApplyInfo()==>>领取人为："+credit.getReceivername());
				}else {
					//得到当前用户信息
					AdminUser existUser = (AdminUser) SecurityUtils.getSubject().getPrincipal();
					if(existUser==null) {
						LOGGER.info("【lyd-admin】操作审核申请信息 editApplyInfo()==>>失败原因为用户不存在"+existUser);
						return setResutError("编辑失败");
					}
					cib.setReceiver(existUser.getId()+"");
				}
			}else {
				return setResutError("编辑失败");
			}
			//设置渠道信息
			cib.setChannel(cib.getBankID());
			if(StringUtils.isEmpty(cib.getMark())) {
				cib.setMark("");
			}
			int count=creditApplyService.updateApplyInfo(cib);
			LOGGER.info("【lyd-admin】编辑审核申请信息 editApplyInfo()==>>成功的值为"+count);
			if(count>0) {
				return setResutSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("【lyd-admin】编辑贷款信息editApplyInfo()==>>失败原因为"+e);
		}
		return setResutError("编辑失败");
	}

	/**
	 *
	 * @描述：更换申请人
	 * @创建人：樊诚
	 * @创建时间：2018年5月18日 下午3:17:56
	 * @param orid
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="/changeUserId",method=RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("changeUserId")
	public String changeUserId(@RequestParam("orid")String orid,@RequestParam("mobile")String mobile){
		String msg = "";
		try {
			if(StringUtils.isEmpty(orid) || !com.wupao.tools.utils.ValidateUtil.isMobile(mobile)){
				LOGGER.info("更换申请人id【lyd-admin.CreditApplyController.changeUserId】===参数有误！请求参数：orid="+orid+"，mobile="+mobile);
				msg = "参数有误！";
				return msg;
			}
			//验证手机号对应用户是否存在
			User user = this.userService.selectByMobile(mobile);
			if(null == user){
				LOGGER.info("更换申请人id【lyd-admin.CreditApplyController.changeUserId】===手机号不存在！请求参数：orid="+orid+"，mobile="+mobile);
				msg="手机号不存在！";
				return msg;
			}
			//验证申请贷款信息是否存在
			int applyCount = this.creditApplyService.selectByOrderID(orid);
			if(applyCount<=0){
				LOGGER.info("更换申请人id【lyd-admin.CreditApplyController.changeUserId】===贷款申请信息不存在！请求参数：orid="+orid+"，mobile="+mobile);
				msg="贷款申请不存在！";
				return msg;
			}
			LOGGER.debug("更换申请人id【lyd-admin.CreditApplyController.changeUserId】===请求参数：orid="+orid+"   ，mobile="+mobile);
			int count = this.creditApplyService.changeUserId(user.getUserID(), orid);
			LOGGER.info("更换申请人id【lyd-admin.CreditApplyController.changeUserId】===修改结果：result="+count);
			if(count>0){
				msg="success";
				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg="绑定异常！";
			LOGGER.error("更换申请人id【lyd-admin.CreditApplyController.changeUserId】，执行异常！",e);
		}
		return msg;
	}

    /**
     *
     * @Description 方法描述: 添加申请信息
     * @return  Map<String,Object>
     * @author 汪正章
     * @date  2018年10月22日 下午1:16:39
     * @throws
     */
    @PostMapping("/addCreditApply")
    public @ResponseBody Map<String, Object> addCreditApply(CreditApplyBean cib) {
        try {
            LOGGER.info("【lyd-admin】添加申请信息addCreditApply()==>>请求参数：cib="+cib);
            //得到当前用户信息
            AdminUser existUser = (AdminUser) SecurityUtils.getSubject().getPrincipal();
            if(existUser==null) {
                LOGGER.info("【lyd-admin】操作审核申请信息 editApplyInfo()==>>失败原因为用户不存在"+existUser);
                return setResutError("编辑失败");
            }
            cib.setReceiver(existUser.getId()+"");
            boolean condition=creditApplyService.addCreditApply(cib);
            LOGGER.info("【lyd-admin】添加申请信息addCreditApply()==>>成功的值为{}",condition);
            if(condition) {
                return setResutSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("【lyd-admin】添加申请信息addCreditApply()==>>失败原因为"+e);
        }
        return setResutError("编辑失败");
    }
}
