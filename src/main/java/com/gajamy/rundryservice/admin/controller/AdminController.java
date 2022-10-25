package com.gajamy.rundryservice.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {
	@RequestMapping("/admin/main.do")
	public String main() {
		return "admin/main";
	}
}
