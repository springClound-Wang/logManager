package com.wupao.log.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @项目名称：lyd-admin
 * @类名称：MyWebMvcConfig
 * @类描述：自定义静态资源映射路径和静态资源存放路径
 * @创建人：wyait
 * @创建时间：2017年11月29日18:40:08
 * @version：
 */
//@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {
	/**
	 * 添加自定义静态资源映射路径和静态资源存放路径
	 */
	
	 @Override 
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		 registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		 registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		 registry.addResourceHandler("/layui/**").addResourceLocations("/layui/");
		 registry.addResourceHandler("/treegrid/**").addResourceLocations("/treegrid/");
		 super.addResourceHandlers(registry); 
	 }

	/**
	 * 
	 * @描述：自定义编码 转换器
	 * @创建人：wyait
	 * @创建时间：2018年5月11日 下午2:05:48
	 * @return
	 *//*
	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(
				Charset.forName("UTF-8"));
		return converter;
	}
	*//**
	 * 利用fastjson替换掉jackson，且解决中文乱码问题
	 *//*
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters){
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
		//super.configureMessageConverters(converters);
		//converters.add(responseBodyConverter());
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(1);
		return viewResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		return engine;
	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("classpath:/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(true);
		return templateResolver;
	}
	
	@Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("Messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

	/**
     * 利用fastjson替换掉jackson，且解决中文乱码问题
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        
    }*/
}
