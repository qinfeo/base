package io.intodream.base.dto;


import com.alibaba.fastjson.JSONObject;
import io.intodream.base.constant.ResultEnums;
import io.intodream.base.exception.ServiceException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * @description: Controller层返回数据包装类(REST风格)
 * @date: 2018-04-25,下午10:06
 * @author: Jwenk
 * @copyright: intoDream.io 筑梦科技
 * @email: xmsjgzs@163.com
 */
public class ResultDTO<T> implements Serializable{

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应返回数据
     */
    private T data;

    public ResultDTO(){}

    public ResultDTO(Integer code, String msg){
        this(code, msg, null);
    }

    public ResultDTO(Integer code, String msg, T data){
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public ResultDTO(ResultEnums resultEnums){
        this(resultEnums, null);
    }

    public ResultDTO(ResultEnums resultEnums, T data){
        this.msg = resultEnums.getMessage();
        this.code = resultEnums.getCode();
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    /**
     * 判断是否操作成功
     * @return
     */
    public boolean isOk(){
        return ResultEnums.SUCCESS.getCode().equals(this.code);
    }

    public static ResultDTO success(){
        return new ResultDTO(ResultEnums.SUCCESS);
    }

    public static ResultDTO error(ResultEnums resultEnums){
        return new ResultDTO(resultEnums);
    }

    public ResultDTO(T data){
        if (data instanceof Throwable){
            this.code = ResultEnums.SYSTEM_EXCEPTION.getCode();
            this.msg = ResultEnums.SYSTEM_EXCEPTION.getMessage();
        } else {
            this.code = ResultEnums.SUCCESS.getCode();
            this.msg = ResultEnums.SUCCESS.getMessage();
            this.data = data;
        }
    }

    /**
     * 创建一个操作成功返回体
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultDTO success(T data){
        return new ResultDTO(ResultEnums.SUCCESS, data);
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 创建一个错误的消息体，通过自定义的错误列表
     * @param errorList
     * @param messageSource
     * @return
     */
    public static ResultDTO error (List<FieldError> errorList, MessageSource messageSource){
        StringBuffer msg = new StringBuffer();
        Locale locale = LocaleContextHolder.getLocale();
        for (FieldError fieldError:errorList){
            //获取错误信息
            String errorMessage = messageSource.getMessage(fieldError,locale);
            //添加到错误消息集合内
            msg.append(fieldError.getField()+"："+errorMessage+" , ");
        }
        return new ResultDTO(ResultEnums.PARAM_ERROR.getCode(), msg.toString());
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
