package com.bbs.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bbs.DB.bbsDB;
import com.bbs.DB.commentDB;
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
    public String main(Model model, @RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session != null) { // 로그인 상태면
			String userId = (String)session.getAttribute("user");
			model.addAttribute("userId", userId);
			if(userId != null) {
				userDB userdb = userservice.finduserById(userId);
				model.addAttribute("userDB", userdb);
			}
		}
			
		page -= 1;
		Pageable pageable = PageRequest.of(page, 4, Sort.by(Sort.Direction.DESC, "bbsnum"));	
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
	
	@GetMapping("/write")
    public String write(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		String userId;
		if(session != null) { // 로그인 상태면
			userId = (String)session.getAttribute("user"); 
	        return "/bbs/write"; 
		} else
			return "redirect:/main";
    }
	
	@GetMapping("/post")
    public String post(@RequestParam(name = "bbs_num") Long bbsnum, Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session != null) { // 로그인 상태면
			String userId = (String)session.getAttribute("user");
			model.addAttribute("userId", userId);
			if(userId != null) {
				userDB userdb = userservice.finduserById(userId);
				model.addAttribute("userDB", userdb);
			}
		}
		bbsDB bbsdb = bbsservice.getByID(bbsnum);
		List<String> ImgExtension = bbsservice.getImgExtension(bbsdb, bbsdb.getFilecount());
		
		List<commentDB> commentdb = commentservice.getByBbsnum(bbsnum);
		
		model.addAttribute("bbsDB", bbsdb);
		model.addAttribute("ImgExtension", ImgExtension);
		model.addAttribute("commentDB", commentdb);
		
        return "/bbs/post"; 
    }
	
	@GetMapping("/signup")
	public String signup() {
		return "/bbs/signup";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		
		return "redirect:/main";
	}
	
	@PostMapping("/check_id") //아이디 중복체크(ajax)
	@ResponseBody
	public boolean idCheck(@RequestParam("id") String id) {
		return userservice.finduser(id);
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
		if(userservice.logincheck(id, pwd)) {
			session.setAttribute("user", id);
			userDB userdb = userservice.finduserById(id);
			if(userdb.getImg() != null) {
				String profile_img = userservice.convertByteToBase64(userdb.getImg());
				session.setAttribute("userImg", profile_img);
			}
		}

		return "redirect:/main";
	}
	
	@PostMapping("/write_clear")
	public String write_clear(@RequestParam String category,
				              @RequestParam String title,
				              @RequestParam String content,
				              @RequestParam String fontsize,
				              @RequestParam("SelectFile") MultipartFile[] files,
				              HttpServletRequest req) {
		int filename = 0;
		LocalDateTime time = LocalDateTime.now();
		String timestr = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		String userId = (String)req.getSession(false).getAttribute("user");
		
		//게시글 이미지 등록
		for(MultipartFile file : files) {
			if(!file.isEmpty()) {
				filename++;
				bbsservice.SaveImg(userId, title, file, String.valueOf(filename));
			}
		}
		bbsservice.insertbbs(category, content, timestr, userId, title, fontsize, filename);
		
		return "redirect:/main";
	}
	
	@PostMapping("/comment_clear")
    public ResponseEntity<String> comment_clear(@RequestParam Long bbsnum, @RequestParam String comment, @RequestParam String id) {
		LocalDateTime time = LocalDateTime.now();
		String timestr = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
		commentservice.insertcomment(bbsnum, comment, timestr, id);

        return new ResponseEntity<>("댓글 등록 완료", HttpStatus.OK);
    }
	
	@PostMapping("/introInsert")
    public ResponseEntity<String> introInsert(@RequestParam String userid, @RequestParam String intro) {
        userservice.updateIntro(userid, intro);
        return new ResponseEntity<>("한줄 소개 등록 완료", HttpStatus.OK);
    }
	
	@PostMapping("/changeId")
	public ResponseEntity<String> changeId(@RequestParam String beforeid, @RequestParam String afterid, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		userservice.updateId(beforeid, afterid);
		bbsservice.ChangeUserId(beforeid, afterid);
		commentservice.ChangeUserId(beforeid, afterid);
		
        return new ResponseEntity<>("아이디 변경 완료", HttpStatus.OK);
    }
	
	@PostMapping(value = "/UploadImg", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> UploadImg(@RequestParam("uploadedFiles") MultipartFile uploadedFile, HttpServletRequest req,Model model) {
	    if (req.getSession(false) != null) {
	        HttpSession session = req.getSession(false);
	        String userId = (String) session.getAttribute("user");

	        try {
	        	byte[] imageBytes = uploadedFile.getBytes();
	        	userservice.saveImage(userId, imageBytes);

	        	//세션 업데이트
	        	String imgUrl = userservice.convertByteToBase64(imageBytes);
	        	session.setAttribute("userImg", imgUrl);

	        	return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
	        }catch(IOException e) {
	        	return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	    }else {
	            return new ResponseEntity<>("Session not found", HttpStatus.UNAUTHORIZED);
	        }

	}
	
	@GetMapping("/worries")
	public String worries(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, 4);	
        Page<bbsDB> bbsPage = bbsservice.findworries(pageable);
        
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
	
	@GetMapping("/secret")
	public String secret(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, 4);	
        Page<bbsDB> bbsPage = bbsservice.findsecret(pageable);
        
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
	
	@GetMapping("/promotion")
	public String promotion(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, 4);	
        Page<bbsDB> bbsPage = bbsservice.findpromotion(pageable);
        
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
	
	@GetMapping("/search")
	public String search(Model model,
	                     @RequestParam("search") String searchKeyword,
	                     @RequestParam(value = "page", defaultValue = "1") int page) {

	    page -= 1;
	    //String searchRegex = ".*" + searchKeyword + ".*";
	    Pageable pageable = PageRequest.of(page, 4);
	    Page<bbsDB> bbsPage = bbsservice.findByTitle(searchKeyword, pageable);

	    int currentPage = page + 1;
	    int calcEnd = (int)(Math.ceil(currentPage / 10.0) * 10);
	    int startPage = calcEnd - 9;
	    int endPage = Math.min(calcEnd, bbsPage.getTotalPages());

	    model.addAttribute("searchDB", bbsPage);
	    model.addAttribute("startPage", startPage);
	    model.addAttribute("endPage", endPage);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPage", bbsPage.getTotalPages());
	    model.addAttribute("searchKeyword", searchKeyword);

	    return "/bbs/main";
	}
	
}
