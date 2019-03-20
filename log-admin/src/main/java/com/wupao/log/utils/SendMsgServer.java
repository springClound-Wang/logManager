package com.wupao.log.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 发送短信验证码
 */
@Component
public class SendMsgServer {

    private static final Logger logger = LoggerFactory
            .getLogger(SendMsgServer.class);
    @Value("${SENDMSG_ACCOUNT}")
    private  String SENDMSG_ACCOUNT;
    @Value("${SENDMSG_PASSWORD}")
    private  String SENDMSG_PASSWORD;
    @Value("${SENDMSG_INTERF_ADDR}")
    private  String  SENDMSG_INTERF_ADDR;
    /**
     *
     * @描述：(公共)发送短消息:
     * @创建人：
     * @创建时间：2016年7月22日 上午10:33:20
     * @param messageStr 发送的消息
     * @param phoneNum   发送的手机号
     * @return  发送成功返回:ok，发送失败返回:no
     */
    public  String SendMsg(String messageStr, String phoneNum) {
        try {
           /* BusinessService bs = new BusinessService();
            bs.setWebService(SENDMSG_INTERF_ADDR);
            long resid = bs.sendBatchMessage(SENDMSG_ACCOUNT, SENDMSG_PASSWORD, phoneNum, messageStr+"【利易达】");
            logger.info("***短信发送连接,手机号["+phoneNum+"],发送返回结果="+messageStr);
            if(resid > 0) {
                return "ok";
            }*/
        }catch(Exception e) {
            logger.info("***SendMsg,Error:"+e.toString());
        }
        return "no";
    }

}
