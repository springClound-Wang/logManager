import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wupao.log.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Random;

/**
 * @Auther: wzz
 * @packageName PACKAGE_NAME
 * @Date: 2019/7/19 10:38
 * @Description:
 */
public class Test {

   static  String result="";
    public static void main(String[] args) {
        for(int j=0;j<2;j++){
            HashMap map=new HashMap();
            map.put("number","2");
            if(StringUtils.isEmpty(result)){
                result="c9579fb961b349a2a5173e94ccde7e04";
            }
            map.put("SMSToken",result);
            Random random=new Random();
            StringBuilder str=new StringBuilder("");//定义一个stringbuilder
            str.append(1);
            int randomInt = random.nextInt(90) + 10;
            str.append(randomInt);
//随机生成数字，并添加到字符串
            for(int i=0;i<8;i++){
                str.append(random.nextInt(10));
            }
            System.out.println("str:"+str);
            map.put("mobile",str.toString());
            result = excute(map);
        }

    }
    public  static String excute(HashMap map){
        System.out.println("map:"+map.toString());

        String result= HttpClientUtil.doPost("http://www.lingfenxiang.com/u/user/sendPhoneCode",map,"UTF-8");
        JSONObject obj= JSON.parseObject(result);
        System.out.println("obj:"+obj.toJSONString());
        JSONObject obj1= JSON.parseObject(obj.get("data").toString());
        System.out.println("data:"+obj.get("data").toString());

        String SMSToken=obj1.get("SMSToken").toString();
        System.out.println("SMSToken:"+obj1.get("SMSToken").toString());

        return SMSToken;
    }
}
