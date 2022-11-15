package com.gajamy.rundryservice.admin.controller;

import com.gajamy.rundryservice.member.dto.MemberDto;
import com.gajamy.rundryservice.member.param.MemberParam;
import com.gajamy.rundryservice.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AdminMemberController {
	private final MemberService memberService;
	@GetMapping("/admin/member/list")
	public String memberList(Model model) {
		List<MemberDto> memberDtoList = memberService.getMemberList();

		model.addAttribute("list",memberDtoList);

		return "admin/member/list";
	}

	@PostMapping("/admin/member/authorize")
	public String adminAuthorize(Model model,MemberParam param) {
		boolean result = memberService.authorize(param.getName());

		if (!result) {
			model.addAttribute("message","권한을 줄 수 없는 사용자 입니다.");
			return "error/error";
		}

		return "admin/member/list";
	}
}
