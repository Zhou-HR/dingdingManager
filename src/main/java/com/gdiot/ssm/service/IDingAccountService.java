package com.gdiot.ssm.service;

import java.util.List;

import com.gdiot.ssm.entity.DingAccountPo;


/**
 * @author ZhouHR
 */
public interface IDingAccountService {
    /**
     * @param mDingAccountPo
     * @return
     */
    int insertOne(DingAccountPo mDingAccountPo);

    /**
     * @param client
     * @return
     */
    int countAccountList(String client);

    /**
     * @param client
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<DingAccountPo> selectAccountList(String client, int pageNo, int pageSize);
}
