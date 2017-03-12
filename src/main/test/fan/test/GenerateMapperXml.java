package fan.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;


import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import fan.createxml.MapperXmlCreator;
import fan.createxml.User;

public class GenerateMapperXml {

	public static void main(String[] args) throws Exception {
		MapperXmlCreator c = new MapperXmlCreator();
		List<Class<?>> models = new LinkedList<Class<?>>();
		
		models.add(User.class);
		c.setModels(models);

		List<Document> docs = c.build();
		XMLWriter writer = null;
		//for (Document document : docs) {
		for(int i = 0; i<docs.size();i++ ){
			Document document = docs.get(i);
			
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
			//writer = new XMLWriter(System.out, format);
			//writer.write(document);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writer = new XMLWriter(baos, format);
			writer.write(document);
			
			String xml = baos.toString();
			xml = xml.replace("&gt;=", "<![CDATA[>=]]>");
			xml = xml.replace("&lt;=", "<![CDATA[<=]]>");
			xml = xml.replace("&gt;", "<![CDATA[>]]>");
			xml = xml.replace("&lt;", "<![CDATA[<]]>");
			File dir=new File("D:/autocode/sqlmap/");
			if(!dir.exists())
				dir.mkdirs();
			FileOutputStream fileOutputStream = new FileOutputStream("D:/autocode/sqlmap/"+models.get(i).getSimpleName()+".xml");
			byte[] buff=xml.getBytes();  
			fileOutputStream.write(buff,0,buff.length);
			fileOutputStream.close();
			System.out.println("生成至文件夹：D:/autocode/sqlmap/ 下 ");
			
		}
		writer.close();

	}

}
