package popteam.cinepic.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String loginId = (String)request.getSession().getAttribute("loginId");
		if(loginId != null)
			return true;
		response.sendRedirect("/cinepic/login");
		return false;
	}

}
