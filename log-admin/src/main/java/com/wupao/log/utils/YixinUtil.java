package com.wupao.log.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @项目名称：wupao-lyd
 * @类名称：YixinUtil
 * @类描述：宜信工具类
 * @创建人：席在盛
 * @创建时间：2016年6月20日 下午3:49:44
 * @version
 */
public class YixinUtil {
	
	/**
	 * 宜信-商通贷,2015-12-01:
	 * */
	//环境地址:
	public static final  String SOURCE_URL = "http://shangdai.sandbox.yixin.com"; //测试:http://shangdai.sandbox.yixin.com;正式:https://shangdai.yixin.com
	
	public static final  String SOURCE_TYPE = "test"; //测试:test;正式:wupao
	
	//私钥:
	public static final String SECRET = "qy0e4JV5DOhKJeRljZVUFFn28gCbQX92"; //测试:qy0e4JV5DOhKJeRljZVUFFn28gCbQX92;正式:ZZaWj9BNeSlm5ISouxwWa1MmhEyK4Zj9
	
	//获取(整数)时间戳(秒):
	public static String getIntTimestamp() {
		
		long seconds = System.currentTimeMillis() / 1000; //(整数)时间戳;
		return seconds+"";
	}
	
	//时间戳(秒)转为日期格式:
	public static String timestampToDate(long timestamp) {		
		String newDate = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//eg:timestamp = 1448955020;		
			newDate = formatter.format(new Date(timestamp * 1000)); //毫秒;

		}catch(Exception e) {
			newDate = null;
		}
		return newDate;
	}
	
	//某日期转为时间戳(秒):
	public static long dateToTimestamp(String dateStr) {	
		long timestamp = 0;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//eg:dateStr = "2015-12-01 15:30:20";
			Date date = formatter.parse(dateStr);
			long seconds = date.getTime();
			timestamp = seconds / 1000; //转为秒;
			
		}catch(Exception e) {
			timestamp = 0;
		}
		return timestamp;
	}

}