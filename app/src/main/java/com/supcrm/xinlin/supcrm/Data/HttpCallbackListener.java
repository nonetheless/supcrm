package com.supcrm.xinlin.supcrm.Data;

/**
 * Created by YJF on 2016/6/26.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
