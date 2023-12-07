package com.bbs.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbs.DB.bbsDB;

public interface bbsRepository extends JpaRepository<bbsDB, Long> {
	public List<bbsDB> findById(String id);
}
