package com.gdiot.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gdiot.ssm.entity.DingAccountPo;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DingAccountMapper {
	int insertOne(DingAccountPo mDingAccountPo);
	int countAccountList(@Param("client")String client);
	List<DingAccountPo> selectAccountList(@Param("client")String client,@Param("limit")int limit,@Param("offset")int offset);
}
