package com.bbs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbs.DB.bbsDB;

public interface bbsRepository extends JpaRepository<bbsDB, Long> {

}
