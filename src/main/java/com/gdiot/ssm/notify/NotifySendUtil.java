package com.gdiot.ssm.notify;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.gdiot.ssm.util.HttpClientUtil;

public class NotifySendUtil {

    /**
     * 发送告警通知
     * String type = "sms";//voice  sms
     * String mobile = "13564017182";
     * String date = "2019年6月5日13时25分";
     * String place = "上海市静安区成都北路333号招商局广场东楼25层";
     * SendNotify( type, mobile, date, place);
     */

    public static JSONObject SendNotify(String type, String mobile, String date, String place, int power_status) {
        String host = AlyNotifyConfig.host_sms;
        String path = AlyNotifyConfig.path_sms;
        String method = AlyNotifyConfig.method;
        String appcode = AlyNotifyConfig.appcode;
        String tpl_id = null;
        String param = null;

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        if ("sms".equals(type)) {
            host = AlyNotifyConfig.host_sms;
            path = AlyNotifyConfig.path_sms;
            querys.put("mobile", mobile);
            param = "date:" + date + ",place:" + place;

            if (power_status == 1) {//停电
                tpl_id = AlyNotifyConfig.tpl_sms_poweroff;
            } else if (power_status == 0) {//来电
                tpl_id = AlyNotifyConfig.tpl_sms_poweron;
            } else if (power_status == 2) {//温度过高
                tpl_id = AlyNotifyConfig.tpl_sms_temperature;
            } else if (power_status == 3) {//湿度过高
                tpl_id = AlyNotifyConfig.tpl_sms_humidity;
            }
        } else if ("voice".equals(type)) {
            host = AlyNotifyConfig.host_voice;
            path = AlyNotifyConfig.path_voice;
            tpl_id = AlyNotifyConfig.tpl_voice_poweroff;
            querys.put("phone", mobile);
            param = "date:" + date + ",place:" + place;

            if (power_status == 1) {//停电
                tpl_id = AlyNotifyConfig.tpl_voice_poweroff;
            } else if (power_status == 0) {//来电
                return null;
            } else if (power_status == 2) {//温度过高
                tpl_id = AlyNotifyConfig.tpl_voice_temperature;
                param = "date1:" + date + ",place1:" + place + ",date2:" + date + ",place2:" + place;
            } else if (power_status == 3) {//湿度过高
                tpl_id = AlyNotifyConfig.tpl_voice_humidity;
                param = "date1:" + date + ",place1:" + place + ",date2:" + date + ",place2:" + place;
            }
        }
        querys.put("param", param);
        querys.put("tpl_id", tpl_id);
        Map<String, String> bodys = new HashMap<String, String>();
        JSONObject responseBody = null;
        try {
            HttpResponse httpResponse = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(httpResponse.toString());
            //获取response的body
//	    	System.out.println("body=="+EntityUtils.toString(httpResponse.getEntity()));//注：次方法只能调用一次，获取流数据EntityUtils.toString()
            String body = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("body==" + body);
            responseBody = new JSONObject(body);
            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseBody = new JSONObject();
        responseBody.put("return_code", "1");
        responseBody.put("order_id", "send notify fail !");
        return responseBody;
    }

    public static JSONObject SendDingNotify(String dduserid, String date, String place, int power_status) {
//		String host = AlyNotifyConfig.ding_host;
//	    String path = AlyNotifyConfig.ding_path;
        String url = AlyNotifyConfig.ding_host + AlyNotifyConfig.ding_path;
//	    String method = AlyNotifyConfig.method;
        String msg = "【国动】";
        if (power_status == 0) {//来电
            msg = AlyNotifyConfig.getDingMsgPowerOn(date, place);
        } else if (power_status == 1) {//停电
            msg = AlyNotifyConfig.getDingMsgPowerOff(date, place);
        } else if (power_status == 2) {//过温
            msg = AlyNotifyConfig.getDingMsgTemperature(date, place);
        } else if (power_status == 3) {//过湿
            msg = AlyNotifyConfig.getDingMsgHumidity(date, place);
        } else if (power_status == 4) {//温度恢复
            msg = AlyNotifyConfig.getDingMsgHumidityOK(date, place);
        } else if (power_status == 5) {//湿度恢复
            msg = AlyNotifyConfig.getDingMsgHumidityOK(date, place);
        }

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Content-Type", "application/json");

        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("userID", dduserid);
        bodys.put("msg", msg);
        JSONObject responseBody = null;
        try {
            String result = HttpClientUtil.postJson(url, bodys, "utf8");
            responseBody = new JSONObject(result);
            if (result != null) {
                responseBody = new JSONObject(result);
            } else {
                responseBody = new JSONObject();
                responseBody.put("errcode", "1");
                responseBody.put("error", "time out!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseBody;

    }
}
