package com.gdiot.ssm.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;


/**
 * @author ZhouHR
 */
@Slf4j
public class PropertiesUtil {
	
	private static final PropertiesUtil PROPERTIES_UTIL=new PropertiesUtil();
	
	private static final Properties properties=null;
	
	private static synchronized Properties  getProperties(){
		if(properties!=null) return properties;
		PropertiesUtil util=PropertiesUtil.getInstance();
		Properties properties=util.loadProperties("application.properties");
		return properties;
	}
	
	private PropertiesUtil(){
		
	}
	
	public static String getValue(String key){
		Properties properties=getProperties();
		return properties.getProperty(key);
		
	}
	
	/**
	 * 加载配置文件
	 * 
	 * @param separator
	 *            系统路径
	 * @return 返回Properties对象
	 * @throws FileNotFoundException
	 *             抛出异常
	 * @throws IOException
	 *             抛出异常
	 */
	public  Properties loadProperties(String fileName )
			{
		//得到bin路径
		Properties props=null;
		FileInputStream fis=null;
		try {
			props = new Properties();
		    props.load(this.getClass().getClassLoader().getResourceAsStream(fileName));
		} catch (Exception e) {
			log.error("加载Properties配置文件失败："+e.getMessage());
		}finally{
			if(null!=fis){
				try {
					fis.close();
				} catch (IOException e) {
					log.error("加载Properties配置文件关闭流失败："+e.getMessage());
				}
			}
		}
		return props;
	}
	
	/**
	 * 用key获取Properties配置文件么个属性的值
	 * @param fileName 要加载的Properties配置文件名
	 * @param key 键
	 * @return 返回获取结果
	 */
	public Object getProperty(String fileName,String key){
		 Properties loadProperties=loadProperties(fileName);
		 return loadProperties.getProperty(key);
		 
	}
	
	/**
	 * 用key给Properties配置文件么个属性赋值
	 * @param fileName 要加载的Properties配置文件名
	 * @param key 键
	 * @param value 值
	 * @return 返回获取结果
	 */
	public void setProperty(String fileName,String key,String value){
		 Properties loadProperties=loadProperties(fileName);
		 loadProperties.setProperty(key, value);
	}
	
	public static PropertiesUtil getInstance(){
		return PROPERTIES_UTIL;
	}
	
	public static void main(String[] args) {
		String str=PropertiesUtil.getValue("erp.url");
		System.out.println(str);
	}

}
