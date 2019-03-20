package com.wupao.log.service;

import java.util.Map;

import com.wupao.log.pojo.LoanShopInfo;

/**
 * 
 * @项目名称：lyd-admin
 * @类名称：CodeDataService
 * @类描述：
 * @创建人：樊诚
 * @创建时间：2018年4月1日 下午1:47:35
 * @version：
 */
public interface CodeDataService {

	/**
	 * 
	 * @描述：分页获取网店授权列表
	 * @创建人：樊诚
	 * @创建时间：2018年4月1日 下午1:41:29
	 * @param shopType
	 * @param username
	 * @param shopName
	 * @param flag  1:网店授权   2：网店估价
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Map<String,Object> selectCodeDataInfoList(String shopType,
			String username, String shopName, Integer flag, Integer pageNum,
			Integer pageSize)throws Exception;

	/**
	 * 
	 * @描述：根据id查询详情
	 * @创建人：樊诚
	 * @创建时间：2018年4月3日 下午2:14:31
	 * @param id
	 * @return
	 */
	LoanShopInfo searchById(Integer id)throws Exception;
}
