package com.wupao.log.utils;

import java.math.BigDecimal;

public class CreditInfo {

	//申请编号
	private String orderID;
	// 用户ID
	private Integer userID;
	// 姓名
	private String name;
	// 性别（1--男 2--女）
	private Integer sex;
	// 身份证号码
	private String IDcard;
	// 手机号
	private String number;

	// 贷款金额
	private BigDecimal money;
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
	
	private String guestName;
	private String channelName;
	//区分宜信和极速借
	private String flag;


	/**
	 * @return the userID
	 */
	public Integer getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(Integer userID) {
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

	public String getGuestName() {
		return guestName = guestName == null ? "" : guestName.trim();
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getChannelName() {
		return channelName = channelName == null ? "" : channelName.trim();
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "CreditInfo [orderID=" + orderID + ", userID=" + userID
				+ ", name=" + name + ", sex=" + sex + ", IDcard=" + IDcard
				+ ", number=" + number + ", money=" + money + ", mark=" + mark
				+ ", status=" + status + ", applyState=" + applyState
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", guestName=" + guestName + ", channelName=" + channelName
				+ "]";
	}

	

}
