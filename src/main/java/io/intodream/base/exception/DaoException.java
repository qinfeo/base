package io.intodream.base.exception;

import io.intodream.base.constant.ResultEnums;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Locale;

/**
 * @author Jwenk
 * @description
 * @copyright intoDream.io 筑梦科技
 * @email xmsjgzs@163.com
 * @date 2018-05-27,下午5:42
 */
public class DaoException extends Exception{
    /**
     * 错误码
     */
    private Integer code;

    public DaoException(ResultEnums enums){
        super(enums.getMessage());
        this.code = enums.getCode();
    }

    public DaoException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public DaoException(ServiceException e){
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
