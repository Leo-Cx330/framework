package com.lh.core.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: framework
 * @Package: com.lh.core.framework.annotation
 * @ClassName: RequestJson
 * @Description: java类作用描述
 * @Author: 李浩
 * @CreateDate: 2018/10/31 5:26 PM
 * @UpdateDate: 2018/10/31 5:26 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestJson {

    String value() default  "";
}
