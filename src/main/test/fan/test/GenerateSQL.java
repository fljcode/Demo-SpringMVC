package fan.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fan.createxml.DbModelAnalysiser;
import fan.createxml.MysqlDialect;
import fan.createxml.User;
import fan.createxml.DateUtil;

public class GenerateSQL {
	
	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder();
		DbModelAnalysiser analysiser = new DbModelAnalysiser(new MysqlDialect());
		
		Map<Class<?>,Integer> mp = new HashMap<Class<?>, Integer>();
		//这里是分表的sql, 如下128就指的是128个表，如果分表，这里mp和下面domains都应该有
		//mp.put(User.class, 128);
		mp.put(User.class, 3);
		
		List<Class<?>> domains = new LinkedList<Class<?>>();
		domains.add(User.class);
	
		String sql = null;
		for (Class<?> class1 : domains) {
			Integer n = mp.get(class1);
			if(n == null)
				sql = analysiser.analysis(class1);
			else
				sql = analysiser.analysis(class1,n);
			System.out.println(sql);
			sb.append(sql + "\n\n");
		}
		
		
		
		createFile(sb.toString());
	}
	
	private static void createFile(String str) {
		byte[] bytes = null;
		try {
			bytes = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File file = new File("d:\\securitiesservice"+DateUtil.format("yyyyMMddHHmmss",new Date())+".sql");
		try {
			FileOutputStream out = new FileOutputStream(file);
			out.write(bytes);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
