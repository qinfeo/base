package io.intodream.base.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;


/**
 * <p>使用JDK8新特性来封装一个线程安全的Date工具类</p>
 * <p>该类主要提供以下接口:</p>
 * <ul>
 *     <li>1.将LocalDateTime对象转换为Date对象, localDateTimeConvert2Date(LocalDateTime date),返回一个Date对象</li>
 *     <li>2.将Date对象转换为LocalDateTime对象, dateConvert2LocalDateTime(Date date),返回一个LocalDateTime对象</li>
 *     <li>3.将LocalDate对象转换为LocalDateTime对象, localDateConvert2LocalDateTime(LocalDate date),返回一个LocalDateTime对象</li>
 *     <li>4.将字符串日期转换为LocalDateTime对象, parse(),该方法有重载,详情见方法说明</li>
 *     <li>4.将日期对象格式化为日期字符串,format(),该方法有重载,详情见方法说明</li>
 *     <li>5.获取两个日期相差的天数,countDays(),该方法有重载,详情见方法说明</li>
 * </ul>
 * 关于LocalDateTime的其他方法说明,因为LocalDateTime本身提供的很多方法就已经很简洁了，这里不需要再次封装，下面就对其进行一些简单介绍:
 * <ul>
 *     <li>1.在当期日期减去一个单位(年，月，日，周，天，时...)可以调用minus(TemporalAmount amountToSubtract)方法，该方法有两个重载，
 *     详情见LocalDateTime内说明，还有简洁的方法minusYears(long years)...</li>
 *     <li>2.在当期日期加上一个单位(年，月，日，周，天，时...)可以调用plus(TemporalAmount amountToAdd)方法，该方法有两个重载，
 *     详情见LocalDateTime内说明，还有简洁的方法plusYears(long years)...
 *     </li>
 *     <li>
 *         3.修改当前日期的某一个值，可以调用with(TemporalAdjuster adjuster),该方法会判断修改的日期是否合法，如果不合法的情况下回修改一个合法的
 *         例如:将2018-03-30，将03改为02，这时在方法内会自动将天数改为02月份的最后一天。简洁的方法有withYear(int year)...
 *     </li>
 *     <li>
 *          4.里面还提供了比较两个日期大小，compareTo0();两个日期是否相等isEqual();详情见LocalDateTime内方法说明
 *     </li>
 * </ul>
 * <p>LocalDate里面也提供了一些便捷的方法比如:isLeapYear()判断是否为闰年，lengthOfMonth()得到月份的天数，lengthOfYear()获取一年的天数</p>
 * @author yangxianxi@gogpay.cn
 * @date 2018/4/25 9:28
 */
public class DateUtils {

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static final String FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 只显示年月日 如(20180425)
     */
    public static final String FORMAT_MINI = "yyyyMMdd";

    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public static final String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 英文全称带符号 如：2017-12-01,23:15:06
     */
    public static final String FORMAT_LONG_SIGN = "yyyy-MM-dd,HH:mm:ss";

    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写 如：2010年12月01日
     */
    public static final String FORMAT_SHORT_CN = "yyyy年MM月dd日";

    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */
    public static final String FORMAT_LONG_CN = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 中文全称带符号 如：2017-12-01,23:15:06
     */
    public static final String FORMAT_LONG_SIGN_CN = "yyyy年MM月dd日,HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static final String FORMAT_FULL_CN = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";

    /**
     * 私有化构造方法，该对象不要被new
     */
    private DateUtils(){}

    /**
     * 获得默认的 date pattern
     */
    private static String getDatePattern() {
        return FORMAT_SHORT;
    }

    /**
     * 使用默认格式获取当前日期格式字符串
     * @return
     */
    public static String now(){
        return format(LocalDateTime.now());
    }

    /**
     * 获取一个本地时区的LocalDateTime
     * @return
     */
    public static LocalDateTime nowDate() {
        return LocalDateTime.now(ZoneId.of("GMT+8"));
    }

    /**
     * 使用客户自定义样式获取当前日期格式字符串
     * @param pattern
     * @return
     */
    public static String now(String pattern){
        return format(LocalDateTime.now(), pattern);
    }

