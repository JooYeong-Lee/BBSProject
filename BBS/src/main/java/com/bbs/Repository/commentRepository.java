package com.bbs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbs.DB.commentDB;

public interface commentRepository extends JpaRepository<commentDB, Long> {

}
