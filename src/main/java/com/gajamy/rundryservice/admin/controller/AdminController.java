package com.gajamy.rundryservice.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
	@GetMapping("admin/login")
	public String login(){
		return "admin/login";
	}

	@PostMapping("admin/login")
	public String loginSubmit() {
		return "admin/main";
	}
}
