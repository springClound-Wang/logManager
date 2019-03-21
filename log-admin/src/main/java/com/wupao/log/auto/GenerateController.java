package com.wupao.log.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码生成接口
 * 
 *
 *
 */
@RestController
@RequestMapping("/generate")
public class GenerateController {

	@Autowired
	private GenerateService generateService;

	/*@GetMapping(params = { "tableName" })
	public GenerateDetail generateByTableName(String tableName) {
		GenerateDetail detail = new GenerateDetail();
		detail.setBeanName(generateService.upperFirstChar(tableName));
		List<BeanField> fields = generateService.listBeanField(tableName);
		detail.setFields(fields);

		return detail;
	}*/
	@RequestMapping("/save")
	public void save(@RequestBody GenerateInput input) {
		generateService.saveCode(input);
	}
	@RequestMapping("/test")
	public String  test() {
		return "test";
	}
}