    /**
     * 将LocalDateTime对象转换为Date对象,如果传入对象为null将会抛出NPE异常
     * @param date
     * @return
     */
    public static Date localDateTimeConvert2Date(LocalDateTime date){
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 将Date对象转换为LocalDateTime对象,如果传入对象为null将会抛出NPE异常
     * @param date
     * @return
     */
    public static LocalDateTime dateConvert2LocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant() ,ZoneId.systemDefault());
    }

    /**
     * 将LocalDate对象转换为LocalDateTime对象,如果传入对象为null将会抛出NPE异常
     * @param date
     * @return
     */
    public static LocalDateTime localDateConvert2LocalDateTime(LocalDate date){
        return LocalDateTime.of(date, LocalDateTime.now().toLocalTime());
    }

    /**
     * 使用预设格式提取字符串日期
     * @param dateStr 日期字符串
     * @return
     */
    public static LocalDateTime parse(String dateStr){
        return parse(dateStr, "");
    }

    /**
     * 将字符串日期，转换为LocalDateTime，需要提供指定格式
     * @param dateStr 日期字符串
     * @param patten 日期格式
     * @return
     */
    public static LocalDateTime parse(String dateStr, String patten){
        DateTimeFormatter formatter = createFormat(patten);
        LocalDateTime localDateTime = null;
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        // 针对yyyy-MM-dd 以及yyyyMMdd格式需要使用LocalDate来转换
        switch (patten){
            case "":
                localDateTime = localDateConvert2LocalDateTime(localDate);
                break;
            case FORMAT_SHORT:
                localDateTime = localDateConvert2LocalDateTime(localDate);
                break;
            case FORMAT_MINI:
                formatter = createFormat();
                localDate = LocalDate.parse(dateStr, formatter);
                localDateTime = localDateConvert2LocalDateTime(localDate);
                break;
            case FORMAT_SHORT_CN:
                localDateTime = localDateConvert2LocalDateTime(localDate);
                break;
            default:
                localDateTime = parse(dateStr, formatter);
                break;
        }
        return localDateTime;
    }

    /**
     * 使用用户格式提取字符串日期
     * @param dateStr
     * @param formatter
     * @return
     */
    public static LocalDateTime parse(String dateStr, DateTimeFormatter formatter){
        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * 根据用户格式返回指定日期字符串，如果格式为null，将使用默认的格式进行处理(yyyy-MM-dd)
     * @param date
     * @param patten 日期格式
     * @return
     */
    public static String format(LocalDateTime date, String patten){
        if (StringUtils.isEmpty(patten)){
            patten = getDatePattern();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patten);
        return date.format(formatter);
    }

    /**
     * 使用预设格式格式化日期
     * @param date
     * @return
     */
    public static String format(LocalDateTime date){
        return format(date, getDatePattern());
    }

    /**
     * 使用预设格式格式化日期
     * @param date
     * @return
     */
    public static String format(Date date){
        return format(dateConvert2LocalDateTime(date));
    }

    /**
     * 根据用户格式返回指定日期格式字符串
     * @param date 日期字符串
     * @param patten 日期格式
     * @return
     */
    public static String format(Date date, String patten){
        return format(dateConvert2LocalDateTime(date), patten);
    }

    /**
     * 按默认格式的字符串距离今天的天数
     * @param date 日期字符串
     * @return
     */
    public static long countDays(String date){
        return countDays(date, "");
    }

    /**
     * 按用户格式字符串距离今天的天数
     * @param date 日期字符串
     * @param patten 日期格式
     * @return
     */
    public static long countDays(String date, String patten){
        LocalDateTime localDateTime = parse(date, patten);
        long days = LocalDateTime.now().until(localDateTime, ChronoUnit.DAYS);
        return days > 0 ? days : (-days);
    }

    /**
     * 创建一个日期格式化对象,如果patten为空的话会使用DateTimeFormatter的默认格式(yyyy-MM-dd)
     * @param patten 日期格式
     * @return
     */
    private static DateTimeFormatter createFormat(String patten){
        if (StringUtils.isEmpty(patten)) {
            return DateTimeFormatter.ISO_DATE;
        }
        return DateTimeFormatter.ofPattern(patten);
    }

    /**
     * 返回一个基础的格式转换器(yyyyMMdd)
     * @return
     */
    private static DateTimeFormatter createFormat(){
        return DateTimeFormatter.BASIC_ISO_DATE;
    }

}
