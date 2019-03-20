package com.wupao.log;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @项目名称：common-utils
 * @包名：com.lyd.admin
 * @类描述：重写SpringBootServletInitializer的configure，将Spring Boot的入口类设置进去
 * @创建人：wyait
 * @创建时间：2018-03-29 15:32
 * @version：V1.0
 */
public class LogAdminInitializer extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(LogAdminApplication.class);
	}
}
