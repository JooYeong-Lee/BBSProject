package com.bbs.DB;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment_list")
public class commentDB {

	private Long bbs_num;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long comment_num;
	private String id;
	@Column(columnDefinition = "TEXT")
	private String comment;
	
	public Long getBbs_num() {
		return bbs_num;
	}
	public void setBbs_num(Long bbs_num) {
		this.bbs_num = bbs_num;
	}
	public Long getComment_num() {
		return comment_num;
	}
	public void setComment_num(Long comment_num) {
		this.comment_num = comment_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
