package vanhoang.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class JpaAspect {

    @Pointcut("this(org.springframework.data.mongodb.repository.MongoRepository) && execution(* insert(..))")
    public void jpaCreateTimeInsertPointcut() {}

    @Before("jpaCreateTimeInsertPointcut()")
    public void setCreateTime(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args[0] != null) {
            Object data = args[0];
            Class<?> clazz = data.getClass();
            try {
                Method setCreateTimeMethod = clazz.getMethod("setCreateTime", Date.class);
                setCreateTimeMethod.invoke(data, new Date());
                log.info("-------->insert create successed");
            } catch (Exception e) {
                log.error("-------> add create while insert data failed: {}", e.getMessage(), e);
            }
        }
    }
}
