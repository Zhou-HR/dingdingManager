package com.gdiot.ssm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.gdiot.ssm.dao.DingDingUserMapper;
import com.gdiot.ssm.entity.DingAccountPo;
import com.gdiot.ssm.entity.DingDepPo;
import com.gdiot.ssm.entity.DingDingUser;
import com.gdiot.ssm.entity.DingUserPo;
import com.gdiot.ssm.entity.DingUserResult;
import com.gdiot.ssm.notify.NotifySendUtil;
import com.gdiot.ssm.service.AsyncService;
import com.gdiot.ssm.service.IDingAccountService;
import com.gdiot.ssm.service.IDingDepDataService;
import com.gdiot.ssm.service.IDingDingUserService;
import com.gdiot.ssm.service.IDingUserDataService;
import com.gdiot.ssm.session.RequestProcess;
import com.gdiot.ssm.task.DataSenderTask;
import com.gdiot.ssm.util.DingDataAnalysis;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhouHR
 */
@RestController
@RequestMapping("/dd")
public class DingDingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DingDingController.class);

    @Autowired
    private DingDingUserMapper dingDingUserMapper;

    @Autowired()
    private AsyncService asyncService;

    @Autowired()
    private IDingUserDataService mIDingUserDataService;

    @Autowired()
    private IDingDepDataService mIDingDepDataService;

    @Autowired()
    private IDingDingUserService mIDingDingUserService;

    @Autowired()
    private IDingAccountService mIDingAccountService;

    /**
     * 发送工作通知消息
     *
     * @return
     */
    @RequestMapping("/sendNotifyByCode")
    public String sendMessage(@RequestBody Map<String, String> params) {
        String userID = null;
        String msg = null;
        if (params != null) {
            if (params.containsKey("userID")) {
                userID = params.get("userID");
            }
            if (params.containsKey("msg")) {
                msg = params.get("msg");
            }
        }
        String ddUserId = userID;
        LOGGER.info("ddUserId=" + ddUserId);

        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);
        OapiMessageCorpconversationAsyncsendV2Response sendnotify = mDingDataAnalysis.sendMessage(ddUserId, msg, accessToken);
        LOGGER.info("sendnotify=" + sendnotify.getBody());
        boolean isSuccess = sendnotify.isSuccess();
        if (isSuccess) {
//			DingMsgDataPo mDingMsgDataPo = new DingMsgDataPo();
//			mDingMsgDataPo.setUserid(userID);
//			mDingMsgDataPo.setMsg(msg);
//			notifyService.insertDingMsg(mDingMsgDataPo);
        }
        return sendnotify.getBody();

    }

    /**
     * 发送工作通知消息
     *
     * @return
     */
    @RequestMapping("/sendNotify")
    public String sendMessageToUser(@RequestBody Map<String, String> params) {
        String userID = null;
        String msg = null;
        if (params != null) {
            if (params.containsKey("userID")) {
                userID = params.get("userID");
            }
            if (params.containsKey("msg")) {
                msg = params.get("msg");
            }
        }

        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);

        OapiMessageCorpconversationAsyncsendV2Response sendnotify = mDingDataAnalysis.sendMessage(userID, msg, accessToken);
        LOGGER.info("getBody=" + sendnotify.getBody());
        LOGGER.info("getErrorCode=" + sendnotify.getErrorCode());
        LOGGER.info("isSuccess=" + sendnotify.isSuccess());
        boolean isSuccess = sendnotify.isSuccess();
        if (isSuccess) {
//			DingMsgDataPo mDingMsgDataPo = new DingMsgDataPo();
//			mDingMsgDataPo.setUserid(userID);
//			mDingMsgDataPo.setMsg(msg);
//			notifyService.insertDingMsg(mDingMsgDataPo);
        }
        return sendnotify.getBody();

    }

    /**
     * 测试用接口：发送钉钉消息
     *
     * @return
     */
    @RequestMapping("/sendDingMsg")
    public String sendDingMsg(@RequestBody Map<String, String> params) {
        String dduserid = null;
        String date = null;
        String place = null;
        String power_status = null;
        if (params != null) {

            if (params.containsKey("dduserid")) {
                dduserid = params.get("dduserid");
            }
            if (params.containsKey("date")) {
                date = params.get("date");
            }
            if (params.containsKey("place")) {
                place = params.get("place");
            }
            if (params.containsKey("power_status")) {
                power_status = params.get("power_status");
            }
        }

        int power_statu = Integer.parseInt(power_status);

        org.json.JSONObject result = NotifySendUtil.SendDingNotify(dduserid, date, place, power_statu);
        LOGGER.info("发送钉钉通知  result=" + result.toString());
        return result.toString();
    }

    /**
     * 查询钉钉用户个数
     *
     * @return
     */
    @RequestMapping("/getUserCount")
    public String getUserCount(@RequestBody Map<String, String> params) {

        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);

        Long userCount = mDingDataAnalysis.getUserCount(accessToken);
        LOGGER.info("usercount=" + userCount);
        return "dingding user count:" + userCount;
    }

    /**
     * 查询钉钉部门用户列表
     *
     * @return
     */
    @RequestMapping("/getDepUserList")
    public String getDepUserList(@RequestBody Map<String, String> params) {
        String depId = null;
        if (params != null) {

            if (params.containsKey("depId")) {
                depId = params.get("depId");
            }
        }
        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);

        String depUserList = mDingDataAnalysis.getDepUserList(depId, accessToken);
        LOGGER.info("usercount=" + depUserList);
        return "dingding depUserList:" + depUserList;
    }

    /**
     * 查询钉钉部门用户详情
     *
     * @return
     */
    @RequestMapping("/getDepUserDetail")
    public String getDepUserDetail(@RequestBody Map<String, String> params) {
        String depId = null;
        if (params != null) {
            if (params.containsKey("depId")) {
                depId = params.get("depId");
            }
        }
        long depIdL = Long.valueOf(depId);

        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);

        String userListDetail = mDingDataAnalysis.getDepUserDetail(depIdL, accessToken);
        LOGGER.info("userListDetail=" + userListDetail);
        return "dingding user count:" + userListDetail;
    }

    /**
     * 查询钉钉用户详情
     *
     * @return
     */
    @RequestMapping("/getUserDetail")
    public String getUserDetail(@RequestBody Map<String, String> params) {
        String userId = null;
        if (params != null) {
            if (params.containsKey("userId")) {
                userId = params.get("userId");
            }
        }

        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);

        DingDingUser mDingDingUser = mDingDataAnalysis.getUserDetail(userId, accessToken);
        if (mDingDingUser != null) {
            mIDingDingUserService.insertDingDingUser(mDingDingUser);

            //		LOGGER.info("mDingDingUser="+mDingDingUser);
            System.out.println("getDdId=" + mDingDingUser.getDdId() + "\n");
            System.out.println("getName=" + mDingDingUser.getName() + "\n");
            System.out.println("getEmail=" + mDingDingUser.getEmail() + "\n");
            System.out.println("getDept1=" + mDingDingUser.getDept1() + "\n");
            System.out.println("getPosition=" + mDingDingUser.getPosition() + "\n");
            System.out.println("getMobile=" + mDingDingUser.getMobile() + "\n");
            System.out.println("getStartWorkDate=" + mDingDingUser.getStartWorkDate() + "\n");//入职时间
            System.out.println("getWorkNo=" + mDingDingUser.getWorkNo() + "\n");
            System.out.println("getPhone=" + mDingDingUser.getPhone() + "\n");
            System.out.println("getWorkLocation=" + mDingDingUser.getWorkLocation() + "\n");

            System.out.println("getUserId=" + mDingDingUser.getUserId() + "\n");
            System.out.println("getInviteMobile=" + mDingDingUser.getInviteMobile() + "\n");
            System.out.println("getManagerUserId=" + mDingDingUser.getManagerUserId() + "\n");
            System.out.println("getMessage=" + mDingDingUser.getMessage() + "\n");
            System.out.println("getNickname=" + mDingDingUser.getNickname() + "\n");
            System.out.println("getOpenId=" + mDingDingUser.getOpenId() + "\n");
            System.out.println("getOrderInDepts=" + mDingDingUser.getOrderInDepts() + "\n");
            System.out.println("getOrgEmail=" + mDingDingUser.getOrgEmail() + "\n");
            System.out.println("getRoles=" + mDingDingUser.getRoles() + "\n");
            System.out.println("getUnionid=" + mDingDingUser.getUnionid() + "\n");
            System.out.println("extattr=" + mDingDingUser.getExtattr());
            System.out.println("getErpNo=" + mDingDingUser.getErpNo());
            System.out.println("getOaNo=" + mDingDingUser.getOaNo());

            String body = mDingDingUser.getBody();
            LOGGER.info("mDingDingUser body=" + body);

            DingUserPo mDingDepUserPo = new DingUserPo();
            mDingDepUserPo.setUser_id(userId);
            mDingDepUserPo.setUser_detail(body);
            mIDingUserDataService.addOne(mDingDepUserPo);

            return "dingding userDetail:" + body;
        } else {
            return "fail";
        }

    }


    /**
     * 查询钉钉用户详情
     *
     * @return
     */
    @RequestMapping("/getDepDetail")
    public String getDepDetail(@RequestBody Map<String, String> params) {
        String depId = null;
        if (params != null) {
            if (params.containsKey("depId")) {
                depId = params.get("depId");
            }
        }
        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);

        DingDepPo mDingDepPo = mDingDataAnalysis.getDepDetail(depId, accessToken);
        LOGGER.info("getDep_id=" + mDingDepPo.getDep_id());
        LOGGER.info("getDep_name=" + mDingDepPo.getDep_name());
        LOGGER.info("getDep_detail=" + mDingDepPo.getDep_detail());
