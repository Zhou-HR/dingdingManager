package com.gdiot.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gdiot.ssm.entity.User;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    List<User> getUser(@Param("appKey") String appKey, @Param("appSecret") String appSecret);

}
