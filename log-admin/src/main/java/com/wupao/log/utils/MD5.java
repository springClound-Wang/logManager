package com.wupao.log.utils;

import java.security.*;

/**
 * @项目名称： wupao-microservice
 * @类名称： MD5
 * @类描述：MD5加密工具类
 * @创建人： 李瑞兆
 * @创建时间： 2018年1月6日 	上午10:56:33
 * @version:
 */
public class MD5 {
	
    /**
     * @描述：接口加密
     * @创建人：李瑞兆
     * @创建时间：2018年1月6日上午10:59:47
     * @param s 
     * @return
     */
    public final static synchronized String TwoMD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '9', '7', '8', '6', 'a', 'c', 'b', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes("utf-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
		String str=MD5.TwoMD5("_jas2018smi_"+"1");
    	System.out.println("str=="+str);
    	
	}
}