package com.bbs.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bbs.DB.bbsDB;

public interface bbsService {
	public void insertbbs(String category, String content, String date, String id, String title);
	public Page<bbsDB> getAllBbs(Pageable pageable);
	public bbsDB getByID(Long id);
}
