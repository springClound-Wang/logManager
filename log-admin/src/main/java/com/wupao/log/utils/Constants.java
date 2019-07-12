package com.wupao.log.utils;
/**   
* @Title: Constants.java
* @Description: TODO(常量类) 
* @author 汪正章 
* @date 2018年4月2日下午5:09:31 
* @Copyright:上海舞泡网络科技有限公司
* @version V1.0   
*/
public interface Constants {
	Integer HTTP_200_CODE = 200;
	Integer HTTP_500_CODE = 500;
	String HTTP_200_NAME = "msg";
	String HTTP_SUCCESS_NAME="success";
	String HTTP_CODE_NAME="code";
	String HTTP_DATA_NAME="data";
	/**
	 * 删除标识正常状态为1
	 */
	Integer DELETEFLAG_NORMAL=1;
	/**
	 * 删除标识删除状态为2
	 */
	Integer DELETEFLAG_EXCEPTIONAL=2;
	/**
	 * request中对象存放贷款资料详情的键
	 */
	String CREDITDEATILINFO="credit";
	/**
	 * 省的类型
	 */
	Integer CREDIT_PROVINCE_TYPE=1;
	/**
	 * 市的类型
	 */
	Integer CREDIT_CITY_TYPE=2;
	/**
	 * 区的类型
	 */
	Integer CREDIT_AREA_TYPE=3;
	/**
	 * 身份证字段
	 */
	String IDCARD="IDcard";
	/**
	 * 银行卡字段
	 */
	String CARDNUMBER="cardNumber";
	
	/**
	 * 家庭-省字段
	 */
	String FAM_REGION="region";
	/**
	 * 家庭-市字段
	 */
	String FAM_CITY="city";
	/**
	 * 家庭-区字段
	 */
	String FAM_AREA="area";
	
	/**
	 * 工作-省字段
	 */
	String WORK_REGION="workRegion";
	/**
	 * 工作-市字段
	 */
	String WORK_CITY="workCity";
	/**
	 * 工作-区字段
	 */
	String WORK_AREA="workArea";
	/**
	 * 合作商字段
	 */
	String BANKID="bankid";
	/**
	 * 宜信
	 */
	Integer BANKID_YIXIN=1;
	/**
	 * 极速借
	 */
	Integer BANKID_JISUJIE=2;
	/**
	 * 当前页码
	 */
	String CURRENTPAGE="currentPage";
	/**
	 * 一整天 cookies 过期时间  单位秒
	 */
	Integer ALLDAY=86400;
	/**
	 * 一小时 cookies 过期时间  单位秒
	 */
	Integer ANHOUR=3600;
	/**
	 * 客服角色code customerService
	 */
	String ROLENAME_CUSTOMER_SERVIC="customerService";
	/**
	 * 图片存储地址的key image_address
	 */
	String IMAGE_ADDRESS="image_address";
	String INDEX="index";

}
