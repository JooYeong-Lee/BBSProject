package com.bbs.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bbs.DB.bbsDB;
import com.bbs.DB.userDB;
import com.bbs.Service.bbsService;
import com.bbs.Service.commentService;
import com.bbs.Service.userService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BBSController {
	@Autowired
	userService userservice;
	@Autowired
	bbsService bbsservice;
	@Autowired
	commentService commentservice;
	
	@GetMapping("/main")
    public String main(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, 4);	
        Page<bbsDB> bbsPage = bbsservice.getAllBbs(pageable);
        
        int currentPage = page + 1;
		int calcEnd = (int)(Math.ceil(currentPage / 10.0) * 10);
		int startPage = calcEnd - 9;
		int endPage = Math.min(calcEnd, bbsPage.getTotalPages());

        model.addAttribute("bbsDB", bbsPage);
        model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", bbsPage.getTotalPages());
        return "/bbs/main";
	}
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
        return "/bbs/post"; 
    }
	
	@GetMapping("/join")
	public String join() {
		return "bbs/join";
	}
	
	@PostMapping("/check_id") //아이디 중복체크(ajax)
	@ResponseBody
	public boolean idCheck(@RequestParam("id") String id) {
		return userservice.finduser(id);
	}
	
	@GetMapping("/login")
	public String login() {
		return "bbs/login";
	}
	
	@PostMapping("/join_clear")
	public String join_clear(@RequestParam String id,
							 @RequestParam String pwd) {
		if(userservice.finduser(id)) // finduser로 데이터를 찾을수 없으면 insertuser 실행
			userservice.insertuser(id, pwd);
		return "redirect:/main";
	}
	
	@PostMapping("/login_clear")
	public String login_clear(@RequestParam String id,
							  @RequestParam String pwd,
							  HttpSession session) {
		if(userservice.logincheck(id, pwd))
			session.setAttribute("user", id);
		return "redirect:/main";
	}
	
	@PostMapping("/write_clear")
	public String write_clear(@RequestParam String category,
				              @RequestParam String title,
				              @RequestParam String content,
				              @RequestParam("SelectFile") MultipartFile file,
				              HttpServletRequest req) {
		LocalDateTime time = LocalDateTime.now();
		String timestr = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		String userId = (String)req.getSession(false).getAttribute("user");
		
		bbsservice.insertbbs(category, content, timestr, userId, title);
		
		return "redirect:/main";
	}
	
}
