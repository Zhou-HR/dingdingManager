package com.gdiot.ssm.notify;

public class AlyNotifyConfig {

    //阿里云，官方106三网短信、语音电话  鼎信科技
    public static final String method = "POST";
    public static final String appcode = "31420aa8caed43bd8b5dfe72614c0379";

    public static final String host_sms = "http://dingxin.market.alicloudapi.com";
    public static final String path_sms = "/dx/sendSms";
    public static final String tpl_sms_poweroff = "TP19060522";//停电
    public static final String tpl_sms_poweron = "TP1906172";//来电
    public static final String tpl_sms_temperature = "TP19071214";//温度
    public static final String tpl_sms_humidity = "TP19071215";//湿度

    //TP19071215 【国动】#date#，位于#place#的机房湿度过高告警！
    //TP19071214【国动】#date#，位于#place#的机房温度过高告警！
//	TP19071217
//	#date1#，位于#place1#的机房湿度过高告警！#date2#，位于#place2#的机房湿度过高告警！
//	TP19071216
//	#date1#，位于#place1#的机房温度过高告警！#date2#，位于#place2#的机房温度过高告警！


    public static final String host_voice = "http://yuyin2.market.alicloudapi.com";
    public static final String path_voice = "/dx/voice_notice";
    public static final String tpl_voice_poweroff = "TP19060523";//停电告警
    public static final String tpl_voice_temperature = "TP19071216";//温度过高告警
    public static final String tpl_voice_humidity = "TP19071217";//湿度过高告警

    //机柜告警 钉钉推送通知
    public static final String ding_host = "http://114.215.241.151:9081";
    public static final String ding_path = "/rmalert/dd/sendNotify";
    public static final String ding_msg_poweroff = "";
    public static final String ding_msg_poweron = "";


    public static String getDingMsgPowerOn(String date, String place) {
        return "【国动】" + date + ",位于" + place + "的机房来电！";
    }

    public static String getDingMsgPowerOff(String date, String place) {
        return "【国动】" + date + ",位于" + place + "的机房停电！";
    }

    public static String getDingMsgTemperature(String date, String place) {
        return "【国动】" + date + ",位于" + place + "的机房温度过高告警！";
    }

    public static String getDingMsgHumidity(String date, String place) {
        return "【国动】" + date + ",位于" + place + "的机房湿度过高告警！";
    }

    public static String getDingMsgTemperatureOK(String date, String place) {
        return "【国动】" + date + ",位于" + place + "的机房温度恢复正常！";
    }

    public static String getDingMsgHumidityOK(String date, String place) {
        return "【国动】" + date + ",位于" + place + "的机房湿度恢复正常！";
    }

    public static String getMsgModule(String type, String date, String place, int power_status) {
        String msg = "【国动】";
        switch (power_status) {
            case 0://来电
                msg = date + ",位于" + place + "的机房来电！";
                break;
            case 1://停电
                msg = date + ",位于" + place + "的机房停电！";
                break;
            case 2://温度过高
                if ("sms".equals(type)) {
                    msg = date + ",位于" + place + "的机房温度过高告警！";
                } else if ("voice".equals(type)) {
                    msg = date + ",位于" + place + "的机房温度过高告警！" + date + ",位于" + place + "的机房温度过高告警！";
                }
                break;
            case 3://湿度过高
                if ("sms".equals(type)) {
                    msg = date + ",位于" + place + "的机房湿度过高告警！";
                } else if ("voice".equals(type)) {
                    msg = date + ",位于" + place + "的机房湿度过高告警！" + date + ",位于" + place + "的机房湿度过高告警！";
                }
                break;
            default:
                break;
        }
        return msg;
    }

}
