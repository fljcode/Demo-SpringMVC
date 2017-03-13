package fan.createxml;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import fan.createxml.ColumnDefine;
import fan.createxml.ModelDefine;
import fan.createxml.DialectUtil;

public class AbStractDialect {

	
	protected static String getColumnLength(ColumnDefine columnDef){
		
		Class<?> clazz = columnDef.getType();

		if(clazz == String.class){
			return lengthForString(columnDef);
		}
		
		
		if(Number.class.isAssignableFrom(clazz)){
			return lengthForNumber(columnDef);
		}
		
		return "";
	}
	
	
	private static String lengthForString(ColumnDefine columnDef){
		int length = columnDef.getLength();
		if(length == 0)
			length = DialectUtil.getDefaultLength(String.class);
		return "(" + length + ")";
	}
	
	
	private static String lengthForNumber(ColumnDefine columnDef){
		Class<?> clazz = columnDef.getType();
		int precision = columnDef.getPrecision();
		int scale = 0;
		if(precision == 0)
			precision = DialectUtil.getDefaultLength(clazz);
		
		if(precision == 0)
			return "";
		
		if(clazz == Double.class || clazz == double.class || clazz == Float.class || clazz == float.class || clazz == BigDecimal.class){
			scale = columnDef.getScale();
			if(scale == 0)
				scale = 4;
		}
		
		if(scale == 0){
			return "(" + precision + ")";
		}else{
			return "(" + precision + "," + scale + ")";
		}
	}
	
	
	protected static String getType(ColumnDefine columnDef){
		
		return null;
	}
	
	
	public static List<ColumnDefine> findPrimaryKeyColumns(ModelDefine modelDef) {
		List<ColumnDefine> columns = modelDef.getColumns();

		List<ColumnDefine> primaryKeys = new LinkedList<ColumnDefine>();

		for (ColumnDefine columnDef : columns) {
			if (columnDef.isPrimaryKey()) {
				primaryKeys.add(columnDef);
			}
		}

		return primaryKeys;
	}
}