//		LOGGER.info("getAutoAddUser="+mDingDepPo.getAutoAddUser());
        LOGGER.info("getCode=" + mDingDepPo.getCode());
//		LOGGER.info("getCreateDeptGroup="+mDingDepPo.getCreateDeptGroup());
        LOGGER.info("getDeptManagerUseridList=" + mDingDepPo.getDeptManagerUseridList());
        LOGGER.info("getDeptPerimits=" + mDingDepPo.getDeptPerimits());
        LOGGER.info("getDeptPermits=" + mDingDepPo.getDeptPermits());
//		LOGGER.info("getGroupContainSubDept="+mDingDepPo.getGroupContainSubDept());
//		LOGGER.info("getOuterDept="+mDingDepPo.getOuterDept());
        LOGGER.info("getParentid=" + mDingDepPo.getParent_id());
        LOGGER.info("getUserPerimits=" + mDingDepPo.getUserPerimits());
        LOGGER.info("getUserPermits=" + mDingDepPo.getUserPermits());

        mIDingDepDataService.addOne(mDingDepPo);

        return "getDep_name=" + mDingDepPo.getDep_name();
    }


    /**
     * 查询钉钉所有父部门
     *
     * @return
     */
    @RequestMapping("/getALLParentDep")
    public String getALLParentDep(@RequestBody Map<String, String> params) {

        String depId = null;
        if (params != null) {
            if (params.containsKey("depId")) {
                depId = params.get("depId");
            }
        }
        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);

        List<Long> list = mDingDataAnalysis.getAllParentDepId(depId, accessToken);
        int count = list.size();
        for (int i = 0; i < count; i++) {
            System.out.println("parentId " + i + " :" + list.get(i) + "\n");
        }
        return "ok";
    }

    /**
     * 查询钉钉指定用户的所有父部门
     *
     * @return
     */
    @RequestMapping("/getUserParentDep")
    public String getUserParentDep(@RequestBody Map<String, String> params) {
        String userId = null;
        if (params != null) {
            if (params.containsKey("userId")) {
                userId = params.get("userId");
            }
        }
        DingDataAnalysis mDingDataAnalysis = new DingDataAnalysis();
        String accessToken = mDingDataAnalysis.getToken();
        System.out.println("AccessToken=" + accessToken);
        DingDingUser mDingDingUser = mDingDataAnalysis.getUserParentDepId(userId, accessToken);
        mIDingDingUserService.updateUserDep(mDingDingUser);

        return "department:" + mDingDingUser.getDept1();
    }

    /**
     * 查询钉钉所有用户详情
     *
     * @return
     */
    @RequestMapping("/getAllDepDetail")
    public String getAllDepDetail(@RequestBody Map<String, String> params) {
        String depId = null;
        if (params != null) {
            if (params.containsKey("depId")) {
                depId = params.get("depId");
            }
        }
        DataSenderTask task = new DataSenderTask(depId, "all_dep_detail");
        task.setAsyncService(asyncService);
        asyncService.executeAsync(task);

        return "ok";
    }


    /**
     * ***********************************查询钉钉所有部门用户详情并保存**************************************
     *
     * @return
     */
    @RequestMapping("/getALLDepUserDetail")
    public String getALLDepUserDetail(@RequestBody Map<String, String> params) {
        String depId = "1";

        DataSenderTask task = new DataSenderTask(depId, "all_dep_user_detail");
        task.setAsyncService(asyncService);
        asyncService.executeAsync(task);

        return "ok";
    }

    /**
     * 查询钉钉所有用户的父部门并更新到数据库
     *
     * @return
     */
    @RequestMapping("/getALLUserParentDep")
    public String getALLUserParentDep(@RequestBody Map<String, String> params) {
        String depId = "1";
        DataSenderTask task = new DataSenderTask(depId, "all_user_parent_dep");
        task.setAsyncService(asyncService);
        asyncService.executeAsync(task);

        return "OK";
    }

    @RequestMapping("/getUserAll")
    @RequestProcess
    public Map<String, Object> getUserAll(@RequestBody Map<String, String> params) {

        int pageNo = 1;
        int pageSize = 100;
        if (params != null) {
            if (params.containsKey("pageNo")) {
                pageNo = Integer.valueOf(params.get("pageNo"));
            }
            if (params.containsKey("pageSize")) {
                pageSize = Integer.valueOf(params.get("pageSize"));
            }
        }

        int count = dingDingUserMapper.selectDingDingUserCount();

        //分页展示时，size超过总数时只返回20个。
        //要获取全部数据，返回结果中count的值就是全部的，后期人员变更的话count也会变更，拿count值做依据写你的接口。这样不影响分页。
        if (pageSize < 1 || pageSize > count) {
            pageSize = count;
        }
        int totalPage = count / pageSize;
        if (pageNo < 1 || pageSize > totalPage) {
            pageNo = 1;
        }

        int limit = pageSize;
        int offset = (pageNo - 1) * pageSize;

        List<DingUserResult> list = new ArrayList<DingUserResult>();
        if (pageNo >= 1) {
            list = dingDingUserMapper.selectDingDingUser(limit, offset);
        }

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("count", count);
        map.put("list", list);

        return map;
    }

    /**
     * 添加钉钉第三方免登账号
     *
     * @return
     */
    @RequestMapping("/addDingLoginAccount")
    public String addDingLoginAccount(@RequestBody Map<String, String> params) {
        String client = null;
        String suiteId = null;
        String appId = null;
        String suiteKey = null;
        String suiteSecret = null;
        if (params != null) {
            if (params.containsKey("client")) {
                client = params.get("client");
            }
            if (params.containsKey("suiteId")) {
                suiteId = params.get("suiteId");
            }
            if (params.containsKey("appId")) {
                appId = params.get("appId");
            }
            if (params.containsKey("suiteKey")) {
                suiteKey = params.get("suiteKey");
            }
            if (params.containsKey("suiteSecret")) {
                suiteSecret = params.get("suiteSecret");
            }
        }
        //client,suiteId,appId,suiteKey,suiteSecret
        DingAccountPo mDingAccountPo = new DingAccountPo();
        mDingAccountPo.setClient(client);
        mDingAccountPo.setSuiteId(suiteId);
        mDingAccountPo.setAppId(appId);
        mDingAccountPo.setSuiteKey(suiteKey);
        mDingAccountPo.setSuiteSecret(suiteSecret);
        int result = mIDingAccountService.insertOne(mDingAccountPo);

        if (result == 1) {
            return "succ";
        } else {
            return "fail";
        }
    }

    /**
     * 查询钉钉第三方免登账号
     *
     * @return
     */
    @RequestMapping("/getDingLoginAccount")
    public List<DingAccountPo> getDingLoginAccount(@RequestBody Map<String, String> params) {
        String client = null;
        int pageNo = 1;
        int pageSize = 10;
        if (params != null) {
            if (params.containsKey("client")) {
                client = params.get("client");
            }
            if (params.containsKey("pageNo")) {
                pageNo = Integer.parseInt(params.get("pageNo"));
            }
            if (params.containsKey("pageSize")) {
                pageSize = Integer.parseInt(params.get("pageSize"));
            }
        }
        return mIDingAccountService.selectAccountList(client, pageNo, pageSize);
    }
}
