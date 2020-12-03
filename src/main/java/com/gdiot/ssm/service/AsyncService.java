package com.gdiot.ssm.service;

/**
 * @author ZhouHR
 */
public interface AsyncService {
    /**
     * 执行异步线程
     *
     * @param runnable
     */
    void executeAsync(Runnable runnable);

    /**
     * 执行Mqtt异步线程
     *
     * @param runnable
     */
    void executeMqttAsync(Runnable runnable);
}
