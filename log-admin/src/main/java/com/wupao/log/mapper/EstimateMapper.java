/**
 * 
 */
package com.wupao.log.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wupao.log.entity.ShopEstimateUserVo;

/**
 * 
 * @项目名称：lyd-admin
 * @类名称：EstimateMapper
 * @类描述：
 * @创建人：樊诚
 * @创建时间：2018年4月1日 上午11:05:45 
 * @version：
 */
@Mapper
public interface EstimateMapper {

	/**
	 * 
	 * @描述：获取额度预估列表
	 * @创建人：樊诚
	 * @创建时间：2018年4月1日 上午11:04:09
	 * @param mobile
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Map<String, Object>> selectEstimateInfoList(@Param("mobile")String mobile,@Param("startDate")String startDate,@Param("endDate")String endDate);
	
	/**
	 * 
	 * @描述：删除额度预估数据 
	 * @创建人：樊诚
	 * @创建时间：2018年4月2日 下午2:27:47
	 * @param id
	 * @param status
	 * @param time
	 * @return
	 */
	int updateById(@Param("id")Integer id,@Param("status")Integer status,@Param("time")Date time);
	
	/**
	 * 
	 * @描述：根据id查询额度预估
	 * @创建人：樊诚
	 * @创建时间：2018年4月2日 下午4:20:40
	 * @param id
	 * @return
	 */
	ShopEstimateUserVo selectById(Integer id);
}
