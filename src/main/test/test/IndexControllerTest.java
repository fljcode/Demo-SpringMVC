package test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import cn.jxufe.web.controller.IndexController;
import junit.framework.TestCase;
//引入静态方法standaloneSetup
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ContextConfiguration (locations = {"classpath:applicationContext.xml" })
@RunWith (SpringJUnit4ClassRunner.class )
public class IndexControllerTest extends TestCase {
	
	@Test
	public void testIndexController() throws Exception{
		IndexController index = new IndexController();
		//MockMvc mockMvc = standaloneSetup(index).build();
		
		MockMvc mockMvc = standaloneSetup(index).setSingleView(new InternalResourceView("/WEB-INF/pages/index.jsp")).build();
		
		mockMvc.perform(get("/index")).andExpect(view().name("home"));
	}
}
