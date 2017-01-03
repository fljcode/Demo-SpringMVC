package cn.jxufe.service.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service("serviceUtil")
public class ServiceUtil {
	@Value("${ling2}")
	private String ling;
	//注入不成功
	@Value("${jdbc.username2}")
	private String username;
	@Value("#{props['fan2']}")
	private String fa;
	
	
	public  String outName(){
	
		System.out.println("--------------------" + ling);
	
		return "index";
	}



	public String getLing() {
		return ling;
	}



	public void setLing(String ling) {
		this.ling = ling;
	}
	
}