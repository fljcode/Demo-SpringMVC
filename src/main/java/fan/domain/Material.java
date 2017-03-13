package fan.domain;

import fan.createxml.BaseDomain;
import fan.createxml.Comment;
import fan.createxml.MulitDomain;
import fan.domain.BaseEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Comment("系统用户")
@Entity(name="MATERIAL")
public class Material {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	// 素材名称
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	//素材名称
	private int type;
	//素材大小
	private int size;
	//广告位ID
	private Long adId;
	//广告位名称
	private String adName;
	//广告位长度
	private int adLength;
	//广告位宽度
	private int adWidth;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//图片URL
	private String picUrl;
	//推广URL
	private String popUrl;
	//备注
	private String comment;
	//素材审核状态 1 待审核 2已审核 3已驳回
	private int checkState;
	
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public int getAdLength() {
		return adLength;
	}
	public void setAdLength(int adLength) {
		this.adLength = adLength;
	}
	public int getAdWidth() {
		return adWidth;
	}
	public void setAdWidth(int adWidth) {
		this.adWidth = adWidth;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getPopUrl() {
		return popUrl;
	}
	public void setPopUrl(String popUrl) {
		this.popUrl = popUrl;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getCheckState() {
		return checkState;
	}
	public void setCheckState(int checkState) {
		this.checkState = checkState;
	}
	
}
