package com.news.aop;

import com.news.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 18786
 */
@Aspect
@Component
public class UserAop {
    @Resource
    private UserService userService;

    @Pointcut("execution(* com.news.service.impl.UserServiceImpl.checkUser(..)) " +
            "|| execution(* com.news.service.impl.UserServiceImpl.getUserList(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        userService.checkLockDate();
    }
}
