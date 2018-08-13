package io.intodream.base.exception;


import io.intodream.base.constant.ResultEnums;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;

/**
 * @Description: 该异常出现在Controller层，由全局异常处理器统一处理
 * @Date: 2018-04-24,下午10:59
 * @Author: Jwenk
 * @Copyright: intoDream.io 筑梦科技
 * @Email: xmsjgzs@163.com
 */
public class CustomException extends Exception{

    /**
     * 错误码
     */
    private Integer code;

    public CustomException(ResultEnums enums){
        super(enums.getMessage());
        this.code = enums.getCode();
    }

    public CustomException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public CustomException(ServiceException e){
        super(e.getMessage());
        this.code = e.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
