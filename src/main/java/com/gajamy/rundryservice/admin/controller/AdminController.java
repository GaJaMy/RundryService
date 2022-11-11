package com.gajamy.rundryservice.admin.controller;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.param.MachineParam;
import com.gajamy.rundryservice.admin.service.machine.MachineService;
import com.gajamy.rundryservice.admin.service.member.MemberManageService;
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

	@GetMapping("/admin/machine/add")
	public String add(Model model, HttpServletRequest request, MachineParam param) {
		MachineDto detail = new MachineDto();

		model.addAttribute("detail",detail);

		return "admin/machine/add";
	}

	@PostMapping("/admin/machine/add")
	public String addSubmit(Model model,HttpServletRequest request, MachineParam param) {
		if (param.getMachineModel().isEmpty()) {
			model.addAttribute("message","모델 명이 없습니다.");
			return "error/error";
		}
		machineService.add(param);

		return "redirect:/admin/machine/list";
	}

	@GetMapping("/admin/machine/edit")
	public String edit(Model model,MachineParam param) {
		MachineDto machineDto = machineService.getMachine(param);

		if (machineDto == null) {
			model.addAttribute("message","기기 정보가 존재하지 않습니다.");
			return "error/error";
		}

		model.addAttribute("editMode", true);
		model.addAttribute("detail",machineDto);

		return "admin/machine/edit";
	}

	@PostMapping("admin/machine/edit")
	public String editSubmit(Model model, MachineParam param) {
		MachineDto machineDto = MachineDto.builder()
			.machineModel(param.getMachineModel())
			.machineType(param.getMachineType())
			.size(param.getSize())
			.laundryType(param.getLaundryType())
			.build();

		boolean result = machineService.set(machineDto);

		if (!result) {
			model.addAttribute("message","기기 정보가 존재하지 않습니다.");
			return "error/error";
		}

		return "redirect:/admin/machine/list";
	}
}
