package com.bbs.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.Repository.bbsRepository;

@Service
public class bbsServiceImpl implements bbsService {
	@Autowired
	private bbsRepository bbsrepository;
}
