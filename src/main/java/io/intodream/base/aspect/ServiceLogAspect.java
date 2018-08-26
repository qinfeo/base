package io.intodream.base.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Description : service层所有public修饰的方法调用返回日志
 *
 * @author yangxianxi
 * @date 2018/4/2 11:14
 */

@Aspect
@Order(5)
@Component
public class ServiceLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLogAspect.class);


    @Pointcut(value = "execution(public * io.intodream..service.*.*(..))")
    public void serviceLog(){}

    @Before(value = "serviceLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.debug("CALL={}; ARGS={}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "serviceLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        LOGGER.debug("RESPONSE={}", JSON.toJSONString(ret));
    }
}
