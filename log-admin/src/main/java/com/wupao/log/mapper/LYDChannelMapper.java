package com.wupao.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wupao.log.utils.CreditInfo;
import com.wupao.log.utils.CreditParam;


/**
 * @项目名称：lyd-admin
 * @类名称：LYDChannelMapper
 * @类描述：利易达渠道接口持久层
 * @创建人：肖龙祥
 * @创建时间：2018年5月2日 上午9:58:22 
 * @version：
 */
@Mapper
public interface LYDChannelMapper {
	/**
	 * @描述：利易达渠道接口-分页查询会员贷款列表
	 * @创建人：肖龙祥
	 * @创建时间：2018年5月2日 上午9:59:12
	 * @param uids
	 * @param param
	 * @return
	 */
	List<CreditInfo> creditPageList(@Param("uids")List<Integer> uids,@Param("param")CreditParam param);
}