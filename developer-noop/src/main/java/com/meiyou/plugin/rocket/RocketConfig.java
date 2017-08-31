package com.meiyou.plugin.rocket;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * 默认配置，可以根据这个配置生成 页面
 * 有加注解的类，方法需要是 Public，否则注解找不到的
 * <p>
 * This class should be used to provide bridge between bee and the app.
 */
public abstract class RocketConfig {

    private static final String TAG = "RocketConfig";

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 获取当前的Context，需要时可强转成Activity；
     */
    public Context getContext() {
        return context;
    }

    /**
     * 关闭Panel触发
     */
    public void onClose() {
    }

    /**
     * 打开Content Panel触发
     */
    public void onOpen() {

    }

    @Deprecated
    public void onInfoContentCreated(Map<String, String> content) {
    }

    @Deprecated
    public void onLogContentCreated(List<String> logList) {

    }
    
}
