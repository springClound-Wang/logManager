package com.wupao.log.utils;

import java.sql.Timestamp;

import org.slf4j.Logger;


/**
 * 
 * @项目名称：wupao-common
 * @类名称：LoggerUtil
 * @类描述：日志信息工具类
 * @创建人：杨鲍 
 * @创建时间：2015年12月2日 下午4:39:46 
 * @version：
 */
@SuppressWarnings("all")
public class LoggerUtil {

	/**
	 * 
	 * @描述：记录日志信息
	 * @创建人：杨鲍
	 * @创建时间：2015年12月2日 下午4:48:43
	 * @param mssage 日志基本信息
	 * @param startTime 接口开始时间
	 * @param endTime 接口结束时间
	 * @param log 日志对象
	 * @param response 响应对象
	 */
	public static void infoLog(final String message, final Long startTime,
			final Long endTime, final Logger log) {
		// 重新开启线程执行日志输出
		new Thread(new Runnable() {
			public void run() {
				printLog(message, startTime, endTime, log,  null,
						"info");
			}
		}).start();
	}

	/**
	 * 
	 * @描述：打印日志功能
	 * @创建人：杨鲍
	 * @创建时间：2015年12月10日 下午12:54:27
	 * @param mssage 日志基本信息
	 * @param startTime 接口开始时间
	 * @param endTime 接口结束时间
	 * @param log 日志对象
	 * @param response 响应对象
	 * @param tag 日志标记
	 * @param e 异常信息
	 */
	private static void printLog(String message, Long startTime, Long endTime,
			Logger log, Exception e, String tag) {

		if ("info".equals(tag)) {
			// 记录一般日志信息
			log.info("=====================分===割===线====================="
					+ System.getProperty("line.separator") + "【info】 "
					+ message 
					+ System.getProperty("line.separator") + "【time】开始时间："
					+ new Timestamp(startTime).toString() + "|--|结束时间："
					+ new Timestamp(endTime).toString() + " --> " + "方法执行时间："
					+ (endTime - startTime) + "毫秒"
					+ System.getProperty("line.separator") + "【result】返回结果："
					+ " --> "
					+ System.getProperty("line.separator")
					+ "=====================分===割===线=====================");
		} else if ("error".equals(tag)) {
			// 记录错误日志信息
			log.error(
					"=====================分===割===线====================="
							+ System.getProperty("line.separator")
							+ "【error】 "
							+ message
							+ System.getProperty("line.separator")
							+ "【time】开始时间："
							+ new Timestamp(startTime).toString()
							+ "|--|结束时间："
							+ new Timestamp(endTime).toString()
							+ " --> "
							+ "方法执行时间："
							+ (endTime - startTime)
							+ "毫秒"
							+ System.getProperty("line.separator")
							+ "【exception】异常信息："
							+ System.getProperty("line.separator")
							+ "=====================分===割===线=====================",
					e);
		}
	}

	/**
	 * 
	 * @描述：记录错误日志信息
	 * @创建人：杨鲍
	 * @创建时间：2015年12月2日 下午4:48:47
	 * @param mssage 日志基本信息
	 * @param startTime 接口开始时间
	 * @param endTime 接口结束时间
	 * @param log 日志对象
	 * @param ex 异常信息
	 */
	public static void errorLog(final String message, final Long startTime,
			final Long endTime, final Logger log, final Exception e) {
		// 重新开启线程执行日志输出
		new Thread(new Runnable() {
			public void run() {
				printLog(message, startTime, endTime, log, e, "error");
			}
		}).start();
	}

}
