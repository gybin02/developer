package com.meiyou.plugin.rocket;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.linggan.zxing.activity.ZXingLibrary;
import com.linggan.zxing.activity.ZxingCallback;
import com.meiyou.plugin.rocket.annotation.Button;
import com.meiyou.plugin.rocket.annotation.Title;
import com.meiyou.plugin.rocket.common.ConfigListener;
import com.meiyou.plugin.rocket.common.RuntimeUtil;

import java.util.List;
import java.util.Map;

/**
 * 默认配置，可以根据这个配置生成 页面
 * 有加注解的类，方法需要是 Public，否则注解找不到的
 * 
 * This class should be used to provide bridge between bee and the app.
 */
public abstract class RocketConfig implements ConfigListener {

    private static final String TAG = "RocketConfig";

    private Context context;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 获取当前的Context，需要时可强转成Activity；
     */
    @Override
    public Context getContext() {
        return context;
    }

    /**
     * 关闭Panel触发
     */
    @Override
    public void onClose() {
    }

    /**
     * 打开Content Panel触发
     */
    public void onOpen() {

    }

    @Deprecated
    @Override
    public void onInfoContentCreated(Map<String, String> content) {
    }

    @Deprecated
    @Override
    public void onLogContentCreated(List<String> logList) {

    }


    /**
     * 进入二维码扫描
     */
    @Title("二维码扫一扫，进入WebView查看Uri")
    @Button
    public void openQrCode() {
        Context context = getContext();
        ZXingLibrary.initDisplayOpinion(context);
        ZXingLibrary.scan(context, new ZxingCallback() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                onQrcodeSuccess(result);
            }
        });
    }

    public void onQrcodeSuccess(String result) {
        Log.d(TAG, "onQrcodeSuccess: " + result);
    }


    /**
     * 清除本应用内部缓存
     */
    @Title("清除应用缓存并退出")
    @Button
    public void uclearAppCache() {
        RuntimeUtil.clearApp(context);
        Toast.makeText(context, "清除应用缓存成功", Toast.LENGTH_SHORT).show();
    }

    @Title("卸载应用")
    @Button
    public void uninstallApp() {
        RuntimeUtil.uninstall(context);
        Toast.makeText(context, "卸载应用成功", Toast.LENGTH_SHORT).show();
    }

}
