package cn.jxufe.web.common.authority.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>> 进入拦截器 <<<<<<<<<<<<<<<<<<<<<<<");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("------------进入login------------------: username: " + username + "   password: " + password);
		//这里是试验用拦截器，做token
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        token.setRememberMe(true);
        currentUser.login(token);
        return true;
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();

	}
}
