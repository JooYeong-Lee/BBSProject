package com.bbs.DB;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bbs_list")
public class bbsDB {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bbsnum;
	private String id;
	private String title;
	private String category;
	private String date;
	@Column(columnDefinition = "TEXT")
	private String content;
	private String fontsize;
	
	public Long getBbsnum() {
		return bbsnum;
	}
	public void setBbsnum(Long bbsnum) {
		this.bbsnum = bbsnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFontsize() {
		return fontsize;
	}
	public void setFontsize(String fontsize) {
		this.fontsize = fontsize;
	}

}
