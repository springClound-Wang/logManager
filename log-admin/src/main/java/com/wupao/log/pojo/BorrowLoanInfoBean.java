package com.wupao.log.pojo;

import java.math.BigDecimal;

/**
 * @author 汪正章
 * @ClassName JisujieController
 * @create 2018-08-30 14:22
 * @desc 极速借贷款类
 * @Version 1.0V
 **/
public class BorrowLoanInfoBean {
    /**
     * 订单编号
     */
    private String orderid;
    /**
     * 用户编号
     */
    private String userid;

    private Integer bankid;

    private String name;

    private Integer sex;

    private String idcard;

    private String cardnumber;

    private String number;

    private String region;

    private String city;

    private String area;

    private String street;

    private String workregion;

    private String workcity;

    private String workarea;

    private String workstreet;

    private Integer schemeid;

    private BigDecimal money;

    private Integer creditusage;
    /**
     * 备注
     */
    private String mark;
    /**
     * 提交状态--提交给极速借
     */
    private Integer applystate;

    private String resreason;

    private Integer status;

    private String createtime;

    private String updatetime;

    private String applicationId;

    /**
     * 身份证正面照片地址
     */
    private String pic1;
    /**
     * 身份证反面照片地址
     */
    private String pic2;
    /**
     *身份证半身正面照片地址
     */
    private String pic3;

    /**
     * 贷款期限
     */
    private Integer repayPeriod;
    // 还款方式（1等额本息 2 先息后本 3阶梯贷）
    private Integer repayMethod;

    /**
     * 家庭联系人姓名
     */
    private String linkName1;
    /**
     *  家庭联系人类型
     */
    private Integer linkType1;
    /**
     * 家庭联系人手机号
     */
    private String linkNumber1;
    /**
     * 工作联系人姓名
     */
    private String linkName2;
    /**
     * 工作联系人类型
     */
    private Integer linkType2;
    /**
     * 工作联系人手机号
     */
    private String linkNumber2;
    /**
     * 其他联系人姓名
     */
    private String linkName3;
    /**
     * 其他联系人类型
     */
    private Integer linkType3;
    /**
     * 其他联系人手机号
     */
    private String linkNumber3;
    
    // ****极速借借款信息
    /**
     * 审核状态（1:提交申请、2:审核通过、3:审核放弃、4:审核拒绝）放款状态(5: 待资金方放款 6: 放款完成)还款结清状态（7:还款完成）
     */
    private Integer loanStatus; 
    /**
     * 描述
     */
    private String desc;
    /**
     * 通知时间
     */
    private String notifyTime;
    /**
     * 消息来源类型
     */
    private Integer sourceType;
    private String loanBankCard;
    private String loanBankName;
    private Integer loanType;

    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkName1() {
        return linkName1;
    }

    public void setLinkName1(String linkName1) {
        this.linkName1 = linkName1;
    }

    public Integer getLinkType1() {
        return linkType1;
    }

    public void setLinkType1(Integer linkType1) {
        this.linkType1 = linkType1;
    }

    public String getLinkNumber1() {
        return linkNumber1;
    }

    public void setLinkNumber1(String linkNumber1) {
        this.linkNumber1 = linkNumber1;
    }

    public String getLinkName2() {
        return linkName2;
    }

    public void setLinkName2(String linkName2) {
        this.linkName2 = linkName2;
    }

    public Integer getLinkType2() {
        return linkType2;
    }

    public void setLinkType2(Integer linkType2) {
        this.linkType2 = linkType2;
    }

    public String getLinkNumber2() {
        return linkNumber2;
    }

    public void setLinkNumber2(String linkNumber2) {
        this.linkNumber2 = linkNumber2;
    }

    public String getLinkName3() {
        return linkName3;
    }

    public void setLinkName3(String linkName3) {
        this.linkName3 = linkName3;
    }

    public Integer getLinkType3() {
        return linkType3;
    }

    public void setLinkType3(Integer linkType3) {
        this.linkType3 = linkType3;
    }

    public String getLinkNumber3() {
        return linkNumber3;
    }

