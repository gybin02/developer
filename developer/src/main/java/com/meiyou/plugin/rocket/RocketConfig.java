package com.meiyou.plugin.rocket;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.linggan.zxing.activity.ZXingLibrary;
import com.linggan.zxing.activity.ZxingCallback;
import com.meiyou.plugin.rocket.annotation.Button;
import com.meiyou.plugin.rocket.common.ConfigListener;
import com.meiyou.plugin.rocket.common.RuntimeUtil;

import java.util.List;
import java.util.Map;

/**
 * 默认配置，可以根据这个配置生成 页面
 * 有加注解的类，方法需要是 Public，否则注解找不到的
 * <p>
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
    @Button("二维码扫一扫，进入WebView执行Uri")
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
    @Button("清除应用缓存并退出")
    public void clearAppCache() {
        RuntimeUtil.clearApp(context);
        Toast.makeText(context, "清除应用缓存成功", Toast.LENGTH_SHORT).show();
    }

    @Button("卸载应用，对root设备有效")
    public void uninstallApp() {
        RuntimeUtil.uninstall(context);
        Toast.makeText(context, "卸载应用成功", Toast.LENGTH_SHORT).show();
    }


    @Button("使用帮助")
    public void a_openWeb() {
        Intent intent = new Intent();
        String wiki = "http://git.meiyou.im/Android/Android/wikis/%E5%BC%80%E5%8F%91%E8%80%85%E9%A1%B5%E9%9D%A2";
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(wiki);
        intent.setData(content_url);
        context.startActivity(intent);
    }
}
