package com.gdiot.ssm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ZhouHR
 */
public class DateUtil2 {
	
	public static String dealWarningTime(String time){
		if(StringUtils.isNotEmpty(time)&&time.length()==12){
			String time1="20"+time.substring(0,2)+"-"+time.substring(2,4)+"-"+time.substring(4,6)+" ";
			time1+=time.substring(6,8)+":"+time.substring(8,10)+":"+time.substring(10,12);
			return time1;
					
		}
		return time;
	}
	//获取前2天的日期，保证当月的
	public static String getSimToday1(int num){
		Calendar cnow=Calendar.getInstance();
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, num);
		if(c.get(Calendar.MONTH)!=cnow.get(Calendar.MONTH))//不是同一个月
		{
			c=Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(c.getTime());
	}
	
	public static String getSimToday2(){
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(date);
	}
	
	/**
	 * 将当前时间格式化为yyyyMMdd
	 * @return
	 */
	public static String getToday(){
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		
		return sdf.format(date);
	}
	
	public static String getToday2(){
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(date);
	}
	
	
	/**
	 * 将时间格式化为yyyyMMdd HH
	 * @param date
	 * @return
	 */
	public static String getHour(Date date){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");
		
		return sdf.format(date);
	}
	
	public static String getNowYear(){
		String where="";
		Calendar c1=Calendar.getInstance();
		Integer year=c1.get(Calendar.YEAR);
		Integer month=c1.get(Calendar.MONTH);
		month++;
		if(month>=4){
			String start=year.toString()+"0401";
			where=" and t1.create_date>='"+start+"' ";
			return where;
		}else{
			String end=year.toString()+"0401";
			year--;
			String start=year.toString()+"0401";
			where=" and t1.create_date>='"+start+"' and t1.create_date<'"+end+"' ";
			return where;
		}
		
	}
	
	public static String getTodayTime(){
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return sdf.format(date);
	}
	
	public static void main(String[] args){
		System.out.println(getSimToday1(-2));
	}

}
