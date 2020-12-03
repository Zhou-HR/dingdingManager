package com.gdiot.ssm.redis;

/**
 * @author tanshuai
 */
public interface Serializer {

    /**
     * 序列化
     */
    byte[] serialize(Object t);

    /**
     * 反序列化
     */
    <T> T deserialize(byte[] bytes);
}
