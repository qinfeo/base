package io.intodream.base.handler;

import io.intodream.base.constant.ResultEnums;
import io.intodream.base.dto.ResultDTO;
import io.intodream.base.exception.CustomException;
import io.intodream.base.exception.DaoException;
import io.intodream.base.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 自定义全局异常处理
 * @date 2018-04-25,下午10:24
 * @author Jwenk
 * @copyright intoDream.io 筑梦科技
 * @email xmsjgzs@163.com
 */
@RestControllerAdvice
public class AppExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResultDTO httpHandler(HttpServletRequest request, Exception e){
        logger.info("请求路径:{}->{}", request.getMethod(), request.getServletPath());
        logger.error("异常信息", e);
        //针对404错误做处理
        if (e instanceof NoHandlerFoundException) {
            return new ResultDTO(ResultEnums.RESOURCE_NOT_EXIST);
        }
        return new ResultDTO(ResultEnums.UNKNOWN_ERROR);
    }

    /**
     * 服务层异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResultDTO serverHandler(ServiceException e) {
        logger.info("服务层异常处理",e);
        return new ResultDTO(e.getCode(),e.getMessage());
    }

    /**
     * 控制层异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResultDTO controllerHandler(CustomException e) {
        logger.info("控制层异常处理",e);
        return new ResultDTO(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(DaoException.class)
    public ResultDTO daoHandler(DaoException e){
        logger.error("数据库操作异常", e);
        return new ResultDTO(ResultEnums.OPERATION_FAILURE);
    }
}
