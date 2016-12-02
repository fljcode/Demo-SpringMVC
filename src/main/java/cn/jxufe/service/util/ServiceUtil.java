package cn.jxufe.service.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service("serviceUtil")
public class ServiceUtil {
	//@Value("${ling}")
	private String ling;
	//注入不成功
	/*@Value("${jdbc.username}")
	private String username;*/

	
	
	public  String outName(){
	
		System.out.println("--------------------" + ling);

		return "index";
	}
	
}