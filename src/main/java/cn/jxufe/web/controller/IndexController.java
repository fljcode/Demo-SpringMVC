package cn.jxufe.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jxufe.service.util.ServiceUtil;

@Controller
@RequestMapping
public class IndexController {
	//@Value("${chinese}")
	private String chinese;
	//@Value("${jdbc.username}")
	private String username;
	@Value("${jie}")
	private String fan;
	
	@Autowired
	private ServiceUtil serviceUtil;
	
	
	//http://localhost:8080/SpringMVC-Maven/index.htm
	@RequestMapping("/index")
	public String list(HttpServletRequest request){
		System.out.println("--------------------" + chinese);
		System.out.println("-------************-------------" + username);
		System.out.println("----------*****************----------" + fan);
		System.out.println("---------" + serviceUtil.outName());
		return "index";
	}
	
}