package com.gajamy.rundryservice.admin.controller;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.param.MachineParam;
import com.gajamy.rundryservice.admin.service.machine.MachineService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {
	private final MachineService machineService;
	@GetMapping("/admin/machine/list")
	public String list(Model model) {
		List<MachineDto> machineDtoList = machineService.list();
		model.addAttribute("list",machineDtoList);
		return "admin/machine/list";
	}

	@GetMapping(value = {"/admin/machine/add","/admin/machine/edit"})
	public String add(Model model, HttpServletRequest request, MachineParam param) {
		boolean editMode = request.getRequestURI().contains("/edit");

		MachineDto detail = new MachineDto();
		if (editMode) {
			MachineDto machineDto = machineService.getMachine(param);
			if (machineDto == null) {
				model.addAttribute("message","기기 정보가 존재하지 않습니다.");
				return "error/error";
			}
			detail = machineDto;
		}

		model.addAttribute("editMode", editMode);
		model.addAttribute("detail",detail);

		return "admin/machine/add";
	}

	@PostMapping(value = {"/admin/machine/add","/admin/machine/edit"})
	public String addSubmit(Model model,HttpServletRequest request, MachineParam param) {
		boolean editMode = request.getRequestURI().contains("/edit");

		MachineDto detail = new MachineDto();
		if (editMode) {
			MachineDto machineDto = machineService.getMachine(param);
			if (machineDto == null) {
				model.addAttribute("message","기기 정보가 존재하지 않습니다.");
				return "error/error";
			}
			machineService.set(param);
		} else {
			machineService.add(param);
		}

		return "redirect:/admin/machine/list";
	}
}
