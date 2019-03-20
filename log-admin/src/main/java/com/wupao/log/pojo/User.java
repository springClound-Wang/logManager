package com.wupao.log.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class User {
	private String userID;

	private String username;

	private String sex;

	private String mobile;

	private String email;

	private String salt;

	private String paypwd;

	private String password;

	private Integer groupId;

	private Integer depmtId;

	private Date insertTime;

	private Date updateTime;

	private Integer updateUid;

	private Integer insertUid;

	private String headImg;

	private Integer status;

	private Integer levelId;

	private Integer isAuth;

	private String cardId;

	private String realname;

	private BigDecimal ableMoney;

	private BigDecimal txMoney;

	private BigDecimal totalMoney;

	private Integer flag;

	private Date lastTime;

	private Integer education;

	private Integer marriage;

	private Integer children;

	private Integer iscar;

	private Integer house;

	private String pic1;

	private String pic2;
	// 地址
	private String province;
	private String city;
	private String area;
	private String street;

	// 用户联系人
	private String linkName1;
	private String linkName2;
	private String linkName3;
	private String linkNumber1;
	private String linkNumber2;
	private String linkNumber3;
	private Integer linkType1;
	private Integer linkType2;
	private Integer linkType3;
	
	private Integer uid;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	public String getPaypwd() {
		return paypwd;
	}

	public void setPaypwd(String paypwd) {
		this.paypwd = paypwd == null ? null : paypwd.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getDepmtId() {
		return depmtId;
	}

	public void setDepmtId(Integer depmtId) {
		this.depmtId = depmtId;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdateUid() {
		return updateUid;
	}

	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}

	public Integer getInsertUid() {
		return insertUid;
	}

	public void setInsertUid(Integer insertUid) {
		this.insertUid = insertUid;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg == null ? null : headImg.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Integer getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(Integer isAuth) {
		this.isAuth = isAuth;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId == null ? null : cardId.trim();
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname == null ? null : realname.trim();
	}

	public BigDecimal getAbleMoney() {
		return ableMoney;
	}

	public void setAbleMoney(BigDecimal ableMoney) {
		this.ableMoney = ableMoney;
	}

	public BigDecimal getTxMoney() {
		return txMoney;
	}

	public void setTxMoney(BigDecimal txMoney) {
		this.txMoney = txMoney;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	/**
	 * @return the education
	 */
	public Integer getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(Integer education) {
		this.education = education;
	}

	/**
	 * @return the marriage
	 */
	public Integer getMarriage() {
		return marriage;
	}

	/**
	 * @param marriage the marriage to set
	 */
	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}

	/**
	 * @return the children
	 */
	public Integer getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(Integer children) {
		this.children = children;
	}

	/**
	 * @return the iscar
	 */
	public Integer getIscar() {
		return iscar;
	}

	/**
	 * @param iscar the iscar to set
	 */
	public void setIscar(Integer iscar) {
		this.iscar = iscar;
	}

	/**
	 * @return the house
	 */
	public Integer getHouse() {
		return house;
	}

	/**
	 * @param house the house to set
	 */
	public void setHouse(Integer house) {
		this.house = house;
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
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", sex="
				+ sex + ", mobile=" + mobile + ", email=" + email + ", salt="
				+ salt + ", paypwd=" + paypwd + ", password=" + password
				+ ", groupId=" + groupId + ", depmtId=" + depmtId
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", updateUid=" + updateUid + ", insertUid=" + insertUid
				+ ", headImg=" + headImg + ", status=" + status + ", levelId="
				+ levelId + ", isAuth=" + isAuth + ", cardId=" + cardId
				+ ", realname=" + realname + ", ableMoney=" + ableMoney
				+ ", txMoney=" + txMoney + ", totalMoney=" + totalMoney
				+ ", flag=" + flag + ", lastTime=" + lastTime + ", education="
				+ education + ", marriage=" + marriage + ", children="
				+ children + ", iscar=" + iscar + ", house=" + house
				+ ", pic1=" + pic1 + ", pic2=" + pic2 + ", province="
				+ province + ", city=" + city + ", area=" + area + ", street="
				+ street + ", linkName1=" + linkName1 + ", linkName2="
				+ linkName2 + ", linkName3=" + linkName3 + ", linkNumber1="
				+ linkNumber1 + ", linkNumber2=" + linkNumber2
				+ ", linkNumber3=" + linkNumber3 + ", linkType1=" + linkType1
				+ ", linkType2=" + linkType2 + ", linkType3=" + linkType3
				+ ", uid=" + uid + "]";
	}
}