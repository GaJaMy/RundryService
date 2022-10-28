package com.gajamy.rundryservice.admin.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gajamy.rundryservice.admin.service.machine.MachineService;
import com.gajamy.rundryservice.common.SingleResponse;
import com.gajamy.rundryservice.configuration.MemberSecurityConfig;
import com.gajamy.rundryservice.member.service.MemberService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AdminController.class)
@Import(MemberSecurityConfig.class)
class AdminControllerTest {

	@MockBean
	private MachineService machineService;

	@MockBean
	private MemberService memberService;

	@Autowired
	private MockMvc mvc;

	private static final SingleResponse response = new SingleResponse<>();

	@BeforeAll
	public static void init() {
		response.MakeResponse(false,101,
			"Not Found Machine",null);
	}

	@Test
	@WithMockUser(username = "mansa0805@naver.com",roles = "ADMIN")
	void getMachineInfo() throws Exception {
	  	//given
		given(machineService.getMachineInfo(anyString()))
			.willReturn(SingleResponse.builder()
				.result(false)
				.statusCode(101)
				.errorMessage("Not Found Machine")
				.data(null)
				.build());
	  	//when

	  	//then
		mvc.perform(post("/admin/machine/0000"))
			.andDo(print())
			.andExpect(jsonPath("$.result").value(false))
			.andExpect(jsonPath("$.statusCode").value(101))
			.andExpect(jsonPath("$.errorMessage").value("Not Found Machine"))
			.andExpect(jsonPath("$.data").isEmpty())
			.andExpect(status().isOk());
	}

	@Test
	void testGetMachineList() {
	}
}