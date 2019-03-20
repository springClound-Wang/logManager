/**
 * 
 */
package com.wupao.log.pojo;

import java.math.BigDecimal;

/**
 * @项目名称：wupao-lyd
 * @类名称：EcCreditdApplyBean
 * @类描述：贷款申请Bean
 * @创建人：席在盛
 * @创建时间：2016年6月15日 上午11:09:24
 * @version
 */

public class CreditApplyBean {
	
	//主键
	private String orderID;
	//用户ID
	private String userID;
	//姓名
	private String name;
	//合作商(1-宜信)
	private Integer bankID;
	//领取人(用户id)
	private String receiver;
	//手机号码
	private String number;
	//网店类型
	private Integer shopType;
	//借入金额  
	private BigDecimal money;
	//渠道商
	private Integer channel;
	//借款状态标识
	private Integer status;
	//备注（审核备注）
	private String mark;
	//种类区分1--电商贷款 2--分期购店贷款
	private Integer kind;
	//创建时间
	private String createTime;
	//更新时间
	private String updateTime;
	/**
	 * 来源 0是PC  1是M
	 */
	private Integer source;
	/**
	 * 领取人姓名
	 */
	private String receivername;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
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
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}
	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
	 * @return the kind
	 */
	public Integer getKind() {
		return kind;
	}
	/**
	 * @param kind the kind to set
	 */
	public void setKind(Integer kind) {
		this.kind = kind;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the shopType
	 */
	public Integer getShopType() {
		return shopType;
	}
	/**
	 * @param shopType the shopType to set
	 */
	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}
	/**
	 * @return the bankID
	 */
	public Integer getBankID() {
		return bankID;
	}
	/**
	 * @param bankID the bankID to set
	 */
	public void setBankID(Integer bankID) {
		this.bankID = bankID;
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
	 * @return the channel
	 */
	public Integer getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Integer channel) {
		this.channel = channel;
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
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}

    @Override
    public String toString() {
        return "CreditApplyBean{" +
                "orderID='" + orderID + '\'' +
                ", userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", bankID=" + bankID +
                ", receiver='" + receiver + '\'' +
                ", number='" + number + '\'' +
                ", shopType=" + shopType +
                ", money=" + money +
                ", channel=" + channel +
                ", status=" + status +
                ", mark='" + mark + '\'' +
                ", kind=" + kind +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", source='" + source + '\'' +
                ", receivername='" + receivername + '\'' +
                '}';
    }
}
