package com.wupao.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @项目名称：common-utils
 * @类描述：
 * @创建人：wyait
 * @创建时间：2018-03-29 15:24
 * @version：V1.0
 */
@Configuration
@SpringBootApplication
//@ComponentScan用于配置扫描com.lyd.admin之外的包下面的类
@ComponentScan(basePackages={"com.wupao"})
public class LogAdminApplication {

	public static void main(String[] args) {
		SpringApplication sa=new SpringApplication(LogAdminApplication.class);
		// 禁用devTools热部署
		//System.setProperty("spring.devtools.restart.enabled", "false");
		// 禁用命令行更改application.properties属性
		sa.setAddCommandLineProperties(false);
		sa.run(args);
	}
}
