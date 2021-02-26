package com.wmc.novel.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!hasAuth(request)) {
			request.getRequestDispatcher("/login").forward(request, response);
			return false;
		}
		return true;
	}


	/**
	 * 权限验证
	 * @param request
	 * @return
	 */
	private boolean hasAuth(HttpServletRequest request) {
		Object admin = request.getSession().getAttribute("admin");
		return admin != null;
	}
}
