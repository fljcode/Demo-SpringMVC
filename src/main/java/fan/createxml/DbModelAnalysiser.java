package fan.createxml;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import fan.createxml.Dialect;
import fan.createxml.ReflectUtil;

public class DbModelAnalysiser {
	
	
	private Dialect dialect;
	

	public DbModelAnalysiser(Dialect dialect) {
		super();
		this.dialect = dialect;
	}


	private static TableDefine analysisTable(Class<?> clazz){
		Entity table = clazz.getAnnotation(Entity.class);
		if(table != null){
			TableDefine r = new TableDefine();
			r.setName(table.name());
			r.setClazz(clazz);
			return r;
		}
		return null;
	}
	
	
	private static List<ColumnDefine> analysisColumn(Class<?> clazz){
		List<ColumnDefine> r = new LinkedList<ColumnDefine>();
		List<Field> fields = ReflectUtil.getField(clazz);
		for (Field field : fields) {
			Transient t = field.getAnnotation(Transient.class);
			if(t != null)
				continue;
			
			
			Column column = field.getAnnotation(Column.class);
			ColumnDefine define = new ColumnDefine();
			define.setField(field);
			define.setType(field.getType());
			if(column != null){
				define.setColumnDefinition(column.columnDefinition());
				define.setName(column.name().trim().length() > 0 ? column.name() : field.getName());
				define.setNullable(column.nullable());
				define.setPrecision(column.precision());
				define.setScale(column.scale());
				define.setLength(column.length());
			}else{
				define.setName(field.getName());
			}
			
			Id primaryKey = field.getAnnotation(Id.class);
			if(primaryKey != null)
				define.setPrimaryKey(true);
			
			r.add(define);
		}
		return r;
	}
	
	
	public static ModelDefine analysisModelDefine(Class<?> clazz){
		TableDefine tableDef = analysisTable(clazz);
		List<ColumnDefine> colsDef = analysisColumn(clazz);
		ModelDefine modelDef = new ModelDefine(clazz, tableDef,colsDef);
		
		return modelDef;
	}
	
	
	public String analysis(Class<?> clazz){
		
		ModelDefine modelDef = analysisModelDefine(clazz);
		return dialect.buildSql(modelDef);
	}
	
	public String analysis(Class<?> clazz, int num){
		ModelDefine modelDef = analysisModelDefine(clazz);
		return dialect.buildSql(modelDef,num);
		
	}
}
