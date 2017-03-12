package fan.createxml;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
//hibernate包中的
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import fan.createxml.ColumnDefine;
import fan.createxml.DbModelAnalysiser;
import fan.createxml.ModelDefine;
import fan.createxml.User;

public class MapperXmlCreator {
	
	private final static String NODECOMMENTPATTERN = "\n    {0}\n    ";

	private List<Class<?>> models;

	public List<Class<?>> getModels() {
		return models;
	}

	public void setModels(List<Class<?>> models) {
		this.models = models;
	}

	public List<Document> build() {
		List<Document> r = new LinkedList<Document>();

		for (Class<?> clazz : models) {
			ModelDefine modelDef = DbModelAnalysiser.analysisModelDefine(clazz);
			Document document = DocumentHelper.createDocument();
			document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN",
					"http://mybatis.org/dtd/mybatis-3-mapper.dtd");
			document.addComment("\n模块说明：\n");
			Element root = document.addElement("mapper");
			root.addAttribute("namespace", modelDef.getClazz().getName() + "Mapper");

			addCommonColumns(document, modelDef);
			addDynamicFilter(document, modelDef, "mapCondition", true,"filters.");
			addDynamicFilter(document, modelDef, "modelCondition", false,"filters.");
			addInsert(document, modelDef);
			addBatchInsert(document, modelDef);
			addUpdate(document, modelDef);
			addFullUpdate(document, modelDef);
			addDelete(document, modelDef, "delete", modelDef.getClazz().getName());
			addDeleteByPrimaryKey(document, modelDef);
			addFind(document, modelDef, "find", "Map", "mapCondition");
			addFind(document, modelDef, "findByModel", "Map", "modelCondition");
			addFindCount(document, modelDef, "findCount", "Map", "mapCondition");
			addFindCount(document, modelDef, "findByModelCount", "Map", "modelCondition");
			addFindByPrimarykey(document, modelDef);

			r.add(document);
		}

