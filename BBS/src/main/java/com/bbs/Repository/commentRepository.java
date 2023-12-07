package com.bbs.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bbs.DB.commentDB;

public interface commentRepository extends JpaRepository<commentDB, Long> {
	//@Query("SELECT c FROM commentDB c WHERE c.bbs_num = :bbsnum")
    public List<commentDB> findByBbsnum(@Param("bbsnum") Long bbsnum);
    public List<commentDB> findById(String id);
}
