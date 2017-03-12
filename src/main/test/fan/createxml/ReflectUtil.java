package fan.createxml;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class ReflectUtil {

	
	public static List<Field> getField(Class<?> clazz){
		
		List<Field> r = new LinkedList<Field>();
		
		Field[] fields = null;
		
		List<Class<?>> superClasses = getSuperClass(clazz);
		for (Class<?> class1 : superClasses) {
			fields = class1.getDeclaredFields();
			for (Field field : fields) {
				r.add(field);
			}
		}
		
		fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			r.add(field);
		}
		

		return r;
	}
	
	
	public static List<Class<?>> getSuperClass(Class<?> clazz){
		
		List<Class<?>> r = new LinkedList<Class<?>>();
		Class<?> superClass = clazz.getSuperclass();
		
		while(superClass != Object.class){
			r.add(0,superClass);
			superClass = superClass.getSuperclass();
		}
		
		return r;
	}
	
}
