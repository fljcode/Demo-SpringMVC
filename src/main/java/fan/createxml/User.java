package fan.createxml;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import fan.createxml.Comment;

@Comment("系统用户")
@Entity(name="tb_user")
public class User extends MulitDomain {
	
	@Transient
	private static long sid = 0l;

	
	@Comment("用户中文名称")
	@Column(name="nameStr", length=32)
	private String name;
	
	@Comment("用户密码")
	private String password;
	
	@Column(length=8, scale=4, precision=16, nullable=false)
	private Float price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
}
