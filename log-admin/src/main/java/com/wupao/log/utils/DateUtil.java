package com.wupao.log.utils;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @项目名称：common
 * @类名称：DateUtil
 * @类描述：当前线程日期处理类（"yyyy-MM-dd HH:mm:ss"）[线程安全]
 * @创建人：wyait
 * @创建时间：2017年6月7日 下午6:24:23
 * @version：
 */
public class DateUtil implements AutoCloseable, Serializable {
    private static final long serialVersionUID = 5110771010886130754L;
    //饿汉单例
    public static DateUtil instance = new DateUtil();

    private DateUtil() {
    }

    public static DateUtil getInstance() {
        return instance;
    }

    //防序列化（杜绝单例对象被反序列化时重新生成对象）
    private Object readResolve() throws ObjectStreamException {
        return instance;
    }

    // SimpleDateFormat线程不安全的类，使用ThreadLocal,
    // 也是将共享变量变为独享，线程独享肯定能比方法独享在并发环境中能减少不少创建对象的开销。如果对性能要求比较高的情况下，一般推荐使用这种方法。
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * @param dateStr
     * @return Date
     * @throws ParseException
     * @描述：格式化String转换为Date
     * @创建人：wyait
     * @创建时间：2017年6月7日 下午6:27:03
     */
    public static Date parse(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    /**
     * @param date
     * @return 格式：yyyy-MM-dd HH:mm:ss
     * @描述：将date日期转换为string
     * @创建人：wyait
     * @创建时间：2017年6月7日 下午6:27:14
     */
    public static String format(Date date) {
        return threadLocal.get().format(date);
    }

    /**
     * @description 将Z日期转换成年月日
     * @author wzz
     * @date 2019/6/22
     * @param[date]
     * @return java.util.Date
     */
    public static Date fomatPaseZ(String date) throws Exception {
        date = date.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date d = format.parse(date);
        return d;
    }
    public  static  String simpleFomate(Date time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeFormat = sdf.format(time);
        return  timeFormat;
    }
    @Override
    public void close() throws Exception {
    }
    public static Date fomatPaseTime(String now) throws Exception {
        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = format2.parse(now);
        return date2;
    }
    /**
     * @description 将年月日时分秒转换成Z日期
     * @author wzz
     * @date 2019/6/22
     * @param[now]
     * @return java.util.Date
     */

    public static String convertToUTC(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }
    public static void main(String[] args) throws Exception {
          Date d= fomatPaseTime("2019-06-24 09:35:19");//fomatPaseZ("2019-06-22T09:58:53.896Z")
        System.out.print(simpleFomate(fomatPaseZ("2019-06-22T09:58:53.896Z")));
    }
}