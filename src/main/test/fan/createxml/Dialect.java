package fan.createxml;

import fan.createxml.ModelDefine;

public interface Dialect {

	public String buildSql(ModelDefine modelDefine);
	
	public String buildSql(ModelDefine modelDefine,int num);
}
