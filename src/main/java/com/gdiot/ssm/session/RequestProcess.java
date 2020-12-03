package com.gdiot.ssm.session;

import java.lang.annotation.*;

/**
 * @author ZhouHR
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestProcess {
    //是需要判断当前用户能否使用本系统 true为需要 false为不需要
    public boolean isInto() default false;
}
