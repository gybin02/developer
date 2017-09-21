package com.meiyou.plugin.sample;

import android.util.Log;
import android.widget.Toast;

import com.meiyou.plugin.rocket.RocketConfig;
import com.meiyou.plugin.rocket.annotation.Button;
import com.meiyou.plugin.rocket.annotation.CheckBox;
import com.meiyou.plugin.rocket.annotation.EditText;
import com.meiyou.plugin.rocket.annotation.Spinner;
import com.meiyou.plugin.rocket.annotation.TextArea;
import com.meiyou.plugin.rocket.annotation.Title;

public class AppRocketConfig extends RocketConfig {

    private static final String TAG = "AppBeeConfig";


    /**
     * 当打开设置页面的时候
     */
    @Override
    public void onClose() {
        super.onClose();
        Log.w(TAG, "onClose");
    }

    @Override
    public void onOpen() {
        super.onOpen();
        Log.w(TAG, "onOpen");
    }

    /**
     * 使用@注解方法生成 测试UI；Button
     * <p>
     * Title 用来显示Button文字
     * Method should have no parameter.
     */
    @Button("测试请求网络")
    public void requestNetwork() {
        Log.d(TAG, " 测试请求网络 requestNetwork");
    }


    /**
     * 生成一个CheckBox
     *
     * @Title 用来 显示Label
     * @Method 返回一个参数用来处理是否点击
     */
    @Title("显示广告")
    @CheckBox
    public void onShowAdsChecked(boolean isChecked) {
        Log.d(TAG, "是否显示广告： " + isChecked);
    }

    /**
     * 生成一个 Spinner
     *
     * @Spinner 注解需要传入 一个 String[]
     * Title 是用来显示Label
     * Method 返回选中的 String;
     */
    @Title("测试环境")
    @Spinner({"测试", "预发", "正式"})
    public void onEnvironmentSelected(String selectedValue) {
        Log.d(TAG, "测试环境： " + selectedValue);
    }


    @Title("显示内容测试环境")
    @TextArea
    public String showInfo() {
        String content = "Hello world";
        return content;
    }

    @Title("显示Edit TextView")
    @EditText("显示Edit TextView, Hint ")
    public void doSearch(String key) {
        String content = "Hello world";
        Log.d(TAG, "doSearch: ");
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
    }

    @EditText("输入Uri，如：meiyou:///news/comment/open")
    public void doUri(String uri) {
//        MeetyouDilutions.create().formatProtocolService(uri);
    }

    @Override
    public void onQrcodeSuccess(String result) {
        super.onQrcodeSuccess(result);
    }
}
