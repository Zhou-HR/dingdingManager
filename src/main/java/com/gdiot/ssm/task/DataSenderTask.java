package com.gdiot.ssm.task;

import com.alibaba.fastjson.JSONArray;
import com.gdiot.ssm.redis.RedisUtil;
import com.gdiot.ssm.service.AsyncService;
import com.gdiot.ssm.service.IDingUserDataService;
import com.gdiot.ssm.util.DingDataAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author ZhouHR
 */
public class DataSenderTask implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSenderTask.class);
    private String data;
    private String type;
    private Map<String, Object> msgMap;

    private AsyncService asyncService;
    public RedisUtil redisUtil;
    private IDingUserDataService mIDingUserDataService;

    public DataSenderTask(String data, String type) {
        super();
        this.data = data;
        this.type = type;
    }

    public DataSenderTask(Map<String, Object> map, String type) {
        super();
        this.msgMap = map;
        this.type = type;
    }

    @Override
    public void run() {
        LOGGER.info("task: DataSenderTask run-data :" + data);
        LOGGER.info("task: DataSenderTask run-type :" + type);
        LOGGER.info("task: DataSenderTask run-msgMap :" + msgMap);

        switch (type) {
            case "all_dep_user_detail":
                getAllDepUserDetail(data);
                break;
            case "all_dep_detail":
                getAllDepDetail(data);
                break;
            case "all_user_parent_dep":
                getAllUserParentDep();
                break;
            default:
                break;
        }
    }

    private void getAllDepUserDetail(String depId) {

        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);

        JSONArray userListDetail = mDingDataAnalysis.getAllDepUserDetail(depId, accessToken);
        LOGGER.info("userListDetail=" + userListDetail);
    }

    private void getAllDepDetail(String depId) {
        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);

        JSONArray userListDetail = mDingDataAnalysis.getAllDepDetail(depId, accessToken);
        LOGGER.info("userListDetail=" + userListDetail);
    }

    private void getAllUserParentDep() {

        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);

        mDingDataAnalysis.getAllUserParentDep(accessToken);

    }

    public AsyncService getAsyncService() {
        return asyncService;
    }

    public void setAsyncService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

}
