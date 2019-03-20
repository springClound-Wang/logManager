/**
 * 
 */
package com.wupao.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wupao.log.pojo.LoanShopInfo;

/**
 * 
 * @项目名称：lyd-admin
 * @类名称：CodeDataMapper
 * @类描述：
 * @创建人：樊诚
 * @创建时间：2018年4月1日 下午12:58:40
 * @version：
 */
@Mapper
public interface CodeDataMapper {

	/**
	 * 
	 * @描述：分页获取网店授权列表
	 * @创建人：樊诚
	 * @创建时间：2018年4月1日 下午1:39:24
	 * @param shopType
	 * @param username
	 * @param shopName
	 * @param flag
	 * @return
	 */
	List<LoanShopInfo> selectCodeDataInfoList(
			@Param("shopType") Integer shopType,
			@Param("username") String username,
			@Param("shopName") String shopName, @Param("flag") Integer flag);

	/**
	 * 
	 * @描述：获取数据总数
	 * @创建人：席在盛
	 * @创建时间：2016年8月12日 下午3:41:02
	 * @param userName
	 * @param wwName
	 * @return
	 */
	int selectCodeDataInfoTotalRows(@Param("userName") String userName,
			@Param("wwName") String wwName, @Param("platKind") String platKind);

	/**
	 * 
	 * @描述：根据id查询详情
	 * @创建人：樊诚
	 * @创建时间：2018年4月3日 下午2:13:32
	 * @param id
	 * @return
	 */
	LoanShopInfo searchById(Integer id);

}
