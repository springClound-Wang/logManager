/**
 * 
 */
package com.wupao.log.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wupao.log.mapper.CreditInfoMapper;
import com.wupao.log.pojo.EcCreditInfoBean;
import com.wupao.log.service.CreditInfoService;
import com.wupao.log.utils.Constants;
import com.wupao.log.utils.HideStringUtil;
import com.wupao.log.utils.PageDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @项目名称：lyd-admin
 * @类名称：CreditInfoServiceImpl
 * @类描述：贷款记录接口实现类
 * @创建人：汪正章
 * @创建时间：2018年4月3日 下午7:13:05
 * @version
 */
@Service
public class CreditInfoServiceImpl implements CreditInfoService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditInfoServiceImpl.class);
	@Autowired
	private CreditInfoMapper creditInfoMapper;
	@Override
	public PageDataResult selectCreditInfoList(EcCreditInfoBean cib, String startDate, String endDate, int page,
			int limit) {
		PageDataResult pdr=new PageDataResult();
		PageHelper.startPage(page, limit);
		List<EcCreditInfoBean> clist=creditInfoMapper.selectCreditInfoList(cib, startDate, endDate);
		//隐藏身份敏感信息
		for (EcCreditInfoBean eb : clist) {
			eb.setIDcard(null);
			eb.setRegion(null);
			eb.setCity(null);
			eb.setStreet(null);
			eb.setArea(null);
		}
		PageInfo<EcCreditInfoBean> pageInfo = new PageInfo<EcCreditInfoBean>(clist);
		pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
		pdr.setList(clist);
		return pdr;
	}

	@Override
	public Map<String, Object> getProCityArea(int type, int id) {
		String tbName = "";
		String upName = "id";
		if (type == Constants.CREDIT_PROVINCE_TYPE) {// 获取省列表
			tbName = "credit_province";
		} else if (type == Constants.CREDIT_CITY_TYPE) {// 获取城市
			tbName = "credit_city";
		} else if (type == Constants.CREDIT_AREA_TYPE) {//获取地区
			tbName = "credit_area";
		}
		return creditInfoMapper.getProCityArea(tbName, upName, id);
	}

	@Override
	public Map<String, Object> selectCreditDetailInfo(String orderid, String userid) {
		Map<String, Object> list=creditInfoMapper.selectCreditDetailInfo(orderid, userid);
		Map<String, Object> newMap =new HashMap<String, Object>();
		//循环map
		Iterator<Map.Entry<String, Object>> entries = list.entrySet().iterator();  
		 while (entries.hasNext()) {  
		   //得到所有键和值
		     Map.Entry<String, Object> entry = entries.next(); 
		     String key=entry.getKey();
		     Object obj= entry.getValue();
		     LOGGER.debug("【lyd-admin】===>>>查询的所有结果selectCreditDetailInfo()：Key = " +key + ", Value = " +obj);  
		     if(Constants.IDCARD.equals(key)) {
		    	 //隐藏身份证
		    	String idCardValue= HideStringUtil.getHideIDcard(obj.toString());
		    	newMap.put(key, idCardValue);
		     }
		     else if(Constants.CARDNUMBER.equals(key)) {
		    	//隐藏银行卡
		    	 if(obj!=null&&!"".equals(obj.toString())) {
		    		 String cardNumberValue= HideStringUtil.getHideBankAccount(obj.toString());
		    		 newMap.put(key, cardNumberValue);
		    	 }else {
		    		 newMap.put(key, obj);
		    	 }
		     }
		     else {
		    	 newMap.put(key, obj);
		     }
		 }  
		return newMap;
	}

	/**
	 * 审批
	 */
	@Override
	@Transactional
	public String updateCreditInfo(Integer status, String mark, String orderID) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int count = this.creditInfoMapper.updateCreditInfo(status, mark,sf.format(new Date()), orderID);
		if(count>0){
			return "success";
		}else {
			return "审批失败！";
		}
	}

	@Override
	@Transactional
	public int bindingUserId(String userId, String orderID) {
		return this.creditInfoMapper.bindingUserId(userId, orderID);
	}

	/**
	 * 修改渠道商
	 */
	@Override
    @Transactional
    public int updateQuDao(Integer bankid, String orderID) {
		return this.creditInfoMapper.updateQuDao(bankid, orderID);
	}
	/*
	 * @Author 汪正章
	 * @Description 根据订单编号查询信息是否存在
	 * @Date 2018/10/22 14:10
	 * @Param [orderId]
	 * @**return** java.lang.Integer
	 **/
	@Override
	public Integer selectCountInfoByOrderId(String orderId) {
		return creditInfoMapper.selectCountInfoByOrderId(orderId);
	}

}
