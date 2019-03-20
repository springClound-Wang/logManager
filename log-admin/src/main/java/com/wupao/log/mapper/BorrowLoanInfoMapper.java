package com.wupao.log.mapper;

import com.wupao.log.pojo.BorrowLoanInfoBean;
import com.wupao.log.pojo.BorrowLoanRegion;
import com.wupao.log.pojo.JisujieLoan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 汪正章
 * @ClassName BorrowLoanInfoMapper
 * @create 2018-09-05 17:58
 * @desc 极速借贷款mapper类
 * @Version 1.0V
 **/
@Mapper
public interface BorrowLoanInfoMapper {
    /**
     *
     * @Description 方法描述: 查询所有贷款记录信息
     * @return  List<EcCreditInfoBean>
     * @author 汪正章
     * @date  2018年9月6日 下午1:37:41
     * @throws
     */
    List<BorrowLoanInfoBean> selectCreditInfoList(@Param("cib") BorrowLoanInfoBean cib, @Param("startDate") String startDate, @Param("endDate") String endDate);


    /**
     *
     * @Description 方法描述: 绑定极速借贷款信息用户id
     * @return  int
     * @author 汪正章
     * @date  2018年4月3日 下午3:37:41
     * @throws
     */
    int bindingUserId(@Param("userId")String userId, @Param("orderID")String orderID);
    /*
     * @Author 汪正章
     * @Description 查看极速借贷款资料详情
     * @Date 2018/9/6 13:29
     * @Param
     * @**return**
     **/
    BorrowLoanInfoBean selectBorrowDetailInfo(@Param("rid") String rid, @Param("uid") String uid);
    /*
     * @Author 汪正章
     * @Description 根据订单号审批状态和备注
     * @Date 2018/9/6 15:06
     * @Param
     * @**return**
     **/
    int updateCreditInfo(@Param("status")Integer status,@Param("mark")String mark,@Param("orderID")String orderID);

    /**
     * 
     * @描述：查询极速借借款信息
     * @创建人：樊诚
     * @创建时间：2018年10月10日 下午3:13:36
     * @param applicationId
     * @return
     */
    JisujieLoan selectJiSujieLoanInfo(@Param("applicationId")String applicationId);
    
    /**
     * 
     * @描述：根据id查询极速借地址信息 
     * @创建人：樊诚
     * @创建时间：2018年10月10日 下午4:54:49
     * @param id
     * @return
     */
    BorrowLoanRegion getAttrById(@Param("id")Integer id,@Param("pid")Integer pid);
    /**
     *
     * @描述：根据applicationid查询极速借信息的orderID
     * @创建人：樊诚
     * @创建时间：2018年10月10日 下午4:54:49
     * @param applicationId
     * @return String
     */
    String getLoanInfoByAid(@Param("applicationId")String applicationId);
    /*
     * @Author 汪正章
     * @Description 根据订单编号查询信息是否存在
     * @Date 2018/10/22 14:05
     * @Param String orderId
     * @**return** Integer
     **/
    Integer selectCountInfoByOrderId(@Param("orderId") String orderId);

}