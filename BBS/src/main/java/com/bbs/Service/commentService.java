package com.bbs.Service;

import java.util.List;

import com.bbs.DB.commentDB;

public interface commentService {
	public void insertcomment(Long bbsnum, String content, String date, String id);
	public List<commentDB> getByBbsnum(Long bbsnum);
	public void ChangeUserId(String beforeid, String afterid);
}
