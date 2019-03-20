package com.wupao.log.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wupao.log.mapper.BorrowLoanInfoMapper;
import com.wupao.log.pojo.BorrowLoanInfoBean;
import com.wupao.log.pojo.BorrowLoanRegion;
import com.wupao.log.pojo.JisujieLoan;
import com.wupao.log.service.BorrowInfoService;
import com.wupao.log.utils.HideStringUtil;
import com.wupao.log.utils.PageDataResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 汪正章
 * @ClassName BorrowInfoServiceImpl
 * @create 2018-09-05 17:58
 * @desc 极速借贷款实现类
 * @Version 1.0V
 **/
@Service
public class BorrowInfoServiceImpl implements BorrowInfoService {
    @Autowired
    BorrowLoanInfoMapper borrowLoanInfoMapper;

    /*
     * @Author 汪正章
     * @Description 绑定极速借借款信息用户id
     * @Date 2018/9/6 9:39
     * @Param
     * @**return**
     **/
    @Override
    @Transactional
    public int bindingUserId(String userID, String orid) {
        return borrowLoanInfoMapper.bindingUserId(userID,orid);
    }

    @Override
    @Transactional
    public String updateCreditInfo(Integer status, String mark, String orderID) {
        int count= borrowLoanInfoMapper.updateCreditInfo(status,mark,orderID);

        if(count>0){
            return "success";
        }else {
            return "审批失败！";
        }
    }

    /*
     * @Author 汪正章
     * @Description 查看极速借贷款资料详情
     * @Date 2018/9/6 13:30
     * @Param
     * @**return**
     **/
    @Override
    public BorrowLoanInfoBean selectBorrowDetailInfo(String orderid, String userid) {
        BorrowLoanInfoBean borrow= borrowLoanInfoMapper.selectBorrowDetailInfo(orderid,userid);
        if(null != borrow){
        	String aid = this.borrowLoanInfoMapper.getLoanInfoByAid(borrow.getApplicationId());
        	if(StringUtils.isNotBlank(aid) && aid.equals(orderid)){
        		JisujieLoan jisujieLoan = borrowLoanInfoMapper.selectJiSujieLoanInfo(borrow.getApplicationId());
            	if(null != jisujieLoan){
            		borrow.setLoanStatus(jisujieLoan.getStatus());
            		borrow.setDesc(jisujieLoan.getDesc());
            		borrow.setNotifyTime(jisujieLoan.getNotifyTime());
            		borrow.setSourceType(jisujieLoan.getSourceType());
            		String content = jisujieLoan.getContent();
            		if(StringUtils.isNotBlank(content)){
            			JSONObject contentJson = JSONObject.parseObject(content);
            			String cardNum = contentJson.getString("bank_card");
            			if(StringUtils.isNotBlank(cardNum)){
            				cardNum = HideStringUtil.getHideBankAccount(cardNum);
            			}
            			borrow.setLoanBankCard(cardNum);
            			borrow.setLoanBankName(contentJson.getString("bank_name"));
            			borrow.setLoanType(contentJson.getInteger("type"));
            		}
            	}
        	}
        }
        //隐藏身份证信息
        String card= HideStringUtil.getHideIDcard(borrow.getIdcard());
        borrow.setIdcard(card);
        return borrow;
    }

    /*
     * @Author 汪正章
     * @Description 查询所有极速借借款信息
     * @Date 2018/9/6 9:17
     * @Param
     * @**return**
     **/
    @Override
    public PageDataResult selectCreditInfoList(BorrowLoanInfoBean borrow, String startDate, String endDate, int page, int limit) {
        PageDataResult pdr=new PageDataResult();
        PageHelper.startPage(page, limit);
        List<BorrowLoanInfoBean> beanList=borrowLoanInfoMapper.selectCreditInfoList(borrow, startDate, endDate);
        //隐藏身份敏感信息
        for (BorrowLoanInfoBean eb : beanList) {
            eb.setIdcard(null);
            eb.setRegion(null);
            eb.setCity(null);
            eb.setStreet(null);
            eb.setArea(null);
            eb.setApplicationId(null);
        }
        PageInfo<BorrowLoanInfoBean> pageInfo = new PageInfo<BorrowLoanInfoBean>(beanList);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(beanList);
        return pdr;
    }

	@Override
	public BorrowLoanRegion getAttrById(Integer id,Integer pid) {
		return this.borrowLoanInfoMapper.getAttrById(id,pid);
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
        return borrowLoanInfoMapper.selectCountInfoByOrderId(orderId);
    }
}