		return r;
	}

	/**
	 * 添加公共查询全字段
	 * 
	 * @param doc
	 * @param modelDef
	 */
	private void addCommonColumns(Document doc, ModelDefine modelDef) {
		String tableName = "${tableName}";//modelDef.getTable().getName();
		Element root = doc.getRootElement();
		root.addComment(MessageFormat.format(NODECOMMENTPATTERN,"功能说明：通用全字段查询"));
		Element commonColumns = root.addElement("sql").addAttribute("id", "commonColumns");

		List<ColumnDefine> columns = modelDef.getColumns();
		StringBuilder columnSql = new StringBuilder();
		for (ColumnDefine columnDef : columns) {
			Column column = columnDef.getField().getAnnotation(Column.class);
			if (column != null && column.name().trim().length() > 0) {
				columnSql.append("\n"+tableName + "." + column.name() + " AS " + columnDef.getField().getName() + ",");
			} else {
				columnSql.append("\n"+tableName + "." + columnDef.getName() + ",");
			}
		}
		columnSql.deleteCharAt(columnSql.length() - 1);
		columnSql.append("\n");
		commonColumns.setText(columnSql.toString());
	}

	/**
	 * 添加通用动态条件
	 * 
	 * @param doc
	 * @param modelDef
	 */
	private void addDynamicFilter(Document doc, ModelDefine modelDef, String id, boolean addExt, String filterPrefix) {
		Element root = doc.getRootElement();
		List<ColumnDefine> columns = modelDef.getColumns();
		Element mapConditionDynamic = root.addElement("sql").addAttribute("id", id).addElement("where");
		for (ColumnDefine columnDef : columns) {
			addDynamicFieldFilter(mapConditionDynamic, columnDef, addExt, filterPrefix);

		}

	}

	/**
	 * 添加单字段动态条件
	 * 
	 * @param node
	 * @param columnDef
	 */
	private void addDynamicFieldFilter(Element node, ColumnDefine columnDef, boolean addExt, String filterPrefix) {
		Class<?> clazz = columnDef.getType();

		if (clazz == String.class) {
			addStringFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == Integer.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == Long.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == Short.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == Byte.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == Boolean.class) {
			addBooleanFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == Float.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == Double.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == BigDecimal.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == int.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == long.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == short.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == byte.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == boolean.class) {
			addBooleanFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == float.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == double.class) {
			addNumberFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == java.util.Date.class) {
			addDateFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == java.sql.Date.class) {
			addDateFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == java.sql.Time.class) {
			addTimeFilter(node, columnDef, addExt, filterPrefix);
			return;
		}

		if (clazz == java.sql.Timestamp.class) {
			addTimestampFilter(node, columnDef, addExt, filterPrefix);
			return;
		}
	}

	private void addStringFilter(Element node, ColumnDefine columnDef, boolean addExt, String filterPrefix) {

		Element filter = null;
		
		/** 添加等于操作条件 */
		/*filter = node.addElement("if").addAttribute("test",
				filterPrefix + columnDef.getField().getName() + " != null and " + filterPrefix + columnDef.getField().getName() + " !=''");
		filter.setText("AND ${tableName}." + columnDef.getName() + " = #{" + filterPrefix + columnDef.getField().getName() + "}");*/

		commonFilter(node, columnDef, addExt, filterPrefix);
		
		if (!addExt)
			return;

		/** 添加Like操作条件 */
		filter = node.addElement("if").addAttribute("test",
				filterPrefix + columnDef.getField().getName() + "Like != null and " + columnDef.getField().getName() + "Like !=''");
		filter.setText("AND ${tableName}." + columnDef.getName() + " like #{" + filterPrefix + columnDef.getField().getName() + "Like}");
		

	}

	private void addBooleanFilter(Element node, ColumnDefine columnDef, boolean addExt, String filterPrefix) {
		/** 添加等于操作条件 */
		Element filter = node.addElement("if").addAttribute("test", columnDef.getField().getName() + " != null");
		filter.setText("AND ${tableName}." + columnDef.getName() + " = #{" + filterPrefix + columnDef.getField().getName() + "}");
	}

	private void addNumberFilter(Element node, ColumnDefine columnDef, boolean addExt, String filterPrefix) {
		commonFilter(node, columnDef, addExt, filterPrefix);
	}

	private void addDateFilter(Element node, ColumnDefine columnDef, boolean addExt, String filterPrefix) {
		commonFilter(node, columnDef, addExt, filterPrefix);
	}

	private void addTimeFilter(Element node, ColumnDefine columnDef, boolean addExt, String filterPrefix) {
		commonFilter(node, columnDef, addExt, filterPrefix);
	}

	private void addTimestampFilter(Element node, ColumnDefine columnDef, boolean addExt, String filterPrefix) {
		commonFilter(node, columnDef, addExt, filterPrefix);
	}

	private void commonFilter(Element node, ColumnDefine columnDef, boolean addExt, String filterPrefix) {
		/** 添加等于操作条件 */
		Element filter = node.addElement("if").addAttribute("test", filterPrefix + columnDef.getField().getName() + " != null");
		filter.setText("AND ${tableName}." + columnDef.getName() + " = #{" + filterPrefix + columnDef.getField().getName() + "}");

		if (!addExt)
			return;

		/** 添加大于操作条件 */
		filter = node.addElement("if").addAttribute("test", filterPrefix + columnDef.getField().getName() + "Gt != null");
		filter.setText("AND ${tableName}." + columnDef.getName() + " > #{" + filterPrefix + columnDef.getField().getName() + "Gt}");

		/** 添加小于操作条件 */
		filter = node.addElement("if").addAttribute("test", filterPrefix + columnDef.getField().getName() + "Lt != null");
		filter.setText("AND ${tableName}." + columnDef.getName() + " < #{" + filterPrefix + columnDef.getField().getName() + "Lt}");

		/** 添加大于等于操作条件 */
		filter = node.addElement("if").addAttribute("test", filterPrefix + columnDef.getField().getName() + "Ge != null");
		filter.setText("AND ${tableName}." + columnDef.getName() + " >= #{" + filterPrefix + columnDef.getField().getName() + "Ge}");

		/** 添加小于等于操作条件 */
		filter = node.addElement("if").addAttribute("test", filterPrefix + columnDef.getField().getName() + "Le != null");
		filter.setText("AND ${tableName}." + columnDef.getName() + " <= #{" + filterPrefix + columnDef.getField().getName() + "Le}");
		

		/** 添加不等于操作条件 */
		filter = node.addElement("if").addAttribute("test", filterPrefix + columnDef.getField().getName() + "Ne != null");
		filter.setText("AND ${tableName}." + columnDef.getName() + " <> #{" + filterPrefix + columnDef.getField().getName() + "Ne}");
		
		/** 添加is null操作条件 */
		filter = node.addElement("if").addAttribute("test", filterPrefix + columnDef.getField().getName() + "IsNull != null");
		filter.setText("AND ${tableName}." + columnDef.getName() + " IS NULL");
		
		/** 添加is not null操作条件 */
		filter = node.addElement("if").addAttribute("test", filterPrefix + columnDef.getField().getName() + "IsNotNull != null");
		filter.setText("AND ${tableName}." + columnDef.getName() + " IS NOT NULL");
	}

	private void addFind(Document doc, ModelDefine modelDef, String findName, String parameterType,
			String conditionId) {
		Element root = doc.getRootElement();
		root.addComment(MessageFormat.format(NODECOMMENTPATTERN,"功能说明：数据查询"));
		Element find = root.addElement("select").addAttribute("id", findName)
				.addAttribute("parameterType", parameterType)
				.addAttribute("resultType", modelDef.getTable().getClazz().getName());
		find.addText("SELECT");
		find.addElement("include").addAttribute("refid", "commonColumns");
		find.addText("FROM ${tableName}"); //modelDef.getTable().getName()
		find.addElement("include").addAttribute("refid", conditionId);
		find.addElement("if").addAttribute("test", "orderByString != null and orderByString != ''").setText(" ORDER BY ${orderByString}");;
		find.addElement("if").addAttribute("test", "rowBounds != null").setText(" limit ${rowBounds.offset},${rowBounds.limit}");;
		
		// find.addElement("isNotEmpty").addAttribute("property",
		// "orderBy").addAttribute("prepend", " ORDER BY ");
	}
	

	private void addFindCount(Document doc, ModelDefine modelDef, String findName, String parameterType,
			String conditionId) {
		Element root = doc.getRootElement();
		root.addComment(MessageFormat.format(NODECOMMENTPATTERN,"功能说明：数据查询中有分页时查询总记录数"));
		Element find = root.addElement("select").addAttribute("id", findName)
				.addAttribute("parameterType", parameterType)
				.addAttribute("resultType", "Long");
		find.addText("SELECT COUNT(1) FROM ${tableName}"); //modelDef.getTable().getName()
		find.addElement("include").addAttribute("refid", conditionId);
	}
	
	
	private void addFindByPrimarykey(Document doc, ModelDefine modelDef){
		ColumnDefine primaryKeyCol = modelDef.getPrimaryKeyColumns().get(0);
		
		Element root = doc.getRootElement();
		root.addComment(MessageFormat.format(NODECOMMENTPATTERN,"功能说明：根据主键查询"));
		Element find = root.addElement("select").addAttribute("id", "findByPrimaryKey").addAttribute("parameterType", "Map") //primaryKeyCol.getType().getName()
				.addAttribute("resultType", modelDef.getTable().getClazz().getName());
		find.addText("SELECT");
		find.addElement("include").addAttribute("refid", "commonColumns");
		find.addText("FROM ${tableName}"); //modelDef.getTable().getName()
		find.addText( " WHERE " + primaryKeyCol.getName() + " = #{" + primaryKeyCol.getField().getName() + "}");
	}

	
	private void addInsert(Document doc, ModelDefine modelDef) {
		List<ColumnDefine> cols = modelDef.getColumns();
		ColumnDefine primaryKeyCol = modelDef.getPrimaryKeyColumns().get(0);

		Element root = doc.getRootElement();
		root.addComment(MessageFormat.format(NODECOMMENTPATTERN,"功能说明：插入数据，如果实体的属性值为空则不处理"));
		Element insert = root.addElement("insert").addAttribute("id", "insert").addAttribute("parameterType", "Map"); //modelDef.getTable().getClazz().getName()
		insert.addText("INSERT INTO ${tableName}");//modelDef.getTable().getName()
		
		Element colsEle = insert.addElement("trim");
		Element valsEle = insert.addElement("trim");

		colsEle.addAttribute("prefix", "(").addAttribute("suffix", ")").addAttribute("suffixOverrides", ",");
		valsEle.addAttribute("prefix", "VALUES (").addAttribute("suffix", ")").addAttribute("suffixOverrides", ",");

		GeneratedValue idway = primaryKeyCol.getField().getAnnotation(GeneratedValue.class);
		if (idway != null && idway.strategy() != GenerationType.IDENTITY) {
			colsEle.addElement("if").addAttribute("test", "model." + primaryKeyCol.getField().getName() + " != null")
					.setText(primaryKeyCol.getName()+",");
			valsEle.addElement("if").addAttribute("test", "model." + primaryKeyCol.getField().getName() + " != null")
					.setText("#{" + primaryKeyCol.getField().getName() + "},");
		}

		
		for (int i = 0; i < cols.size(); i++) {
			ColumnDefine colDef = cols.get(i);
			if (colDef == primaryKeyCol)
				continue;
			String spliteChar = ",";
			if(i == cols.size() - 1)
				spliteChar = "";

			colsEle.addElement("if").addAttribute("test", "model." + colDef.getField().getName() + " != null")
					.setText("${tableName}."+colDef.getName() + spliteChar);
			valsEle.addElement("if").addAttribute("test", "model." + colDef.getField().getName() + " != null")
					.setText("#{model." + colDef.getField().getName() + "}" + spliteChar);
		}

	}
	
	
	private void addBatchInsert(Document doc, ModelDefine modelDef) {
		
		List<ColumnDefine> cols = modelDef.getColumns();
		ColumnDefine primaryKeyCol = modelDef.getPrimaryKeyColumns().get(0);
		Element root = doc.getRootElement();
		root.addComment(MessageFormat.format(NODECOMMENTPATTERN,"功能说明：插入数据，如果实体的属性值为空则不处理"));
		Element insert = root.addElement("insert").addAttribute("id", "batchInsert");//.addAttribute("parameterType", modelDef.getTable().getClazz().getName());
		insert.addText("INSERT INTO ${tableName}");//modelDef.getTable().getName()
		
		Element colsEle = insert.addElement("trim");
		Element valsEle = insert.addElement("foreach").addAttribute("open", "VALUES");

		colsEle.addAttribute("prefix", "(").addAttribute("suffix", ")").addAttribute("suffixOverrides", ",");
		valsEle.addAttribute("collection", "models").addAttribute("item", "model").addAttribute("separator", ",");
		valsEle.addText("(");
		
		GeneratedValue idway = primaryKeyCol.getField().getAnnotation(GeneratedValue.class);
		if (idway != null && idway.strategy() != GenerationType.IDENTITY) {
			colsEle.addText(primaryKeyCol.getName()+",");
			valsEle.addText("#{model." + primaryKeyCol.getField().getName() + "},");
		}

		
		for (int i = 0; i < cols.size(); i++) {
			ColumnDefine colDef = cols.get(i);
			if (colDef == primaryKeyCol)
				continue;
			String spliteChar = ",";
			if(i == cols.size() - 1)
				spliteChar = "";

			colsEle.addText("${tableName}." + colDef.getName() + spliteChar);
			valsEle.addText("#{model." + colDef.getField().getName() + "}" + spliteChar);
		}
		valsEle.addText(")");
	}
	
	
	public void addUpdate(Document doc, ModelDefine modelDef){
		
		List<ColumnDefine> cols = modelDef.getColumns();
		ColumnDefine primaryKeyCol = null;
		for (ColumnDefine colDef : cols) {
			if (colDef.isPrimaryKey()) {
				primaryKeyCol = colDef;
				break;
			}
		}
		
		Element root = doc.getRootElement();
		root.addComment(MessageFormat.format(NODECOMMENTPATTERN,"功能说明：更新数据，如果实体的属性值为空则不处理"));
		Element update = root.addElement("update").addAttribute("id", "update").addAttribute("parameterType", "Map");
		//update.addAttribute("resultType", "Integer");
		update.addText("UPDATE ${tableName}"); //modelDef.getTable().getName()
		Element setEle = update.addElement("set");
		
		for (int i = 0; i < cols.size(); i++) {
			ColumnDefine colDef = cols.get(i);
			if (colDef == primaryKeyCol)
				continue;
			String spliteChar = ",";
			if(i == cols.size() - 1)
				spliteChar = "";

			setEle.addElement("if").addAttribute("test", "model."+colDef.getField().getName() + " != null")
					.setText("${tableName}."+colDef.getName() + " = #{model." + colDef.getField().getName() + "}" + spliteChar);
		}
		
		update.addText("WHERE " + primaryKeyCol.getName() + " = #{model." + primaryKeyCol.getField().getName() + "}");
	}
	
	public void addFullUpdate(Document doc, ModelDefine modelDef){
		
		List<ColumnDefine> cols = modelDef.getColumns();
		ColumnDefine primaryKeyCol = null;
		for (ColumnDefine colDef : cols) {
			if (colDef.isPrimaryKey()) {
				primaryKeyCol = colDef;
				break;
			}
		}
		
		Element root = doc.getRootElement();
		root.addComment(MessageFormat.format(NODECOMMENTPATTERN,"功能说明：插入数据，对属性值是否为空不敏感"));
		Element fullUpdate = root.addElement("update").addAttribute("id", "fullUpdate").addAttribute("parameterType", "Map");
		//fullUpdate.addAttribute("resultType", "Integer");
		fullUpdate.addText("UPDATE ${tableName} SET "); //modelDef.getTable().getName()
		
		for (int i = 0; i < cols.size(); i++) {
			ColumnDefine colDef = cols.get(i);
			if (colDef == primaryKeyCol)
				continue;
			String spliteChar = ",";
			if(i == cols.size() - 1)
				spliteChar = "";
			
			fullUpdate.addText("${tableName}."+colDef.getName() + " = #{model." + colDef.getField().getName() + "}" + spliteChar);
		}
		
		fullUpdate.addText(" WHERE " + primaryKeyCol.getName() + " = #{model." + primaryKeyCol.getField().getName() + "}");
	}
	
	public void addDelete(Document doc, ModelDefine modelDef,String id, String parameterType){
		
		ColumnDefine primaryKeyCol = modelDef.getPrimaryKeyColumns().get(0);
		
		Element root = doc.getRootElement();
		root.addComment(MessageFormat.format(NODECOMMENTPATTERN,"功能说明：删除数据"));
		Element delete = root.addElement("delete").addAttribute("id", id);
		if(parameterType != null)
			delete.addAttribute("parameterType", parameterType);
		
		//delete.addAttribute("resultType", "Integer");
		
		delete.addText("DELETE FROM ${tableName}"); //modelDef.getTable().getName()
		//delete.addText( " WHERE " + primaryKeyCol.getName() + " = #{" + primaryKeyCol.getField().getName() + "}");
		//delete.addElement("include").addAttribute("refid", "mapCondition");
		delete.addElement("include").addAttribute("refid", "modelCondition");
		
		
	}
	
	
	
	public void addDeleteByPrimaryKey(Document doc, ModelDefine modelDef){
		ColumnDefine primaryKeyCol = modelDef.getPrimaryKeyColumns().get(0);
		Element root = doc.getRootElement();
		root.addComment(MessageFormat.format(NODECOMMENTPATTERN,"功能说明：根据主键删除数据"));
		Element delete = root.addElement("delete").addAttribute("id", "deleteByPrimaryKey");
		delete.addText("DELETE FROM ${tableName}"); //modelDef.getTable().getName()
		delete.addText( " WHERE " + primaryKeyCol.getName() + " = #{" + primaryKeyCol.getField().getName() + "}");
	}
	
	

	public static void main(String[] args) throws Exception {

		MapperXmlCreator c = new MapperXmlCreator();
		List<Class<?>> models = new LinkedList<Class<?>>();
		models.add(User.class);
		//models.add(StockRealTime.class);
		c.setModels(models);

		List<Document> docs = c.build();
		XMLWriter writer = null;
		for (Document document : docs) {
			
			/** 格式化输出,类型IE浏览一样 */
			OutputFormat format = OutputFormat.createPrettyPrint();
			//OutputFormat format = new OutputFormat(); 
			//设置元素是否有子节点都输出  
	        format.setExpandEmptyElements(true);  
	        //设置不输出XML声明  
	        format.setSuppressDeclaration(true);
	        format.setTrimText(true);
	        format.setIndent(true);
	        format.setIndentSize(4);
	        format.setNewLineAfterNTags(4);
	        format.setNewLineAfterDeclaration(true);
//	       / format.setNewlines(true);
	        
	        
			/** 指定XML编码 */
			format.setEncoding("UTF-8");
			writer = new XMLWriter(System.out, format);
			writer.write(document);
			
		}
		writer.close();
	}
}
