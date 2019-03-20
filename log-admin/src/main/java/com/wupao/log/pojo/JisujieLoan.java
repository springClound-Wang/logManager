package com.wupao.log.pojo;

import java.math.BigDecimal;
/**
 * 
 * @项目名称：lyd-admin
 * @类名称：JisujieLoan
 * @类描述：极速借借款信息类
 * @创建人：樊诚
 * @创建时间：2018年10月10日 下午5:51:38 
 * @version：
 */
public class JisujieLoan {
    private String messageId;//消息id

    private String uid;//合作用户编号

    private String applicationId;//s申请id

    private Integer status;//审核状态（1:提交申请、2:审核通过、3:审核放弃、4:审核拒绝）放款状态(5: 待资金方放款 6: 放款完成)还款结清状态（7:还款完成）

    private String desc;//描述

    private String notifyTime;//通知时间

    private String formId;//提款ID

    private Integer sourceType;//消息来源类型

    private String content;/*包含json字符串(放款完成的到账类型(1:到银行卡 2:到电子账户)
        注：status不为2时该值为0【type】，收款卡号所属银行【bank_name】，收款卡号【bank_card】，放款金额（单位分）【amount】)*/

    private BigDecimal amount;//借款金额（单位分）

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId == null ? null : applicationId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}