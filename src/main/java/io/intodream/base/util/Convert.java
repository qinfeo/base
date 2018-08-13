package io.intodream.base.util;

import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * 类型转换器
 * @author yangxianxi@gogpay.cn
 * @date 2018/5/15 17:49
 */
public class Convert {

    /**
     * 简单的类型转换器，该工具只能用于包含相同字段的JavaBean对象之间转换
     * @param source 元JavaBean
     * @param clazz 需要的Object类型
     * @param <T>
     * @return
     */
    public static  <T> T convert(@NotNull Object source, @NotNull Class<T> clazz){
        try {
            Object newObject = clazz.newInstance();
            BeanUtils.copyProperties(source, newObject);
            return (T) newObject;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
