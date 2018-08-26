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
 * Description : 日志切面
 *
 * @author yangxianxi
 * @date 2018/3/28 15:01
 */

@Aspect
@Order(5)
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);


    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut(value = "execution(public * io.intodream..web.*.*(..))")
    public void webLog(){}

    @Before(value = "webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("REQUEST={} {}; SOURCE IP={}; ARGS={}", request.getMethod(),
                request.getRequestURL().toString(), request.getRemoteAddr(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("RESPONSE={}; SPEND TIME={}MS", JSON.toJSONString(ret), System.currentTimeMillis() - startTime.get());
        startTime.remove();
    }
}
