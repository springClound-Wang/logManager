package com.wupao.log.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @项目名称：wupao-lyd
 * @类名称：UUIDGenerator
 * @类描述：生成order_id工具类
 * @创建人：席在盛
 * @创建时间：2016年7月6日 下午5:16:49
 * @version
 */
public class UUIDGenerator
{
    private UUIDGenerator() {}

    public static String generate10()
    {
        StringBuilder strBuilder = new StringBuilder();

        // 纳秒, 共10位
        appendNanoTime(strBuilder, 10);

        return strBuilder.toString();
    }

    public static String generate20()
    {
        StringBuilder strBuilder = new StringBuilder();

        // 年月日时分秒, 共14位
        appendDateTime(strBuilder);

        // 纳秒, 共6位
        appendNanoTime(strBuilder, 6);

        return strBuilder.toString();
    }

    public static String generate30()
    {
        return generate30("10100004");
    }

    public static String generate30(String systemId)
    {
        // 系统ID, 共8位
        StringBuilder strBuilder = new StringBuilder(systemId);

        // 年月日时分秒, 共14位
        appendDateTime(strBuilder);

        // 纳秒, 共8位
        appendNanoTime(strBuilder, 8);

        return strBuilder.toString();
    }

    private static void appendDateTime(StringBuilder strBuilder)
    {
        // 年月日时分秒
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = dateFormat.format(new Date());
        strBuilder.append(dateStr);
    }

    private static void appendNanoTime(StringBuilder strBuilder, int length)
    {
        // 纳秒
        String nanoTime = String.valueOf(System.nanoTime());

        if (nanoTime.length() > length)
        {
            nanoTime = nanoTime.substring(0, length);
        }
        else if (nanoTime.length() < length)
        {
            nanoTime = StringUtils.leftPad(nanoTime, length, "0");
        }

        strBuilder.append(nanoTime);
    }

//    private static int ip2Int()
//    {
//        int ipInt = 0;
//        try
//        {
//            byte[] bytes = InetAddress.getLocalHost().getAddress();
//            for (int i = 0; i < 4; i++)
//            {
//                ipInt = (ipInt << 8) - Byte.MIN_VALUE + (int) bytes[i];
//            }
//        }
//        catch (UnknownHostException e)
//        {
//            ipInt = 0;
//        }
//
//        return ipInt;
//    }
//
//    private static String int2Str(int intValue)
//    {
//        String hexString = Integer.toHexString(intValue);
//        StringBuilder strBuilder = new StringBuilder("00000000");
//        strBuilder.replace(8 - hexString.length(), 8, hexString);
//        return strBuilder.toString();
//    }
    
    public static void main(String[] args) {
		System.out.println("useID"+generate10());
	}
    
}
