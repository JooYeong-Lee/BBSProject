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

	private Long bbsnum;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentnum;
	private String id;
	@Column(columnDefinition = "TEXT")
	private String comment;
	private String date;
	
	public Long getBbsnum() {
		return bbsnum;
	}
	public void setBbsnum(Long bbsnum) {
		this.bbsnum = bbsnum;
	}
	public Long getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(Long commentnum) {
		this.commentnum = commentnum;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
