package com.meiyou.plugin.rocket;

import com.meiyou.plugin.rocket.annotation.Button;
import com.meiyou.plugin.rocket.annotation.CheckBox;
import com.meiyou.plugin.rocket.annotation.EditText;
import com.meiyou.plugin.rocket.annotation.Spinner;
import com.meiyou.plugin.rocket.annotation.TextArea;
import com.meiyou.plugin.rocket.annotation.Title;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MethodInfo {

    static final int VIEW_BUTTON = 1;
    static final int VIEW_CHECKBOX = 2;
    static final int VIEW_SPINNER = 3;
    static final int VIEW_EDITTEXT = 4;
    static final int VIEW_TEXTAREA = 5;

    static final int INVALID = -1;

    private final Method method;

    private int viewType = INVALID;
    private Object data;
    private String title;
    private Object instance;

    private MethodInfo(Method method, Object instance) {
        this.method = method;
        this.instance = instance;

        parseMethodAnnotations();
    }

    static MethodInfo newInstance(Method method, Object instance) {
        return new MethodInfo(method, instance);
    }

    private void parseMethodAnnotations() {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();

            if (annotationType == Title.class) {
                title = ((Title) annotation).value();
                continue;
            }

            if (annotationType == Button.class) {
                viewType = VIEW_BUTTON;
                data = ((Button) annotation).value();
                continue;
            }

            if (annotationType == CheckBox.class) {
                viewType = VIEW_CHECKBOX;
                continue;
            }

            if (annotationType == Spinner.class) {
                data = ((Spinner) annotation).value();
                viewType = VIEW_SPINNER;
            }
            if (annotationType == TextArea.class) {
//        data = ((TextArea)annotation).value();
                viewType = VIEW_TEXTAREA;
            }
            if (annotationType == EditText.class) {
                data = ((EditText) annotation).value();
                viewType = VIEW_EDITTEXT;
            }
        }
    }

    Method getMethod() {
        return method;
    }

    int getViewType() {
        return viewType;
    }

    Object getData() {
        return data;
    }

    String getTitle() {
        return title;
    }

    Object getInstance() {
        return instance;
    }
}
