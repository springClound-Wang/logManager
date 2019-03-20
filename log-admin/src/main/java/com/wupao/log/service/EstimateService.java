package com.wupao.log.service;


import java.util.List;
import java.util.Map;

import com.wupao.log.entity.ShopEstimateUserVo;

/**
 * 
 * @项目名称：lyd-admin
 * @类名称：EstimateService
 * @类描述：
 * @创建人：樊诚
 * @创建时间：2018年4月1日 上午11:09:47 
 * @version：
 */
public interface EstimateService {

	/**
	 * 
	 * @描述：获取额度预估列表
	 * @创建人：樊诚
	 * @创建时间：2018年4月1日 上午11:11:53
	 * @param mobile
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Map<String,Object>> selectEstimateInfoList(String mobile,String startDate,String endDate,Integer pageNum,Integer pageSize);
	
	/**
	 * 
	 * @描述：删除额度预估数据
	 * @创建人：樊诚
	 * @创建时间：2018年4月2日 下午2:28:52
	 * @param id
	 * @return
	 */
	String updateById(Integer id);
	
	/**
	 * 
	 * @描述：根据id查询额度预估数据
	 * @创建人：樊诚
	 * @创建时间：2018年4月2日 下午4:26:08
	 * @param id
	 * @return
	 */
	ShopEstimateUserVo selectById(Integer id);
	
}
