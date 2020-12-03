package com.gdiot.ssm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gdiot.ssm.entity.DingDingUser;


/**
 * @author ZhouHR
 */
public interface IDingDingUserService {
    /**
     * @param dingDingUser
     * @return
     */
    List<DingDingUser> selectOne(DingDingUser dingDingUser);

    /**
     * @param dingDingUser
     * @return
     */
    int insertDingDingUser(DingDingUser dingDingUser);

    /**
     * @param dingDingUser
     * @return
     */
    int updateidDingDingUser(DingDingUser dingDingUser);

    /**
     * @param dingDingUser
     * @return
     */
    int updateUserDep(DingDingUser dingDingUser);

    /**
     * @return
     */
    List<DingDingUser> selectAllUserId();
}
