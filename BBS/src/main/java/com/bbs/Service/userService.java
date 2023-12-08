package com.bbs.Service;

import java.io.IOException;

import com.bbs.DB.userDB;

public interface userService {
	public userDB finduserById(String id);
	public boolean finduser(String id);
	public boolean logincheck(String id, String pwd);
	public void insertuser(String id, String pwd);
	public void updateIntro(String id, String intro);
	public void updateId(String beforeid, String afterid);
	public void saveImage(String userId, byte[] imageBytes) throws IOException;
	public String convertByteToBase64(byte[] imageData);
}
