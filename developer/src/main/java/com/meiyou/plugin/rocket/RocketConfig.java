package com.meiyou.plugin.rocket;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyChanger;
import com.meiyou.plugin.rocket.annotation.Button;
import com.meiyou.plugin.rocket.annotation.EditText;
import com.meiyou.plugin.rocket.annotation.Order;
import com.meiyou.plugin.rocket.annotation.Title;
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

    public Activity context;

    @Override
    public void setContext(Activity context) {
        this.context = context;
    }

    /**
     * 获取当前的Context，需要时可强转成Activity；
     */
    @Override
    public Activity getContext() {
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
     * 清除本应用内部缓存
     */
    @Order(0)
    @Button("清除应用缓存并退出")
    public void clearAppCache() {
        RuntimeUtil.clearApp(context);
        Toast.makeText(context, "清除应用缓存成功", Toast.LENGTH_SHORT).show();
    }

    @Order(0)
    @Button("使用帮助")
    public void openWeb() {
        Intent intent = new Intent();
        String wiki = "http://git.meiyou.im/Android/Android/wikis/%E5%BC%80%E5%8F%91%E8%80%85%E9%A1%B5%E9%9D%A2";
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(wiki);
        intent.setData(content_url);
        context.startActivity(intent);
    }

//    /**
//     * 进入二维码扫描
//     */
//    @Order(10)
//    @Button("二维码扫一扫，进入WebView执行Uri")
//    public void openQrCode() {
//        Context context = getContext();
//        ZXingLibrary.initDisplayOpinion(context);
//        ZXingLibrary.scan(context, new ZxingCallback() {
//            @Override
//            public void onSuccess(String result) {
//                super.onSuccess(result);
//                onQrcodeSuccess(result);
//            }
//        });
//    }
//
//    public void onQrcodeSuccess(String result) {
//        Log.d(TAG, "onQrcodeSuccess: " + result);
//    }


    @Order(0)
    @Button("卸载应用，对root设备有效")
    public void uninstallApp() {
        RuntimeUtil.uninstall(context);
        Toast.makeText(context, "卸载应用成功", Toast.LENGTH_SHORT).show();
    }

    @Order(0)
    @Button("安装Charles证书")
    public void installProxy() {
        String url = "http://chls.pro/ssl";
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        getContext().startActivity(intent);
    }

    @Order(1)
    @Title("自动设置WiFi代理")
    @EditText("格式：192.168.53.161:8888")
    public void doWifiProxy(String input) {
//        MeetyouDilutions.create().formatProtocolService(uri);
//        WifiConnect.setHttpProxySystemProperty("192.168.53.171","8800",null,context);
        //支持 4.0 ~ 5.0,
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Intent intent = new Intent();
            intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
            context.startActivity(intent);
        } else {
            //https://github.com/lonelydeveloper97/android-proxy-changing
            try {
                String[] split = input.split(":");
                if (split.length != 2) {
                    Toast.makeText(context, "代理输入有误！ 正确格式：192.168.53.161:8888 ", Toast.LENGTH_SHORT)
                         .show();
                    return;
                }
//                "192.168.53.161";
                String host = split[0];
                int port = Integer.parseInt(split[1]);
                WifiProxyChanger.changeWifiStaticProxySettings(host, port, context);
                Toast.makeText(context, "成功设置WiFi 代理:" + host + " " + port, Toast.LENGTH_SHORT)
                     .show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
