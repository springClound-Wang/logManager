package com.wupao.log.pojo;

import java.util.Date;

public class LoanShopInfo {
    private Integer id;

    private String uuid;

    private Integer shopType;/*店铺类型 1=天猫  2=淘宝*/

    private String openDate;/*经营年限*/

    private String describeScore;/*宝贝与描述相符得分*/

    private String describeTradeLevel;/*宝贝与描述相符—比同行业平均*/

    private String serviceScore;/*卖家的服务态度得分*/

    private String serviceTradeLevel;/*卖家的服务态度—比同行业平均水平*/

    private String logisticsScore;/*物流服务的质量得分*/

    private String logisticsTradeLevel;/*物流服务的质量—比同行业平均水平*/

    private String generalMisdeeds;/*一般违规*/

    private String seriousMisdeeds;/*严重违规*/

    private String sellingMisdeeds;/*售假违规*/

    private String shopName;/*店铺名称*/

    private String realName;/*真实姓名*/

    private String certificatesType;/*证件类型*/

    private String certificatesNo;/*证件号码*/

    private String registerTime;/*注册时间*/

    private String bindingEmail;/*绑定邮箱*/

    private String bindingPhone;/*绑定手机*/

    private String bindingTaobaoId;/*绑定淘宝ID*/

    private String alipayAccount;/*支付宝账号*/

    private String company;/*公司名称*/

    private String businessNumber;/*工商营业执照注册号*/

    private String corporation;/*法人姓名*/

    private String registerAddress;/*公司注册地址*/

    private String contactAddress;/*公司联系地址*/

    private String companyNature;/*公司性质*/

    private String registerMoney;/*注册资金*/

    private String annualSales;/*年销售额*/

    private String businessScope;/*营业执照经营范围*/

    private String establishTime;/*公司成立时间*/

    private String mainChannel;/*主营渠道*/

    private String orderCredit;/*订单贷款*/

    private String anyTimeCredit;/*随借随还*/

    private String equalCredit;/*等额本金*/

    private String monthlyCredit;/*按月付息12个月*/

    private String groupCredit;/*天猫专属（组合贷）*/

    private String huabeiMoney;/*花呗额度*/

    private String useCreditNum;/*使用中的贷款数量*/

    private Date insertTime;/*创建时间*/

    private String creditSum;/*贷款总额*/

    private String uniqueFlag;/*唯一标识（在盛专用）*/

    private String mainCategory;/*主营类目*/

    private String oneLevelCategory;/*一级类目*/

    private String scaleOfTaxpayer;/*纳税人规模*/

    private String tmallBond;/*天猫保证金*/

    private String annualFee;/*技术年费*/

    private String turnoverVolume;/*当前成交额*/

    private String shopUrl;/*店铺地址*/

    private String clearNum;/*订单清洗次数*/

    private String beatMerchant;/*售后服务综合指标打败商家占比*/

    private String disputeRefundRate;/*纠纷退款率*/

    private String refundEndTime;/*退款完结时长*/

    private String refundSelfCompletionRate;/*退款自主完结率*/

    private String shippingAddress;/*发货地址*/

    private String returnAddress;/*退货地址*/

    private String describeScoreResult;/*描述相符得分打分结果*/

    private String serviceScoreResult;/*服务态度得分打分结果*/

    private String logisticsScoreResult;/*物流速度得分打分结果*/

    private String numberOfPoints;/*共打分人数*/

    private String credit;/*信誉*/

    private String creditValue;/*信誉值*/

    private String praiseRate;/*卖家信用评价展示好评率*/

    private String alipayAuthenticationTime;/*支付宝个人认证时间*/

    private String oldCustomerNum;/*老客户数*/

    private String collectionQuantity;/*收藏量*/

    private String fireInsurance;/*消保金*/

    private String businessAddress;/*经营地址*/

    private String creditComposition;/*历史信用构成*/

