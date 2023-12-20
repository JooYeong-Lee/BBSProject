package com.bbs.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bbs.DB.bbsDB;

public interface bbsRepository extends JpaRepository<bbsDB, Long> {
	public List<bbsDB> findById(String id);
	Page<bbsDB> findByCategory(String category, Pageable pageable);
	
	@Query(value = "SELECT v.bbsnum, v.id, v.title, v.category, v.date, v.content, v.filecount, v.fontsize " +
	        "FROM bbs_list AS v " +
	        "WHERE v.title LIKE '%' || :title || '%' " +
	        "ORDER BY v.bbsnum DESC", nativeQuery = true)
	Page<bbsDB> findByTitleContaining(@Param("title") String keyword, Pageable pageable);
}
