package com.bbs.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.Repository.userRepository;

@Service
public class userServiceImpl implements userService {
	@Autowired
	private userRepository userrepository;
}
