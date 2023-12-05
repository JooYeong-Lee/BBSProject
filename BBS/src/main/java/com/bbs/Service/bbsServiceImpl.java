package com.bbs.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bbs.DB.bbsDB;
import com.bbs.Repository.bbsRepository;

@Service
public class bbsServiceImpl implements bbsService {
	@Autowired
	private bbsRepository bbsrepository;
	
	public void insertbbs(String category, String content, String date, String id, String title) {
		bbsDB bbsdb = new bbsDB();
		bbsdb.setCategory(category);
		bbsdb.setContent(content);
		bbsdb.setDate(date);
		bbsdb.setId(id);
		bbsdb.setTitle(title);
		bbsrepository.save(bbsdb);
	}
	
	public Page<bbsDB> getAllBbs(Pageable pageable) {
        return bbsrepository.findAll(pageable);
    }
	
	public bbsDB getByID(Long id) {
	    return bbsrepository.findById(id).orElseThrow();
	}
}
