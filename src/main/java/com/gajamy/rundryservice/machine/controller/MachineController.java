package com.gajamy.rundryservice.machine.controller;

import com.gajamy.rundryservice.admin.dto.MachineDto;
import com.gajamy.rundryservice.admin.service.machine.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MachineController {
	private final MachineService machineService;

	@GetMapping("washing/detail")
	public String detail(Model model ,@RequestParam String machineModel) {
		MachineDto machineDto = machineService.getMachineName(machineModel);
		model.addAttribute("machine",machineDto);
		return "washing/detail";
	}
}
