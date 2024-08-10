package com.encrypt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: Encrypt
 * Package: com.encrypt.annotation
 * Description:
 *
 * @Author: @weixueshi
 * @Create: 2024/8/10 - 15:10
 * @Version: v1.0
 */

/**
 * 加密注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Encrypt {
}
