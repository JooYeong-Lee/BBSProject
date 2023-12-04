package com.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class BBSController {
	@GetMapping("/main")
    public String main(Model model) {
        return "bbs/main"; 
    }
	@GetMapping("/write")
    public String write(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		String userId;
		if(session != null) { // 로그인이 상태면
			userId = (String)session.getAttribute("user"); 
		} else { // 로그인 상태가 아니면
			userId = "Unknown User";
		}
			
        return "bbs/write"; 
    }
	@GetMapping("/post")
    public String post() {
        return "bbs/post"; 
    }
	
	@PostMapping("/write_clear")
	public String write_clear(@RequestParam String category,
				              @RequestParam String title,
				              @RequestParam String content,
				              @RequestParam("SelectFile") MultipartFile file,
				              HttpServletRequest req) {
		//bbsDB에 id, title, category, date, content 들어가야함
		HttpSession session = req.getSession(false);
		String userId;
		if(session != null) { // 로그인이 상태면
			userId = (String)session.getAttribute("user"); 
		} else { // 로그인 상태가 아니면
			userId = "Unknown User";
		}
		
		return "redirect:/main";
	}
	
}
