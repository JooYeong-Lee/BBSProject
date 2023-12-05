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
	private Long bbs_num;
	private String id;
	private String title;
	private String category;
	private String date;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	public Long getBbs_num() {
		return bbs_num;
	}
	public void setBbs_num(Long bbs_num) {
		this.bbs_num = bbs_num;
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
}
