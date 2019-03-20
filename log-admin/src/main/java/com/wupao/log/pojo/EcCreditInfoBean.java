/**
 *
 */
package com.wupao.log.pojo;

import java.math.BigDecimal;

/**
 * @项目名称：lyd-admin
 * @类名称：EcCreditBean
 * @类描述：电商贷款信息Bean
 * @创建人：汪正章
 * @创建时间：2018年4月3日 上午10:56:27
 * @version
 */
public class EcCreditInfoBean {

	//申请编号
	private String orderID;
	// 用户ID
	private String userID;
	// 姓名
	private String name;
	// 性别（1--男 2--女）
	private Integer sex;
	// 身份证号码
	private String IDcard;
	// 手机号
	private String number;
	//大区-华中 华东
	private String region;
	// 城市--北京  浙江
	private String city;
	// 区域--杭州
	private String area;
	// 街道信息
	private String street;

	// 身份证正面照片地址
	private String pic1;
	// 身份证反面照片地址
	private String pic2;
	// 身份证半身正面照片地址
	private String pic3;
	// 身份证半身反面照片地址
	private String pic4;
	//渠道类型
	private Integer bankid;

	//借款方案ID
	private Integer schemeID;


	//家庭联系人姓名
	private String  linkName1;
	// 家庭联系人类型
	private Integer linkType1;
	// 家庭联系人手机号
	private String linkNumber1;
	//工作联系人姓名
	private String  linkName2;
	// 工作联系人类型
	private Integer linkType2;
	// 工作联系人手机号
	private String linkNumber2;
	//其他联系人姓名
	private String  linkName3;
	// 其他联系人类型
	private Integer linkType3;
	// 其他联系人手机号
	private String linkNumber3;

	// 贷款金额
	private BigDecimal money;
	// 贷款用途
	private Integer creditUsage;
	// 还款方式（1等额本息 2 先息后本 3阶梯贷）
	private Integer repayMethod;
	// 贷款期限
	private Integer repayPeriod;
	// 备注
	private String mark;
	// 状态标识
	private Integer status;
	//提交状态--提交给宜信
	private Integer applyState;
	// 创建时间
	private String createTime;
	// 更新时间
	private String updateTime;
	/**
	 * 来源 0是PC  1是M
	 */
	private Integer source;

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return the iDcard
	 */
	public String getIDcard() {
		return IDcard;
	}

	/**
	 * @param iDcard the iDcard to set
	 */
	public void setIDcard(String iDcard) {
		IDcard = iDcard;
	}

	/**
	 * @return the schemeID
	 */
	public Integer getSchemeID() {
		return schemeID;
	}

	/**
	 * @param schemeID the schemeID to set
	 */
	public void setSchemeID(Integer schemeID) {
		this.schemeID = schemeID;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}


	/**
	 * @return the pic1
	 */
	public String getPic1() {
		return pic1;
	}

	/**
	 * @param pic1 the pic1 to set
	 */
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	/**
	 * @return the pic2
	 */
	public String getPic2() {
		return pic2;
	}

	/**
	 * @param pic2 the pic2 to set
	 */
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	/**
	 * @return the pic3
	 */
	public String getPic3() {
		return pic3;
	}

	/**
	 * @param pic3 the pic3 to set
	 */
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	/**
	 * @return the pic4
	 */
	public String getPic4() {
		return pic4;
	}

