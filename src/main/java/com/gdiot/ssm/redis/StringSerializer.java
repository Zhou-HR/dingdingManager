package com.gdiot.ssm.redis;

import java.nio.charset.Charset;

/**
 * @author tanshuai
 */
public class StringSerializer implements Serializer {

    private final Charset charset = Charset.forName("UTF8");

    @Override
    public byte[] serialize(Object s) {
        return (s == null || !(s instanceof String) ? null : ((String) s).getBytes(charset));
    }

    @Override
    @SuppressWarnings("unchecked")
    public String deserialize(byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset));
    }
}
