package io.intodream.base.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式验证工具类
 * @author yangxianxi@gogpay.cn
 * @date 2018/4/27 15:12
 */
public class RegexUtils {

    /**
     * Email正则表达式
     */
    public static final String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";

    /**
     * 电话号码正则表达式= (^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)
     */
    public static final String PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)" ;

    /**
     * 手机号码正则表达式=^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$
     */
    public static final String MOBILE ="^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9]|16[0-9]|19[0-9])\\d{8}$";


    /**
     * Integer正则表达式 ^-?(([1-9]\d*$)|0)
     */
    public static final String INTEGER = "^-?(([1-9]\\d*$)|0)";

    /**
     * 正整数正则表达式 >=0 ^[1-9]\d*|0$
     */
    public static final String INTEGER_NEGATIVE = "^[1-9]\\d*|0$";

    /**
     * 负整数正则表达式 <=0 ^-[1-9]\d*|0$
     */
    public static final String INTEGER_POSITIVE = "^-[1-9]\\d*|0$";

    /**
     * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$
     */
    public static final String DOUBLE ="^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";

    /**
     * 正Double正则表达式 >=0 ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　
     */
    public static final String DOUBLE_NEGATIVE ="^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";

    /**
     * 负Double正则表达式 <= 0 ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$
     */
    public static final String DOUBLE_POSITIVE ="^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";

    /**
     * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁
     */
    public static final String AGE="^(?:[1-9][0-9]?|1[01][0-9]|120)$";

    /**
     * 邮编正则表达式 [0-9]\d{5}(?!\d) 国内6位邮编
     */
    public static final String CODE="[0-9]\\d{5}(?!\\d)";

    /**
     * 匹配由数字、26个英文字母或者下划线组成的字符串 ^\w+$
     */
    public static final String STR_ENG_NUM_="^\\w+$";

    /**
     * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$
     */
    public static final String STR_ENG_NUM="^[A-Za-z0-9]+";

    /**
     * 匹配由26个英文字母组成的字符串 ^[A-Za-z]+$
     */
    public static final String STR_ENG="^[A-Za-z]+$";

    /**
     * 过滤特殊字符串正则
     * regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]";
     */
    public static final String STR_SPECIAL="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]";

    /***
     * 日期正则 支持：
     * YYYY-MM-DD
     * YYYY/MM/DD
     * YYYY_MM_DD
     * YYYYMMDD
     * YYYY.MM.DD的形式
     */
    public static final String DATE_ALL="((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)" +
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)" +
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)" +
            "([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)" +
            "|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)" +
            "(0?2)([-\\/\\._]?)(29)$)" +
            "|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)" +
            "([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|" +
            "(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";
    /***
     * 日期正则 支持：
     * YYYY-MM-DD
     */
    public static final String DATE_FORMAT1="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    /**
     * URL正则表达式
     * 匹配 http www ftp
     */
    public static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?" +
            "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*" +
            "(\\w*:)*(\\w*\\+)*(\\w*\\.)*" +
            "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";

    /**
     * 身份证正则表达式
     */
    public static final String ID_CARD ="((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +
            "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +
            "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";

    /**
     * 机构代码
     */
    public static final String JI_GOU_CODE = "^[A-Z0-9]{8}-[A-Z0-9]$";

    /**
     * 匹配数字组成的字符串 ^[0-9]+$
     */
    public static final String STR_NUM = "^[0-9]+$";

    /**
     * 社会信用码各位置序号加权因子
     */
    private static final int[] CREDIT_POWER = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};

    /**
     * 社会信用码代码字符集
     */
    private static final char[] CREDIT_CODE = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','T','U','W','X','Y'};

    /**
     * 社会信用码固定长度
     */
    private static final int CREDIT_CODE_LENGTH = 18;

    /**
     * 社会信用码采用31进账计算，所以会用到该值
     */
    private static final int CREDIT_CODE_HEX = 31;

    /**
     * 用于存放社会信用码每一个字符与其对应的值
     */
    private static Map<String, Integer> data = new HashMap<>(31);

    /**
     * 身份证加权因子
     */
    private static final int[] ID_CARD_POWER = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 身份证最后一位校验码
     */
    private static final char[] ID_CARD_CAPTCHA = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    /**
     * 二代身份证长度
     */
    private static final int ID_CARD_LENGTH = 18;

    /**
     * 一代身份证长度
     */
    private static final int ID_CARD_LENGTH_1 = 15;

    /**
     * 身份证算法需要的校验码
     */
    private static final int ID_CARD_HEX = 11;

    /**
     * 字符串null转空
     * @param str
     * @return boolean
     */
    public static String nullToStr(String str) {
        return StringUtils.isEmpty(str) ? "" : str;
    }

    /**
     * 字符串null赋值默认值
     * @param str  目标字符串
     * @param def 默认值
     * @return String
     */
    public static String nullToStr(String str, String def) {
        return StringUtils.isEmpty(str) ? def : str;
    }


    /**
     * 判断字段是否为Email 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isEmail(String str) {
        return regular(str, EMAIL);
    }

    /**
     * 判断是否为电话号码 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isPhone(String str) {
        return regular(str, PHONE);
    }


    /**
     * 判断是否为手机号码 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isMobile(String str) {
        return regular(str, MOBILE);
    }


    /**
     * 判断是否为Url 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isUrl(String str) {
        return regular(str, URL);
    }


    /**
     * 判断字段是否为数字 正负整数 正负浮点数 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isNumber(String str) {
        return regular(str, DOUBLE);
    }


    /**
     * 判断字段是否为INTEGER 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isInteger(String str) {
        return regular(str, INTEGER);
    }


    /**
     * 判断字段是否为正整数正则表达式 >=0 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isIntegerNegative(String str) {
        return regular(str, INTEGER_NEGATIVE);
    }


    /**
     * 判断字段是否为负整数正则表达式 <=0 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isIntegerPositive(String str) {
        return regular(str, INTEGER_POSITIVE);
    }


    /**
     * 判断字段是否为DOUBLE 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isDouble(String str) {
        return regular(str, DOUBLE);
    }


    /**
     * 判断字段是否为正浮点数正则表达式 >=0 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isDoubleNegative(String str) {
        return regular(str, DOUBLE_NEGATIVE);
    }

    /**
     * 判断字段是否为负浮点数正则表达式 <=0 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isDoublePositive(String str) {
        return regular(str, DOUBLE_POSITIVE);
    }

    /**
     * 判断字段是否为日期 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isDate(String str) {
        return regular(str, DATE_ALL);
    }

    /**
     * 验证2010-12-10
     * @param str
     * @return
     */
    public static boolean isDate1(String str) {
        return regular(str, DATE_FORMAT1);
    }
    /**
     * 判断字段是否为年龄 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isAge(String str) {
        return regular(str, AGE) ;
    }

    /**
     * 判断字段是否超长
     * 字串为空返回false, 超过长度{length}返回true 反之返回false
     * @param str
     * @param length
     * @return boolean
     */
    public static boolean isLengthOut(String str, int length) {
        return StringUtils.isEmpty(str) ? false : str.trim().length() > length;
    }

    /**
     * 判断字段是否为身份证 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isIdCard(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        if(str.trim().length() == ID_CARD_LENGTH_1 || str.trim().length() == ID_CARD_LENGTH) {
            return regular(str, ID_CARD);
        }else {
            return false;
        }

    }
    /**
     * 判断字段是否为邮编 符合返回true
     * @param str
     * @return boolean
     */
    public static boolean isPostcode(String str) {
        return regular(str, CODE);
    }
    /**
     * 判断字符串是不是全部是英文字母
     * @param str
     * @return boolean
     */
    public static boolean isEnglish(String str) {
        return regular(str, STR_ENG);
    }

    /**
     * 判断字符串是不是全部是英文字母+数字
     * @param str
     * @return boolean
     */
    public static boolean isEngNum(String str) {
        return regular(str, STR_ENG_NUM);
    }
    /**
     * 判断字符串是不是全部是英文字母+数字+下划线
     * @param str
     * @return boolean
     */
    public static boolean isEnglishNumberUnderline(String str) {
        return regular(str, STR_ENG_NUM_);
    }
    /**
     * 过滤特殊字符串 返回过滤后的字符串
     * @param str
     * @return boolean
     */
    public static String filterStr(String str) {
        Pattern p = Pattern.compile(STR_SPECIAL);
        Matcher m = p.matcher(str);
        return  m.replaceAll("").trim();
    }

    /**
     * 校验机构代码格式
     * @return
     */
    public static boolean isJiGouCode(String str){
        return regular(str, JI_GOU_CODE);
    }

    /**
     * 判断字符串是不是数字组成
     * @param str
     * @return boolean
     */
    public static boolean isStrNumber(String str) {
        return regular(str, STR_NUM);
    }

    /**
     * 判断是否为18的社会信用代码
     * @param str
     * @return
     */
    public static boolean isCreditCode(String str){
        if(StringUtils.isEmpty(str) || str.trim().length() < CREDIT_CODE_LENGTH) {
            return false;
        }
        initMap();
        char[] codes = str.toCharArray();
        int sum = 0;
        for (int i = 0; i < codes.length - 1; i++){
            sum += data.get(codes[i] + "") * CREDIT_POWER[i];
        }
        int mod = sum % CREDIT_CODE_HEX;
        mod = mod == 0 ? CREDIT_CODE_HEX : CREDIT_CODE_HEX - mod;
        return CREDIT_CODE[mod] == codes[codes.length - 1];
    }

    /**
     * 判断是否身份证最后一位是否为合法的校验码，（该规则只适用于18位身份证）
     * @param str
     * @return
     */
    public static boolean isIdCardLastCode(String str){
        if(StringUtils.isEmpty(str) || str.trim().length() < ID_CARD_LENGTH) {
            return false;
        }
        char[] codes = str.toUpperCase().toCharArray();
        int sum = 0;
        for (int i = 0; i < codes.length - 1; i++){
            sum += ID_CARD_POWER[i] * Integer.valueOf(String.valueOf(codes[i]));
        }
        int mod = sum % ID_CARD_HEX;
        return codes[codes.length - 1] == ID_CARD_CAPTCHA[mod];
    }

    /**
     * 匹配是否符合正则表达式pattern 匹配返回true
     * @param str 匹配的字符串
     * @param pattern 匹配模式
     * @return boolean
     */
    private static boolean regular(String str, String pattern){
        if (null == str || str.trim().length()<=0){
            return false;
        }
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 初始化信用码每个字符串与其对应的值
     */
    private static void initMap(){
        for (int i = 0; i < CREDIT_CODE.length; i++){
            data.put(CREDIT_CODE[i]+ "",i);
        }
    }
}
