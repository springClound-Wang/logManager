package com.wupao.log.web.loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wupao.log.entity.ShopEstimateUserVo;
import com.wupao.log.service.EstimateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @项目名称：lyd-admin
 * @类名称：EstimateController
 * @类描述：额度预估
 * @创建人：樊诚
 * @创建时间：2018年4月1日 上午11:28:56 
 * @version：
 */
@Controller
@RequestMapping("/estimate")
public class EstimateController {

	@Autowired
	private EstimateService estimateService;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EstimateController.class);
	
	/**
	 * 
	 * @描述：额度预估页面
	 * @创建人：樊诚
	 * @创建时间：2018年4月2日 上午9:59:56
	 * @return
	 */
	@RequestMapping(value="/estimateInfoPage")
	public String estimateInfoPage(){
		return "/loan/estimate_info";
	}
	
	/**
	 * 
	 * @描述：获取额度预估列表
	 * @创建人：樊诚
	 * @创建时间：2018年4月1日 上午11:35:27
	 * @param mobile
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/selectEstimateInfo",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<Map<String,Object>> selectEstimateInfo(@RequestParam(value="mobile")String mobile,
			@RequestParam(value="startDate")String startDate,
			@RequestParam(value="endDate")String endDate,
			@RequestParam(value="pageNum",required=false)Integer pageNum,
			@RequestParam(value="pageSize",required=false)Integer pageSize){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			if(null == pageNum){
				pageNum=1;
			}
			if(null == pageSize){
				pageSize=10;
			}
			LOGGER.debug("获取额度预估列表【lyd-admin.EstimateController.selectEstimateInfo()】,请求参数：mobile="+mobile+",startDate="+startDate+",endDate="+endDate+",pageNum="+pageNum+",pageSize="+pageSize);
			list=this.estimateService.selectEstimateInfoList(mobile, startDate, endDate, pageNum, pageSize);
			LOGGER.debug("获取额度预估列表【lyd-admin.EstimateController.selectEstimateInfo()】===执行结果：list="+list);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取额度预估列表【lyd-admin.EstimateController.selectEstimateInfo()】查询异常！", e);
		}
		return new PageInfo<Map<String,Object>>(list);
	}
	
	/**
	 * 
	 * @描述：删除额度预估数据 
	 * @创建人：樊诚
	 * @创建时间：2018年4月2日 下午2:31:17
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updateById",method=RequestMethod.POST)
	@ResponseBody
	public String updateById(@RequestParam("id")Integer id){
		String msg="";
		try {
			if(null == id){
				LOGGER.debug("删除额度预估数据 【lyd-admin.EstimateController.updateById()】，参数有误!请求参数：id="+id);
				return "参数有误！";
			}
			LOGGER.info("删除额度预估数据 【lyd-admin.EstimateController.updateById()】===请求参数：id="+id);
			msg=this.estimateService.updateById(id);
			LOGGER.info("删除额度预估数据 【lyd-admin.EstimateController.updateById()】===执行结果：msg="+msg);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除额度预估数据 【lyd-admin.EstimateController.updateById()】===执行异常！", e);
			msg="删除数据异常！";
		}
		return msg;
	}
	
	/**
	 * 
	 * @描述：根据id查询额度预估数据
	 * @创建人：樊诚
	 * @创建时间：2018年4月2日 下午4:37:51
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/selectById",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectById(@RequestParam("id")Integer id){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			if(null == id){
				LOGGER.debug("根据id查询额度预估数据【lyd-admin.EstimateController.selectById()】===参数有误：id="+id);
				map.put("msg","参数有误！");
				return map;
			}
			LOGGER.debug("根据id查询额度预估数据【lyd-admin.EstimateController.selectById()】===请求参数：id="+id);
			ShopEstimateUserVo shopEstimate=this.estimateService.selectById(id);
			if(null == shopEstimate){
				LOGGER.debug("根据id查询额度预估数据【lyd-admin.EstimateController.selectById()】===未查询到数据!");
				map.put("msg","没有查询数据！");
				return map;
			}
			map.put("msg","success");
			map.put("shopEstimate", shopEstimate);
			LOGGER.debug("根据id查询额度预估数据【lyd-admin.EstimateController.selectById()】===执行结果数：map="+map);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据id查询额度预估数据【lyd-admin.EstimateController.selectById()】===执行异常！", e);
			map.put("msg","查询额度预估数据 异常！");
			return map;
		}
		return map;
	}
}
