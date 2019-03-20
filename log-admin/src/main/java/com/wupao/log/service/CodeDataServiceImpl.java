package com.wupao.log.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wupao.log.mapper.CodeDataMapper;
import com.wupao.log.pojo.LoanShopInfo;
import com.wupao.log.utils.MD5;
import com.wupao.log.utils.Response;
import com.wupao.http.entity.HttpResult;
import com.wupao.http.utils.HttpService;
import com.wupao.tools.utils.JSONUtils;
/**
 * 
 * @项目名称：lyd-admin
 * @类名称：CodeDataServiceImpl
 * @类描述：
 * @创建人：樊诚
 * @创建时间：2018年4月1日 下午1:42:23 
 * @version：
 */
@Service
@SuppressWarnings("all")
public class CodeDataServiceImpl implements CodeDataService{

	@Autowired
	private CodeDataMapper codeDataMapper;

	@Autowired
	private HttpService httpService;
	
	//公钥
	@Value("${LYD_TOKEN_KEY}")
	private String LYD_TOKEN_KEY;
	
	//接口地址
	@Value("${LYD_EVALUATE_URL}")
	private String LYD_EVALUATE_URL;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeDataServiceImpl.class);
	
	/**
	 * 分页获取网店授权列表
	 */
	@Override
	public Map<String,Object> selectCodeDataInfoList(String shopType,String username,String shopName,Integer flag, Integer pageNum, Integer pageSize)throws Exception {
		String url = LYD_EVALUATE_URL + "/lydApi/selectCodeDataInfoList";
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("shopType", shopType);
		param.put("username", username);
		param.put("shopName", shopName);
		param.put("flag", flag);
		param.put("pageNum",pageNum);
		param.put("pageSize",pageSize);
		param.put("key", MD5.TwoMD5(LYD_TOKEN_KEY+MD5.TwoMD5(LYD_TOKEN_KEY+flag))); 
		LOGGER.debug("查询网店授权或网店估价接口【lyd-admin.CodeDataServiceImpl.selectCodeDataInfoList】===请求参数：param"+param);
		HttpResult result = this.httpService.doGet(url, param);
		LOGGER.debug("查询网店授权或网店估价接口【lyd-admin.CodeDataServiceImpl.selectCodeDataInfoList】===请求结果：result"+result);
		Response<String> response = MAPPER.readValue(result.getBody(), Response.class);
		Map<String,Object> map = JSONUtils.jsonToMap(response.getResult());
		return map;
	}

	/**
	 * 根据id查询详情
	 */
	@Override
	public LoanShopInfo searchById(Integer id) throws Exception{
		String url = LYD_EVALUATE_URL + "/lydApi/selectById";
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("key",MD5.TwoMD5(LYD_TOKEN_KEY+MD5.TwoMD5(LYD_TOKEN_KEY+id)));
		LOGGER.debug("查询网店授权或网店估价详情接口【lyd-admin.CodeDataServiceImpl.searchById】===请求参数：param"+param);
		HttpResult result = this.httpService.doGet(url,param);
		LOGGER.debug("查询网店授权或网店估价详情接口【lyd-admin.CodeDataServiceImpl.searchById】===请求结果：result"+result);
		Response<String> response = MAPPER.readValue(result.getBody(), Response.class);
		LoanShopInfo loanShopInfo = JSONUtils.jsonToPojo(response.getResult(), LoanShopInfo.class);
		return loanShopInfo;
	}

}
