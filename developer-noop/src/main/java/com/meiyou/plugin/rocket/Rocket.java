package com.meiyou.plugin.rocket;

import android.content.Context;
import android.util.Log;

/**
 * Rocket 一个方便开发，配置设置的小工具。
 * a debug and QA tool to analyze the app, change the settings or view custom information
 *
 * @author zhengxiaobin@xiaoyouzi.com
 * @since 17/6/13 下午5:51
 */
public class Rocket {

    private static final String TAG = Rocket.class.getSimpleName();


    public Rocket() {

    }

    public static Rocket init(Context context) {
        return new Rocket();
    }

    public void inject(Class<?> config) {
        Log.e(TAG, "inject: " + "is from  Developer noop");
    }


}
