package com.meiyou.plugin.rocket;

import com.meiyou.plugin.rocket.common.ContentHolder;

import java.util.Date;

@Deprecated
class LogHistory implements ContentHolder {

    private final String tag;
    private final String message;
    private final Date time;

    public LogHistory(String tag, String message, Date time) {
        this.tag = tag;
        this.message = message;
        this.time = time;
    }

    @Override
    public String getTitle() {
        return time.toString();
    }

    @Override
    public String getValue() {
        return tag + ": " + message;
    }
}
