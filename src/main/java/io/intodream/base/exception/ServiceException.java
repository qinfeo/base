package io.intodream.base.exception;


import io.intodream.base.constant.ResultEnums;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;

/**
 * @description 该异常为Service层异常，由全局异常统一处理
 * @date 2018-04-24,下午11:03
 * @author Jwenk
 * @copyright intoDream.io 筑梦科技
 * @email xmsjgzs@163.com
 */

public class ServiceException extends RuntimeException{

    /**
     * 错误码
     */
    private Integer code;

    public ServiceException(ResultEnums enums){
        super(enums.getMessage());
        this.code = enums.getCode();
    }

    public ServiceException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
