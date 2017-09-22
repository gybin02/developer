package com.meiyou.plugin.rocket.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 提供EDitText 控件
 * Edit Text 返回值 在content里面
 * eg.
 *
 * @EditText public void doUri(String content);
 * It is used to provide a title for the settings
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface EditText {
    /**
     * EditText hint 内容
     * @return
     */
    String value() default "";
}
