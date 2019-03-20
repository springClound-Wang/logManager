package com.wupao.log.web.code;

import java.util.List;
import java.util.Map;

import com.wupao.log.pojo.LoanShopInfo;
import com.wupao.log.service.CodeDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.wupao.log.web.loan.EstimateController;
import com.wupao.tools.utils.JSONUtils;

/**
 * 
 * @项目名称：lyd-admin
 * @类名称：CodeDataController
 * @类描述：二维码扫描数据控制类
 * @创建人：樊诚
 * @创建时间：2018年4月1日 下午1:47:10 
 * @version：
 */
@Controller
@RequestMapping("/code")
public class CodeDataController {

	@Autowired
	private CodeDataService codeDataService;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EstimateController.class);
	
	/**
	 * 
	 * @描述：网店授权页面
	 * @创建人：樊诚
	 * @创建时间：2018年4月2日 上午10:47:15
	 * @return
	 */
	@RequestMapping(value="/codeDataInfoPage")
	public ModelAndView codeDataInfoPage(@RequestParam(value="currentPage",required=false)Integer currentPage){
		ModelAndView mv=new ModelAndView("/code/codeData_info");
		mv.addObject("currentPage", currentPage);
		return mv;
	}
	
	/**
	 * 
	 * @描述：网店估价列表页面
	 * @创建人：樊诚
	 * @创建时间：2018年4月9日 上午10:47:15
	 * @param currentPage
	 * @return
	 */
	@RequestMapping(value="/evaluateListPage")
	public ModelAndView evaluateListPage(@RequestParam(value="currentPage",required=false)Integer currentPage){
		ModelAndView mv=new ModelAndView("/code/evaluateData_info");
		mv.addObject("currentPage", currentPage);
		return mv;
	}
	
	/**
	 * 
	 * @描述：分页获取网店授权列表
	 * @创建人：樊诚
	 * @创建时间：2018年4月1日 下午1:51:49
	 * @param shopType
	 * @param username
	 * @param shopName
	 * @param flag  1:网店授权列表    	2：网店估价列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getCodeDataInfo",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<LoanShopInfo> getCodeDataInfo(@RequestParam(value="shopType")String shopType,
                                                  @RequestParam(value="username")String username,
                                                  @RequestParam(value="shopName")String shopName,
                                                  @RequestParam(value="flag")Integer flag,
                                                  @RequestParam(value="pageNum")Integer pageNum,
                                                  @RequestParam(value="pageSize")Integer pageSize){
		PageInfo<LoanShopInfo> pageInfo = null;
		try {
			if(flag==null){
				LOGGER.debug("分页获取网店授权列表【lyd-admin->getCodeDataInfo()】，参数有误：flag="+flag);
				return new PageInfo<LoanShopInfo>();
			}
			if(null == pageNum){
				pageNum=1;
			}
			if(null == pageSize){
				pageSize=10;
			}
			LOGGER.debug("分页获取网店授权列表【lyd-admin.CodeDataController.getCodeDataInfo()】,请求参数：shopType="+shopType+",username="+username+",shopName="+shopName+
					",flag="+flag+",pageNum="+pageNum+",pageSize="+pageSize);
			Map<String, Object> map=this.codeDataService.selectCodeDataInfoList(shopType, username, shopName,flag, pageNum, pageSize);
			String listStr = (String) map.get("loanShopInfos");
			List<LoanShopInfo> list = JSONUtils.jsonToList(listStr, LoanShopInfo.class);
			Integer total = (Integer) map.get("total");
			pageInfo = new PageInfo<LoanShopInfo>(list);
			pageInfo.setTotal(total);
			pageInfo.setPageNum(pageNum);
			pageInfo.setPageSize(pageSize);
			LOGGER.debug("分页获取网店授权列表【lyd-admin.CodeDataController.getCodeDataInfo()】===请求结果：pageInfo="+pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("分页获取网店授权列表【lyd-admin.CodeDataController.getCodeDataInfo()】===查询异常！", e);
		}
		return pageInfo;
	}
	
	/**
	 * 
	 * @描述：根据id查询详情
	 * @创建人：樊诚
	 * @创建时间：2018年4月3日 下午2:16:26
	 * @param id
	 * @param currentPage
	 * @return
	 */
	@RequestMapping(value="/searchById",method=RequestMethod.GET)
	public ModelAndView searchById(@RequestParam("id")Integer id,@RequestParam("flag")Integer flag,@RequestParam("currentPage") Integer currentPage){
		ModelAndView mv=new ModelAndView();
		try {
			if(null == id){
				LOGGER.debug("根据id查询详情【lyd-admin.CodeDataController.searchById()】===参数有误：id="+id);
				mv.addObject("msg","参数有误！");
				return mv;
			}
			LoanShopInfo loanShopInfo=this.codeDataService.searchById(id);
			if(null == loanShopInfo){
				LOGGER.debug("根据id查询详情【lyd-admin.CodeDataController.searchById()】===没有查询到数据！");
				mv.addObject("msg","没有查询数据！");
				return mv;
			}
			LOGGER.debug("根据id查询详情【lyd-admin.CodeDataController.searchById()】===请求参数：id="+id+"，flag="+flag+"，currentPage="+currentPage);
			if(flag==1){
				mv.setViewName("/code/codeData_detail");
			}else if(flag==2){
				mv.setViewName("/code/evaluateData_detail");
			}
			mv.addObject("msg", "success");
			mv.addObject("loanShopInfo", loanShopInfo);
			mv.addObject("currentPage",currentPage);
			mv.addObject("flag",flag);
			LOGGER.debug("根据id查询详情【lyd-admin.CodeDataController.searchById()】===请求结果：loanShopInfo="+loanShopInfo+"，currentPage="+currentPage+"，flag="+flag);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据id查询详情【lyd-admin.CodeDataController.searchById()】===执行异常！", e);
			mv.addObject("msg","查询额度预估数据 异常！");
		}
		return mv;
	}
	
}
