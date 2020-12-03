package com.gdiot.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gdiot.ssm.entity.DingUserPo;
import org.springframework.stereotype.Component;

/**
 * @author ZhouHR
 */
@Mapper
@Component
public interface DingUserDataMapper {
    /**
     * @param mDingDepPo
     * @return
     */
    int insertOne(DingUserPo mDingDepPo);

    /**
     * @param mDingDepPo
     * @return
     */
    int updateDetail(DingUserPo mDingDepPo);

    /**
     * @param user_id
     * @return
     */
    List<DingUserPo> selectOne(@Param("user_id") String user_id);
}
