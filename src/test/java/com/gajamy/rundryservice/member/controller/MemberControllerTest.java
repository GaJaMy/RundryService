package com.gajamy.rundryservice.member.controller;

import com.gajamy.rundryservice.configuration.MemberSecurityConfig;
import com.gajamy.rundryservice.member.service.MemberService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@WebMvcTest(MemberController.class)
@Import(MemberSecurityConfig.class)
class MemberControllerTest {

	@MockBean
	private MemberService memberService;

	@BeforeAll
	public static void init() {

	}

	@Test
	void login() {
	}
}