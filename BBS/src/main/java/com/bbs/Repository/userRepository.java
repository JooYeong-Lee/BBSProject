package com.bbs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbs.DB.userDB;

public interface userRepository extends JpaRepository<userDB, String> {

}
