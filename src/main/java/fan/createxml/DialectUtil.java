package fan.createxml;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DialectUtil {

	private final static Map<Class<?>,Integer> MAP_TYPE_LENGTH = new ConcurrentHashMap<Class<?>,Integer>();
	private final static Map<Class<?>,String> MAP_TYPE = new ConcurrentHashMap<Class<?>,String>();
	
	
	static{
		MAP_TYPE_LENGTH.put(String.class, 255);
		MAP_TYPE_LENGTH.put(Long.class, 20);
		MAP_TYPE_LENGTH.put(Float.class, 10);
		MAP_TYPE_LENGTH.put(Double.class, 15);
		MAP_TYPE_LENGTH.put(BigDecimal.class, 19);
		
		
		MAP_TYPE.put(String.class, "VARCHAR");
		MAP_TYPE.put(Integer.class, "INT");
		MAP_TYPE.put(Long.class, "BIGINT");
		MAP_TYPE.put(Short.class, "SMALLINT");
		MAP_TYPE.put(Byte.class, "TINYINT");
		MAP_TYPE.put(Boolean.class, "BIT");
		MAP_TYPE.put(Float.class, "FLOAT");
		MAP_TYPE.put(Double.class, "DECIMAL");
		MAP_TYPE.put(BigDecimal.class, "DECIMAL");
		MAP_TYPE.put(java.util.Date.class, "DATETIME");
		MAP_TYPE.put(java.sql.Date.class, "DATE");
		MAP_TYPE.put(java.sql.Time.class, "TIME");
		MAP_TYPE.put(java.sql.Timestamp.class, "TIMESTAMP");
		
		MAP_TYPE.put(int.class, "INT");
		MAP_TYPE.put(long.class, "BIGINT");
		MAP_TYPE.put(short.class, "SMALLINT");
		MAP_TYPE.put(byte.class, "TINYINT");
		MAP_TYPE.put(boolean.class, "BIT");
		MAP_TYPE.put(float.class, "FLOAT");
		MAP_TYPE.put(double.class, "DOUBLE");
		
		
		
	}
	
	
	public static int getDefaultLength(Class<?> clazz){
		Integer length = MAP_TYPE_LENGTH.get(clazz);
		if(length != null)
			return length;
		
		return 0;
	}
	
	
	public static String getType(Class<?> clazz){
		
		return MAP_TYPE.get(clazz);
	}
}
