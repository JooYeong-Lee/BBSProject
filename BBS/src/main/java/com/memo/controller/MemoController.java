package com.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MemoController {
	@GetMapping("/main")
    public String main() {
    	
        return "/memo/main"; 
    }
}
