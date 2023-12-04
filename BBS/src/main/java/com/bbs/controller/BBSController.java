package com.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class BBSController {
	@GetMapping("/main")
    public String main() {
        return "bbs/main"; 
    }
	@GetMapping("/write")
    public String write() {
        return "bbs/write"; 
    }
	@GetMapping("/post")
    public String post() {
        return "bbs/post"; 
    }
}
