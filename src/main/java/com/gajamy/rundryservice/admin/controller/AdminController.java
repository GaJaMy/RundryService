package com.gajamy.rundryservice.admin.controller;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.param.MachineParam;
import com.gajamy.rundryservice.admin.service.machine.MachineService;
import com.gajamy.rundryservice.common.ListResponse;
import com.gajamy.rundryservice.common.SingleResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class AdminController {
	@GetMapping("/admin/machine/list")
	public String list() {
		return "admin/machine/list";
	}

	@GetMapping(value = {"/admin/machine/add","/admin/machine/edit"})
	public String add(Model model, HttpServletRequest request, MachineParam param) {
		boolean editMode = request.getRequestURI().contains("/edit");

		return "admin/machine/add";
	}


}
