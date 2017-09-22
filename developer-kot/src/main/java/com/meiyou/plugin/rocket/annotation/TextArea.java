package com.meiyou.plugin.rocket.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 提供 TextArea内容
 * 返回 注解方法的Return值；
 * It is used to provide a title for the settings
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface TextArea {

//  String value();
}
