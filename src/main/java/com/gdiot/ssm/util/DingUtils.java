package com.gdiot.ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.CorpMessageCorpconversationAsyncsendRequest;
import com.dingtalk.api.request.OapiAuthScopesRequest;
import com.dingtalk.api.request.OapiDepartmentListIdsRequest;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.request.OapiProcessinstanceListidsRequest;
import com.dingtalk.api.request.OapiRoleListRequest;
import com.dingtalk.api.request.OapiUserCreateRequest;
import com.dingtalk.api.request.OapiUserGetDeptMemberRequest;
import com.dingtalk.api.request.OapiUserGetOrgUserCountRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.request.OapiUserListbypageRequest;
import com.dingtalk.api.response.CorpMessageCorpconversationAsyncsendResponse;
import com.dingtalk.api.response.OapiAuthScopesResponse;
import com.dingtalk.api.response.OapiDepartmentListIdsResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.dingtalk.api.response.OapiProcessinstanceListidsResponse;
import com.dingtalk.api.response.OapiRoleListResponse;
import com.dingtalk.api.response.OapiUserCreateResponse;
import com.dingtalk.api.response.OapiUserGetDeptMemberResponse;
import com.dingtalk.api.response.OapiUserGetOrgUserCountResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.gdiot.ssm.entity.DingUserPo;
import com.gdiot.ssm.service.IDingUserDataService;
import com.taobao.api.ApiException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:对接钉钉的工具类
 * @Authod:zhang_cq
 * @Date:2018/3/19 下午2:59
 */
public class DingUtils {
    //国动集团 test
//	public static Long AGENTID = 269962304L; // 自动分配微应用的ID
//	public static String APPKEY = "dingjnfheu6vhbcxvugm"; // 
//	public static String APPSECRET = "JIVA8V4KpbJZnRb2x2FCtpBonRjC22HUuP4eGVE3ogeRUUSsXBoO1Bq5ROU4hw_M"; // 

    //国动集团 正式
    public static Long AGENTID = 274787266L; //
    public static String APPKEY = "dingmjyy7k9kc8xjsm1d"; // 自动分配微应用的ID
    public static String APPSECRET = "WGjd37lRf8Z7sfQDljB8mfldHmCQ3aeHbEBkfnieQSeDzq3LhgYn1yiTm5cnnORW"; //
    public static String processCode = "PROC-02D26D8A-6A71-4171-8C72-8AFC19D3E734";

    //国动信息技术 智能电表
//	public static Long AGENTID = 270133545L; // 自动分配微应用的ID
//	public static String APPKEY = "ding72oqzkdtqfhfqepu"; // 
//	public static String APPSECRET = "KMt5fM_R3F-HcndXY4iXiwxe7sEvkKL4nFwFH1Utj-nk9X9W_mJOZqT4UX40abm6"; // 	
    public static String tokenURL = "https://oapi.dingtalk.com/gettoken";
    public static String sendMsgURL = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";
    public static String getUserCountURL = "https://oapi.dingtalk.com/user/get_org_user_count";

    //国动信息技术 钉钉出差审批
//	public static Long AGENTID = 341334746L; // 自动分配微应用的ID
//	public static String APPKEY = "ding6hfazloykplfk2fy"; // 
//	public static String APPSECRET = "hhkzxS15gFvY3VI8Uolj88T3JaUiZJxtFMoZQau2U2875bVdnVIAzZsRCkE55Bgf"; // 
//	public static String processCode="PROC-04F5093E-F179-4DE6-965B-4A5B3E04EE83";
    public static String processURL = "https://oapi.dingtalk.com/topapi/processinstance/listids?access_token=ACCESS_TOKEN";
    public static String processDetailURL = "https://oapi.dingtalk.com/topapi/processinstance/get?access_token=ACCESS_TOKEN";

    //国动信息技术 钉钉单一登录 ddlogin
//	public static Long AGENTID = 360890833L;
//	public static String APPKEY = "dingwoquxnyzoo9a1zzs";
//	public static String APPSECRET = "1EGdgvA90USDJK61W9e7Bjp4phacZ1GfxPtRd6qa2_YwBwgsSnPtSuNoIVjhO66o";

    //国动物联网
//	public static Long AGENTID = 270131679L; // 
//	public static String APPKEY = "dinghdpce2zfpjaq9w2y"; // 自动分配微应用的ID
//	public static String APPSECRET = "gN7uhVov9Ozq9-caeqScsaCq-SOeWBsjID4VnmZgCTN2UzLRSagOFBvtYkmE4RBw"; // 

}


