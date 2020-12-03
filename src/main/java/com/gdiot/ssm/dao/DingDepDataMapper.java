package com.gdiot.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gdiot.ssm.entity.DingDepPo;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DingDepDataMapper {
	int insertOne(DingDepPo mDingDepPo);
	int updateDetail(DingDepPo mDingDepPo);
	List<DingDepPo> selectOne(@Param("dep_id")String dep_id);
}
