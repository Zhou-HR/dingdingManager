package com.gdiot.ssm.session;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.gdiot.ssm.entity.User;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class UserAuthInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	SessionUtil sessionUtil;
	
	public UserAuthInterceptor(){
		System.out.println("sdffdsfsdf");
	}

	/**
	 * 在业务处理器处理请求之前对该请求进行拦截处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if(handler instanceof HandlerMethod) {
		
	//		if(handler instanceof DefaultServletHttpRequestHandler)
	//			return true;
	//		
	//		if(handler instanceof ResourceHttpRequestHandler)
	//			return true;
			
			//获取项目名
			String projectName=request.getContextPath();
			//请求带的参数
			String queryString=request.getQueryString();
			
			String access_token=request.getParameter("access_token");
			//请求的当前资源
			//bancinsurance
			String requestUri=request.getRequestURI().replaceAll(projectName,"");
			User user=null;
			
			Method method = ((HandlerMethod) handler).getMethod();
			boolean bFail=false;
			if(method.isAnnotationPresent(RequestProcess.class)){
				user=sessionUtil.getSessionUser(access_token);
				if(user==null) {
					sendJsonMessage(response);
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void sendJsonMessage(HttpServletResponse response) throws Exception { 
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer = response.getWriter(); 
		writer.print("{\"code\": \"-1\",\"msg\": \"invalid access_token!\"}"); 
		writer.close(); 
		response.flushBuffer(); 
	}
	
}
