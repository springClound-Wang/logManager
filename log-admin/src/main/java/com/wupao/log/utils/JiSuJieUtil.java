package com.wupao.log.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by xizaisheng on 2016/11/10.
 */
public class JiSuJieUtil {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(JiSuJieUtil.class);
    /**
     * 线上

    public final static String IFRAMEURL ="https://jie.jisujie.com/partner/entry";//正式环境：https://jie.jisujie.com/partner/entry 测试环境：http://jie2.test.jisucloud.cn/partner/entry

    //接口地址
    public final static String APIURL = "http://api.jisujie.com";//正式环境：http://api.jisujie.com 测试环境：http://api.sandbox.jisucloud.cn
    //迭代的新版接口地址
    public final static String APPLY_JSJ_URL = "https://jie.jisujie.com";//正式环境：https://jie.jisujie.com 测试环境：http://jie2.test.jisucloud.cn
    //唯一标识
    public final static String PARTNER = "wupao";
    //KEY
    public final static String KEY = "69342ae422f18aca1ec05838b26ad223";//正式环境: key:69342ae422f18aca1ec05838b26ad223 测试环境：3afd4295f2e13e916e4479ac7af2d0b0*/
    /**
     * 本地测试
     */
    public final static String IFRAMEURL ="http://jie2.test.jisucloud.cn/partner/entry";//正式环境：https://jie.jisujie.com/partner/entry 测试环境：http://jie2.test.jisucloud.cn/partner/entry

    //接口地址【接口升级后，暂时无用】
    public final static String APIURL = "http://api.sandbox.jisucloud.cn";//正式环境：http://api.jisujie.com 测试环境：http://api.sandbox.jisucloud.cn
    //迭代的新版接口地址
    public final static String APPLY_JSJ_URL = "http://jie2.test.jisucloud.cn";//正式环境：https://jie.jisujie.com 测试环境：http://jie2.test.jisucloud.cn
    //唯一标识
    public final static String PARTNER = "wupao";
    //KEY
    public final static String KEY = "3afd4295f2e13e916e4479ac7af2d0b0";//正式环境: key:69342ae422f18aca1ec05838b26ad223 测试环境：3afd4295f2e13e916e4479ac7af2d0b0

    /**
     * 对于参数按首字母排序
     *
     * @param param
     * @return
     */
    public static String[] sortParams(List<?> param) {
        String[] result = new String[param.size()];
        for (int i = 0; i < param.size(); i++) {
            result[i] = (String) param.get(i);
        }
        Arrays.sort(result);
        return result;
    }




    /**
     * 获取地区信息
     *
     * @param type-类型 1市区 2县级
     * @param code    -对应编码
     * @return
     */
    public static String getArea(Integer type, Integer code,String uid) {
        String url = null;
        String result = "";
        String ts=YixinUtil.getIntTimestamp();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("partner", PARTNER);
        paramMap.put("ts",ts);
        paramMap.put("uid", uid);
        //把参数顺序排序便于转换签名
        List<String> param = new ArrayList<String>();
        param.add("partner=" + PARTNER);
        param.add("uid="+uid);
        param.add("ts=" + ts);
        if (type != null && code != null) {
            if (type == 0) {
                url =APPLY_JSJ_URL+"/api/getprovincelist";
            } else if (type == 1) {
                url = APPLY_JSJ_URL+"/api/getcitylist";
                param.add("province=" +code );
                paramMap.put("province",code+"");
            } else if (type == 2) {
                url = APPLY_JSJ_URL+"/api/getdistrictlist";
                param.add("city=" +code );
                paramMap.put("city", code+"");
            }
        }
        String signStr="";
        String[] sortParam = sortParams(param);//排序参数
        for (int i = 0; i < sortParam.length; i++) {
            signStr += sortParam[i] + "&#@";
        }
        signStr += "key=" + KEY;
        String sign = DigestUtils.md5Hex(signStr);
        LOGGER.debug("请求的参数：" + signStr+"加密后的签名："+sign+"当前毫秒数："+ts);
        paramMap.put("sign", sign);
        LOGGER.info(" **极速借** 获取地区信息的URL地址为：" + url);
        LOGGER.debug("请求的地址：" + url);
        //eg:<option value=''></option>
        if (StringUtils.isNotEmpty(url)) {
            result = HttpClientUtil.doPost(url, paramMap, "UTF-8");
        }
        return result;
    }



    public static void main(String[] args) {



    }
}
