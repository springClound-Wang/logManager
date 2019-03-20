/**
 * 
 */
package com.wupao.log.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wupao.log.mapper.CreditApplyMapper;
import com.wupao.log.pojo.CreditApplyBean;
import com.wupao.log.service.CreditApplyService;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.utils.UUIDGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @项目名称：lyd-admin
 * @类名称：CreditApplyServiceImpl
 * @类描述：
 * @创建人：汪正章
 * @创建时间：2018年4月3日 上午8:58:38
 * @version
 */
@Service
public class CreditApplyServiceImpl implements CreditApplyService {

	@Autowired
	private CreditApplyMapper creditApplyMapper;
	
	@Override
	public PageDataResult selectApplyInfoList(CreditApplyBean cib, int page, int limit, String startDate,String endDate) {
		int check=0;
		if(cib.getKind()!=null&&cib.getKind()>0) {
			check=check+1;
		}
		if(cib.getChannel()!=null&&cib.getChannel()>0) {
			check=check+1;
		}
		if(cib.getStatus()!=null&&cib.getStatus()>0) {
			check=check+1;
		}
		if(StringUtils.isNotEmpty(startDate)||StringUtils.isNotEmpty(endDate)||StringUtils.isNotEmpty(cib.getName())||StringUtils.isNotEmpty(cib.getNumber())
				||StringUtils.isNotEmpty(cib.getReceivername())) {
			check=check+1;
		}
		PageDataResult pdr=new PageDataResult();
		PageHelper.startPage(page, limit);
		List<CreditApplyBean> clist=creditApplyMapper.selectApplyList(cib, startDate, endDate,check);
		PageInfo<CreditApplyBean> pageInfo = new PageInfo<CreditApplyBean>(clist);
		pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
		pdr.setList(clist);
		return pdr;
	}
	
	@Override
	@Transactional
	public int updateApplyInfo(CreditApplyBean cib) {
		return creditApplyMapper.updateApplyInfo(cib);
	}

	@Override
	public CreditApplyBean selectApplyByOrderId(String orderid) {
		return creditApplyMapper.selectApplyByOrderId(orderid);
	}

	@Override
    @Transactional
    public int changeUserId(String userId, String orderID) {
		Date day=new Date(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return this.creditApplyMapper.changeUserId(userId, orderID,df.format(day));
	}

	@Override
	public int selectByOrderID(String orderID) {
		return this.creditApplyMapper.selectByOrderID(orderID);
	}
	/*
	 * @Author 汪正章
	 * @Description 添加贷款申请
	 * @Date 2018/10/22 15:32
	 * @Param [ecb]
	 * @**return** boolean
	 **/
	@Override
	@Transactional
	public boolean addCreditApply(CreditApplyBean ecb) {
		ecb.setOrderID(UUIDGenerator.generate20());// 生成主键orderID
		ecb.setStatus(2);// 默认状态审核成功
        ecb.setMark("更改贷款渠道自动审核成功");
        //设置渠道信息
        ecb.setChannel(ecb.getBankID());
        ecb.setKind(1);
		return creditApplyMapper.addCreditApply(ecb) > 0;
	}
}
