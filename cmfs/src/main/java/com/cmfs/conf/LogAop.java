package com.cmfs.conf;

import com.cmfs.entity.ZAdmin;
import com.cmfs.entity.ZLog;
import com.cmfs.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
public class LogAop {
    @Pointcut("@annotation(logAnnotation)")
    public void pointcut(LogAnnotation logAnnotation){}
    @Resource
    private LogService logService;
    @Around("pointcut(logAnnotation)")
    public Object aroud(ProceedingJoinPoint proceedingJoinPoint,LogAnnotation logAnnotation){
        //获取session对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        ZAdmin admin = (ZAdmin) request.getSession().getAttribute("admin");
        //获取用户名
        String username = admin.getUsername();
        ZLog zLog = new ZLog();
        zLog.setName(username);
        zLog.setId(UUID.randomUUID().toString());
        //获取方法名
        String method = logAnnotation.value();
        zLog.setMethod(method);
        zLog.setOperateTime(new Date());
        try {
            Object proceed = proceedingJoinPoint.proceed();
            zLog.setYesornot("操作成功");
            logService.insert(zLog);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            zLog.setYesornot("操作失败");
            logService.insert(zLog);
            return null;
        }
    }
}
