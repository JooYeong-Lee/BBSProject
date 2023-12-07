package com.bbs.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.DB.commentDB;
import com.bbs.Repository.commentRepository;

@Service
public class commentServiceImpl implements commentService {
	@Autowired
	private commentRepository commentrepository;
	
	public void insertcomment(Long bbsnum, String content, String date, String id) {
		commentDB commentdb = new commentDB();
		
		commentdb.setBbsnum(bbsnum);
		commentdb.setComment(content);
		commentdb.setDate(date);
		commentdb.setId(id);
		
		commentrepository.save(commentdb);
	}
	public List<commentDB> getByBbsnum(Long bbsnum) {
	    return commentrepository.findByBbsnum(bbsnum);
	}
	public void ChangeUserId(String beforeid, String afterid) {
		List<commentDB> commentlist = commentrepository.findById(beforeid);
		
		for(commentDB comment : commentlist) {
			comment.setId(afterid);
		}
		commentrepository.saveAll(commentlist);
	}
}
