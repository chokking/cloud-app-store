package com.webcloud.aop;

import com.webcloud.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class RequestAop {

    public static Map<String, Object> userCache = new ConcurrentHashMap(100);
    private final String executeExpr = "execution(* com.webcloud.*Controller.*(..))";
    private final static List<String> filterParam = new ArrayList<>();
    private final String LOGOUTURL = "/user/logout";
    private final String DETAIL = "detail";

    static {
    }

    /**
     * @param joinPoint:
     * @Return:
     **/
    @Around(executeExpr)
    public Object processLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        final String requestURI = request.getRequestURI();
        if (!filterParam.contains(requestURI) && !requestURI.contains(DETAIL)) {
            final String token = request.getParameter("token");
            if (StringUtils.isAnyBlank(token)) {
                return ResponseVo.failed("哥们儿,没有携带token令牌");
            }
            if (requestURI.equals(LOGOUTURL)) {
                userCache.remove(token);
                return joinPoint.proceed();
            }
            Object o = userCache.get(token);
            if (null == o) {
                return ResponseVo.failed("登录失效，请重新登录");
            }
        }
        return joinPoint.proceed();
    }

}

