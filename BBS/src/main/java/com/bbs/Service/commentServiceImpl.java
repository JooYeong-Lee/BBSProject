package com.bbs.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.Repository.commentRepository;

@Service
public class commentServiceImpl implements commentService {
	@Autowired
	private commentRepository commentrepository;
}