    private String estimateResult;/*估价结果*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getDescribeScore() {
		return describeScore;
	}

	public void setDescribeScore(String describeScore) {
		this.describeScore = describeScore;
	}

	public String getDescribeTradeLevel() {
		return describeTradeLevel;
	}

	public void setDescribeTradeLevel(String describeTradeLevel) {
		this.describeTradeLevel = describeTradeLevel;
	}

	public String getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(String serviceScore) {
		this.serviceScore = serviceScore;
	}

	public String getServiceTradeLevel() {
		return serviceTradeLevel;
	}

	public void setServiceTradeLevel(String serviceTradeLevel) {
		this.serviceTradeLevel = serviceTradeLevel;
	}

	public String getLogisticsScore() {
		return logisticsScore;
	}

	public void setLogisticsScore(String logisticsScore) {
		this.logisticsScore = logisticsScore;
	}

	public String getLogisticsTradeLevel() {
		return logisticsTradeLevel;
	}

	public void setLogisticsTradeLevel(String logisticsTradeLevel) {
		this.logisticsTradeLevel = logisticsTradeLevel;
	}

	public String getGeneralMisdeeds() {
		return generalMisdeeds;
	}

	public void setGeneralMisdeeds(String generalMisdeeds) {
		this.generalMisdeeds = generalMisdeeds;
	}

	public String getSeriousMisdeeds() {
		return seriousMisdeeds;
	}

	public void setSeriousMisdeeds(String seriousMisdeeds) {
		this.seriousMisdeeds = seriousMisdeeds;
	}

	public String getSellingMisdeeds() {
		return sellingMisdeeds;
	}

	public void setSellingMisdeeds(String sellingMisdeeds) {
		this.sellingMisdeeds = sellingMisdeeds;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCertificatesType() {
		return certificatesType;
	}

	public void setCertificatesType(String certificatesType) {
		this.certificatesType = certificatesType;
	}

	public String getCertificatesNo() {
		return certificatesNo;
	}

	public void setCertificatesNo(String certificatesNo) {
		this.certificatesNo = certificatesNo;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getBindingEmail() {
		return bindingEmail;
	}

	public void setBindingEmail(String bindingEmail) {
		this.bindingEmail = bindingEmail;
	}

	public String getBindingPhone() {
		return bindingPhone;
	}

	public void setBindingPhone(String bindingPhone) {
		this.bindingPhone = bindingPhone;
	}

	public String getBindingTaobaoId() {
		return bindingTaobaoId;
	}

	public void setBindingTaobaoId(String bindingTaobaoId) {
		this.bindingTaobaoId = bindingTaobaoId;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	public String getRegisterMoney() {
		return registerMoney;
	}

	public void setRegisterMoney(String registerMoney) {
		this.registerMoney = registerMoney;
	}

	public String getAnnualSales() {
		return annualSales;
	}

	public void setAnnualSales(String annualSales) {
		this.annualSales = annualSales;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getEstablishTime() {
		return establishTime;
	}

	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}

	public String getMainChannel() {
		return mainChannel;
	}

	public void setMainChannel(String mainChannel) {
		this.mainChannel = mainChannel;
	}

	public String getOrderCredit() {
		return orderCredit;
	}

	public void setOrderCredit(String orderCredit) {
		this.orderCredit = orderCredit;
	}

	public String getAnyTimeCredit() {
		return anyTimeCredit;
	}

	public void setAnyTimeCredit(String anyTimeCredit) {
		this.anyTimeCredit = anyTimeCredit;
	}

	public String getEqualCredit() {
		return equalCredit;
	}

	public void setEqualCredit(String equalCredit) {
		this.equalCredit = equalCredit;
	}

	public String getMonthlyCredit() {
		return monthlyCredit;
	}

	public void setMonthlyCredit(String monthlyCredit) {
		this.monthlyCredit = monthlyCredit;
	}

	public String getGroupCredit() {
		return groupCredit;
	}

	public void setGroupCredit(String groupCredit) {
		this.groupCredit = groupCredit;
	}

	public String getHuabeiMoney() {
		return huabeiMoney;
	}

	public void setHuabeiMoney(String huabeiMoney) {
		this.huabeiMoney = huabeiMoney;
	}

	public String getUseCreditNum() {
		return useCreditNum;
	}

	public void setUseCreditNum(String useCreditNum) {
		this.useCreditNum = useCreditNum;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getCreditSum() {
		return creditSum;
	}

	public void setCreditSum(String creditSum) {
		this.creditSum = creditSum;
	}

	public String getUniqueFlag() {
		return uniqueFlag;
	}

	public void setUniqueFlag(String uniqueFlag) {
		this.uniqueFlag = uniqueFlag;
	}

	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getOneLevelCategory() {
		return oneLevelCategory;
	}

	public void setOneLevelCategory(String oneLevelCategory) {
		this.oneLevelCategory = oneLevelCategory;
	}

	public String getScaleOfTaxpayer() {
		return scaleOfTaxpayer;
	}

	public void setScaleOfTaxpayer(String scaleOfTaxpayer) {
		this.scaleOfTaxpayer = scaleOfTaxpayer;
	}

	public String getTmallBond() {
		return tmallBond;
	}

	public void setTmallBond(String tmallBond) {
		this.tmallBond = tmallBond;
	}

	public String getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(String annualFee) {
		this.annualFee = annualFee;
	}

	public String getTurnoverVolume() {
		return turnoverVolume;
	}

	public void setTurnoverVolume(String turnoverVolume) {
		this.turnoverVolume = turnoverVolume;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getClearNum() {
		return clearNum;
	}

	public void setClearNum(String clearNum) {
		this.clearNum = clearNum;
	}

	public String getBeatMerchant() {
		return beatMerchant;
	}

	public void setBeatMerchant(String beatMerchant) {
		this.beatMerchant = beatMerchant;
	}

	public String getDisputeRefundRate() {
		return disputeRefundRate;
	}

	public void setDisputeRefundRate(String disputeRefundRate) {
		this.disputeRefundRate = disputeRefundRate;
	}

	public String getRefundEndTime() {
		return refundEndTime;
	}

	public void setRefundEndTime(String refundEndTime) {
		this.refundEndTime = refundEndTime;
	}

	public String getRefundSelfCompletionRate() {
		return refundSelfCompletionRate;
	}

	public void setRefundSelfCompletionRate(String refundSelfCompletionRate) {
		this.refundSelfCompletionRate = refundSelfCompletionRate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getReturnAddress() {
		return returnAddress;
	}

	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}

	public String getDescribeScoreResult() {
		return describeScoreResult;
	}

	public void setDescribeScoreResult(String describeScoreResult) {
		this.describeScoreResult = describeScoreResult;
	}

	public String getServiceScoreResult() {
		return serviceScoreResult;
	}

	public void setServiceScoreResult(String serviceScoreResult) {
		this.serviceScoreResult = serviceScoreResult;
	}

	public String getLogisticsScoreResult() {
		return logisticsScoreResult;
	}

	public void setLogisticsScoreResult(String logisticsScoreResult) {
		this.logisticsScoreResult = logisticsScoreResult;
	}

	public String getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(String numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(String creditValue) {
		this.creditValue = creditValue;
	}

	public String getPraiseRate() {
		return praiseRate;
	}

	public void setPraiseRate(String praiseRate) {
		this.praiseRate = praiseRate;
	}

	public String getAlipayAuthenticationTime() {
		return alipayAuthenticationTime;
	}

	public void setAlipayAuthenticationTime(String alipayAuthenticationTime) {
		this.alipayAuthenticationTime = alipayAuthenticationTime;
	}

	public String getOldCustomerNum() {
		return oldCustomerNum;
	}

	public void setOldCustomerNum(String oldCustomerNum) {
		this.oldCustomerNum = oldCustomerNum;
	}

	public String getCollectionQuantity() {
		return collectionQuantity;
	}

	public void setCollectionQuantity(String collectionQuantity) {
		this.collectionQuantity = collectionQuantity;
	}

	public String getFireInsurance() {
		return fireInsurance;
	}

	public void setFireInsurance(String fireInsurance) {
		this.fireInsurance = fireInsurance;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getCreditComposition() {
		return creditComposition;
	}

	public void setCreditComposition(String creditComposition) {
		this.creditComposition = creditComposition;
	}

	public String getEstimateResult() {
		return estimateResult;
	}

	public void setEstimateResult(String estimateResult) {
		this.estimateResult = estimateResult;
	}

	@Override
	public String toString() {
		return "LoanShopInfo [id=" + id + ", uuid=" + uuid + ", shopType="
				+ shopType + ", openDate=" + openDate + ", describeScore="
				+ describeScore + ", describeTradeLevel=" + describeTradeLevel
				+ ", serviceScore=" + serviceScore + ", serviceTradeLevel="
				+ serviceTradeLevel + ", logisticsScore=" + logisticsScore
				+ ", logisticsTradeLevel=" + logisticsTradeLevel
				+ ", generalMisdeeds=" + generalMisdeeds + ", seriousMisdeeds="
				+ seriousMisdeeds + ", sellingMisdeeds=" + sellingMisdeeds
				+ ", shopName=" + shopName + ", realName=" + realName
				+ ", certificatesType=" + certificatesType
				+ ", certificatesNo=" + certificatesNo + ", registerTime="
				+ registerTime + ", bindingEmail=" + bindingEmail
				+ ", bindingPhone=" + bindingPhone + ", bindingTaobaoId="
				+ bindingTaobaoId + ", alipayAccount=" + alipayAccount
				+ ", company=" + company + ", businessNumber=" + businessNumber
				+ ", corporation=" + corporation + ", registerAddress="
				+ registerAddress + ", contactAddress=" + contactAddress
				+ ", companyNature=" + companyNature + ", registerMoney="
				+ registerMoney + ", annualSales=" + annualSales
				+ ", businessScope=" + businessScope + ", establishTime="
				+ establishTime + ", mainChannel=" + mainChannel
				+ ", orderCredit=" + orderCredit + ", anyTimeCredit="
				+ anyTimeCredit + ", equalCredit=" + equalCredit
				+ ", monthlyCredit=" + monthlyCredit + ", groupCredit="
				+ groupCredit + ", huabeiMoney=" + huabeiMoney
				+ ", useCreditNum=" + useCreditNum + ", insertTime="
				+ insertTime + ", creditSum=" + creditSum + ", uniqueFlag="
				+ uniqueFlag + ", mainCategory=" + mainCategory
				+ ", oneLevelCategory=" + oneLevelCategory
				+ ", scaleOfTaxpayer=" + scaleOfTaxpayer + ", tmallBond="
				+ tmallBond + ", annualFee=" + annualFee + ", turnoverVolume="
				+ turnoverVolume + ", shopUrl=" + shopUrl + ", clearNum="
				+ clearNum + ", beatMerchant=" + beatMerchant
				+ ", disputeRefundRate=" + disputeRefundRate
				+ ", refundEndTime=" + refundEndTime
				+ ", refundSelfCompletionRate=" + refundSelfCompletionRate
				+ ", shippingAddress=" + shippingAddress + ", returnAddress="
				+ returnAddress + ", describeScoreResult="
				+ describeScoreResult + ", serviceScoreResult="
				+ serviceScoreResult + ", logisticsScoreResult="
				+ logisticsScoreResult + ", numberOfPoints=" + numberOfPoints
				+ ", credit=" + credit + ", creditValue=" + creditValue
				+ ", praiseRate=" + praiseRate + ", alipayAuthenticationTime="
				+ alipayAuthenticationTime + ", oldCustomerNum="
				+ oldCustomerNum + ", collectionQuantity=" + collectionQuantity
				+ ", fireInsurance=" + fireInsurance + ", businessAddress="
				+ businessAddress + ", creditComposition=" + creditComposition
				+ ", estimateResult=" + estimateResult + "]";
	}
}