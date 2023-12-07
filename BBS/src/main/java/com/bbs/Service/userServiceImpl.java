package com.bbs.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.DB.userDB;
import com.bbs.Repository.userRepository;

@Service
public class userServiceImpl implements userService {
	@Autowired
	private userRepository userrepository;
	
	public userDB finduserById(String id) {
		Optional<userDB> userOptional = userrepository.findById(id);
		
		return userOptional.get();
	}
	
	public boolean finduser(String id) {
		return userrepository.findById(id).isEmpty();
	}
	
	public boolean logincheck(String id, String pwd) {
		//user 변수에 id를 넣어서 데이터가 존재하면 true, false 설정
		Optional<userDB> user = userrepository.findById(id);
		
		//user가 true고, userDB에 설정된 pwd값과 입력된 pwd값이 같을때 true 리턴
		//위 조건 만족 못할시 false
		return user.isPresent() && user.get().getPwd().equals(pwd);
	}
	
	public void insertuser(String id, String pwd) {
		userDB userDB = new userDB();
		userDB.setId(id);
		userDB.setPwd(pwd);
		userDB.setIntroduce("한줄 소개를 등록해보세요!");
		userrepository.save(userDB);
	}
	
	public void updateIntro(String id, String intro) {
		Optional<userDB> userOptional = userrepository.findById(id);
		
		if (userOptional.isPresent()) {
		userDB userDB = userOptional.get();
		userDB.setIntroduce(intro);
		
		userrepository.save(userDB);
		}
	}
	
	public void updateId(String beforeid, String afterid) {
		Optional<userDB> userOptional = userrepository.findById(beforeid);
		
		if (userOptional.isPresent()) {
			userDB oldUser = userOptional.get();

	        // 새로운 엔터티를 생성하고 이전 엔터티의 데이터를 복사
	        userDB newUser = new userDB();
	        newUser.setId(afterid);
	        newUser.setPwd(oldUser.getPwd());
	        newUser.setIntroduce(oldUser.getIntroduce());

	        // 새로운 엔터티를 저장
	        userrepository.save(newUser);

	        // 이전 엔터티를 삭제 (선택 사항)
	        userrepository.deleteById(beforeid);
		}
	}	
}
