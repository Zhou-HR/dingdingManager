package com.gdiot.ssm.redis;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.fastjson.JSON;
import com.gdiot.ssm.util.PropertiesUtil;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {
    private Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;//= PropertiesUtil.getValue("spring.redis.host");

    @Value("${spring.redis.port}")
    private int port;//= Integer.parseInt(PropertiesUtil.getValue("spring.redis.port"));

    @Value("${spring.redis.timeout}")
    private int timeout;//= Integer.parseInt(PropertiesUtil.getValue("spring.redis.timeout"));

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;//= Integer.parseInt(PropertiesUtil.getValue("spring.redis.jedis.pool.max-idle"));

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;//= Long.parseLong(PropertiesUtil.getValue("spring.redis.jedis.pool.max-wait"));

    @Value("${spring.redis.password}")
    private String password;//= PropertiesUtil.getValue("spring.redis.password");

    @Value("${spring.redis.block-when-exhausted}")
    private boolean blockWhenExhausted;//= "true".equals(PropertiesUtil.getValue("spring.redis.block-when-exhausted")) ? true:false;

    @Bean(name = "redisPoolFactory")
    public JedisPool redisPoolFactory() throws Exception {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        JedisPool jedisPool = null;
        if (password == null || password.length() < 1) {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, null);
        } else {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        }

        LOGGER.info("JedisPool注入成功！！");
        LOGGER.info("redis地址：" + host + ":" + port);
        return jedisPool;
    }

    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                if (params != null && params.length > 1
                        && params[0] instanceof RequestFacade
                        && params[1] instanceof Serializable) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.getClass().getName());
                    sb.append("." + method.getName());
                    sb.append("{");
                    sb.append((String) (((RequestFacade) params[0]).getAttribute("tokenuser")));
                    sb.append("}");
                    sb.append("&");
                    sb.append(params[1].getClass().getName());
                    sb.append("{");
                    sb.append(JSON.toJSONString(params[1]));
                    sb.append("}");
                    return sb.toString();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.getClass().getName());
                    sb.append("." + method.getName());
                    if (params == null || params.length == 0 || params[0] == null) {
                        return null;
                    }
                    String join = String.join("&", Arrays.stream(params).map(Object::toString).collect(Collectors.toList()));
                    String format = String.format("%s{%s}", sb.toString(), join);
                    //log.info("缓存key：" + format);
                    return format;
                }
            }
        };
    }


}