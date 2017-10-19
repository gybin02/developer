package com.meiyou.plugin.rocket.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 提供方法排序，支持排序，增序显示.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface Order {

    int value() default 10000;
}
