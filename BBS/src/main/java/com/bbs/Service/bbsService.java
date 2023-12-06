package com.bbs.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.bbs.DB.bbsDB;
import com.bbs.Repository.bbsRepository;

public interface bbsService {
	public void insertbbs(String category, String content, String date, String id, String title, String fontsize, int filecount);
	public Page<bbsDB> getAllBbs(Pageable pageable);
	public bbsDB getByID(Long id);
	public void ChangeUserId(String beforeid, String afterid);
	public void SaveImg(String id, String title, MultipartFile file, String filename);
	public List<String> getImgExtension(bbsDB bbsdb, int filecount);
	
	public Page<bbsDB> findfree(Pageable pageable);
	public Page<bbsDB> findworries(Pageable pageable);
	public Page<bbsDB> findsecret(Pageable pageable);
	public Page<bbsDB> findpromotion(Pageable pageable);
	public Page<bbsDB> findByTitle(String searchKeyword, Pageable pageable);
	
}
