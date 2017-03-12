package test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jxufe.service.util.ServiceUtil;

public class testTest extends BaseTest {
	@Autowired
	private ServiceUtil serviceUtil;
	
	@Test
	public void test1(){
		System.out.println(serviceUtil.getClass());
	}
}
