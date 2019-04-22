package com.miaoyunhan.piao_liu_peng.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.miaoyunhan.piao_liu_peng.configration.ResponseBean;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JiGuangPushUtils {

    protected static final String APP_KEY ="a5b637bc5366afa19f1448dd";
    protected static final String MASTER_SECRET = "fa18a610c40adaebebf8429d";
    protected static final String GROUP_PUSH_KEY = "2c88a01e073a0fe4fc7b167c";
    protected static final String GROUP_MASTER_SECRET = "b11314807507e2bcfdeebe2e";
    
    public static ResponseBean pushAll(){
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = PushPayload.alertAll("推送测试");

        try {
            PushResult result = jpushClient.sendPush(payload);
            log.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
        return new ResponseBean(200,"推送完成",null);
    }
}
