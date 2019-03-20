package com.wupao.log.handler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wupao.log.utils.IStatusMessage;
import com.wupao.log.utils.ShiroFilterUtils;

/**
 * 
 * @项目名称：lyd-admin
 * @类名称：GlobalExceptionHandler
 * @类描述：统一异常处理，包括【普通调用和ajax调用】
 * </br>ControllerAdvice来做controller内部的全局异常处理，但对于未进入controller前的异常，该处理方法是无法进行捕获处理的，SpringBoot提供了ErrorController的处理类来处理所有的异常(TODO)。
 * </br>1.当普通调用时，跳转到自定义的错误页面；2.当ajax调用时，可返回约定的json数据对象，方便页面统一处理。
 * @创建人：wyait
 * @创建时间：2018年5月22日 上午11:44:55 
 * @version：
 */
//@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(GlobalExceptionHandler.class);

	public static final String DEFAULT_ERROR_VIEW = "error";

	/**
	 * 
	 * @描述：针对普通请求和ajax异步请求的异常进行处理
	 * @创建人：wyait
	 * @创建时间：2018年5月22日 下午4:48:58
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public void defaultErrorHandler(HttpServletRequest request,
			HttpServletResponse response, Exception e) throws Exception {
		ModelAndView mv=new ModelAndView();
		logger.info(getClass().getName() + ".defaultErrorHandler】统一异常处理："+e.getMessage());
		//3000
		mv.addObject("code",IStatusMessage.SystemStatus.EPT
				.getCode());
		mv.addObject("message","操作异常，请稍后再试");
		mv.addObject("url", request.getRequestURL());
		// 1 判断请求方式:普通请求，调整到对应的页面；ajax异步请求，响应数据提示信息
		if (!ShiroFilterUtils.isAjax(request)) {
			// 1.1 普通请求
			//mv.addObject("message", e.getMessage());  
			logger.debug(getClass().getName() + ".defaultErrorHandler】统一异常处理：普通请求");
			mv.setViewName(DEFAULT_ERROR_VIEW);
		}
		logger.debug(getClass().getName() + ".defaultErrorHandler】统一异常处理：MV="+mv);
		//return mv;
	}

	/**
	 * 
	 * @描述：处理UnknownSessionException异常【在sessionManager管理器的时候已经抛出异常】
	 * @创建人：wyait
	 * @创建时间：2018年5月22日 下午1:34:07
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = UnknownSessionException.class)
	public void toExceptionHandler(HttpServletRequest req,
			HttpServletResponse response, Exception e) throws Exception {
		logger.debug(this.getClass().getName()
				+ ".toExceptionHandler】统一异常UnknownSessionException处理：开始===");
		logger.debug("清除cookie操作开始");
		// 删除cookie
		Cookie co = new Cookie("username", "");
		// Cookie co = new Cookie("username", loginName);
		co.setMaxAge(0);// 设置立即过期
		co.setPath("/");// 根目录，整个网站有效
		response.addCookie(co);
		logger.debug("清除cookie完毕！");
	}

	/*
	 * @ExceptionHandler(value = MyJsonException.class)
	 * 
	 * @ResponseBody public Map<String, String>
	 * jsonExceptionHandler(HttpServletRequest req, Exception e) {
	 * 
	 * Map<String, String> re = new HashMap<String, String>(); re.put("error",
	 * "1"); re.put("msg", e.getMessage()); return re; }
	 */
}
