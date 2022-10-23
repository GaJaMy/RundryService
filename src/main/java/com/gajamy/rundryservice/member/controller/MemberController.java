package com.gajamy.rundryservice.member.controller;

import com.gajamy.rundryservice.member.param.MemberParam;
import com.gajamy.rundryservice.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/member/register")
	public String register() {
		return "member/register";
	}

	@PostMapping("/member/register")
	public String registerSubmit(Model model ,MemberParam param) {
		boolean result = memberService.register(param);
		model.addAttribute("result",result);
		return "member/register_complete";
	}

	@GetMapping("/member/login")
	public String login(Model model){

		return "member/login";
	}

	@PostMapping("/member/login")
	public String loginSubmit(Model model, MemberParam param) {
		boolean result = memberService.login(param);
		model.addAttribute("result",result);
		return "washing/list";
	}
}
