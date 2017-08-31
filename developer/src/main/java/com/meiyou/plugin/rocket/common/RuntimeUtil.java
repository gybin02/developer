package com.meiyou.plugin.rocket.common;

import android.content.Context;
import android.util.Log;


import java.io.IOException;

/**
 * Shell 命令执行
 *
 * @author zhengxiaobin@xiaoyouzi.com
 * @since 17/6/29 下午2:45
 */
public class RuntimeUtil {


    private static final String TAG = "RuntimeCmdManager";

    /**
     * 清除本应该的所有数据
     *
     * @param context
     */
    public static void clearApp(Context context) {
        try {
            String packageName = context.getPackageName();
            clearAppUserData(packageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 卸载自己
     *
     * @param context
     */
    public static void uninstall(Context context) {
        String packageName = context.getPackageName();
        execRuntimeProcess("pm uninstall " + packageName);
    }

    /**
     * 清除应用缓存的用户数据，同时停止所有服务和Alarm定时task
     * String cmd = "pm clear " + packageName;
     * String cmd = "pm clear " + packageName + " HERE";
     * Runtime.getRuntime().exec(cmd)
     *
     * @param packageName
     * @return
     */
    public static Process clearAppUserData(String packageName) {
        Process p = execRuntimeProcess("pm clear " + packageName);
        if (p == null) {
            Log.d(TAG,"Clear app data packageName:" + packageName
                    + ", FAILED !");
        } else {
            Log.d(TAG, "Clear app data packageName:" + packageName
                    + ", SUCCESS !");
        }
        return p;
    }


    /**
     * 卸载应用，只能对root设备有效
     * String cmd = "pm uninstall " + packageName;
     * Runtime.getRuntime().exec("pm uninstall " + packageName)
     *
     * @param packageName
     * @return
     */
    public static Process uninstallApp(String packageName) {
        Process p = execRuntimeProcess("pm uninstall " + packageName);
        if (p == null) {
            Log.d(TAG,"Uninstall app packageName:" + packageName + ", FAILED !");
        } else {
            Log.d(TAG,"Uninstall app packageName:" + packageName + ", SUCCESS !");
        }
        return p;
    }


    public static Process execRuntimeProcess(String commond) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(commond);
        } catch (IOException e) {
            Log.d(TAG,"exec Runtime commond:" + commond + ", IOException" + e);
            e.printStackTrace();
        }
        Log.d(TAG,"exec Runtime commond:" + commond + ", Process:" + p);
        return p;
    }
}
