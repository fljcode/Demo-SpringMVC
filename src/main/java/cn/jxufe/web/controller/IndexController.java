package cn.jxufe.web.controller;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.jxufe.service.util.ServiceUtil;

@Controller
@RequestMapping
public class IndexController {
	//@Value("${chinese}")
	private String chinese;
	@Value("${jdbc.username2}")
	private String username;
	@Value("#{props['fan2']?:'nullfan2'}")
	private String fan;
	
	@Autowired
	private ServiceUtil serviceUtil;

	//spring-mvc.xml中viewResolver配置了视图的前后缀，如前缀/WEB-INF/jsp/，给解析成：/SpringMVC-Maven/WEB-INF/jsp/index.jsp就与target中对应上了；
	//http://localhost:8080/SpringMVC-Maven/index.htm
	@RequestMapping("/index")
	public ModelAndView list(HttpServletRequest request){
		//serviceUtil.setLing("fanlingjie");
	/*	
		System.out.println("------------------" + serviceUtil.getLing());
		System.out.println("--------------------" + chinese);
		System.out.println("-------************-------------" + username);
		System.out.println("----------*****************----------" + fan);
		System.out.println("---------" + serviceUtil.outName());*/
		System.out.println("kkkkkkkkkkkkkkkk" + "realtime".equalsIgnoreCase(null));
		System.out.println("branch changed2 ");
		System.out.println("branch changed2 ");
		ModelAndView r = new ModelAndView();
		r.setViewName("index");
		return r;
	}
	@RequestMapping("/convert")
	public String testRedict(HttpServletRequest request){
	
		System.out.println("------------进入重定向----------------");
		
		return "redirect:/index";
	}
	//@RequiresPermissions(value = {"fan:first", "fan:second"})
	@RequestMapping("/convert2")
	public ModelAndView testRedict2(HttpServletRequest request){
	
		System.out.println("------------进入需要权限验证的地方------------------");
		System.out.println("------------接下来重定向到index------------------");
		ModelAndView r = new ModelAndView();
		r.setViewName("redirect:/index");
		return r;
	}
	//http://localhost:8080/SpringMVC-Maven/login.htm?username=fan&password=456
	/**
	 * 不用注解用代码验证是否有某个权限
	 * 注意拦截器中token注释掉
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView testLogin(HttpServletRequest request){
		/*正常这些放到拦截器中，在这里checkPermission
		 * String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("------------进入login------------------: username: " + username + "   password: " + password);
		//根据获取的用户名和密码封装成Token
		//UsernamePasswordToken token =new UsernamePasswordToken(username,password);*/
		
		 Subject subject = SecurityUtils.getSubject(); 
		 /*
	     UsernamePasswordToken token = new UsernamePasswordToken(username, password); 
	     token.setRememberMe(true);
	     */
	     String error = null;
        try { 
        	//调用MyRealm中doGetAuthorizationInfo寻找权限
        	  subject.checkPermission("fan:second");
        	//经验证，第一次登陆此函数调用MyRealm中doGetAuthenticationInfo，验证后以后不再调用MyRealm中doGetAuthenticationInfo函数，直接返回上次结果
           // subject.login(token);  
            System.out.println("--------------------验证成功 ");
        } catch (UnknownAccountException e) {  
            error = "用户名/密码错误";  
        } catch (IncorrectCredentialsException e) {  
            error = "用户名/密码错误";  
        } catch (Throwable e) {  
            //其他错误，比如锁定，如果想单独处理请单独catch处理  
            error = "其他错误：" + e.getMessage();  
        }  
        System.out.println("--------------------login info " + error);
		//是否记住用户
		
		ModelAndView r = new ModelAndView();
		r.setViewName("index");
		return r;
	}
	
	
	
	
}