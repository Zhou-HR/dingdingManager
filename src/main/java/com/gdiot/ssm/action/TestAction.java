package com.gdiot.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.gdiot.ssm.dao.DingDingUserMapper;
import com.gdiot.ssm.entity.DingDingUser;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ZhouHR
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestAction {

    @Autowired
    private DingDingUserMapper dingDingUserMapper;

    @RequestMapping("/selectTbData")
    public List<DingDingUser> selectTbData() {

        DingDingUser dingDingUser = new DingDingUser();

        dingDingUser.setDdId("virus");

        dingDingUserMapper.insertDingDingUser(dingDingUser);
        dingDingUserMapper.updateidDingDingUser(dingDingUser);
        return null;

    }

    @GetMapping(value = "/test")
    public void test() {
        log.info("test===========@@@@@@@@@@@@@");
        log.info("测试页面");
        System.out.printf("测试页面");
    }
}
