package com.schenker.zeus.interceptor;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.schenker.zeus.annotation.DisplayAfterValidation;
import com.schenker.zeus.model.User;

public class DoDisplayInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception{
		boolean flag = false;
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		DisplayAfterValidation dis = method.getAnnotation(DisplayAfterValidation.class);
		if(dis!=null){
			if(request.getSession().getAttribute("userInSession") instanceof User){
				User user = (User)request.getSession().getAttribute("userInSession");
				System.out.println("Check User Previllege for user -->"+user.getUserId());
				flag = true;
			}else{
				throw new Exception("User type Invalid");
			}
		}
		return flag;
	}
}
