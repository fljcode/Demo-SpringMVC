package fan.createxml;

import java.text.MessageFormat;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;

import fan.createxml.AnalysisException;
import fan.createxml.ColumnTypeException;
import fan.createxml.ColumnDefine;
import fan.createxml.ModelDefine;
import fan.createxml.Comment;
import fan.createxml.DialectUtil;

public class MysqlDialect extends AbStractDialect implements Dialect {

	//private final static String CREATE_TABLE_PATTERN = "DROP TABLE IF EXISTS `{0}`;\r\n" + "CREATE TABLE `{0}` ";
	private final static String CREATE_TABLE_PATTERN = "CREATE TABLE `{0}` ";

	public String buildSql(ModelDefine modelDefine) {
		StringBuilder sql = new StringBuilder();
		sql.append(MessageFormat.format(CREATE_TABLE_PATTERN, modelDefine.getTable().getName()));
		sql.append(this.buildColumn(modelDefine));
		sql.append(this.buildTableProperty(modelDefine));
		sql.append(";");
		return sql.toString();
	}
	
	public String buildSql(ModelDefine modelDefine, int num) {
		StringBuilder sql = new StringBuilder();
		String BLANK_SUFFIX = "000";
		for (int i = 0;i < num ; i++) {
			String sIndex = String.valueOf(i);
			String suffix = BLANK_SUFFIX.substring(sIndex.length()) + sIndex;
			sql.append("\n");
			sql.append(MessageFormat.format(CREATE_TABLE_PATTERN, modelDefine.getTable().getName()+"_" +suffix));
			sql.append(this.buildColumn(modelDefine));
			sql.append(this.buildTableProperty(modelDefine));
			sql.append(";");
			sql.append("\n");
		}
		return sql.toString();
	}

	public String buildColumn(ModelDefine modelDef) {
		List<ColumnDefine> columns = modelDef.getColumns();
		if (columns == null || columns.size() < 0)
			return "";

		StringBuilder sql = new StringBuilder();
		sql.append("(\n");

		for (ColumnDefine columnDefine : columns) {
			sql.append(this.getTypeDefine(columnDefine));
			
			GeneratedValue idway = columnDefine.getField().getAnnotation(GeneratedValue.class);
			if(idway != null && idway.strategy() == GenerationType.IDENTITY){
				sql.append(" AUTO_INCREMENT");
			}
			sql.append(",\n");
		}
		sql.append(this.buildPrimaryKey(modelDef));
		sql.append("\n)");

		return sql.toString();
	}

	
	
	private String getTypeDefine(ColumnDefine columnDef) {
		StringBuilder r = new StringBuilder();
		
		if(columnDef.getColumnDefinition() == null || columnDef.getColumnDefinition().trim().length() < 1){
			r.append(this.getAnalysisTypeDefine(columnDef));
		}else{
			r.append("    " + columnDef.getColumnDefinition());
		}
		
		Comment comment = columnDef.getField().getAnnotation(Comment.class);
		if(comment != null){
			String commentString = comment.value().trim();
			if(commentString.length() > 0)
				r.append(" COMMENT '" + commentString + "'");
		}

		return r.toString();
	}
	
	private String getAnalysisTypeDefine(ColumnDefine columnDef){
		StringBuilder r = new StringBuilder();
		r.append("    `" + columnDef.getName() + "`");

		Class<?> clazz = columnDef.getType();
		String type = DialectUtil.getType(clazz);

		if (type == null)
			throw new ColumnTypeException("解析字段类型失败，" + clazz + "不能找到对应的字段类型");
		
		Temporal temporal = columnDef.getField().getAnnotation(Temporal.class);
		if(temporal == null)
			r.append(" " + type);
		else
			r.append(" " + temporal.value().name());

		String length = getColumnLength(columnDef);
		if (length != null)
			r.append(length);
		
		
		if(!columnDef.isNullable())
			r.append(" NOT NULL");
		
		return r.toString();
	}

	
	
	private String buildPrimaryKey(ModelDefine modelDef) {

		List<ColumnDefine> primaryKeys = findPrimaryKeyColumns(modelDef);

		if(primaryKeys.size() < 1)
			throw new AnalysisException(modelDef.getClazz() + "没有主键");
		
		ColumnDefine primaryKey = primaryKeys.get(0);
		StringBuilder sql = new StringBuilder();
		sql.append("    PRIMARY KEY (`" + primaryKey.getName() + "`)");
		
		
		return sql.toString();

	}
	

	
	private String buildTableProperty(ModelDefine modelDef) {

		List<ColumnDefine> primaryKeys = findPrimaryKeyColumns(modelDef);
		ColumnDefine primaryKey = primaryKeys.get(0);
		StringBuilder sql = new StringBuilder();
		sql.append(" ENGINE=InnoDB");
		
		GeneratedValue idway = primaryKey.getField().getAnnotation(GeneratedValue.class);
		if(idway != null && idway.strategy() == GenerationType.IDENTITY){
			sql.append(" AUTO_INCREMENT=1");
		}
		
		//sql.append(" DEFAULT CHARSET=utf8");
		
		Comment comment = modelDef.getClazz().getAnnotation(Comment.class);
		if(comment != null){
			String commentString = comment.value().trim();
			if(commentString.length() > 0)
				sql.append(" COMMENT '" + commentString + "'");
		}
		
		return sql.toString();

	}
	

}
