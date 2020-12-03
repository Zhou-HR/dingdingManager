package com.gdiot.ssm.service;

import java.util.List;
import java.util.Map;

import com.gdiot.ssm.entity.DingDepPo;
import com.gdiot.ssm.entity.DingUserPo;


/**
 * @author ZhouHR
 */
public interface IDingDepDataService {
    /**
     * @param mDingDepPo
     * @return
     */
    int addOne(DingDepPo mDingDepPo);

    /**
     * @param dep_id
     * @return
     */
    List<DingDepPo> selectOne(String dep_id);
}
