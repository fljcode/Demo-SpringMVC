package fan.createxml;

import java.lang.reflect.Field;

public class ColumnDefine {
	
	
	private Field field;
	
	private Class<?> type;

	private String name;
	
	private int length = 0;
	
	private String columnDefinition;
	
	private boolean nullable = true;
	
	private int precision = 0;
	
	private int scale = 0;
	
	private boolean primaryKey = false;
	

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColumnDefinition() {
		return columnDefinition;
	}

	public void setColumnDefinition(String columnDefinition) {
		this.columnDefinition = columnDefinition;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	
	
	
}
