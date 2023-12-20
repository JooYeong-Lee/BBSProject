package com.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude 추가하면 security를 의존성 주입했을때 기본적으로 불러와지는 security 로그인 화면을 제거할수있음
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoProjectApplication.class, args);
	}

}
