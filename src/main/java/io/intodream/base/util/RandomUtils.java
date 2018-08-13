package io.intodream.base.util;

import java.util.UUID;

/**
 * @author Jwenk
 * @description 随机数生成工具
 * @copyright intoDream.io 筑梦科技
 * @email xmsjgzs@163.com
 * @date 2018-05-27,下午3:09
 */
public class RandomUtils {
    /**
     * 开始范围
     */
    public static final int START = 10000;
    /**
     * 结束范围
     */
    public static final int END = 99999;

    /**
     * 获取主键ID
     * @return
     */
    public static Integer getId(){
        return getId(START, END);
    }

    /**
     * 获取指定范围的Integer主键
     * @param start
     * @param end
     * @return
     */
    public static Integer getId(int start, int end){
        int result = org.apache.commons.lang3.RandomUtils.nextInt(start, end);
        return Integer.valueOf(result);
    }

    /**
     * 获取主键ID
     * @return
     */
    public static String getStringId(){
        return getStringId(START, END);
    }

    /**
     * 获取指定范围的Integer主键
     * @param start
     * @param end
     * @return
     */
    public static String getStringId(int start, int end){
        int result = org.apache.commons.lang3.RandomUtils.nextInt(start, end);
        return Integer.valueOf(result).toString();
    }

    /**
     * 获取一个StringUUID
     * @return
     */
    public static String UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取一个没有"-"的UUID
     * @return
     */
    public static String simpleUUID() {
        return UUID().replace("-", "");
    }
}
