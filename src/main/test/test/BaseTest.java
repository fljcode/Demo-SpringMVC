package test;
import junit.framework.TestCase;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration (locations = {"classpath:applicationContext.xml" })
@RunWith (SpringJUnit4ClassRunner.class )
public class BaseTest extends TestCase {
	
}
