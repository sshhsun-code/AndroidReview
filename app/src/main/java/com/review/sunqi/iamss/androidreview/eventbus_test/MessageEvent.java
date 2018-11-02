package com.review.sunqi.iamss.androidreview.eventbus_test;

/**
 * Created by sunqi on 2018/11/2.
 */

public class MessageEvent {

    private String msg;
    private long time;

    public MessageEvent(String msg, long time) {
        this.msg = msg;
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "msg='" + msg + '\'' +
                ", time=" + time +
                '}';
    }
}
