package com.bbs.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.DB.userDB;
import com.bbs.Repository.userRepository;

@Service
public class userServiceImpl implements userService {
	@Autowired
	private userRepository userrepository;
	
	public boolean finduser(String id) {
		return userrepository.findById(id).isEmpty();
	}
	
	public void insertuser(String id, String pwd) {
		userDB userDB = new userDB();
		userDB.setId(id);
		userDB.setPwd(pwd);
		userrepository.save(userDB);
	}
}
