package com.bigbigrain.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	//执行时机:controller已经执行,modelAndview已经返回
	//使用场景: 记录操作日志,记录登录用户的ip,时间等.
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("======LoginInterceptor=======afterCompletion========");

	}

	//执行时机:Controller方法已经执行,ModelAndView没有返回
	//使用场景: 可以在此方法中设置全局的数据处理业务
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("======LoginInterceptor=======postHandle========");

	}

	//返回布尔值:如果返回true放行,返回false则被拦截住
	//执行时机:controller方法没有被执行,ModelAndView没有被返回
	//使用场景: 权限验证
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//判断当前访问路径是否为登录的路径,如果是则放行
		if(request.getRequestURI().indexOf("/login") > 0){
			return true;
		}
		
		//判断session中是否有登录信息,如果没有则跳转到登录页面,如果有则放行
		HttpSession session = request.getSession();
		if(session.getAttribute("username") != null){
			return true;
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

}
