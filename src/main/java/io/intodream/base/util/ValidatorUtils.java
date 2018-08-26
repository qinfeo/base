package io.intodream.base.util;

import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.groups.Default;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JavaBean属性校验
 * @author xmsjgzs@163.com
 * @date 2018/8/26 13:07
 */
public class ValidatorUtils {

    private static javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> Map<String, String> validate(T obj){
        Map<String,String> errorMap = null;
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (CollectionUtils.isNotEmpty(set)) {
            errorMap = set.stream().collect(Collectors.toMap(v -> v.getPropertyPath().toString(), ConstraintViolation::getMessage));
        }
        return errorMap;
    }
}
