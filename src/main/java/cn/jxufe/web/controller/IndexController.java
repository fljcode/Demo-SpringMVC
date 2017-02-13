package cn.jxufe.web.controller;

import javax.servlet.http.HttpServletRequest;

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
		System.out.println("branch changed");
		ModelAndView r = new ModelAndView();
		r.setViewName("index");
		return r;
	}
	@RequestMapping("/convert")
	public String testRedict(HttpServletRequest request){
	
		System.out.println("------------进入重定向----------------");
		
		return "redirect:/index";
	}
	@RequestMapping("/convert2")
	public ModelAndView testRedict2(HttpServletRequest request){
	
		System.out.println("------------进入重定向----------------");
		ModelAndView r = new ModelAndView();
		r.setViewName("redirect:/index");
		return r;
	}
	
	
	
	
	
}