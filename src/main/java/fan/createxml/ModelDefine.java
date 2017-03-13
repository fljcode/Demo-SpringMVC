package fan.createxml;

import java.util.LinkedList;
import java.util.List;

public class ModelDefine {
	
	private Class<?> clazz;
	//同一个包中的，没import
	private TableDefine table;
	
	private List<ColumnDefine> columns;
	
	
	public ModelDefine(Class<?> clazz, TableDefine table, List<ColumnDefine> columns) {
		super();
		this.clazz = clazz;
		this.table = table;
		this.columns = columns;
	}
	
	

	public Class<?> getClazz() {
		return clazz;
	}


	public TableDefine getTable() {
		return table;
	}

	public List<ColumnDefine> getColumns() {
		return columns;
	}
	
	public List<ColumnDefine> getPrimaryKeyColumns() {
		List<ColumnDefine> columns = this.getColumns();

		List<ColumnDefine> primaryKeys = new LinkedList<ColumnDefine>();

		for (ColumnDefine columnDef : columns) {
			if (columnDef.isPrimaryKey()) {
				primaryKeys.add(columnDef);
			}
		}

		return primaryKeys;
	}

}