    public void setLinkNumber3(String linkNumber3) {
        this.linkNumber3 = linkNumber3;
    }

    public Integer getRepayPeriod() {
        return repayPeriod;
    }

    public void setRepayPeriod(Integer repayPeriod) {
        this.repayPeriod = repayPeriod;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getBankid() {
        return bankid;
    }

    public void setBankid(Integer bankid) {
        this.bankid = bankid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber == null ? null : cardnumber.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getWorkregion() {
        return workregion;
    }

    public void setWorkregion(String workregion) {
        this.workregion = workregion == null ? null : workregion.trim();
    }

    public String getWorkcity() {
        return workcity;
    }

    public void setWorkcity(String workcity) {
        this.workcity = workcity == null ? null : workcity.trim();
    }

    public String getWorkarea() {
        return workarea;
    }

    public void setWorkarea(String workarea) {
        this.workarea = workarea == null ? null : workarea.trim();
    }

    public String getWorkstreet() {
        return workstreet;
    }

    public void setWorkstreet(String workstreet) {
        this.workstreet = workstreet == null ? null : workstreet.trim();
    }

    public Integer getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(Integer schemeid) {
        this.schemeid = schemeid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getCreditusage() {
        return creditusage;
    }

    public void setCreditusage(Integer creditusage) {
        this.creditusage = creditusage;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public Integer getApplystate() {
        return applystate;
    }

    public void setApplystate(Integer applystate) {
        this.applystate = applystate;
    }

    public String getResreason() {
        return resreason;
    }

    public void setResreason(String resreason) {
        this.resreason = resreason == null ? null : resreason.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId == null ? null : applicationId.trim();
    }
    public Integer getRepayMethod() {
        return repayMethod;
    }

    public void setRepayMethod(Integer repayMethod) {
        this.repayMethod = repayMethod;
    }

    public Integer getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(Integer loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public String getLoanBankCard() {
		return loanBankCard;
	}

	public void setLoanBankCard(String loanBankCard) {
		this.loanBankCard = loanBankCard;
	}

	public String getLoanBankName() {
		return loanBankName;
	}

	public void setLoanBankName(String loanBankName) {
		this.loanBankName = loanBankName;
	}

	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

    @Override
    public String toString() {
        return "BorrowLoanInfoBean{" +
                "orderid='" + orderid + '\'' +
                ", userid='" + userid + '\'' +
                ", bankid=" + bankid +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", idcard='" + idcard + '\'' +
                ", cardnumber='" + cardnumber + '\'' +
                ", number='" + number + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", street='" + street + '\'' +
                ", workregion='" + workregion + '\'' +
                ", workcity='" + workcity + '\'' +
                ", workarea='" + workarea + '\'' +
                ", workstreet='" + workstreet + '\'' +
                ", schemeid=" + schemeid +
                ", money=" + money +
                ", creditusage=" + creditusage +
                ", mark='" + mark + '\'' +
                ", applystate=" + applystate +
                ", resreason='" + resreason + '\'' +
                ", status=" + status +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", pic1='" + pic1 + '\'' +
                ", pic2='" + pic2 + '\'' +
                ", pic3='" + pic3 + '\'' +
                ", repayPeriod=" + repayPeriod +
                ", repayMethod=" + repayMethod +
                ", linkName1='" + linkName1 + '\'' +
                ", linkType1=" + linkType1 +
                ", linkNumber1='" + linkNumber1 + '\'' +
                ", linkName2='" + linkName2 + '\'' +
                ", linkType2=" + linkType2 +
                ", linkNumber2='" + linkNumber2 + '\'' +
                ", linkName3='" + linkName3 + '\'' +
                ", linkType3=" + linkType3 +
                ", linkNumber3='" + linkNumber3 + '\'' +
                ", loanStatus=" + loanStatus +
                ", desc='" + desc + '\'' +
                ", notifyTime='" + notifyTime + '\'' +
                ", sourceType=" + sourceType +
                ", loanBankCard='" + loanBankCard + '\'' +
                ", loanBankName='" + loanBankName + '\'' +
                ", loanType=" + loanType +
                ", email='" + email + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}