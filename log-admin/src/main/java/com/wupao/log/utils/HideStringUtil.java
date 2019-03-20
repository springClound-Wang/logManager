/**
 * 
 */
package com.wupao.log.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @项目名称：wupao-lyd
 * @类名称：HideStringUtil
 * @类描述：隐藏字符串
 * @创建人：席在盛
 * @创建时间：2016年8月11日 下午3:03:50
 * @version
 */
public class HideStringUtil {

	
	/**
	 * 
	 * @描述：通用隐藏方法
	 * @创建人：席在盛
	 * @创建时间：2016年8月11日 下午3:51:55
	 * @param relString
	 */
	public static String getHideString (String relString) {
		
		if (StringUtils.isNotBlank(relString)) {
			int length=relString.length();
			if (length>1&&length<=5) {
				String start=StringUtils.substring(relString, 0,length-1);
				return start+"***";
			}else if (length>5&&length<=11) {
				String start=StringUtils.substring(relString, 0, 2);
				String end=StringUtils.substring(relString, length-3, length-1);
				return start+"****"+end;
			}else if (length>11) {
				String start=StringUtils.substring(relString, 0, 3);
				String end=StringUtils.substring(relString, length-4, length-1);
				return start+"******"+end;
			}
		}
		return "";
	}
	
	/**
	 * 
	 * @描述：隐藏真实姓名
	 * @创建人：席在盛
	 * @创建时间：2016年8月11日 下午3:04:54
	 * @param realname
	 * @return
	 */
	public static String getHideRealName(String realname) {
		
		if (StringUtils.isNotBlank(realname)) {
			String name=StringUtils.substring(realname,0, 1);
			StringBuffer sb=new StringBuffer();
			for (int i = 1; i < realname.length(); i++) {
				sb.append("*");
			}
			return name+sb.toString();
			
		}
		return "";
	}
	/**
	 * 
	 * @描述：隐藏身份证信息
	 * @创建人：席在盛
	 * @创建时间：2016年8月11日 下午3:11:10
	 * @param idcard
	 * @return
	 */
	public static String getHideIDcard(String cardID) {
		
		if (StringUtils.isNotBlank(cardID)) {
			// 身份证号
			String start = StringUtils.substring(cardID, 0, 6);
			String end = StringUtils.substring(cardID,
					cardID.length() - 4);
			return start + "******" + end;
		}
		return "";
	}
	
	
	/**
	 * 
	 * @描述：隐藏手机号码
	 * @创建人：席在盛
	 * @创建时间：2016年8月11日 下午3:11:10
	 * @param idcard
	 * @return
	 */
	public static String getHideMobile(String mobile) {
		
		if (StringUtils.isNotBlank(mobile)) {
			String start = StringUtils.substring(mobile, 0, 3);
			String end = StringUtils.substring(mobile,
					mobile.length() - 4);
			return start + "*****" + end;
		}
		return "";
	}
	/**
	 * 
	* @Description 方法描述: 隐藏银行卡号 
	* @return  String
	* @author 汪正章 
	* @date  2018年4月4日 下午1:58:45
	* @throws
	 */
	public static String getHideBankAccount(String bankAccount) {
		  int length=bankAccount.length();
		  String str=bankAccount.substring(0, length-11)+"*******"+bankAccount.substring(length-2);
		  return str;
	}
	
	
	
	
	public static void main(String[] args) {
		String reaString="席在盛";
		String cardIdString="430821199111084814";
		System.out.println(getHideRealName(reaString));
		System.out.println(getHideIDcard(cardIdString));;
		System.out.println(getHideString("heheheheheheheheheh"));
		System.out.println(getHideMobile("1851676901"));
		
	}
	
	
}
