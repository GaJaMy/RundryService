package com.gajamy.rundryservice.member.controller;

import com.gajamy.rundryservice.member.entity.Member;
import com.gajamy.rundryservice.member.param.MemberParam;
import com.gajamy.rundryservice.member.service.MemberService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping("/member/login")
	public String login(Model model){
		return "member/login";
	}

	@RequestMapping("/washing/list")
	public String test() {
		return "washing/list";
	}

	@RequestMapping("/member/kakao_login")
	public String kakaoLogin() {
		StringBuffer loginUrl = new StringBuffer();
		loginUrl.append("https://kauth.kakao.com/oauth/authorize?client_id=");
		loginUrl.append("dff0cc1a6aa3a9880f70376c249edea3");
		loginUrl.append("&redirect_uri=");
		loginUrl.append("http://localhost:8080/kakao_callback");
		loginUrl.append("&response_type=code");

		return "redirect:"+loginUrl.toString();
	}

	@RequestMapping("/kakao_callback")
	public String kakaoCallback(@RequestParam String code, HttpSession session){
		String kakaoToken = memberService.getReturnAccessToken(code);

		MemberParam result = memberService.getuserInfo(kakaoToken);
		memberService.registKakaoMember(result);
		return "washing/list";
	}
}
