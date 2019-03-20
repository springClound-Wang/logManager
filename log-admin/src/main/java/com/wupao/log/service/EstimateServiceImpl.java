package com.wupao.log.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.wupao.log.entity.ShopEstimateUserVo;
import com.wupao.log.mapper.EstimateMapper;
/**
 * 
 * @项目名称：lyd-admin
 * @类名称：EstimateServiceImpl
 * @类描述：
 * @创建人：樊诚
 * @创建时间：2018年4月1日 上午11:14:20 
 * @version：
 */
@Service
public class EstimateServiceImpl implements EstimateService{

	@Autowired
	private EstimateMapper estimateMapper;
	
	/**
	 * 获取额度预估列表
	 */
	@Override
	public List<Map<String,Object>> selectEstimateInfoList(String mobile,
			String startDate, String endDate,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String,Object>> list=this.estimateMapper.selectEstimateInfoList(mobile, startDate, endDate);
		return list;
	}

	/**
	 * 删除额度预估数据 
	 */
	@Override
	public String updateById(Integer id) {
		int count=this.estimateMapper.updateById(id,2,new Date());
		if(count!=1){
			return "删除失败！";
		}
		return "success";
	}

	/**
	 * 根据id查询额度预估数据
	 */
	@Override
	public ShopEstimateUserVo selectById(Integer id) {
		return this.estimateMapper.selectById(id);
	}
}
