package com.meiyou.plugin.sample;

import android.util.Log;
import android.widget.Toast;

import com.meiyou.plugin.rocket.RocketConfig;
import com.meiyou.plugin.rocket.annotation.Button;
import com.meiyou.plugin.rocket.annotation.CheckBox;
import com.meiyou.plugin.rocket.annotation.EditText;
import com.meiyou.plugin.rocket.annotation.Order;
import com.meiyou.plugin.rocket.annotation.Spinner;
import com.meiyou.plugin.rocket.annotation.TextArea;
import com.meiyou.plugin.rocket.annotation.Title;

public class AppRocketConfig extends RocketConfig {

    private static final String TAG = "AppBeeConfig";

    //    @Title("Test")
//    @CheckBox
//    public void test2(boolean isCheck) {
//
//    }
    @Order(1)
    @Button("sdfsd")
    public void test3() {

    }

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
    @Order(2)
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
    @Order(3)
    @Title("QaTest 功能(Charles查看埋点数据)")
    @CheckBox
    public void onShowAdsChecked(boolean isChecked) {
        Log.d(TAG, "是否显示广告： " + isChecked);
    }


    @Title("测试")
    @CheckBox
    public void onTest(boolean isCheck) {
        Log.e(TAG, "onTest: " + isCheck);
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

    @Order(4)
    @Title("显示内容测试环境")
    @TextArea
    public String showInfo() {
        String content = "";
        for (int i = 0; i < 10; i++) {
            content += "Hello world ";
        }
        return content;
    }

    @Title("显示Edit TextView")
    @EditText("显示Edit TextView, Hint ")
    public void doSearch(String key) {
        String content = "Hello world";
        Log.d(TAG, "doSearch: ");
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
    }

    @Order(5)
    @EditText("输入Uri，如：meiyou:///news/comment/open")
    public void doUri(String uri) {

    }


//        try {
//            String host = "192.168.53.161";
//            int port = 8800;
//            WifiProxyChanger.changeWifiStaticProxySettings(host, port,context);
//            Toast.makeText(context,"成功设置WiFi 代理:"+host+" "+port,Toast.LENGTH_SHORT).show();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (ApiNotSupportedException e) {
//            e.printStackTrace();
//        } catch (NullWifiConfigurationException e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public void onQrcodeSuccess(String result) {
//        super.onQrcodeSuccess(result);
//    }

    @Title("可视化埋点")
    @CheckBox
    public void openWHMD(boolean enable) {
        Toast.makeText(context, "可视化埋点状态: " + enable, Toast.LENGTH_SHORT).show();
    }

}
