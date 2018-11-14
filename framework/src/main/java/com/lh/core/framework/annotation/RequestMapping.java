package com.lh.core.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: framework
 * @Package: com.lh.core.framework.annotation
 * @ClassName: RequestMapping
 * @Description: java类作用描述
 * @Author: 李浩
 * @CreateDate: 2018/10/24 5:12 PM
 * @UpdateDate: 2018/10/24 5:12 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String value() default "";
}
