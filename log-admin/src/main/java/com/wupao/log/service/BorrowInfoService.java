package com.wupao.log.service;

import com.wupao.log.pojo.BorrowLoanInfoBean;
import com.wupao.log.pojo.BorrowLoanRegion;
import com.wupao.log.utils.PageDataResult;

/**
 * @项目名称：lyd-admin
 * @类名称：BorrowInfoService
 * @类描述： 极速接接口类
 * @创建人：汪正章
 * @创建时间：2018年9月5日 下午7:11:16
 * @version
 */
public interface BorrowInfoService {
    /*
     * @Author 汪正章
     * @Description 查询所有极速借借款信息
     * @Date 2018/9/6 9:17
     * @Param 
     * @**return** 
     **/
    PageDataResult selectCreditInfoList(BorrowLoanInfoBean borrow, String startDate, String endDate, int page, int limit);


    /*
     * @Author 汪正章
     * @Description 绑定极速借借款信息用户id
     * @Date 2018/9/6 9:39
     * @Param
     * @**return**
     **/
    int bindingUserId(String userID, String orid);
    /*
     * @Author 汪正章
     * @Description 查看极速借贷款资料详情
     * @Date 2018/9/6 13:30
     * @Param
     * @**return**
     **/
    BorrowLoanInfoBean selectBorrowDetailInfo(String orderid, String userid);

   /*
    * @Author 汪正章
    * @Description 审批
    * @Date 2018/9/6 15:07
    * @Param
    * @**return**
    **/
    String updateCreditInfo(Integer status, String mark, String orderID);
    
    /**
     * 
     * @描述：根据id查询极速借地址信息
     * @创建人：樊诚
     * @创建时间：2018年10月10日 下午4:56:13
     * @param id
     * @return
     */
    BorrowLoanRegion getAttrById(Integer id,Integer pid);

    /*
     * @Author 汪正章
     * @Description 根据订单编号查询信息是否存在
     * @Date 2018/10/22 14:05
     * @Param String orderId
     * @**return** Integer
     **/
    Integer selectCountInfoByOrderId(String orderId);

}
