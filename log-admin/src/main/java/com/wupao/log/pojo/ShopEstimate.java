package com.wupao.log.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ShopEstimate {
    private Integer id;

    private String userid;/*会员ID*/

    private Integer shoptype;/*店铺类型（1 淘宝 2 天猫 3 京东）*/

    private Integer parm1;/*估价参数1（1店铺名称 2店铺地址 3旺旺名称）*/

    private String parm2;/*估价参数2*/

    private BigDecimal parm3;/*估价参数3（月均支付金额）*/

    private BigDecimal limit;/*额度*/

    private Integer status;/*状态标识(1正常 2 删除)*/

    private String mark;/*备注*/

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getShoptype() {
        return shoptype;
    }

    public void setShoptype(Integer shoptype) {
        this.shoptype = shoptype;
    }

    public Integer getParm1() {
        return parm1;
    }

    public void setParm1(Integer parm1) {
        this.parm1 = parm1;
    }

    public String getParm2() {
        return parm2;
    }

    public void setParm2(String parm2) {
        this.parm2 = parm2 == null ? null : parm2.trim();
    }

    public BigDecimal getParm3() {
        return parm3;
    }

    public void setParm3(BigDecimal parm3) {
        this.parm3 = parm3;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	@Override
	public String toString() {
		return "ShopEstimate [id=" + id + ", userid=" + userid + ", shoptype="
				+ shoptype + ", parm1=" + parm1 + ", parm2=" + parm2
				+ ", parm3=" + parm3 + ", limit=" + limit + ", status="
				+ status + ", mark=" + mark + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + "]";
	}
    
}