	/**
	 * @param pic4 the pic4 to set
	 */
	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}

	/**
	 * @return the linkName1
	 */
	public String getLinkName1() {
		return linkName1;
	}

	/**
	 * @param linkName1 the linkName1 to set
	 */
	public void setLinkName1(String linkName1) {
		this.linkName1 = linkName1;
	}

	/**
	 * @return the linkName2
	 */
	public String getLinkName2() {
		return linkName2;
	}

	/**
	 * @param linkName2 the linkName2 to set
	 */
	public void setLinkName2(String linkName2) {
		this.linkName2 = linkName2;
	}

	/**
	 * @return the linkName3
	 */
	public String getLinkName3() {
		return linkName3;
	}

	/**
	 * @param linkName3 the linkName3 to set
	 */
	public void setLinkName3(String linkName3) {
		this.linkName3 = linkName3;
	}

	/**
	 * @return the linkType1
	 */
	public Integer getLinkType1() {
		return linkType1;
	}

	/**
	 * @param linkType1 the linkType1 to set
	 */
	public void setLinkType1(Integer linkType1) {
		this.linkType1 = linkType1;
	}

	/**
	 * @return the linkNumber1
	 */
	public String getLinkNumber1() {
		return linkNumber1;
	}

	/**
	 * @param linkNumber1 the linkNumber1 to set
	 */
	public void setLinkNumber1(String linkNumber1) {
		this.linkNumber1 = linkNumber1;
	}

	/**
	 * @return the linkType2
	 */
	public Integer getLinkType2() {
		return linkType2;
	}

	/**
	 * @param linkType2 the linkType2 to set
	 */
	public void setLinkType2(Integer linkType2) {
		this.linkType2 = linkType2;
	}

	/**
	 * @return the linkNumber2
	 */
	public String getLinkNumber2() {
		return linkNumber2;
	}

	/**
	 * @param linkNumber2 the linkNumber2 to set
	 */
	public void setLinkNumber2(String linkNumber2) {
		this.linkNumber2 = linkNumber2;
	}

	/**
	 * @return the linkType3
	 */
	public Integer getLinkType3() {
		return linkType3;
	}

	/**
	 * @param linkType3 the linkType3 to set
	 */
	public void setLinkType3(Integer linkType3) {
		this.linkType3 = linkType3;
	}

	/**
	 * @return the linkNumber3
	 */
	public String getLinkNumber3() {
		return linkNumber3;
	}

	/**
	 * @param linkNumber3 the linkNumber3 to set
	 */
	public void setLinkNumber3(String linkNumber3) {
		this.linkNumber3 = linkNumber3;
	}

	/**
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @return the creditUsage
	 */
	public Integer getCreditUsage() {
		return creditUsage;
	}

	/**
	 * @param creditUsage the creditUsage to set
	 */
	public void setCreditUsage(Integer creditUsage) {
		this.creditUsage = creditUsage;
	}

	/**
	 * @return the repayMethod
	 */
	public Integer getRepayMethod() {
		return repayMethod;
	}

	/**
	 * @param repayMethod the repayMethod to set
	 */
	public void setRepayMethod(Integer repayMethod) {
		this.repayMethod = repayMethod;
	}

	/**
	 * @return the repayPeriod
	 */
	public Integer getRepayPeriod() {
		return repayPeriod;
	}

	/**
	 * @param repayPeriod the repayPeriod to set
	 */
	public void setRepayPeriod(Integer repayPeriod) {
		this.repayPeriod = repayPeriod;
	}

	/**
	 * @return the mark
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the orderID
	 */
	public String getOrderID() {
		return orderID;
	}

	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	/**
	 * @return the applyState
	 */
	public Integer getApplyState() {
		return applyState;
	}

	/**
	 * @param applyState the applyState to set
	 */
	public void setApplyState(Integer applyState) {
		this.applyState = applyState;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getBankid() {
		return bankid;
	}

	public void setBankid(Integer bankid) {
		this.bankid = bankid;
	}

	@Override
	public String toString() {
		return "EcCreditInfoBean{" +
				"orderID='" + orderID + '\'' +
				", userID='" + userID + '\'' +
				", name='" + name + '\'' +
				", sex=" + sex +
				", IDcard='" + IDcard + '\'' +
				", number='" + number + '\'' +
				", region='" + region + '\'' +
				", city='" + city + '\'' +
				", area='" + area + '\'' +
				", street='" + street + '\'' +
				", pic1='" + pic1 + '\'' +
				", pic2='" + pic2 + '\'' +
				", pic3='" + pic3 + '\'' +
				", pic4='" + pic4 + '\'' +
				", bankid=" + bankid +
				", schemeID=" + schemeID +
				", linkName1='" + linkName1 + '\'' +
				", linkType1=" + linkType1 +
				", linkNumber1='" + linkNumber1 + '\'' +
				", linkName2='" + linkName2 + '\'' +
				", linkType2=" + linkType2 +
				", linkNumber2='" + linkNumber2 + '\'' +
				", linkName3='" + linkName3 + '\'' +
				", linkType3=" + linkType3 +
				", linkNumber3='" + linkNumber3 + '\'' +
				", money=" + money +
				", creditUsage=" + creditUsage +
				", repayMethod=" + repayMethod +
				", repayPeriod=" + repayPeriod +
				", mark='" + mark + '\'' +
				", status=" + status +
				", applyState=" + applyState +
				", createTime='" + createTime + '\'' +
				", updateTime='" + updateTime + '\'' +
				", source='" + source + '\'' +
				'}';
	}
}
