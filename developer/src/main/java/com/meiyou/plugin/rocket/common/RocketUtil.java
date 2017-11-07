package com.meiyou.plugin.rocket.common;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

/**
 * @author zhengxiaobin@xiaoyouzi.com
 * @since 17/11/7
 */

public class RocketUtil {

    /**
     * 拷贝到剪贴板
     *
     * @param context
     * @param content
     */
    public static void copy(Context context, String content) {
        //拷贝到剪贴板
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip = ClipData.newPlainText("text", content);
        clipboardManager.setPrimaryClip(myClip);

        Toast.makeText(context, "内容已经复制到剪贴板", Toast.LENGTH_SHORT).show();
    }
    
}
