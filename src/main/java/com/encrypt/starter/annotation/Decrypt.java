package com.encrypt.starter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: Decrypt
 * Package: com.encrypt.annotation
 * Description:
 *
 * @Author: @weixueshi
 * @Create: 2024/8/10 - 15:09
 * @Version: v1.0
 */

/**
 * 解密注解
 */
@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Decrypt {
}
