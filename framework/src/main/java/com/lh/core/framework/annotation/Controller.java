package com.lh.core.framework.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: framework
 * @Package: com.lh.core.framework
 * @ClassName: ControllerAnnotation
 * @Description: java类作用描述
 * @Author: lihao
 * @CreateDate: 2018/10/24 5:05 PM
 * @UpdateDate: 2018/10/24 5:05 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {

    String value() default "";
}
