package com.gdiot.ssm.service;

import java.util.List;
import java.util.Map;

import com.gdiot.ssm.entity.DingUserPo;


/**
 * @author ZhouHR
 */
public interface IDingUserDataService {
    /**
     * @param mXBEMDataPo
     * @return
     */
    int addOne(DingUserPo mXBEMDataPo);

    /**
     * @param user_id
     * @return
     */
    List<DingUserPo> selectOne(String user_id);
}
