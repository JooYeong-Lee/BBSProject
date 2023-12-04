package com.bbs.Service;

import com.bbs.DB.userDB;

public interface userService {
	public boolean finduser(String id);
	public boolean logincheck(String id, String pwd);
	public void insertuser(String id, String pwd);
}
